package com.example.copydata.job;

//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class MemberJob {
//
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//    private final EntityManagerFactory firstEntityManager;
//    private final EntityManagerFactory secondEntityManager;
//
//    private int chunkSize = 10;
//
//    @Bean
//    public Job memberJobBatchBuilder() {
//        return jobBuilderFactory.get("memberJob")
//                .start(memberJobStep())
//                .build();
//    }
//
//    @Bean
//    public Step memberJobStep() {
//        return stepBuilderFactory.get("memberJobStep")
//                .<FirstMember, SecondMember>chunk(chunkSize)
//                .reader(memberJobItemReader())
//                .writer(memberJobItemWriter())
//                .build();
//    }
//
//    @Bean
//    public JpaPagingItemReader<FirstMember> memberJobItemReader() {
//        return new JpaPagingItemReaderBuilder<FirstMember>()
//                .name("memberJobItemReader")
//                .entityManagerFactory(firstEntityManager)
//                .queryString("SELECT m FROM Member m")
//                .build();
//    }
//
//    @Bean
//    public JpaItemWriter<SecondMember> memberJobItemWriter() {
//        JpaItemWriter<SecondMember> secondMemberJpaItemWriter = new JpaItemWriter<>();
//        secondMemberJpaItemWriter.setEntityManagerFactory(secondEntityManager);
//        return secondMemberJpaItemWriter;
//    }
//}
