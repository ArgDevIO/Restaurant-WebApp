package argmus.restaurantwebapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Adresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Address name is required")
    private String name;
    @NotBlank(message = "Street address is required")
    private String street;
    private int number;
    private int entry;
    private int apartment;
    private String interphoneCode;
    private String city;
    private String village;
    private String coordinates;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    private Date created_At;
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    private Date updated_At;

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }
}
