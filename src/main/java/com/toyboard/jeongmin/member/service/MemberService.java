package com.toyboard.jeongmin.member.service;

import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.member.dto.LoginRequest;
import com.toyboard.jeongmin.member.dto.LoginResponse;
import com.toyboard.jeongmin.member.dto.MemberRequest;
import com.toyboard.jeongmin.member.dto.UserInfoResponse;
import com.toyboard.jeongmin.member.exception.DuplicateEmailException;
import com.toyboard.jeongmin.member.exception.InvalidCredentialsException;
import com.toyboard.jeongmin.member.exception.NotFoundEmailException;
import com.toyboard.jeongmin.member.jwt.JwtTokenProvider;
import com.toyboard.jeongmin.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public String signUp(MemberRequest memberRequest){

        if(!memberRepository.existsByEmailValue(memberRequest.getEmail())) {
            Member member = Member.builder()
                    .email(memberRequest.getEmail())
                    .password(memberRequest.getPassword())
                    .regTime(memberRequest.getRegTime())
                    .build();

            memberRepository.save(member);
            return "회원가입이 되었습니다!";
        } else {
            throw new DuplicateEmailException();
        }
    }

    public LoginResponse logIn(LoginRequest loginRequest){

        Member member = memberRepository.findByEmailValueAndPasswordValue(loginRequest.getEmail(),
                loginRequest.getPassword()).orElseThrow(InvalidCredentialsException::new);

        return jwtTokenProvider.createToken(member.getId(), member.getRoles().getAuthority());
    }

    public UserInfoResponse findUserInfo(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NotFoundEmailException::new);

        return UserInfoResponse.builder()
                .id(member.getId())
                .email(member.getEmail().getValue())
                .regTime(member.getRegTime())
                .build();
    }

}
