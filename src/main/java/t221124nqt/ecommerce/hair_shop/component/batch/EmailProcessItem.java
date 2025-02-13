package t221124nqt.ecommerce.hair_shop.component.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.util.EmailUtil;

@Component
@Slf4j
public class EmailProcessItem implements ItemProcessor<User, User> {
    private EmailUtil emailUtil;

    public EmailProcessItem(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    @Override
    @Nullable
    public User process(@NonNull User user) throws Exception {
        // Map<String, Object> templateModel = new HashMap<>();
        // this.emailUtil.sendEmailFromTemplate(user.getEmail(), "Thư gửi chúc tết",
        // "email/hpny-mail", templateModel);
        log.info("Send email to {}", user.getEmail());
        return user;
    }
}
