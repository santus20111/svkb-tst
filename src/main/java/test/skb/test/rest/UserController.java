package test.skb.test.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import test.skb.test.models.User;
import test.skb.test.rest.models.UserResponse;
import test.skb.test.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    private Mono<UserResponse> getClient(@PathVariable("id") String id) {
        User user = userService.findById(id);

        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return userService.getUserBonuses(user).map(bonuses -> new UserResponse(user, bonuses));
    }
}
