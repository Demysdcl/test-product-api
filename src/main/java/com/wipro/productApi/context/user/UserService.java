package com.wipro.productApi.context.user;

import com.wipro.productApi.exception.ObjectNotFoundExpection;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByLogin(String login) {
        Optional<User> userOptional = this.userRepository.findByLogin(login);
        return userOptional.orElseThrow(() -> new ObjectNotFoundExpection(
                        String.format("Nenhum usuário encontrado para o login: %s", login)));
    }
}
