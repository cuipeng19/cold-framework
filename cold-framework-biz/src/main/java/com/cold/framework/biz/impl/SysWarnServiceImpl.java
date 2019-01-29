package com.cold.framework.biz.impl;

import com.cold.framework.biz.AbstractDbBaseService;
import com.cold.framework.biz.SysWarnService;
import com.cold.framework.dao.mapper.SysWarnMapper;
import com.cold.framework.dao.model.SysWarn;
import com.cold.framework.dao.util.ColdMapper;
import com.cold.framework.notify.email.EmailSender;
import com.cold.framework.notify.monitor.WarnMsg;
import com.cold.framework.notify.monitor.WarnMsgHandler;
import com.cold.framework.notify.sms.SmsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cuipeng
 * @date 2019/1/10 20:48
 */
@Service
public class SysWarnServiceImpl extends AbstractDbBaseService<SysWarn,Long> implements SysWarnService {

    public SysWarnServiceImpl(ColdMapper<SysWarn> mapper) {
        super(mapper);
    }

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private SysWarnMapper sysWarnMapper;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private WarnMsgHandler warnMsgHandler;
    @Autowired
    private SmsFactory smsFactory;

    @Override
    public void sendEmail(WarnMsg warnMsg) {
        List<SysWarn> sysWarns = sysWarnMapper.getEmail().stream().filter(e -> {
            boolean result = false;
            for(String eventType : e.getEventType().split(",")) {
                if(eventType.equals(warnMsg.getEventType())) {
                    result = true;
                }
            }
            return result;
        }).collect(Collectors.toList());

        // send email
        sysWarns.parallelStream().forEach(e -> emailSender.sendSimpleEmail(e.getEmail(),warnMsgHandler.buildEmailTitle(warnMsg),warnMsgHandler.buildEmailContent(warnMsg)));

        // log
        if(sysWarns.size()>0) {
            logger.info("Send email with warn message successful：" + warnMsg.getExceptionSystem() + "(" + warnMsg.getExceptionName() + "),send to："
                    + sysWarns.stream().map(e -> e.getUserName()).collect(Collectors.joining(",")));
        } else {
            logger.info("The email of monitor：" + warnMsg.getExceptionSystem() + "(" + warnMsg.getExceptionName() + "),not matched to related person.");
        }
    }

    @Override
    public void sendSms(WarnMsg warnMsg) {
        List<SysWarn> sysWarns = sysWarnMapper.getPhone().stream().filter(e -> {
            boolean result = false;
            for(String eventType : e.getEventType().split(",")) {
                if(eventType.equals(warnMsg.getEventType())) {
                    result = true;
                }
            }
            return result;
        }).collect(Collectors.toList());

        // send sms
        sysWarns.parallelStream().forEach(e -> smsFactory.getSmsSender().sendCustomSms(e.getPhone(),warnMsgHandler.buildSmsContent(warnMsg)));

        // log
        if(sysWarns.size()>0) {
            logger.info("Send sms with warn message successful：" + warnMsg.getExceptionSystem() + "(" + warnMsg.getExceptionName() + "),send to："
                    + sysWarns.stream().map(e -> e.getUserName()).collect(Collectors.joining(",")));
        } else {
            logger.info("The sms of monitor：" + warnMsg.getExceptionSystem() + "(" + warnMsg.getExceptionName() + "),not matched to related person.");
        }
    }
}
