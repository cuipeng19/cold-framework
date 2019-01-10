package com.cold.framework.notify.monitor;

import com.cold.framework.common.util.ExceptionUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Handle data of warn.
 *
 * @author cuipeng
 * @date 2019/1/10 18:32
 */
@Service
public class WarnMsgHandler {

    /**
     * Build data of warn.
     *
     * @param eventType event type
     * @param e exception
     * @param path project path
     * @return
     */
    public WarnMsg buildWarnMsg(String eventType, Exception e, String path) {
        WarnMsg warnMsg = new WarnMsg();
        warnMsg.setEventType(eventType);
        warnMsg.setExceptionName(e.toString());
        String[] names = path.split("/");
        warnMsg.setExceptionSystem(names[names.length-3]);
        warnMsg.setWarnTime(new Date());
        warnMsg.setExceptionStack(ExceptionUtil.getStackTrace(e));
        return warnMsg;
    }

    /**
     * build email title.
     *
     * @param warnMsg warn data
     * @return
     */
    public String buildTitle(WarnMsg warnMsg) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("monitor：");
        buffer.append(warnMsg.getExceptionSystem());
        buffer.append("(");
        buffer.append(warnMsg.getExceptionName());
        buffer.append(")");
        return buffer.toString();
    }

    /**
     * build email content
     *
     * @param warnMsg warn data
     * @return
     */
    public String buildContent(WarnMsg warnMsg) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Project name：" + warnMsg.getExceptionSystem() + "\r\n");
        buffer.append("Occur time：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(warnMsg.getWarnTime()) + "\r\n");
        buffer.append("stack information：" + warnMsg.getExceptionStack());
        return buffer.toString();
    }
}
