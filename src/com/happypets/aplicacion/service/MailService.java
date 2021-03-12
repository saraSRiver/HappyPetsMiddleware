package com.happypets.aplicacion.service;

import com.happypets.aplicacion.service.exceptions.MailException;

public interface MailService {
	public void sendMail(String subject, String codigo, String...to) 
			throws MailException;
	public void sendMailHtml(String subject, String codigo, String...to)
			throws MailException;
}
