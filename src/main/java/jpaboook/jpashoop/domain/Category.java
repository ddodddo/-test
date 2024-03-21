package jpaboook.jpashoop.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {
	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long id;

	private String name;

	@OneToMany(mappedBy = "category")
	private List<CategoryItem> categoryItems = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();

	// 연관관계 메서드
	public void addChildCategory(Category child) {
		this.child.add(child);
		child.setParent(this);
	}

}
