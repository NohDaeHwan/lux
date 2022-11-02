package com.used.lux.service;

import com.used.lux.domain.ProductOrder;
import com.used.lux.dto.ProductOrderDto;
import com.used.lux.dto.UserAccountDto;
import com.used.lux.repository.ProductOrderRepository;
import com.used.lux.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final ProductOrderRepository productOrderRepository;

    private final UserAccountRepository userAccountRepository;

    public Page<ProductOrder> orderlistAll(Long id, Pageable pageable){
        Page<ProductOrder> orders = productOrderRepository.findByUserAccountId(id,pageable);
        return orders;
    }

    public  Page<ProductOrderDto> orderlistPage(UserAccountDto userAccountDto, Pageable pageable){
        Page<ProductOrderDto> paging = productOrderRepository.findByUserAccountId(userAccountDto.id(),pageable).map(ProductOrderDto::from);
        return  paging;
    }

    @Transactional
    public UserAccountDto getUser(Long id) {
        return UserAccountDto.from(userAccountRepository.findById(id).get());
    }

}
