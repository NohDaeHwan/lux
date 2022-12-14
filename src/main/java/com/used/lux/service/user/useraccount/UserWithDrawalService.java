package com.used.lux.service.user.useraccount;

import com.used.lux.repository.useraccount.UserWithdrawalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserWithDrawalService {
    //UserWithDrawal DB의 서비스 클래스입니다. 해당 DB에 연결하려면 repository와 service 영역에 추가적인 작성이 필요합니다.
    private final UserWithdrawalRepository userWithDrawalRepository;

    public Long countAll() {
        return userWithDrawalRepository.count();
    }
}
