package com.used.lux.domain.order;

import javax.persistence.*;

import com.used.lux.domain.AuditingFields;
import com.used.lux.domain.State;
import com.used.lux.domain.constant.OrderState;
import com.used.lux.domain.constant.SellType;
import com.used.lux.domain.product.Product;
import com.used.lux.domain.useraccount.UserAccount;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	@Column(name="phone_number", nullable = false, length = 13)
	private String phoneNumber;

	@Setter
	@Column(nullable = false, length = 5)
	private String zoneCode;

	@Setter
	@Column(nullable = false, length = 100)
	private String address;

	@Setter
	private Long payment;

	@Setter
	@Column(name="requested_term", length = 100)
	private String requestedTerm; // 요청사항

	@Setter
	@Column(name = "order_state")
	@Enumerated(EnumType.STRING)
	private OrderState orderState;

	@Setter
	@Column(name="prod_sell_type", length = 50)
	@Enumerated(EnumType.STRING)
	private SellType prodSellType; // 중고, 경매

	@Setter
	private Long productId;

	@Setter
	@ManyToOne(optional = false)
	@JoinColumn(name = "user_account_id")
	private UserAccount userAccount;
}
