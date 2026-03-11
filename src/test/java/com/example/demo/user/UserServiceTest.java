package com.example.demo.user;

import com.example.demo.common.exception.BaseException;
import com.example.demo.user.model.User;
import com.example.demo.user.model.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static com.example.demo.common.model.BaseResponseStatus.SIGNUP_DUPLICATE_EMAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class) // Mockito 테스트 활성화
class UserServiceTest {
    @Mock   // 가짜 객체 생성
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailService emailService;
    @Mock
    private EmailVerifyRepository emailVerifyRepository;

    @InjectMocks    // mock들을 UserService에 주입
    private UserService userService;

    UserDto.SignupReq dto;
    User user;

    // 모든 테스트 코드에 중복되는 부분 미리 세팅, 테스트 코드가 짧아짐
    @BeforeEach
    void setUp() {
        dto = new UserDto.SignupReq(
                "test01@test.com", "test01", "qwer1234"
        );

        user = User.builder()
                .idx(1L)
                .email(dto.getEmail())
                .name(dto.getName())
                .build();
    }

    @Test
    void userService_회원가입_성공() {
        // given : 주어져야 하는 데이터를 여기서 지정

        // 아래 모든 테스트에서 중복되는 코드 위로 뺌
//        dto = new UserDto.SignupReq(
//                "test01@test.com", "test01", "qwer1234"
//        );
//
//        user = User.builder()
//                .idx(1L)
//                .email(dto.getEmail())
//                .name(dto.getName())
//                .build();

        given(userRepository.findByEmail(dto.getEmail())).willReturn(Optional.empty());
        given(userRepository.save(any(User.class))).willReturn(user);

        // when : 실제 실행을 테스트할 코드
        UserDto.SignupRes result = userService.signup(dto);

        // then : 실행 결과
        assertNotNull(result);
        assertEquals(1L, result.getIdx());  // 성공
        assertEquals(dto.getEmail(), result.getEmail());
    }


    // 위의 성공케이스보다 이런 실패 케이스가 더 중요함
    @Test
    void userService_회원가입_실패_중복된_이메일() {
        // given : 주어져야 하는 데이터를 여기서 지정
        given(userRepository.findByEmail(dto.getEmail())).willReturn(Optional.of(user));

        // when : 실제 실행을 테스트할 코드
        // assertThrows : 예외처리 관련된 코드
        BaseException ex = assertThrows(BaseException.class,
                () -> userService.signup(dto));

        // then : 실행 결과
        assertEquals(ex.getStatus().getCode(), SIGNUP_DUPLICATE_EMAIL.getCode());
        assertEquals(ex.getMessage(), SIGNUP_DUPLICATE_EMAIL.getMessage());


    }
}