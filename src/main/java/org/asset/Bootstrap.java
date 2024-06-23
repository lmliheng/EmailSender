package org.asset;

import org.asset.Config.EmailConfig;
import org.asset.Utils.EmailSender;
import javax.mail.MessagingException;
import java.io.IOException;

public class Bootstrap {

    public static void main(String[] args) {
        EmailConfig emailConfig = new EmailConfig();

        EmailSender emailSender = new EmailSender(emailConfig);

        String htmlFilePath = "/Data/hello.html"; // 替换为您的HTML文件路径
        String subject = "文档导航注册邮箱-请接收";

        try {
            emailSender.sendEmail(subject, htmlFilePath);
            System.out.println("完成邮箱发送函数的加载！");
        } catch (MessagingException | IOException e) {
            e.fillInStackTrace();
        }
    }
}