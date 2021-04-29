package com.happypets.aplicacion.serviceImpl;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

import com.happypets.aplicacion.configuration.ConfigurationManager;
import com.happypets.aplicacion.service.MailService;
import com.happypets.aplicacion.service.exceptions.MailException;

public class MailServiceImpl implements MailService{
	private static final String EMAIL = "mailService.user";
	private static final String PASSWORD = "mailService.password";
	private static final String HOST = "mailService.hostName";
	private static final String PORT = "mailService.port";
	private static ConfigurationManager cfg = ConfigurationManager.getInstance();
	public void sendMail(String subject, String codigo, String...to) 
			throws MailException {
		try {
			Email email = new SimpleEmail();
			email.setHostName(cfg.getParameter(HOST));
			email.setSmtpPort(Integer.valueOf(cfg.getParameter(PORT)));
			email.setAuthenticator(new DefaultAuthenticator
					(cfg.getParameter(EMAIL), cfg.getParameter(PASSWORD)));
			email.setSSLOnConnect(true);
			email.setFrom(cfg.getParameter(EMAIL));
			email.setSubject(subject);
			email.setMsg(codigo);
			email.addTo(to);
			email.send();
			
		}catch(EmailException se) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Intentando enviar email ");
			stringBuilder.append(" from ");
			stringBuilder.append(cfg.getParameter(EMAIL));
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
			email.setHostName(cfg.getParameter(HOST));
			email.setSmtpPort(Integer.valueOf(cfg.getParameter(PORT)));
			email.setAuthenticator(new DefaultAuthenticator
					(cfg.getParameter(EMAIL), cfg.getParameter(PASSWORD)));
			email.setSSLOnConnect(true);
			email.setFrom(cfg.getParameter(EMAIL));
			email.setSubject(subject);
			email.setMsg(codigo);
			email.addTo(to[0]);
			email.send();

		}catch(EmailException se) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Intentando enviar email ");
			stringBuilder.append(" from ");
			stringBuilder.append(cfg.getParameter(EMAIL));
			stringBuilder.append(" a ");
			stringBuilder.append(to);
			stringBuilder.append(se);
			throw new MailException(stringBuilder.toString());
		}
	}
}
