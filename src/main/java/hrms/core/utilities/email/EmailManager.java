package hrms.core.utilities.email;

import java.io.File;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import hrms.core.utilities.results.ErrorResult;
import hrms.core.utilities.results.Result;
import hrms.core.utilities.results.SuccessResult;
import lombok.var;

@Service
public class EmailManager implements EmailService {
    private JavaMailSender javaMailSender;

    @Autowired
    public EmailManager(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public Result send(EmailMessage emailMessage) {
        try {
            var message = this.javaMailSender.createMimeMessage();
            var helper = new MimeMessageHelper(message, true);
            helper.setSubject(emailMessage.getSubject());
            helper.setText(emailMessage.getText(), true);
            if (emailMessage.getDate() != null) {
                helper.setSentDate(emailMessage.getDate());
            }
            if (emailMessage.getPath() != null) {
                var file = new FileSystemResource(new File(emailMessage.getPath()));
                helper.addAttachment(file.getFilename(), file);
            }
            for (var toAddress : emailMessage.getToAddresses()) {
                helper.setTo(toAddress);
                this.javaMailSender.send(message);
            }
            return new SuccessResult();
        } catch (

        MessagingException e) {
            return new ErrorResult("Mail couldn't send.");
        }
    }
}