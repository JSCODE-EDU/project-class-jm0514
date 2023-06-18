package com.toyboard.jeongmin.member.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime;

    @Enumerated(EnumType.STRING)
    private Roles roles;

    @Builder
    public Member(Long id, String email, String password, LocalDateTime regTime) {
        this.id = id;
        this.email = new Email(email);
        this.password = new Password(password);
        this.regTime = LocalDateTime.now();
        this.roles = Roles.USER;
    }

}
