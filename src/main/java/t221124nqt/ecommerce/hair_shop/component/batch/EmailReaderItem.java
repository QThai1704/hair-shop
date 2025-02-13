package t221124nqt.ecommerce.hair_shop.component.batch;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.service.imp.auth.IUserService;

@Component
@Slf4j
public class EmailReaderItem implements ItemReader<User> {
    private IUserService userService;

    public EmailReaderItem(IUserService userService) {
        this.userService = userService;
    }

    private List<User> list;
    private int count = 0;

    @PostConstruct
    public void init() {
        this.list = userService.getAllUsers();
    }

    @Override
    @Nullable
    public User read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (count < list.size()) {
            return list.get(count++);
        }
        log.info("User Data {}", count++);
        return null;
    }

}
