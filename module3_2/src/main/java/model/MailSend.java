package model;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSend {
	String host = "smtp.gmail.com";
	int port = 587;
	final String username = "peustivate";
	final String password = "Ust0934335";// your password
	
	public void checkMailSend(String mail) {
	}
	
	public Boolean sendMail(String id,String pwd, String mail) {
		
		Boolean back = false;
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port);
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("peustivate@gmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
			message.setSubject("pwd mail");
			message.setText("Dear " + id + ", your password is " + pwd);

			Transport transport = session.getTransport("smtp");
			transport.connect(host, port, username, password);

			Transport.send(message);

			System.out.println("寄送email結束.");
			back = true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		return back;
	}
	
//	public static void main(String[] args) {
//		String mail = "yyf0414714823@gmail.com";
//		String pwd = "12345";
//		String id = "yyf";
//		MailSend mailsend = new MailSend();
//		Boolean it = mailsend.sendMail(id,pwd,mail);
//		System.out.println(it);
//	}
}
