package argmus.restaurantwebapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "otp_temp")
public class OTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private int code;

    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    private Date expiryDateTime;

    public OTP(String phone, int code) {
        this.phone = phone;
        this.code = code;
    }

    @PrePersist
    protected void onCreate() {
        Date now = new Date(System.currentTimeMillis());
        this.expiryDateTime = new Date(now.getTime() + 100_000);
    }
}
