package com.used.lux.service;

import com.used.lux.dto.UserAccountDto;
import com.used.lux.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.DoubleStream;

@RequiredArgsConstructor
@Service
public class AdUserAccountService {

    private final UserAccountRepository userAccountRepository;

    public Page<UserAccountDto> getUserList(Pageable pageable) {
        return userAccountRepository.findAll(pageable).map(UserAccountDto::from);
    }

    public UserAccountDto getUserDetail(Long userId) {
        return null;
    }
}
