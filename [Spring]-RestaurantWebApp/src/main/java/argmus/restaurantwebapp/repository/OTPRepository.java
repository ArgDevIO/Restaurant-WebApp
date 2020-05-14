package argmus.restaurantwebapp.repository;

import argmus.restaurantwebapp.model.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OTPRepository extends JpaRepository<OTP, Long> {
    OTP findOTPByPhone(String phone);
}
