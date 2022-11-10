package com.used.lux.domain;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@Table(name = "product_order")
@Entity
public class ProductOrder extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(nullable = false, length = 100)
	private String name;

	@Setter
	@Column(name="phone_number", nullable = false, length = 11)
	private String phoneNumber;

	@Setter
	@Column(nullable = false, length = 100)
	private String address;

	@Setter
	@Column(length = 100)
	private String email;

	@Setter
	private int payment;

	@Setter
	@Column(name="requested_term", length = 100)
	private String requestedTerm; // 요청사항

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_id")
	private State state;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	protected ProductOrder() {}

	private ProductOrder(String name, String phoneNumber, String address, String email, int payment, String requestedTerm,
						 State state, Product product, UserAccount userAccount) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
		this.requestedTerm = requestedTerm;
		this.payment = payment;
		this.state = state;
		this.product = product;
		this.userAccount = userAccount;
	}

	public static ProductOrder of(String name, String phoneNumber, String address, String email, int payment,
								  String requestedTerm, State state, Product product, UserAccount userAccount) {
		return new ProductOrder(name, phoneNumber, address, email, payment, requestedTerm, state, product, userAccount);
	}

}
