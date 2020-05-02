package com.wipro.productApi.context.user;

import com.wipro.productApi.exception.ObjectNotFoundExpection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    public void setUp() {
        this.userService = new UserService(this.userRepository);
    }

    @Test
    void should_find_user_with_login_wipro() {
        when(this.userRepository.findByLogin("wipro"))
                .thenReturn(Optional.of(new User(1l, "wipro", "123")));
        User user = this.userService.findByLogin("wipro");
        assertNotNull(user);
        assertEquals("wipro", user.getLogin());
    }

    @Test
    void should_throws_object_not_found_exception_with_login_test() {
        when(this.userRepository.findByLogin("test"))
                .thenReturn(Optional.empty());
        Exception exception = assertThrows(ObjectNotFoundExpection.class, () -> {
            this.userService.findByLogin("test");
        });
        assertEquals(
                "Nenhum usuário encontrado para o login: test",
                exception.getMessage());
    }
}