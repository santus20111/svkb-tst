package test.skb.test.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import test.skb.test.models.User;

public interface UserService {
    Mono<User> findById(String userId);
    Mono<Integer> getUserBonuses(String userId);
}
