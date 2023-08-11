package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    
    @Test
    @Rollback(value = false)
    public void 회원가입(){
        //given
        Member member = new Member();
        member.setName("memberA");
        
        //when
        Long join = memberService.join(member);
        //then
        assertThat(member.getId()).isEqualTo(join);
    }
    
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2);
        //then
        fail("예외가 발생한다.");

    }

}