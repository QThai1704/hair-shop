package t221124nqt.ecommerce.hair_shop.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.extern.slf4j.Slf4j;
import t221124nqt.ecommerce.hair_shop.component.batch.EmailProcessItem;
import t221124nqt.ecommerce.hair_shop.component.batch.EmailReaderItem;
import t221124nqt.ecommerce.hair_shop.domain.auth.User;
import t221124nqt.ecommerce.hair_shop.service.auth.IUserService;
import t221124nqt.ecommerce.hair_shop.util.EmailUtil;

@Configuration
@Slf4j
public class SpringBatchConfig {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private IUserService userService;
    @Autowired
    private EmailUtil emailUtil;

    // Job
    @Bean
    public Job sendMaillJob(Step sendMailStep) {
        return new JobBuilder("sendMailJob", jobRepository)
                .start(sendMailStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    // Step
    @Bean
    public Step stepSendMail(JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<User> readerEmail, ItemProcessor<User, User> processorEmail,
            ItemWriter<User> writerEmail) {
        System.out.println("stepSendMail");
        return new StepBuilder("stepSendMail", jobRepository)
                .<User, User>chunk(1000, transactionManager)
                .reader(readerEmail)
                .processor(processorEmail)
                .writer(writerEmail)
                .build();
    }

    // Reader
    @Bean
    @JobScope
    public EmailReaderItem readerEmail() {
        log.info("readerSendMail");
        return new EmailReaderItem(userService);
    }

    // Processor
    @Bean
    @JobScope
    public EmailProcessItem processorEmail() {
        log.info("processSendMail");
        return new EmailProcessItem(emailUtil);
    }

    // Writer
    @Bean
    @JobScope
    public ItemWriter<User> writerSendMail() {
        log.info("writerSendMail");
        return new ItemWriter<User>() {
            @Override
            public void write(Chunk<? extends User> items) throws Exception {
                log.info("ItemWriter: Bắt đầu ghi chunk {}", items.size());
                items.forEach(item -> log.info("Đang ghi item {}", item.getId()));
                log.info("ItemWriter: Kết thúc ghi tổng {} / step chunk", items.size());
            }
        };
    }

}
