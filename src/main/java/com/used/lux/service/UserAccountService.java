package com.used.lux.service;

import com.used.lux.domain.*;
import com.used.lux.dto.ProductOrderDto;
import com.used.lux.dto.UserAccountDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.repository.ProductOrderRepository;
import com.used.lux.repository.UserAccountLogRepository;
import com.used.lux.repository.UserAccountRepository;
import com.used.lux.request.ProductUpdateRequest;
import com.used.lux.request.UserUpdateRequest;
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

    private final UserAccountLogRepository userAccountLogRepository;

    public  boolean exsistByUserEmail(String userName) {
        return userAccountRepository.existsByUserEmail(userName);
    }

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

    @Transactional
    public void userPointUpdate(Principal principal, UserUpdateRequest userUpdateRequest){
        UserAccount userAccount = userAccountRepository.getReferenceById(principal.id());
        userAccount.setPoint(userUpdateRequest.userPoint()+ userAccount.getPoint());
        userAccountLogRepository.save(UserAccountLog.of(principal.userEmail(), principal.userGrade(),userUpdateRequest.userPoint(),"충전","-"));
    }

    public void addUser(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }
}
