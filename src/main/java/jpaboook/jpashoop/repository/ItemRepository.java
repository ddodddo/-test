package jpaboook.jpashoop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpaboook.jpashoop.domain.Item;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
	private final EntityManager em;

	public void save(Item item) {
		if (item.getId() == null) {
			em.persist(item);
		} else {
			em.merge(item); // update와 비슷한 기능
		}
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}
}
