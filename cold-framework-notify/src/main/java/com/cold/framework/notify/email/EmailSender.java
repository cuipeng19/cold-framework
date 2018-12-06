package com.cold.framework.notify.email;

import com.cold.framework.common.dictionary.ColdState;
import com.cold.framework.common.exception.ColdException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * It gives you access to the template which can then be userd to send email methods.
 *
 * <p>
 *     This class needs a JavaMailSender.
 * </p>
 *
 * @author cuipeng
 * @since 2018/12/6 9:57
 */
@Component
public class EmailSender {

    @Value("${spring.mail.username}")
    private String emailFrom;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendSimpleEmail(String emailTo, String title, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(emailTo);
        message.setSubject(title);
        message.setText(content);
        javaMailSender.send(message);
    }

    public void sendExtraEmail(String emailTo, String title, String content, Map<String,MultipartFile> extra) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(emailFrom);
            helper.setTo(emailTo);
            helper.setSubject(title);
            helper.setText(content);
            extra.forEach((attachmentFilename, file) -> {
                try {
                    helper.addAttachment(attachmentFilename, file);
                } catch (MessagingException e) {
                    logger.error("Failed to add extra.", e);
                }
            });
        } catch (MessagingException e) {
            throw new ColdException(ColdState.EMAIL_SEND_FAIL, e);
        }
        javaMailSender.send(mimeMessage);
    }
}
