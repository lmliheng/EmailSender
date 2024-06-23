package org.asset.Utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.util.Properties;
import org.asset.Config.EmailConfig;

public class EmailSender {
    private final String to;
    private final String from;
    private final String host;
    private final String username;
    private final String password;

    private final String alias ;
    private final Properties properties;



    public EmailSender(EmailConfig emailConfig) {
        this.to = emailConfig.getTo();
        this.from = emailConfig.getFrom();
        this.host = emailConfig.getHost();
        this.username = emailConfig.getUsername();
        this.password = emailConfig.getPassword();
        this.properties = emailConfig.getProperties();
        this.alias = emailConfig.getAlias();

    }

    public void sendEmail(String subject, String resourcePath) throws MessagingException, IOException {
        // 从类路径中读取HTML文件内容
        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        StringBuilder htmlContent = new StringBuilder();
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    htmlContent.append(line).append("\n");
                }
            }
        }



        // 创建一个默认的MimeMessage对象
        MimeMessage message = new MimeMessage(Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        }));

        // 设置发件人和收件人
       // message.setFrom(new InternetAddress(from));

        InternetAddress internetAddress = new InternetAddress(from);
        System.out.println("加载form中...");

        internetAddress.setPersonal(alias, "UTF-8"); // 设置别名，指定字符集为UTF-8
        System.out.println("加载alias中...");

        message.setFrom(internetAddress);

        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

        // 设置邮件主题
        message.setSubject(subject);
        System.out.println("加载subject中...");

        // 设置邮件正文为HTML内容
        message.setContent(htmlContent.toString(), "text/html; charset=UTF-8");
        System.out.println("加载正文中...");
        // 发送消息
        Transport.send(message);

        System.out.println("邮件发送结束...");
    }
}