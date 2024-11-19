package cinema.service.MailSender;

public interface IMailSenderService {
    void sendMessageWithAttachment(String to, String subject, String text) throws Exception;
    void generateVerificationCode(String email, String token) throws Exception;
}
