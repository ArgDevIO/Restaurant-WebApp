package argmus.restaurantwebapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.*;

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
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
    @Transient
    private String confirmPassword;

    @NotBlank(message = "Phone number is required")
    @Column(unique = true)
    private String phone;

    @EqualsAndHashCode.Exclude
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<Address> addresses = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles = new HashSet<>();

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

    // UserDetails - Security
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String[] userRoles = this.roles.stream().map(Role::getName).toArray(String[]::new);
        return AuthorityUtils.createAuthorityList(userRoles);
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return this.email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getFullName() != null ? !getFullName().equals(user.getFullName()) : user.getFullName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getConfirmPassword() != null ? !getConfirmPassword().equals(user.getConfirmPassword()) : user.getConfirmPassword() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(user.getPhone()) : user.getPhone() != null) return false;
        if (getCreated_At() != null ? !getCreated_At().equals(user.getCreated_At()) : user.getCreated_At() != null)
            return false;
        return getUpdated_At() != null ? getUpdated_At().equals(user.getUpdated_At()) : user.getUpdated_At() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getFullName() != null ? getFullName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getConfirmPassword() != null ? getConfirmPassword().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getCreated_At() != null ? getCreated_At().hashCode() : 0);
        result = 31 * result + (getUpdated_At() != null ? getUpdated_At().hashCode() : 0);
        return result;
    }
}
