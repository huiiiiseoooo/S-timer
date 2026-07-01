package com.choishingong.s_timer.auth.service;

import com.choishingong.s_timer.auth.dto.LoginRequest;
import com.choishingong.s_timer.auth.dto.LoginResponse;
import com.choishingong.s_timer.auth.dto.SignupRequest;
import com.choishingong.s_timer.domain.user.entity.Provider;
import com.choishingong.s_timer.domain.user.entity.User;
import com.choishingong.s_timer.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        // 회원가입 이메일 중복 확인
        // 중복이 아니면 비밀번호 암호화 하고 user 생성해서 db에 저장
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .provider(Provider.LOCAL)
                .providerId(null)
                .build();

        userRepository.save(user);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));
        // 이메일로 조회
        if (user.getProvider() != Provider.LOCAL) {
            throw new IllegalArgumentException("소셜 로그인 계정입니다.");
        }
        // 비밀번호 일치 확인
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        // 성공하면 LoginResponse 로 반환 이게 프론트에 다시 전달하는 것
        return new LoginResponse(
                user.getId(),
                user.getEmail(),
                user.getNickname()
        );
    }
}
