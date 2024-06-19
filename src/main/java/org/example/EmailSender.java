package org.example;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;  // 形成邮箱发送框架

public class EmailSender {

    public static void main(String[] args) {  // 邮箱发送端配置，可以解耦

        String to = "liheng221994@qq.com"; // 收件人邮箱地址
        String from = "liheng2137@qq.com"; // 发件人邮箱地址
        String host = "smtp.qq.com"; // SMTP服务器地址
        String username = "liheng2137@qq.com"; // 发件人邮箱用户名（完整的邮箱地址）
        String password = "rxpckvnuzaracfjh"; // 发件人邮箱授权码（不是邮箱密码）

        // 设置邮件服务器属性
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465"); // 使用SSL时的端口
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465"); // SSL端口
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); // SSL工厂类

        // 获取默认的Session对象
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 创建一个默认的MimeMessage对象
            MimeMessage message = new MimeMessage(session);

            // 设置发件人和收件人
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // 设置邮件主题
            message.setSubject("JavaMail API 测试");

            // 设置邮件正文
            message.setText("这是一封由JavaMail API发送的邮件。");

            // 发送消息
            Transport.send(message);
            System.out.println("邮件已成功发送！");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}