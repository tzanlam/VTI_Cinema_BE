package cinema.service.MailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService implements IMailSenderService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text) throws Exception {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@baeldung.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // text có thể để dạng html = true
            mailSender.send(message);

        }catch (MessagingException e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void generateVerificationCode(String email, String token, String text) throws Exception {
        try{
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(email);
            helper.setFrom("vtiCinema@baeldung.com");
            helper.setSubject("Confirm email");
            helper.setText(text,true);
            mailSender.send(message);
        }catch (MessagingException e){
            throw new Exception(e.getMessage());
        }
    }
}
