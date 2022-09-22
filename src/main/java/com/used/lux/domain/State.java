package com.used.lux.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Table(name = "state")
@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name="state_name", nullable = false, length = 100)
    private String stateName;  // 검수, 상품, 경매, 렌트

    @Setter
    @Column(name="state_step", nullable = false, length = 100)
    private String stateStep; // 검수전, 검수중, 검수완료, 판매중, 판매완료, 판매취소, 경매중, 경매완료, 렌트대기, 렌트중, 상품회수중, 예약중, 상품회수완료

}
