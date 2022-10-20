package com.used.lux.service;

import com.used.lux.dto.*;
import com.used.lux.dto.admin.AdUserAccountDto;
import com.used.lux.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdUserAccountService {

    private final UserAccountRepository userAccountRepository;

    private final ProductOrderLogRepository productOrderLogRepository;

    private final ProductOrderCancelRepository productOrderCancelRepository;

    private final AppraisalRepository appraisalRepository;

    private final AuctionLogRepository auctionLogRepository;

    private final UserAccountLogRepository userAccountLogRepository;

    private final UserGradeRepository userGradeRepository;

    private final UserWithdrawalRepository userWithdrawalRepository;

    public Page<UserAccountDto> getUserList(Pageable pageable) {
        return userAccountRepository.findAll(pageable).map(UserAccountDto::from);
    }

    public AdUserAccountDto getUserDetail(Long userId) {
        // 회원 상세
        UserAccountDto userAccountDto = UserAccountDto.from(userAccountRepository.findById(userId).get());
        // 주문내역
        List<ProductOrderLogDto> productOrderLogDtos = productOrderLogRepository.findByUserEmail(userAccountDto.userEmail())
                .stream().map(ProductOrderLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 취소내역
        List<ProductOrderCancelDto> productOrderCancelDtos = productOrderCancelRepository.findByUserName(userAccountDto.userName())
                .stream().map(ProductOrderCancelDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 검수내역
        List<AppraisalDto> appraisalDtos = appraisalRepository.findByUserAccountId(userAccountDto.id())
                .stream().map(AppraisalDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 경매 내역
        List<AuctionLogDto> auctionLogDtos = auctionLogRepository.findByBidder(userAccountDto.userName())
                .stream().map(AuctionLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 포인트 내역
        List<UserAccountLogDto> userAccountLogDtos = userAccountLogRepository.findByUserEmail(userAccountDto.userEmail())
                .stream().map(UserAccountLogDto::from).collect(Collectors.toCollection(ArrayList::new));

        return AdUserAccountDto.of(userAccountDto, productOrderLogDtos, productOrderCancelDtos,
                appraisalDtos, auctionLogDtos, userAccountLogDtos);
    }

    public List<UserGradeDto> getUserGrade() {
        return userGradeRepository.findAll()
                .stream().map(UserGradeDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    public List<UserWithdrawalDto> getUserWithdrawal() {
        return userWithdrawalRepository.findAll()
                .stream().map(UserWithdrawalDto::from).collect(Collectors.toCollection(ArrayList::new));
    }
}