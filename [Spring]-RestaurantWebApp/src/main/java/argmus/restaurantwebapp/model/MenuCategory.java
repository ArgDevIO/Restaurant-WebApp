package argmus.restaurantwebapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "Menu_Categories")
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Category name is required")
    private String name;
    private String icon;
    private boolean active;

    @JsonIgnore
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<MenuProduct> products;

    public MenuCategory(String name, String icon, boolean active) {
        this.name = name;
        this.icon = icon;
        this.active = active;
        this.products = new HashSet<>();
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuCategory)) return false;

        MenuCategory category = (MenuCategory) o;

        if (isActive() != category.isActive()) return false;
        if (getId() != null ? !getId().equals(category.getId()) : category.getId() != null) return false;
        if (getName() != null ? !getName().equals(category.getName()) : category.getName() != null) return false;
        return getIcon() != null ? getIcon().equals(category.getIcon()) : category.getIcon() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getIcon() != null ? getIcon().hashCode() : 0);
        result = 31 * result + (isActive() ? 1 : 0);
        return result;
    }
}