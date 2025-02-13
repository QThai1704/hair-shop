package t221124nqt.ecommerce.hair_shop.controller.other;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import t221124nqt.ecommerce.hair_shop.util.EmailUtil;
import t221124nqt.ecommerce.hair_shop.util.anotation.ApiMessage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SendEmailController {
    private final EmailUtil emailUtil;

    @RequestMapping("/send-email")
    @ApiMessage(message = "Gửi email chúc tết")
    public String sendEmailHappyNewYear() {
        Map<String, Object> templateModel = new HashMap<>();
        this.emailUtil.sendEmailFromTemplate("thainguyen17.via@gmail.com", "Thư gửi chúc tết", "email/hpny-mail",
                templateModel);
        return "Email sent successfully";
    }
}
