package com.used.lux.service.user.useraccount;

import com.used.lux.domain.order.ProductOrder;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.domain.useraccount.UserAccountLog;
import com.used.lux.dto.user.order.ProductOrderDto;
import com.used.lux.dto.user.useraccount.UserAccountDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.repository.order.ProductOrderRepository;
import com.used.lux.repository.useraccount.UserAccountLogRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
import com.used.lux.request.useraccount.UserNameUpdateRequest;
import com.used.lux.request.useraccount.UserUpdateRequest;
import com.used.lux.response.useraccount.UserAccountResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        // 시큐리티 인증 재설정
        SecurityContextHolder.getContext().setAuthentication(
                createNewAuthentication(SecurityContextHolder.getContext().getAuthentication(), userAccount.getUserEmail()));
    }

    private Authentication createNewAuthentication(Authentication currentAuth, String username) {
        Principal newPrincipal = Principal.from(UserAccountDto.from(userAccountRepository.findByUserEmail(username).get()));
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;
    }

    public void addUser(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    @Transactional
    public  void deleteUser(Principal principal){
       userAccountRepository.deleteById(principal.id());
       SecurityContextHolder.clearContext();

    }


    public void userNameUpdate(Principal principal,UserNameUpdateRequest userNameUpdateRequest) {


        UserAccount userAccount =userAccountRepository.findByUserName(principal.userName());


        userAccount.setUserName(userNameUpdateRequest.userName());


        userAccountRepository.save(userAccount);




    }


}
