package org.aeeset.Utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;
import org.aeeset.Config.EmailConfig;

public class EmailSender {
    private String to;
    private String from;
    private String host;
    private String username;
    private String password;
    private Properties properties;

    public EmailSender(EmailConfig emailConfig) {
        this.to = emailConfig.getTo();
        this.from = emailConfig.getFrom();
        this.host = emailConfig.getHost();
        this.username = emailConfig.getUsername();
        this.password = emailConfig.getPassword();
        this.properties = emailConfig.getProperties();
    }

    public void sendEmail(String subject, String resourcePath) throws MessagingException, IOException {
        // 从类路径中读取HTML文件内容
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        StringBuilder htmlContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                htmlContent.append(line).append("\n");
            }
        }

        // 创建一个默认的MimeMessage对象
        MimeMessage message = new MimeMessage(Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        }));

        // 设置发件人和收件人
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // 设置邮件主题
        message.setSubject(subject);

        // 设置邮件正文为HTML内容
        message.setContent(htmlContent.toString(), "text/html; charset=UTF-8");

        // 发送消息
        Transport.send(message);
        System.out.println("邮件已成功发送");
    }
}