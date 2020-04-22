package com.yodlee.support.microservice.utility;

import java.io.File;
import java.util.Date;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

public class Util {

	public static void emailNoAttachment(Session emailSession, String toemail, String subject, String bodyMessage) {
		if (toemail != null) {

			try {
				MimeMessage msg = new MimeMessage(emailSession);
				msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
				msg.addHeader("format", "flowed");
				msg.addHeader("Content-Transfer-Encoding", "8bit");

				msg.setFrom(new InternetAddress("AutomationCOE@yodlee.com"));

				msg.setReplyTo(InternetAddress.parse(toemail, false));

				msg.setSubject(subject, "UTF-8");

				msg.setSentDate(new Date());

				msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toemail, false));

				Multipart multipart = new MimeMultipart();
				BodyPart htmlBodyPart = new MimeBodyPart();
				htmlBodyPart.setContent(bodyMessage, "text/html");
				multipart.addBodyPart(htmlBodyPart);

				msg.setContent(multipart);
				Transport.send(msg);
			} catch (MessagingException e) {
				System.err.println(e);
			}
		}
	}

	public static String getEmailTemplate(String type) {
		try {
			String path = type.equalsIgnoreCase("admin") ? "classpath:adminemail.html" : "classpath:support.html";
			File adminhtml = new File("adminemail.html");
			FileUtils.copyInputStreamToFile(ResourceUtils.getURL(path).openStream(), adminhtml);
			return FileUtils.readFileToString(adminhtml);

		} catch (Exception e) {
			return null;
		}
	}

}
