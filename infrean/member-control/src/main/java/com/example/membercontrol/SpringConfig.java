package com.example.membercontrol;

import com.example.membercontrol.aop.TimeTraceAop;
import com.example.membercontrol.repository.JpaMemberRepository;
import com.example.membercontrol.repository.MemberRepository;
import com.example.membercontrol.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration annotation이 선언 된 class 내부에 스프링 컨테이너에 빈으로 등록할 객체들을
 * 코드로 작성할 수 있다.
 *
 * 자동으로 빈으로 등록되기 위해서는 Controller, Service, Repository annotation과 같이 Component annotation을 포함한
 * annotation을 선언하면 된다.
 */
@Configuration
public class SpringConfig {

    // Spring Data JPA
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // JPA
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // JDBC
//    private final DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /**
     * interface를 두고 여러가지 구현 class 가 있는 경우, 아래와 같이 쉽게 바꿔 끼워 넣을 수 있음.
     */
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
