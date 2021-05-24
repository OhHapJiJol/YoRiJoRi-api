
package dev.ohjj.yorijori.api.persistence.member;

import dev.ohjj.yorijori.api.persistence.member.UserService;
import dev.ohjj.yorijori.api.persistence.member.entity.UserEntity;
import dev.ohjj.yorijori.api.persistence.member.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired EntityManager em;


    @Test
    @DisplayName("회원가입")
    void join() throws Exception {
        // given
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail("best0167@naver.com");
        userEntity.setPassword("1234");
        userEntity.setNickname("nika");
        userEntity.setPhone("01011111111");

        // when
        Long saveId = userService.join(userEntity);

        // then
        em.flush();
        assertEquals(userEntity, userRepository.findBySeq(saveId));
    }

    @Test
    @DisplayName("중복닉네임 예외")
    void duplicateNicknameFind() throws Exception {
        // given
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setEmail("kani@naver.com");
        userEntity1.setPassword("1234");
        userEntity1.setNickname("kani");
        userEntity1.setPhone("01022222222");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("kani1@naver.com");
        userEntity2.setPassword("1234");
        userEntity2.setNickname("kani");
        userEntity2.setPhone("01022222223");

        // when
        userService.join(userEntity1);

        // then
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> userService.join(userEntity2));
        assertEquals("이미 존재하는 닉네임입니다.", thrown.getMessage());
    }

}
