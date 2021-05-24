package dev.ohjj.yorijori.api.persistence.member.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private Long seq;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="nickname", unique = true)
    private String nickname;

    @Column(name="phone", nullable = false)
    private String phone;

    @Column(name="status", nullable = false)
    private byte status;

    @Column(nullable = false)
    // @Enumerated(EnumType.STRING)
    private String role;

    @CreationTimestamp
    private Timestamp REG_DATE;

    @UpdateTimestamp
    private Timestamp MOD_DATE;

}
