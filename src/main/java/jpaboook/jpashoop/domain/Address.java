package jpaboook.jpashoop.domain;

import jakarta.persistence.Embeddable;
import jakarta.servlet.http.HttpServlet;
import lombok.Getter;

@Embeddable
@Getter
public class Address extends HttpServlet {

	private String zipcode;
	private String city;
	private String street;

	// 생성자를 protecetd로 설정하여 외부에서 생성하지 못하게 함
	// JPA에서는 protected 생성자를 사용하여 객체 생성을 제한할 수 있음
	// JPA 구현 라이브러리가 객체를 생성할 때 리플렉션 같은 기술을 사용할 수 있도록 하기 위함
	protected Address() {

	}

	public Address(String city, String street, String zipcode) {
		this.city = city;
		this.street = street;
		this.zipcode = zipcode;
	}
}
