/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eric
 */


import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JCheckBox;

public class SendEmail {
    
   private String sendTo;
   public SendEmail(String recipient){
       sendTo = recipient;
   }
   public void send(String customMsg,boolean send){
      
      if(!send){
          return;
      }
      String to = sendTo;//change accordingly

      // Sender's email ID needs to be mentioned
      String from = "Course Checker";//change accordingly
      final String username = "course.notifications.ubco@gmail.com";//change accordingly
      final String password = "";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(username,from));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(to));

         // Set Subject: header field
         message.setSubject("Open Course Notification");

         // Now set the actual message
         message.setText(customMsg);

         // Send message
         Transport.send(message);         

      } catch (MessagingException e) {
            throw new RuntimeException(e);
      } catch (UnsupportedEncodingException ex) {
           Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
   
