package com.used.lux.domain.auction;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.used.lux.domain.*;
import com.used.lux.domain.constant.AppraisalGrade;
import com.used.lux.domain.constant.AuctionState;
import com.used.lux.domain.constant.GenterType;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name = "auction")
@Entity
public class Auction extends AuditingFields {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Setter
	@Column(name = "auc_nm", length = 100)
	private String aucNm;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cate_b_id")
	private CategoryB cateB;

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "catey_m_id")
	private CategoryM cateM;

	@Setter
	@Column(name = "auc_state")
	@Enumerated(EnumType.STRING)
	private AuctionState aucState;

	@Setter
	@Column(name="auc_grade")
	@Enumerated(EnumType.STRING)
	private AppraisalGrade aucGrade; // A, B, C, F

	@Setter
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "auc_brand")
	private Brand aucBrand;

	@Setter
	@Column(name = "auc_gender")
	@Enumerated(EnumType.STRING)
	private GenterType aucGender;

	@Setter
	@Column(name = "auc_size", length = 50)
	private String aucSize;

	@Setter
	@Column(name = "auc_color", length = 50)
	private String aucColor;

	@Setter
	@Column(name = "auc_content", length = 1000)
	private String aucContent;

	@Setter
	@Column(name = "auc_view_cnt")
	private int aucViewCnt; // 조회수

	@Setter
	@Column(name="start_price", nullable = false)
	private int startPrice; // 시작가격

	@Setter
	@Column(name="present_price", nullable = false)
	private int presentPrice; // 현재 가격

	@Setter
	@Column(name="end_price")
	private int endPrice; // 낙찰 가격

	@Setter
	@Column(name="auc_start_date", nullable = false)
	private LocalDateTime aucStartDate; // 경매 시작일

	@Setter
	@Column(name="auc_end_date", nullable = false)
	private LocalDateTime aucEndDate; // 경매 종료일

	@Setter
	@Column(name="bidding_count")
	private int biddingCount; // 입찰수

	@Setter
	@Column(length = 100)
	private String bidder; // 입찰자
}
