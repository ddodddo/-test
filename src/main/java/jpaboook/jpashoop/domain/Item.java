package jpaboook.jpashoop.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jpaboook.jpashoop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {
	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;

	private String name;
	private int price;
	private int stockQuantity;

	@OneToMany(mappedBy = "item")
	private List<CategoryItem> categoryItems = new ArrayList<>();

	// 비즈니스 로직

	/**
	 * stock 증가
	 * @param stockQuantity
	 */
	public void addStock(int stockQuantity) {
		this.stockQuantity += stockQuantity;
	}

	/**
	 * stock 감소
	 * @param stockQuantity
	 */
	public void removeStock(int stockQuantity) {
		int restStock = this.stockQuantity - stockQuantity;
		if (restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}
		this.stockQuantity = restStock;
	}
}
