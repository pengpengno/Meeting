package com.github.meeting.web.repository;

import com.github.meeting.web.model.Account;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.security.core.userdetails.UserDetails;
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
public interface AccountRepository extends R2dbcRepository<Account, Long> {



    public Mono<UserDetails> findByUsername(String username);


}