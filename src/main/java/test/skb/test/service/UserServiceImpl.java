package test.skb.test.service;

import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import test.skb.test.models.User;
import test.skb.test.models.Users;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User findById(String userId) {
        return Users.findById(userId).orElse(null);
    }

    @Override
    public Mono<Integer> getUserBonuses(User user) {
        WebClient.RequestBodyUriSpec requestBodySpecUriSpec = WebClient.create("/v1/bonuses/" + user.getId()).method(HttpMethod.GET);
        return requestBodySpecUriSpec.retrieve().bodyToMono(BonusesResponse.class).map(BonusesResponse::getAmount).onErrorResume(error -> {
            System.out.println("error: " + error.getMessage());
            return Mono.just(0);
        });
    }

    @Data
    private static class BonusesResponse {
        private Integer amount;
    }
}
