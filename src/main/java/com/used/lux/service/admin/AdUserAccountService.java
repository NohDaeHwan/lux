package com.used.lux.service.admin;

import com.used.lux.domain.useraccount.UserAccount;
import com.used.lux.dto.admin.AdUserAccountDto;
import com.used.lux.dto.user.appraisal.AppraisalDto;
import com.used.lux.dto.user.appraisal.AppraisalRequestLogDto;
import com.used.lux.dto.user.auction.AuctionLogDto;
import com.used.lux.dto.user.order.ProductOrderCancelDto;
import com.used.lux.dto.user.order.ProductOrderLogDto;
import com.used.lux.dto.user.useraccount.UserAccountDto;
import com.used.lux.dto.user.useraccount.UserAccountLogDto;
import com.used.lux.dto.UserGradeDto;
import com.used.lux.dto.user.useraccount.UserWithdrawalDto;
import com.used.lux.mapper.AppraisalMapper;
import com.used.lux.mapper.UserAccountLogMapper;
import com.used.lux.mapper.UserAccountMapper;
import com.used.lux.mapper.UserGradeMapper;
import com.used.lux.repository.*;
import com.used.lux.repository.appraisal.AppraisalRequestLogRepository;
import com.used.lux.repository.appraisal.AppraisalRepository;
import com.used.lux.repository.appraisal.AppraisalResultRepository;
import com.used.lux.repository.auction.AuctionLogRepository;
import com.used.lux.repository.order.ProductOrderCancelRepository;
import com.used.lux.repository.order.ProductOrderLogRepository;
import com.used.lux.repository.useraccount.UserAccountLogRepository;
import com.used.lux.repository.useraccount.UserAccountRepository;
import com.used.lux.repository.useraccount.UserWithdrawalRepository;
import com.used.lux.request.useraccount.UserMemoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdUserAccountService {

    private final UserAccountRepository userAccountRepo;
    private final UserAccountMapper userAccountMapper;
    private final UserAccountLogMapper userAccountLogMapper;

    private final AppraisalRequestLogRepository appraisalRequestLogRepository;

    private final ProductOrderLogRepository productOrderLogRepository;

    private final ProductOrderCancelRepository productOrderCancelRepository;

    private final AppraisalRepository appRepo;
    private final AppraisalMapper appMapper;

    private final AppraisalResultRepository appResultRepo;

    private final AuctionLogRepository auctionLogRepository;

    private final UserAccountLogRepository userAccountLogRepository;

    private final UserGradeRepository userGradeRepository;
    private final UserGradeMapper userGradeMapper;

    private final UserWithdrawalRepository userWithdrawalRepository;

    @Transactional(readOnly = true)
    public Page<UserAccountDto> getUserList(Pageable pageable, String gender, String age, String grade,
                                            String date, String query) {
        return userAccountRepo.searchUser(gender, age, grade, date, query, pageable).map(userAccountMapper::toDto);
    }

    @Transactional(readOnly = true)
    public AdUserAccountDto getUserDetail(Long userId) {
        // 회원 상세
        UserAccountDto userAccountDto = userAccountMapper.toDto(userAccountRepo.findById(userId).get());
        // 주문내역
        List<ProductOrderLogDto> productOrderLogDtos = productOrderLogRepository.findByUserEmail(userAccountDto.userEmail())
                .stream().map(ProductOrderLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 취소내역
        List<ProductOrderCancelDto> productOrderCancelDtos = productOrderCancelRepository.findByUserName(userAccountDto.userName())
                .stream().map(ProductOrderCancelDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 검수내역
        // pageable일땐 stream이어도 map만해줘도된다. 상관없음.
        // list, arraylist, collection 등 다른 리스트 형태들은 파이프(stream) 역할을 해줘야한다.
        List<AppraisalDto> appraisalDtos = appRepo.findByUserAccountId2(userAccountDto.id())
                .stream().map((item) -> {
                    return appMapper.toDtoCustom(item, appResultRepo.findById(item.getAppResultId()).orElse(null));
                }).toList();
        // 경매 내역
        List<AuctionLogDto> auctionLogDtos = auctionLogRepository.findByBidderList(userAccountDto.userName())
                .stream().map(AuctionLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        // 포인트 내역
        List<UserAccountLogDto> userAccountLogDtos = userAccountLogRepository.findByUserEmail(userAccountDto.userEmail())
                .stream().map(userAccountLogMapper::toDto).collect(Collectors.toCollection(ArrayList::new));

        List<AppraisalRequestLogDto> userAppraisalLogDtos = appraisalRequestLogRepository.findByUserIdOrderByModifiedAtDesc(userAccountDto.id())
                .stream().map(AppraisalRequestLogDto::from).collect(Collectors.toCollection(ArrayList::new));
        return AdUserAccountDto.of(userAccountDto, productOrderLogDtos, productOrderCancelDtos,
                appraisalDtos, auctionLogDtos, userAccountLogDtos, userAppraisalLogDtos);
    }

    @Transactional(readOnly = true)
    public UserAccountDto getUserMemo(Long userId) {
        return userAccountMapper.toDto(userAccountRepo.findById(userId).get());
    }

    @Transactional(readOnly = true)
    public List<UserGradeDto> getUserGrade() {
        return userGradeRepository.findAll()
                .stream().map(userGradeMapper::toDto).collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional(readOnly = true)
    public List<UserWithdrawalDto> getUserWithdrawal() {
        return userWithdrawalRepository.findAll()
                .stream().map(UserWithdrawalDto::from).collect(Collectors.toCollection(ArrayList::new));
    }

    @Transactional
    public void updateMemo(Long userId, UserMemoUpdateRequest request) {
        UserAccount userAccount = userAccountRepo.getReferenceById(userId);
        userAccount.setMemo(request.memo());
    }
}
