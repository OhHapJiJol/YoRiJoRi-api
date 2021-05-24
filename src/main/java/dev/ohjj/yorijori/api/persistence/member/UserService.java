package dev.ohjj.yorijori.api.persistence.member;

import dev.ohjj.yorijori.api.persistence.member.entity.UserEntity;
import dev.ohjj.yorijori.api.persistence.member.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Long join(UserEntity userEntity) {
        validateDuplicateUserNickname(userEntity);

        System.out.println(userEntity);
        String rawPassword = userEntity.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        userEntity.setPassword(encPassword);
        userEntity.setRole("ROLE_USER");
        System.out.println(userEntity.getRole());

        userRepository.save(userEntity);

        return userEntity.getSeq();
    }

    private void validateDuplicateUserNickname(UserEntity userEntity) {
        List<UserEntity> findUsers = userRepository.findByNickname(userEntity.getNickname());
        if (!findUsers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 닉네임입니다.");
        }
    }

    public List<UserEntity> findUsers() {
        return userRepository.findAll();
    }

    public UserEntity findOne(Long user_seq) {
        return userRepository.findBySeq(user_seq);
    }

}
