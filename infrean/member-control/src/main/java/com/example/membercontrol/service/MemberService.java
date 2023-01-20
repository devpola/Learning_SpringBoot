package com.example.membercontrol.service;

import com.example.membercontrol.domain.Member;
import com.example.membercontrol.repository.MemberRepository;
import com.example.membercontrol.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

/**
 * Service annotation 정의에 들어가면, Component annotation이 선언되어있음을 알 수 있음.
 * SpringBootApplication annotation이 선언되어있는 Main 함수가 존재하는 package 하위 패키지들을 돌아다니며
 * Component annotation이 선언된 class들을 자동으로 스프링 컨테이너의 빈으로 등록한다(컴포넌트 스캔)
 *
 * 컴포넌트 스캔으로 등록 된 빈 간에 의존관계가 있을 경우, Autowired annotation을 통해, 자동으로 의존성 주입을 받을 수 있다.
 * */
// @Service // 현재 SpringConfig class 내부에 빈으로 직접 등록 되어 있어, 주석처리
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        validateDuplicateMember(member);    // 중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
