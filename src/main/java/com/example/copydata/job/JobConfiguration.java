package com.example.copydata.job;

import com.example.copydata.domain.ExternalMember;
import com.example.copydata.repository.CustomMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Slf4j
@Configuration
public class JobConfiguration {

    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private CustomMemberRepository customMemberRepository;
    private EntityManagerFactory externalEntityManagerFactory;
    private DataSource externalDataSource;

    public JobConfiguration(JobBuilderFactory jobBuilderFactory,
                            StepBuilderFactory stepBuilderFactory,
                            CustomMemberRepository customMemberRepository,
                            @Qualifier(value = "externalEntityManagerFactory") EntityManagerFactory externalEntityManagerFactory,
                            @Qualifier(value = "externalDataSource") DataSource externalDataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.customMemberRepository = customMemberRepository;
        this.externalEntityManagerFactory = externalEntityManagerFactory;
        this.externalDataSource = externalDataSource;
    }

    private static final int chunkSize = 100;

    @Bean
    public Job copyDataJob() throws Exception {
        return jobBuilderFactory.get("copyDataJob")
                .start(externalMemberReaderStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step externalMemberReaderStep(@Value("#{jobParameters[requestDate]}") String requestDate) throws Exception {
        return stepBuilderFactory.get("itemReaderStep")
                .<ExternalMember, ExternalMember>chunk(chunkSize)
                .reader(jpaPagingItemReaderBuilder(null))
                .writer(customMemberWriter())
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<ExternalMember> jpaPagingItemReaderBuilder(@Value("#{jobParameters[requestDate]}") String requestDate) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        final LocalDate date = LocalDate.parse(requestDate, formatter);

        return new JpaPagingItemReaderBuilder<ExternalMember>()
                .name("externalMemberReader")
                .entityManagerFactory(externalEntityManagerFactory)
                .queryString("SELECT em FROM ExternalMember em where em.memberId.registDate >= :requestDate")
                .parameterValues(new HashMap<>() {{
                    put("requestDate", LocalDateTime.of(date, LocalTime.MIDNIGHT));
                }})
                .pageSize(chunkSize)
                .build();
    }

    private ItemWriter<ExternalMember> customMemberWriter() {
        return list -> {
            for (ExternalMember externalMember : list) {
                log.info("externalMember: {}", externalMember.toString());
                customMemberRepository.save(externalMember.toCustom());
            }
        };
    }
}
