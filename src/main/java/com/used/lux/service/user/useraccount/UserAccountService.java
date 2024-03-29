package com.used.lux.service.user.useraccount;

import com.used.lux.domain.order.ProductOrder;
import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.domain.useraccount.UserAccountLog;
import com.used.lux.dto.user.order.ProductOrderDto;
import com.used.lux.dto.user.useraccount.UserAccountDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.mapper.ProductOrderMapper;
import com.used.lux.mapper.UserAccountMapper;
import com.used.lux.repository.order.ProductOrderRepository;
import com.used.lux.repository.useraccount.UserAccountLogRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
import com.used.lux.request.useraccount.UserNameUpdateRequest;
import com.used.lux.request.useraccount.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class UserAccountService {

    private final ProductOrderRepository productOrderRepository;
    private final ProductOrderMapper productOrderMapper;

    private final UserAccountRepository userAccountRepo;

    private final UserAccountLogRepository userAccountLogRepository;

    private final UserAccountMapper userAccountMapper;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    public  boolean exsistByUserEmail(String userName) {
        return userAccountRepo.existsByUserEmail(userName);
    }

    public Page<ProductOrder> orderlistAll(Long id, Pageable pageable){
        Page<ProductOrder> orders = productOrderRepository.findByUserAccountId(id,pageable);
        return orders;
    }

    public  Page<ProductOrderDto> orderlistPage(UserAccountDto userAccountDto, Pageable pageable){
        Page<ProductOrderDto> paging = productOrderRepository.findByUserAccountId(userAccountDto.id(),pageable).map(productOrderMapper::toDto);
        return  paging;
    }

    public UserAccountDto getUser(Long id) {
        return userAccountMapper.toDto(userAccountRepo.findById(id).orElse(null));
    }

    @Transactional
    public void userPointUpdate(Principal principal, UserUpdateRequest userUpdateRequest){
        UserAccount userAccount = userAccountRepo.getReferenceById(principal.id());
        userAccount.setUserPoint(userUpdateRequest.userPoint()+ userAccount.getUserPoint());
        userAccountLogRepository.save(UserAccountLog.builder()
                        .id(null).orderId(null)
                        .userId(userAccount.getId()).userGrade(userAccount.getUserGrade())
                        .point(userUpdateRequest.userPoint()).usageDetail("충전").saleNumber("-")
                        .build());
        // 시큐리티 인증 재설정
        SecurityContextHolder.getContext().setAuthentication(
                createNewAuthentication(SecurityContextHolder.getContext().getAuthentication(), userAccount));
    }

    private Authentication createNewAuthentication(Authentication currentAuth, UserAccount user) {
        Principal newPrincipal = Principal.from(user);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;
    }

    public void addUser(UserAccount userAccount) {
        userAccountRepo.save(userAccount);
    }


    @Transactional
    public  void deleteUser(Principal principal){
       userAccountRepo.deleteById(principal.id());
       SecurityContextHolder.clearContext();
    }

    @Transactional
    public void userNameUpdate(Principal principal, UserNameUpdateRequest userNameUpdateRequest) {
        UserAccount user = userAccountRepo.findByUserName(principal.userName());
        user.setUserName(userNameUpdateRequest.userName());
        userAccountRepo.save(user);
        // 시큐리티 인증 재설정
        SecurityContextHolder.getContext().setAuthentication(
                createNewAuthentication(SecurityContextHolder.getContext().getAuthentication(), user));
    }

    public boolean exsistByUserName(String name) {
        return userAccountRepo.existsByUserName(name);
    }

    public boolean exsistByPhoneNumber(String phoneNumber) {
        return userAccountRepo.existsByPhoneNumber(phoneNumber);
    }

}
