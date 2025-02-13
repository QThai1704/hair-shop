package t221124nqt.ecommerce.hair_shop.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailUtil {
    private final ResourceLoader resourceLoader;
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    // Xử lý hình ảnh trước khi thêm vào template
    private String readImageToBase64(String pathOfImage) throws IOException {
        Resource resource = resourceLoader.getResource(pathOfImage);
        File imageFile = new File(String.valueOf(resource.getFile()));
        byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    public void sendEmailSync(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart, StandardCharsets.UTF_8.name());
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content, isHtml);
            this.javaMailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            System.out.println("ERROR SEND EMAIL: " + e);
        }
    }

    @Async
    public void sendEmailFromTemplate(String to, String subject, String templateName,
            Map<String, Object> templateModel) {
        try {
            String imageBase64 = readImageToBase64("classpath:/static/img/Background-tet-2025.jpeg");
            templateModel.put("images", imageBase64);
            Context context = new Context();
            context.setVariables(templateModel);
            String content = this.templateEngine.process(templateName, context);
            this.sendEmailSync(to, subject, content, true, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
