package com.example.membercontrol.service;

import com.example.membercontrol.domain.Member;
import com.example.membercontrol.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * SpringBootTest annotation 를 통해, Spring Boot를 활용하고,
 * DB까지 연동하여 진행하는 '통합 테스트' 진행
 *
 * Transactional annotation을 통해, 테스트 후 roll back 실행
 * AfterEach annotation을 통해, 테스트 후 롤백하는 코드를 작성할 필요 x
 */
@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    MemberService memberService;
    MemberRepository memberRepository;

    @Autowired
    public MemberServiceIntegrationTest(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("spring");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void joinException() {
        // given
        Member member1 = new Member();
        member1.setName("name");

        Member member2 = new Member();
        member2.setName("name");

        // when
        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
