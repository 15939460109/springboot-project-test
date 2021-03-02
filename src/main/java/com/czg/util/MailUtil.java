package com.czg.util;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtil {

    // 从qq邮箱 发送邮件 到163邮箱
    public static void send() throws Exception {
        // 1.通过配置构成邮件的会话
        Properties prop = new Properties();
        // 配置协议和服务器地址
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.setProperty("mail.smtp.host", "smtp.qq.com");
        prop.setProperty("mail.smtp.auth", "true");

        String port = "465";
        prop.setProperty("mail.smtp.port", port);
        prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.setProperty("mail.smtp.socketFactory.fallback", "false");
        prop.setProperty("mail.smtp.socketFactory.port", port);

        // 2.创建会话
        Session session = Session.getInstance(prop);

        // 3.创建一封邮件
        MimeMessage message = new MimeMessage(session);
        String sendMail = "2357367330@qq.com";
        String recipients = "15939460109@163.com";
        message.setFrom(new InternetAddress(sendMail, "czgs", "UTF-8"));
        // MimeMessage.RecipientType.CC 抄送         MimeMessage.RecipientType.BCC 密送
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(recipients, "czgr", "UTF-8"));
        // 标题
        message.setSubject("一个问候", "UTF-8");
        // 正文
        message.setContent("hello 哈喽", "text/html;charset=UTF-8");
        // 发送时间
        message.setSentDate(new Date());

        // 可以保存为*.eml的文件格式
        message.saveChanges();

        // 4.获取邮件传输对象
        Transport transport = session.getTransport();
        // 账户
        String account = "2357367330@qq.com";
        // 授权码
        String password = "xzfuxdbxdplpeafi";

        // 建立连接
        transport.connect(account, password);
        // 发送
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }

    public static void main(String[] args) {
        try {
            send();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
