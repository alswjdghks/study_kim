package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
public class MemberServiceTest {

    @Autowired MemberRepository memberRepository;
    @Autowired MemberService memberService;
    @Autowired
    EntityManager em;
    @Test
//    @Rollback(value = false) -> db에 데이터가 들어가는지 확인 할때 사용
    public void 회원가입() throws Exception{
        //given
        Member member = new Member();
        member.setName("min");
        //when
        Long savedId = memberService.join(member);
        //then
//        em.flush(); // insert문 볼 수 있게 해주는 방법 -> db에 쿼리가 나감.
        assertEquals(member,memberRepository.findOne(savedId));
    }

    @Test(expected = IllegalAccessError.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("min");

        Member member2 = new Member();
        member2.setName("min");

        //when
        memberService.join(member1);
        memberService.join(member2); //예외 발생

        //then
        fail("예외가 발생해야 한다."); // 여기 오면 잘못된거

    }
}

