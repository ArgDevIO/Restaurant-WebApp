package argmus.restaurantwebapp.repository;

import argmus.restaurantwebapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByEmail(String email);
    boolean existsUserByPhone(String phone);
    User findUserByEmail(String email);
}
