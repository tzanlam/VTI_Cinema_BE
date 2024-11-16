package cinema.service.MailSender;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.io.File;

@Service
public class MailSenderService implements IMailSenderService {
    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text) throws Exception {
        try{
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("noreply@baeldung.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true); // text có thể để dạng html = true
//            FileSystemResource file
//                    = new FileSystemResource(new File(pathToAttachment));
//            helper.addAttachment("Invoice", file);// File có thể là hình ảnh hoặc pdf,...
            emailSender.send(message);

        }catch (MessagingException e){
            throw new Exception(e.getMessage());
        }

    }
}
