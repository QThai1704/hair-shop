package t221124nqt.ecommerce.hair_shop.component;

import java.util.UUID;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringBatchComponent {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job sendMailJob;

    @Scheduled(cron = "0/40 * * * * *")
    public void runBatchEmailJob() throws JobExecutionException, NoSuchJobException {
        JobParameters sendMailJobParameters = new JobParametersBuilder()
                .addString("uniqueKeySendMailJob", UUID.randomUUID().toString())
                .toJobParameters();
        jobLauncher.run(sendMailJob, sendMailJobParameters);
    }
}
