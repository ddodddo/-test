package jpaboook.jpashoop.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpaboook.jpashoop.domain.Member;
import jpaboook.jpashoop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;

	/**
	 * 회원 가입
	 * @param member
	 * @return
	 */
	// readOnly = false
	@Transactional
	public Long join(Member member) {
		validateDuplicateMember(member); // 중복 회원 검증
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicateMember(Member member) {
		// EXCEPTION
		// 실무에서는 동시에 같은 이름으로 회원가입이 일어날 수 있음
		// 이 경우 데이터베이스에 name에 유니크 제약조건을 걸어서 동시에 같은 이름으로 회원가입이 일어나지 않도록 함
		// 동시에 같은 이름으로 회원가입이 일어나면 데이터베이스에서 예외가 발생함

		List<Member> findMembers = memberRepository.findByName(member.getName());
		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		}
	}

	/**
	 * 전체 회원 조회
	 * @return
	 */
	// 읽기 전용 메서드에는 readOnly = true를 사용하면 성능이 최적화됨
	// @Transactional(readOnly = true)
	public List<Member> findMembers() {
		return memberRepository.findAll();
	}

	public Member findOne(Long memberId) {
		return memberRepository.findOne(memberId);
	}
}
