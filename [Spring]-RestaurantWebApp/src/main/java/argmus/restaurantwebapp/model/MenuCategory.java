package argmus.restaurantwebapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Menu_Categories")
public class MenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String icon;

    @JsonIgnore
    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    private Set<MenuProduct> products;

    public MenuCategory(String name, String icon) {
        this.name = name;
        this.icon = icon;
        this.products = new HashSet<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuCategory)) return false;

        MenuCategory category = (MenuCategory) o;

        if (!id.equals(category.id)) return false;
        if (!name.equals(category.name)) return false;
        if (!icon.equals(category.icon)) return false;
        return products.equals(category.products);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + icon.hashCode();
        result = 31 * result + products.hashCode();
        return result;
    }
}