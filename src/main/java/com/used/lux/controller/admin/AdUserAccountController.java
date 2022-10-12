package com.used.lux.controller.admin;

import com.used.lux.dto.admin.AdUserAccountDto;
import com.used.lux.dto.security.Principal;
import com.used.lux.response.UserAccountResponse;
import com.used.lux.service.AdUserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin/user")
@RestController
public class AdUserAccountController {

    private final AdUserAccountService adUserAccountService;

    // 회원관리에서 정보 보여주기
    @GetMapping
    public ResponseEntity<Object> userList(@AuthenticationPrincipal Principal principal,
                                                            @PageableDefault(size = 30) Pageable pageable){
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.OK).body("ADMIN 계정으로 로그인 해주세요");
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return ResponseEntity.status(HttpStatus.OK).body("ADMIN 권한이 없음");
        }
        Page<UserAccountResponse> userList = adUserAccountService.getUserList(pageable).map(UserAccountResponse::from);
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }

    // 회원관리에서 상세정보 보여주기
    @GetMapping("/{userId}")
    public ResponseEntity<Object> userDetail(@PathVariable Long userId,
                                             @AuthenticationPrincipal Principal principal){
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.OK).body("ADMIN 계정으로 로그인 해주세요");
        }
        if (principal.role().getName() != "ROLE_ADMIN") {
            return ResponseEntity.status(HttpStatus.OK).body("ADMIN 권한이 없음");
        }
        AdUserAccountDto userDetail = adUserAccountService.getUserDetail(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userDetail);
    }

}
