package com.xmut.osm.security.util;

import lombok.Data;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author 阮胜
 * @date 2018/8/3 15:56
 */
@Component
public class EmailUtil {
    private static final String MAIN_FROM = "cearnach@yeah.net";
    private static final String VCODE_TEXT_PROPERTY_PATH = "/static/properties/email-text.txt";
    private static final String SUBJECT = "来自 Shopping Mall 电商平台的邮箱验证码";
    private static String V_CODE_TEXT = null;

    static {
        try {
            InputStream in = new ClassPathResource(VCODE_TEXT_PROPERTY_PATH).getInputStream();
            InputStreamReader isr = new InputStreamReader(in, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
            V_CODE_TEXT = sb.toString();
            if (V_CODE_TEXT.length() == 0) {
                throw new RuntimeException("初始化邮箱内容失败!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private EmailUtil() {

    }

    private final static class EmailUtilInstance {
        private static EmailUtil getInstance() {
            return new EmailUtil();
        }
    }

    public static EmailUtil getInstance() {
        return EmailUtilInstance.getInstance();
    }

    public void send(JavaMailSender mailSender, String mailTo, String subject, String text) throws MessagingException {
        EmailBody emailBody = new EmailBody(mailTo, subject, text);
        send(mailSender, emailBody);
    }

    public void sendVCode(JavaMailSender mailSender, String mailTo, String subject, String vcode, String account) throws MessagingException {
        EmailBody emailBody = new EmailBody(mailTo, subject, generateVCodeText(vcode, account));
        send(mailSender, emailBody);
    }

    public void sendVCode(JavaMailSender mailSender, String mailTo, String vcode, String account) throws MessagingException {
        sendVCode(mailSender, mailTo, SUBJECT, vcode, account);
    }

    public void send(JavaMailSender mailSender, EmailBody emailBody) throws MessagingException {
        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom(MAIN_FROM);
        message.setTo(emailBody.getMailTo());
        message.setSubject(emailBody.getSubject());
        message.setText(emailBody.getText());
        mailSender.send(mimeMessage);
    }

    @Data
    private class EmailBody {

        private String mailFrom = "cearnach@yeah.net";
        private String mailTo;
        private String subject;
        private String text;

        EmailBody(String mailTo, String subject, String text) {
            this.mailTo = mailTo;
            this.subject = subject;
            this.text = text;
        }
    }

    public String generateVCodeText(String vcode, String account) {
        return V_CODE_TEXT.replace("${Verification-Code}", vcode)
                .replace("${Verification-Account}", account);
    }
}
