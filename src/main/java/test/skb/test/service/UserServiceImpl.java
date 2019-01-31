package test.skb.test.service;

import lombok.Data;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import test.skb.test.models.User;
import test.skb.test.models.Users;

@Service
public class UserServiceImpl implements UserService {

    @Value("${bonsuses_service_base_url}")
    private String bonusServiceBaseUrl;

    @Override
    public Mono<User> findById(String userId) {
        User user = Users.findById(userId);
        if(user == null) {
            return Mono.error(new Exception());
        }
        return Mono.just(user);
    }

    @Override
    public Mono<Integer> getUserBonuses(String id) {
        WebClient.RequestBodyUriSpec requestBodySpecUriSpec = WebClient.create(bonusServiceBaseUrl + "/v1/bonuses/" + id).method(HttpMethod.GET);
        return requestBodySpecUriSpec.retrieve().bodyToMono(BonusesResponse.class).map(BonusesResponse::getAmount).onErrorResume(error -> {
            LoggerFactory.getLogger(UserServiceImpl.class).error(error.getMessage());
            return Mono.just(0);
        });
    }

    @Data
    private static class BonusesResponse {
        private Integer amount;
    }
}
