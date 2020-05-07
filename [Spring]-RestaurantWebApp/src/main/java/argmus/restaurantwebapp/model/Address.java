package argmus.restaurantwebapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
