package com.example.copydata.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;

//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class InitConfig {
//
//    private final InitService initService;
//
//    @PostConstruct
//    public void init() {
//        initService.createMemberIfNothing();
//    }
//
//
//    @Slf4j
//    @Component
//    @RequiredArgsConstructor
//    static class InitService {
//
//        private final FirstMemberRepository firstMemberRepository;
//
//        public void createMemberIfNothing() {
//            final long count = firstMemberRepository.count();
//
//            if (count != 0) {
//                return ;
//            }
//
//            for (int i = 0; i < 100; i++) {
//                final FirstMember member = new FirstMember(LocalDateTime.now(), new BigDecimal(i + 1), "member" + (i + 1));
//                firstMemberRepository.save(member);
//            }
//        }
//    }
//}
