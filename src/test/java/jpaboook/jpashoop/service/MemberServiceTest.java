package jpaboook.jpashoop.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jpaboook.jpashoop.domain.Member;
import jpaboook.jpashoop.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceTest {
	@Autowired
	MemberRepository memberRepository;

	@Autowired
	MemberService memberService;

	// 엔티티 매니저를 직접 주입받아서 사용할 수 있음
	@Autowired
	EntityManager em;

	@Test
	public void 회원가입() throws Exception {
		//given
		Member member = new Member();
		member.setName("kim");

		//when
		Long saveId = memberService.join(member);

		//then
		Member findMember = memberRepository.findOne(saveId);
		assertEquals(member, findMember);
	}

	@Test
	public void 중복_회원_예외() throws Exception{
		//given
		Member member1 = new Member();
		member1.setName("kim");

		Member member2 = new Member();
		member2.setName("kim");

		//when
		memberService.join(member1);
		try {
			memberService.join(member2); // 예외가 발생해야 한다.
		} catch (IllegalStateException e) {
			return;
		}

		//then
		fail("예외가 발생해야 한다.");
	}
}
