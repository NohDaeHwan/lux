package com.used.lux.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@Column(name="requested_term", length = 100)
	private String requestedTerm;

	@Setter
	@Column(name="invoice_number")
	private Long invoiceNumber;

	@Setter
	@Column(name="payment_method", nullable = false, length = 100)
	private String paymentMethod;

	@Setter
	@Column(nullable = false)
	private int payment;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_id")
	private Product product;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;

	public ProductOrder(Long id, String name, String phoneNumber, String address, String email, String requestedTerm, Long invoiceNumber, String paymentMethod, int payment, Product product, UserAccount userAccount) {
		super();
	}

	public ProductOrder() {

	}


	public static ProductOrder of(Long id, String name, String phoneNumber, String address, String email, String requestedTerm, Long invoiceNumber, String paymentMethod, int payment, Product product, UserAccount userAccount) {
		return  new ProductOrder(id,name,phoneNumber,address,email,
				requestedTerm,invoiceNumber, paymentMethod, payment,product,userAccount);
	}


}
