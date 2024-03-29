package com.used.lux.repository.order;

import com.used.lux.domain.order.ProductOrder;
import com.used.lux.repository.querydsl.ProductOrderRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long>, ProductOrderRepositoryCustom {

    @Query(value ="SELECT po FROM ProductOrder po WHERE po.prodSellType LIKE %:orderSellType% AND po.orderState = :orderState AND " +
            "po.createdAt >= :orderDate AND po.name LIKE %:query%",
            countQuery = "SELECT po FROM ProductOrder po WHERE po.prodSellType LIKE %:orderSellType% AND po.orderState = :orderState AND " +
                    "po.createdAt >= :orderDate AND  po.name LIKE %:query%")
    Page<ProductOrder> findByBackProductOrderList(String orderState, String orderSellType, LocalDateTime orderDate, String query, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from product_order where user_account_id=:id order by id",
            countQuery = "select count(*) from product_order where user_account_id=:id")
    Page<ProductOrder> findByUserAccountId(@Param("id") Long id, Pageable pageable);

    // 1. findById()는 pk 가져오기.
    // 2. pk조회가 아니면 findBy컬럼명()
    // 3. pageable을 원하면 1이나 2에서 @Query 사용
    // 4. 만약에 조회하는 컬럼이 외래키라면 findBy외래키컬럼명_조인되는테이블의(첫대문자_첫대문자)컬럼명() 으로 불러온다.
    // 5. row 한줄 데이터를 수정할때 getReferenceById():주소를 가져온다.

}
