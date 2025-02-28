package t221124nqt.ecommerce.hair_shop.component;

import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class TranslatorComponent {
    private static ResourceBundleMessageSource messageSource;

    public TranslatorComponent(ResourceBundleMessageSource messageSource) {
        TranslatorComponent.messageSource = messageSource;
    }

    public static String toLocale(String msgCode){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(msgCode, null, locale);
    }
}
