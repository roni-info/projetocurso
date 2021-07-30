package br.com.roni.service;

import org.springframework.mail.SimpleMailMessage;

import br.com.roni.model.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	void sendEmail(SimpleMailMessage msg);
}
