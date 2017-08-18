package com.pbs.ams.common.util;

//import com.pbs.ams.common.constant.UpmsConstant;
//import com.pbs.ams.web.model.AmsProductUser;
//import com.pbs.ams.web.model.AmsTradeAccount;
//import com.pbs.ams.web.model.UpmsCompanyUser;
//import com.pbs.ams.web.service.impl.AmsProductUserServiceImpl;
//import com.pbs.ams.web.service.impl.AmsTradeAccountServiceImpl;
//import com.pbs.ams.web.service.impl.UpmsCompanyUserServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * 检查工具类
 * User : zpf
 * Date : 2017/7/18
 */
public class CheckUtil {

//    private static final AmsTradeAccountServiceImpl amsTradeAccountService = SpringContextUtil.getBean("amsTradeAccountServiceImpl", AmsTradeAccountServiceImpl.class);
//    private static final UpmsCompanyUserServiceImpl upmsCompanyUserService = SpringContextUtil.getBean("upmsCompanyUserServiceImpl", UpmsCompanyUserServiceImpl.class);
//    private static final AmsProductUserServiceImpl amsProductUserService = SpringContextUtil.getBean("amsProductUserServiceImpl", AmsProductUserServiceImpl.class);
//
//    /**
//     * according to params estimate Object can be deleted.
//     * @param id
//     * @return
//     */
//    public static boolean canDelete(String key, Long id){
//        boolean result = true;
//        if (null != id) {
//            switch (key) {
//                case UpmsConstant.COMPANY : result = checkCompany(result, id); break;
//                case UpmsConstant.PRODUCT : result = checkProduct(result, id); break;
//                case UpmsConstant.ACCOUNT : result = checkAccount(result, id); break;
//            }
//        }
//        return result;
//    }
//
//    /**
//     * check company
//     * @param result
//     * @param id
//     * @return
//     */
//    private static boolean checkCompany(boolean result, Long id) {
//        List<Long> ids = new ArrayList<Long>();
//        ids.add(id);
//        List<UpmsCompanyUser> upmsCompanyUsers = upmsCompanyUserService.getUsersByCompanyId(ids);
//        if (upmsCompanyUsers != null && upmsCompanyUsers.size() > 0) {
//            result = false;
//        }
//        return result;
//    }
//    /**
//     * check product
//     * @param result
//     * @param id
//     * @return
//     */
//    private static boolean checkProduct(boolean result, Long id) {
//        AmsProductUser amsProductUser = new AmsProductUser();
//        amsProductUser.setProductId(id);
//        List<AmsProductUser> amsProductUsers = amsProductUserService.select(amsProductUser);
//        if (amsProductUsers != null && amsProductUsers.size() > 0) {
//            result = false;
//        }
//        return result;
//    }
//    /**
//     * check account
//     * @param result
//     * @param id
//     * @return
//     */
//    private static boolean checkAccount(boolean result, Long id) {
//        AmsTradeAccount amsTradeAccount = amsTradeAccountService.selectByPrimaryKey(id);
//        if (amsTradeAccount.getCompanyId() != null) {
//            result = false;
//        }
//        return result;
//    }
}
