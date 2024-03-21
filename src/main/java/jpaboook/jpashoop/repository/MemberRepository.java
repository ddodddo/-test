package jpaboook.jpashoop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpaboook.jpashoop.domain.Member;

@Repository
public class MemberRepository {
	// 엔티티 매니저를 주입받음
	@PersistenceContext
	private EntityManager em;

	public void save(Member member) {
		em.persist(member);
	}

	public Member findOne(Long id) {
		return em.find(Member.class, id);
	}

	public List<Member> findAll() {
		// JPQL
		// SQL을 추상화한 객체지향 쿼리언어
		// 엔티티 객체를 대상으로 쿼리
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	public List<Member> findByName(String name) {
		return em.createQuery("select m from Member m where m.name = :name", Member.class)
				.setParameter("name", name)
				.getResultList();
	}
}
