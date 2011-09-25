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
import javax.mail.Message.RecipientType;
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
    
    String HOST = "smtp.gmail.com";
    String USER = "anpaultd@gmail.com";
    String PASSWORD = "pisangijo";
    String PORT = "465";
    String STARTTLS = "true";
    String AUTH = "true";
    String DEBUG = "true";
    String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";

    /**
     * 
     * @param email_recipients Email Address Receiver
     * @param subject Email Subject
     * @param message Email Contain
     * @param from Email Sender
     * @throws MessagingException
     * @throws UnsupportedEncodingException 
     */
    public void postMail(String email_recipients, String subject, String messages) throws MessagingException, UnsupportedEncodingException {
        
        Properties props = new Properties();
        String from = "anpaultd@gmail.com";
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.user", USER);
        props.put("mail.smtp.auth", AUTH);
        props.put("mail.smtp.starttls.enable", STARTTLS);
        props.put("mail.smtp.debug", DEBUG);
        props.put("mail.smtp.socketFactory.port", PORT);
        props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        
        try {

            //Obtain the default mail session
            Session session = Session.getDefaultInstance(props, null);
            session.setDebug(true);

            //Construct the mail message
            MimeMessage message = new MimeMessage(session);
            message.setText(messages);
            message.setSubject(subject);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(RecipientType.TO, new InternetAddress(email_recipients));
            message.saveChanges();

            //Use Transport to deliver the message
            Transport transport = session.getTransport("smtp");
            transport.connect(HOST, USER, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generating Password
     * @return 
     */
    public String generatingPassword() {
        Random randEngine = new Random();
        int pass = randEngine.nextInt();
        return Integer.toHexString(pass);
    }

    /**
     * Return Md5 String
     * @param password String password
     * @return
     * @throws NoSuchAlgorithmException 
     */
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

    /**
     * Send Generating password
     * @param recipient 
     */
    public String sendFirstPassword(String recipient, String name) throws NoSuchAlgorithmException, MessagingException, UnsupportedEncodingException {
        String Subject = "THR Registeration";
        String password = getStringMD5(generatingPassword());
        String message = "Thank you for registeration on THR" + "\n"
                + "Here Your Registeration Information and Password " + "\n\n"
                + "Name : " + name + "\n"
                + "Email : " + recipient + "\n"
                + "Pasword : " + password + "\n\n";
        postMail(recipient, Subject, message);
        return password;
    }
    
    public static void main(String[] args) throws NoSuchAlgorithmException, MessagingException, UnsupportedEncodingException {
        EmailHandler eh = new EmailHandler();
        System.out.println(eh.getStringMD5("lutan"));
        eh.sendFirstPassword("sidiksoleman@gmail.com", "Sidik Soleman");
    }
}
