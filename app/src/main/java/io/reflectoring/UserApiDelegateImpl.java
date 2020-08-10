package io.reflectoring;

import io.reflectoring.api.UserApiDelegate;
import io.reflectoring.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserApiDelegateImpl implements UserApiDelegate {

    @Override
    public ResponseEntity<User> getUserByName(String username) {
        User user = new User();

        user.setId(123L);
        user.setFirstName("Edy");
        user.setLastName("Segura");
        user.setUsername("edysegura");
        user.setEmail("edysegura@gmail.com");
        user.setPassword("secret");
        user.setPhone("+123 4567890");
        user.setUserStatus(0);

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<User> createUser(User body) {
        return ResponseEntity.ok(body);
    }
}
