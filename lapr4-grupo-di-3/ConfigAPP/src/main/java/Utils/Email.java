/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hugov
 */
public class Email {
    
    /**
     *
     * based on:
     *
     * https://stackoverflow.com/questions/46663/
     * how-can-i-send-an-email-by-java-application-using-gmail-yahoo-or-
     * hotmail?fbclid=IwAR3BwHueYuEQ913Qo9v7Y_
     * _YdEiKLdx-YGYpEjmHpmULkB2b3uKDKXAiFN4
     *
     * @param from
     * @param pass
     * @param to
     * @param subject
     * @param body
     * @return
     */
    public static boolean sendFromGMail(String from, String pass, String to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        boolean b1 = true;

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress();

            toAddress = new InternetAddress(to);

            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
            b1 = false;
        } catch (MessagingException me) {
            me.printStackTrace();
            b1 = false;
        }
        return b1;

    }
}
