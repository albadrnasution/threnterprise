/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author soleman
 */
public class EmailHandler {

    public void postMail(String recipients, String subject, String message, String from) throws MessagingException, UnsupportedEncodingException {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from, "THR Administrator"));
        msg.addRecipient(Message.RecipientType.TO,
                new InternetAddress(recipients, "Mr./Ms."));
        msg.setSubject(subject);
        msg.setText(message);
        Transport.send(msg);
    }

    public String generatingPassword() {
        Random randEngine = new Random();
        int pass = randEngine.nextInt();
        return Integer.toHexString(pass);
    }

    public String getStringMD5(String password) throws NoSuchAlgorithmException {
        MessageDigest md5ku = MessageDigest.getInstance("MD5");
        md5ku.reset();
        byte messageDigest[] = md5ku.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < messageDigest.length; i++) {
            hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
        }
        return hexString.toString();
    }

    public void sendFirstPassword(String recipients) {
      
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        EmailHandler eh = new EmailHandler();
        System.out.println(eh.getStringMD5("lutan"));
    }
}
