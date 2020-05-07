package argmus.restaurantwebapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email")
    @NotBlank(message = "email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
    @Transient
    private String confirmPassword;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<Address> addresses;

    private Date created_At;
    private Date updated_At;

    public User(String fullName, String email, String password, String confirmPassword, String phone, Address address) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phone = phone;
        this.addresses = new HashSet<>();
        this.addresses.add(address);
    }


    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }

    // UserDetails - Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
