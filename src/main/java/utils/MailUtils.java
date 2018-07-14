package utils;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.Properties;


public class MailUtils {
    private static final Logger LOGGER = Logger.getLogger(MailUtils.class);
    public static void sendMail(String email,String message){
        //1.创建服务器会话对象
        Properties pt = new Properties();
        pt.setProperty("mail.transport.protocol","SMTP");
        //设置发送邮件服务器
        pt.setProperty("mail.host","localhost");
        pt.setProperty("mail.smtp.auth","true");
        //创建验证器
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("service","root");
            }
        };
        Session session = Session.getInstance(pt,auth);
        //2.创建message相当于邮件内容
        Message msg = new MimeMessage(session);
        try {
            //设置发送者
            msg.setFrom(new InternetAddress("service@store.com"));
            //设置发送者与接收者
            msg.setRecipient(RecipientType.TO,new InternetAddress(email));
            //设置邮件主题
            msg.setSubject("激活邮件");
            //设置邮件内容
            msg.setContent(message,"text/html,charset=utf-8");
            //3.创建transport发送邮件
            Transport.send(msg);
        } catch (MessagingException e) {
            LOGGER.error("send message error"+e.getMessage(),e);
        }

    }
}
