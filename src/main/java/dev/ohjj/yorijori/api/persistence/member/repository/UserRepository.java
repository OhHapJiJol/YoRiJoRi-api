package dev.ohjj.yorijori.api.persistence.member.repository;

import dev.ohjj.yorijori.api.persistence.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

    List<UserEntity> findByNickname(String nickname);

    UserEntity findBySeq(Long seq);

}
