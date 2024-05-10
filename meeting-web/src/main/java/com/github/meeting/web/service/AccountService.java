package com.github.meeting.web.service;

import com.github.meeting.web.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

/**
 * Description:
 * <p>
 * </p>
 *
 * @author pengpeng
 * @version 1.0
 * @since 2024/5/9
 */
@RequiredArgsConstructor
public class AccountService {



    private final AccountRepository userRepository;



    @GetMapping("/{userId}")
    public Mono<SecurityProperties.User> getUser(@PathVariable Long userId) {
//        return this.userRepository.findById(userId);
        return null;
    }



    @DeleteMapping("/{userId}")
    public Mono<Void> deleteUser(@PathVariable Long userId) {
//        return this.userRepository.deleteById(userId);
        return null;
    }

}