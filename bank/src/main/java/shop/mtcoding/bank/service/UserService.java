package shop.mtcoding.bank.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.dto.user.JoinReqDto;
import shop.mtcoding.bank.dto.user.JoinRespDto;
import shop.mtcoding.bank.handler.ex.CustomApiException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public JoinRespDto 회원가입(JoinReqDto joinReqDto) {

        // 1. 동일 유저네임 존재 검사
        Optional<User> userOp = userRepository.findByUsername(joinReqDto.getUsername());

        if (userOp.isPresent()) {
            // 유저네임 중복
            throw new CustomApiException("동일한 username이 존재합니다.");
        }

        // 2. 패스워드 인코딩 + 회원가입
        User userPS = userRepository.save(joinReqDto.toEntity(passwordEncoder));

        // 3. DTO 응답
        return new JoinRespDto(userPS);
    }
}
