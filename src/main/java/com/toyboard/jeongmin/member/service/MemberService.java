package com.toyboard.jeongmin.member.service;

import com.toyboard.jeongmin.member.domain.Member;
import com.toyboard.jeongmin.member.domain.Password;
import com.toyboard.jeongmin.member.domain.encryptor.EncryptorI;
import com.toyboard.jeongmin.member.dto.*;
import com.toyboard.jeongmin.member.exception.DuplicateEmailException;
import com.toyboard.jeongmin.member.exception.InvalidCredentialsException;
import com.toyboard.jeongmin.member.exception.NotFoundUserIdException;
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
    private final EncryptorI encryptor;


    @Transactional
    public String signUp(MemberRequest memberRequest){

        if(!memberRepository.existsByEmailValue(memberRequest.getEmail())) {
            Member member = Member.builder()
                    .email(memberRequest.getEmail())
                    .password(Password.of(encryptor, memberRequest.getPassword()))
                    .regTime(memberRequest.getRegTime())
                    .build();

            memberRepository.save(member);
            System.out.println(member.getPassword().getValue());
            return "회원가입이 되었습니다!";
        } else {
            throw new DuplicateEmailException();
        }
    }

    public LoginResponse logIn(LoginRequest loginRequest){

        String password = encryptor.encrypt(loginRequest.getPassword());
        System.out.println(password);
        Member member = memberRepository.findByEmailValueAndPasswordValue(loginRequest.getEmail()
                , password).orElseThrow(InvalidCredentialsException::new);

        return jwtTokenProvider.createToken(member.getId(), member.getRoles().getAuthority());
    }

    public UserInfoResponse findUserInfo(Member member) {

        Long memberId = member.getId();

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(NotFoundUserIdException::new);

        return UserInfoResponse.builder()
                .id(memberId)
                .email(findMember.getEmail().getValue())
                .regTime(findMember.getRegTime())
                .build();
    }

}
