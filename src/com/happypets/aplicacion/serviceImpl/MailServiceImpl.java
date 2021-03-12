package com.happypets.aplicacion.serviceImpl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import com.happypets.aplicacion.service.MailService;
import com.happypets.aplicacion.service.exceptions.MailException;

public class MailServiceImpl implements MailService{
	private static final String EMAIL ="happypetscarers@gmail.com";
	public void sendMail(String subject, String codigo, String...to) 
			throws MailException {
		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator
					("happypetscarers@gmail.com", "PromesaSemi93"));
			email.setSSLOnConnect(true);
			email.setFrom("happypetscarers@gmail.com");
			email.setSubject(subject);
			email.setMsg(codigo);
			email.addTo(to);
			email.send();
			
		}catch(EmailException se) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Intentando enviar email ");
			stringBuilder.append(" from ");
			stringBuilder.append(EMAIL);
			stringBuilder.append(" a ");
			stringBuilder.append(to);
			stringBuilder.append(se);
			throw new MailException(stringBuilder.toString());
		}
	}
	public void sendMailHtml(String subject, String codigo, String...to)
			throws MailException {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName("smtp.gmail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator
					("happypetscarers@gmail.com", "PromesaSemi93"));
			email.setSSLOnConnect(true);
			email.setFrom("happypetscarers@gmail.com");
			email.setSubject(subject);
			email.setMsg(codigo);
			email.addTo("abastosmarketplace@gmail.com");
			email.send();

		}catch(EmailException se) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Intentando enviar email ");
			stringBuilder.append(" from ");
			stringBuilder.append(EMAIL);
			stringBuilder.append(" a ");
			stringBuilder.append(to);
			stringBuilder.append(se);
			throw new MailException(stringBuilder.toString());
		}
	}
}
