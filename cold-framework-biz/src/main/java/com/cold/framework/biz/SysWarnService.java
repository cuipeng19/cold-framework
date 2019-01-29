package com.cold.framework.biz;

import com.cold.framework.dao.model.SysWarn;
import com.cold.framework.notify.monitor.WarnMsg;

/**
 * @author cuipeng
 * @date 2019/1/10 20:47
 */
public interface SysWarnService extends BaseService<SysWarn,Long> {

    /**
     * send email
     *
     * @param warnMsg warn data
     */
    void sendEmail(WarnMsg warnMsg);

    /**
     * send sms
     *
     * @param warnMsg warn data
     */
    void sendSms(WarnMsg warnMsg);
}
