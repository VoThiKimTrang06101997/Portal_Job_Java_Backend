package com.r2s.findInternship.service;

import com.r2s.findInternship.common.MailResponse;
import com.r2s.findInternship.common.MessageResponse;

public interface MailService {
	void send(MailResponse mailResponse);

	public MessageResponse sendMailActive(String email);

	MessageResponse sendMailForgotPassword(String email, boolean flag);
}
