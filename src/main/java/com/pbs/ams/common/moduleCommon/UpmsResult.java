package com.pbs.ams.common.moduleCommon;

import com.pbs.ams.common.base.BaseResult;

/**
 * Created by lx on 2017/8/15.
 */
public class UpmsResult extends BaseResult {
    public UpmsResult(UpmsResultConstant upmsResultConstant, Object data) {
        super(upmsResultConstant.getCode(), upmsResultConstant.getMessage(), data);
    }

}
