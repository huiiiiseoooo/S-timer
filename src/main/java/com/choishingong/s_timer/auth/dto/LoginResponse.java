package com.choishingong.s_timer.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

// 로그인 성공했다는걸 다시 프론트에 반환

@Getter
@AllArgsConstructor
public class LoginResponse {

    private Long userId;
    private String email;
    private String nickname;
}
