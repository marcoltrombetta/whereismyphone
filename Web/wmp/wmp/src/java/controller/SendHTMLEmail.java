package controller;

//File Name SendHTMLEmail.java

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendHTMLEmail
{
public static void main(String [] args)
{
   
   // Recipient's email ID needs to be mentioned.
   String to = "";

   // Sender's email ID needs to be mentioned
   String from = "";

   // Assuming you are sending email from localhost
   String host = "localhost";

   // Get system properties
   Properties properties = System.getProperties();

   // Setup mail server
   properties.setProperty("smtp.gmail.com", host);

   // Get the default Session object.
   Session session = Session.getDefaultInstance(properties);

   try{
      // Create a default MimeMessage object.
      MimeMessage message = new MimeMessage(session);

      // Set From: header field of the header.
      message.setFrom(new InternetAddress(from));

      // Set To: header field of the header.
      message.addRecipient(Message.RecipientType.TO,
                               new InternetAddress(to));

      // Set Subject: header field
      message.setSubject("This is the Subject Line!");

      // Send the actual HTML message, as big as you like
      message.setContent("<h1>This is actual message</h1>",
                         "text/html" );

      // Send message
      Transport.send(message);
      System.out.println("Sent message successfully....");
   }catch (MessagingException mex) {
      mex.printStackTrace();
   }
}


public static void sslmail(String mail,String subject, String text) {
	Properties props = new Properties();
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.socketFactory.port", "465");
	props.put("mail.smtp.socketFactory.class",
			"javax.net.ssl.SSLSocketFactory");
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.port", "465");

	Session session = Session.getInstance(props,
		new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("","");
			}
		});

	try {

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("from@no-spam.com"));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mail));
		message.setSubject(subject);
		message.setContent(text,"text/html");
		//message.setText(text);

		Transport.send(message);

		System.out.println("Done");

	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
}

}
