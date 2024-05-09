package com.github.meeting.web.repository;

//import org.springframework.security.core.userdetails.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
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
public interface AccountRepository {


    public Mono<SecurityProperties.User> findById(Long userId) ;


    Mono<Void> deleteById(Long userId);
}