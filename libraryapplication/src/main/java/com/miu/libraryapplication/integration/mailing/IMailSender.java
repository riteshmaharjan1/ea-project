package com.miu.libraryapplication.integration.mailing;

public interface IMailSender {
    void sendMail(String mail, String msgBody, String subject);
}
