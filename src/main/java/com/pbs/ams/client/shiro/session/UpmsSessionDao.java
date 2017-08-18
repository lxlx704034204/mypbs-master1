package com.pbs.ams.client.shiro.session;

import com.pbs.ams.client.util.SerializableUtil;
import com.pbs.ams.common.constant.UpmsConstant;
import com.pbs.ams.common.constant.UpmsEnum;
import com.pbs.ams.common.util.RedisUtil;
import org.apache.commons.lang.ObjectUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.Serializable;
import java.util.*;

/**
 * 基于redis的sessionDao，缓存共享session
 * Created by ams on 2017/2/23.
 */


public class UpmsSessionDao extends CachingSessionDAO {

    private static Logger _log = LoggerFactory.getLogger(UpmsSessionDao.class);

//    指示：
//    AMS_UPMS_SHIRO_SESSION_ID ("pbs-ams-management-shiro-session-id"),      // 会话key                       for:UpmsSessionDao
//    AMS_UPMS_SERVER_SESSION_ID ("pbs-ams-management-server-session-id"),    // 全局会话key                   for:UpmsSessionDao/pbs-ams-management-client.properties
//    AMS_UPMS_SERVER_SESSION_IDS ("pbs-ams-management-server-session-ids"),  // 全局会话列表key               for:UpmsSessionDao
//    AMS_UPMS_SERVER_CODE ("pbs-ams-management-server-code"),                // code key                     for:UpmsSessionDao
//    AMS_UPMS_CLIENT_SESSION_ID("pbs-ams-management-client-session-id"),     // 局部会话key                  for:UpmsSessionDao/UpmsAuthenticationFilter
//    AMS_UPMS_CLIENT_SESSION_IDS ("pbs-ams-management-client-session-ids"),  // 单点同一个code所有局部会话key for:UpmsSessionDao/UpmsAuthenticationFilter


    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        RedisUtil.set(UpmsEnum.AMS_UPMS_SHIRO_SESSION_ID.getString() + "_" + sessionId, SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        _log.debug("doCreate >>>>> sessionId={}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        String session = RedisUtil.get(UpmsEnum.AMS_UPMS_SHIRO_SESSION_ID.getString() + "_" + sessionId);
        _log.debug("doReadSession >>>>> sessionId={}", sessionId);
        return SerializableUtil.deserialize(session);
    }

    @Override
    protected void doUpdate(Session session) {
        // 如果会话过期/停止 没必要再更新了
        if(session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            return;
        }
        // 更新session的最后一次访问时间
        UpmsSession upmsSession = (UpmsSession) session;
        UpmsSession cacheUpmsSession = (UpmsSession) doReadSession(session.getId());
        if (null != cacheUpmsSession) {
            upmsSession.setStatus(cacheUpmsSession.getStatus());
            upmsSession.setAttribute(UpmsConstant.FORCE_LOGOUT, cacheUpmsSession.getAttribute(UpmsConstant.FORCE_LOGOUT));
        }
        RedisUtil.set(UpmsEnum.AMS_UPMS_SHIRO_SESSION_ID.getString() + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
        // 更新ams_UPMS_SERVER_SESSION_ID、ams_UPMS_SERVER_CODE过期时间
        _log.debug("doUpdate >>>>> sessionId={}", session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        String sessionId = session.getId().toString();
        String upmsType = ObjectUtils.toString(session.getAttribute(UpmsConstant.UPMS_TYPE));
        if ("client".equals(upmsType)) {
            // 删除局部会话和同一code注册的局部会话
            String code = RedisUtil.get(UpmsEnum.AMS_UPMS_CLIENT_SESSION_ID.getString() + "_" + sessionId);
            Jedis jedis = RedisUtil.getJedis();
            jedis.del(UpmsEnum.AMS_UPMS_CLIENT_SESSION_ID.getString() + "_" + sessionId);
            jedis.srem(UpmsEnum.AMS_UPMS_CLIENT_SESSION_IDS.getString() + "_" + code, sessionId);
            jedis.close();
        }
        if ("server".equals(upmsType)) {
            // 当前全局会话code
            String code = RedisUtil.get(UpmsEnum.AMS_UPMS_SERVER_SESSION_ID.getString() + "_" + sessionId);
            // 清除全局会话
            RedisUtil.remove(UpmsEnum.AMS_UPMS_SERVER_SESSION_ID.getString() + "_" + sessionId);
            // 清除code校验值
            RedisUtil.remove(UpmsEnum.AMS_UPMS_SERVER_CODE.getString() + "_" + code);
            // 清除所有局部会话
            Jedis jedis = RedisUtil.getJedis();
            Set<String> clientSessionIds = jedis.smembers(UpmsEnum.AMS_UPMS_CLIENT_SESSION_IDS.getString() + "_" + code);
            for (String clientSessionId : clientSessionIds) {
                jedis.del(UpmsEnum.AMS_UPMS_CLIENT_SESSION_ID.getString() + "_" + clientSessionId);
                jedis.srem(UpmsEnum.AMS_UPMS_CLIENT_SESSION_IDS.getString() + "_" + code, clientSessionId);
            }
            _log.debug("当前code={}，对应的注册系统个数：{}个", code, jedis.scard(UpmsEnum.AMS_UPMS_CLIENT_SESSION_IDS.getString() + "_" + code));
            jedis.close();
            // 维护会话id列表，提供会话分页管理
            RedisUtil.lrem(UpmsEnum.AMS_UPMS_SERVER_SESSION_IDS.getString(), 1, sessionId);
        }
        // 删除session
        RedisUtil.remove(UpmsEnum.AMS_UPMS_SHIRO_SESSION_ID.getString() + "_" + sessionId);
        _log.debug("doUpdate >>>>> sessionId={}", sessionId);
    }

    /**
     * 获取会话列表
     * @param offset
     * @param limit
     * @return
     */
    public Map getActiveSessions(int offset, int limit) {
        Map sessions = new HashMap();
        Jedis jedis = RedisUtil.getJedis();
        // 获取在线会话总数
        long total = jedis.llen(UpmsEnum.AMS_UPMS_SERVER_SESSION_IDS.getString());
        // 获取当前页会话详情
        List<String> ids = jedis.lrange(UpmsEnum.AMS_UPMS_SERVER_SESSION_IDS.getString(), offset, (offset + limit - 1));
        List<Session> rows = new ArrayList<>();
        for (String id : ids) {
            String session = RedisUtil.get(UpmsEnum.AMS_UPMS_SHIRO_SESSION_ID.getString() + "_" + id);
            // 过滤redis过期session
            if (null == session) {
                RedisUtil.lrem(UpmsEnum.AMS_UPMS_SERVER_SESSION_IDS.getString(), 1, id);
                total = total - 1;
                continue;
            }
             rows.add(SerializableUtil.deserialize(session));
        }
        jedis.close();
        sessions.put("total", total);
        sessions.put("rows", rows);
        return sessions;
    }

    /**
     * 强制退出
     * @param ids
     * @return
     */
    public int forceout(String ids) {
        String[] sessionIds = ids.split(",");
        for (String sessionId : sessionIds) {
            // 会话增加强制退出属性标识，当此会话访问系统时，判断有该标识，则退出登录
            String session = RedisUtil.get(UpmsEnum.AMS_UPMS_SHIRO_SESSION_ID.getString() + "_" + sessionId);
            UpmsSession upmsSession = (UpmsSession) SerializableUtil.deserialize(session);
            upmsSession.setStatus(UpmsSession.OnlineStatus.force_logout);
            upmsSession.setAttribute(UpmsConstant.FORCE_LOGOUT, UpmsConstant.FORCE_LOGOUT);
            RedisUtil.set(UpmsEnum.AMS_UPMS_SHIRO_SESSION_ID.getString() + "_" + sessionId, SerializableUtil.serialize(upmsSession), (int) upmsSession.getTimeout() / 1000);
        }
        return sessionIds.length;
    }

    /**
     * 更改在线状态
     *
     * @param sessionId
     * @param onlineStatus
     */
    public void updateStatus(Serializable sessionId, UpmsSession.OnlineStatus onlineStatus) {
        UpmsSession session = (UpmsSession) doReadSession(sessionId);
        if (null == session) {
            return;
        }
        session.setStatus(onlineStatus);
        RedisUtil.set(UpmsEnum.AMS_UPMS_SHIRO_SESSION_ID.getString() + "_" + session.getId(), SerializableUtil.serialize(session), (int) session.getTimeout() / 1000);
    }

}
