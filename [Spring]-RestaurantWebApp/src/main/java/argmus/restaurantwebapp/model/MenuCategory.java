package argmus.restaurantwebapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Integer id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuCategory)) return false;

        MenuCategory category = (MenuCategory) o;

        if (isActive() != category.isActive()) return false;
        if (!getId().equals(category.getId())) return false;
        if (!getName().equals(category.getName())) return false;
        return getIcon() != null ? getIcon().equals(category.getIcon()) : category.getIcon() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + (getIcon() != null ? getIcon().hashCode() : 0);
        result = 31 * result + (isActive() ? 1 : 0);
        return result;
    }
}