package argmus.restaurantwebapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private String number;
    private String entry;
    private String apartment;
    private String interphoneCode;
    private String city;
    private String village;
    private String coordinates;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToMany(
            mappedBy = "address",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Order> orders = new HashSet<>();

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    private Date created_At;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;

        Address address = (Address) o;

        if (getId() != null ? !getId().equals(address.getId()) : address.getId() != null) return false;
        if (getName() != null ? !getName().equals(address.getName()) : address.getName() != null) return false;
        if (getStreet() != null ? !getStreet().equals(address.getStreet()) : address.getStreet() != null) return false;
        if (getNumber() != null ? !getNumber().equals(address.getNumber()) : address.getNumber() != null) return false;
        if (getEntry() != null ? !getEntry().equals(address.getEntry()) : address.getEntry() != null) return false;
        if (getApartment() != null ? !getApartment().equals(address.getApartment()) : address.getApartment() != null)
            return false;
        if (getInterphoneCode() != null ? !getInterphoneCode().equals(address.getInterphoneCode()) : address.getInterphoneCode() != null)
            return false;
        if (getCity() != null ? !getCity().equals(address.getCity()) : address.getCity() != null) return false;
        if (getVillage() != null ? !getVillage().equals(address.getVillage()) : address.getVillage() != null)
            return false;
        if (getCoordinates() != null ? !getCoordinates().equals(address.getCoordinates()) : address.getCoordinates() != null)
            return false;
        if (getUser() != null ? !getUser().equals(address.getUser()) : address.getUser() != null) return false;
        if (getCreated_At() != null ? !getCreated_At().equals(address.getCreated_At()) : address.getCreated_At() != null)
            return false;
        return getUpdated_At() != null ? getUpdated_At().equals(address.getUpdated_At()) : address.getUpdated_At() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getEntry() != null ? getEntry().hashCode() : 0);
        result = 31 * result + (getApartment() != null ? getApartment().hashCode() : 0);
        result = 31 * result + (getInterphoneCode() != null ? getInterphoneCode().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getVillage() != null ? getVillage().hashCode() : 0);
        result = 31 * result + (getCoordinates() != null ? getCoordinates().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getCreated_At() != null ? getCreated_At().hashCode() : 0);
        result = 31 * result + (getUpdated_At() != null ? getUpdated_At().hashCode() : 0);
        return result;
    }
}
