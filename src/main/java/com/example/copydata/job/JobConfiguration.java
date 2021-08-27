package com.example.copydata.job;

import com.example.copydata.domain.ExternalMember;
import com.example.copydata.repository.CustomMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
//    private final WebService webService;
    private final CustomMemberRepository customMemberRepository;
    private final DataSource externalDataSource;

    private static final int chunkSize = 100;

    @Bean
    public Job itemReader() throws Exception {
        return jobBuilderFactory.get("itemReader")
                .start(ItemReaderStep())
                .build();
    }

    @Bean
    public Step ItemReaderStep() throws Exception {
        return stepBuilderFactory.get("itemReaderStep")
                .<ExternalMember, ExternalMember>chunk(chunkSize)
                .reader(jdbcPagingItemReader())
                .writer(itemWriter())
                .build();
    }


    @Bean
    public JdbcPagingItemReader<ExternalMember> jdbcPagingItemReader() throws Exception {
        return new JdbcPagingItemReaderBuilder<ExternalMember>()
                .pageSize(chunkSize)
                .fetchSize(chunkSize)
                .dataSource(externalDataSource) // externalDataSource
                .rowMapper(new BeanPropertyRowMapper<>(ExternalMember.class))
                .queryProvider(createQueryProvider())
                .name("jdbcPagingItemReader")
                .build();
    }

    private ItemWriter<ExternalMember> itemWriter() {
        return list -> {
            for (ExternalMember externalMember : list) {
                log.info(externalMember.toString());
                //                webService.sendRequest(firstMember.toRequest());
                customMemberRepository.save(externalMember.toCustom());
            }
        };
    }


    @Bean
    public PagingQueryProvider createQueryProvider() throws Exception {
        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(externalDataSource);
        queryProvider.setSelectClause("select regist_date, member_id");
        queryProvider.setFromClause("from tn_member");

        Map<String, Order> sortKeys = new HashMap<>(1);
        sortKeys.put("member_id", Order.ASCENDING);

        queryProvider.setSortKeys(sortKeys);

        return queryProvider.getObject();
    }
}
