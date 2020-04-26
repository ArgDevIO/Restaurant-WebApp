package argmus.restaurantwebapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "Menu_Products")
public class MenuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private int price;
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private MenuCategory category;

    public MenuProduct(String name, String description, int price, boolean active, MenuCategory category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuProduct)) return false;

        MenuProduct product = (MenuProduct) o;

        if (getPrice() != product.getPrice()) return false;
        if (isActive() != product.isActive()) return false;
        if (!getId().equals(product.getId())) return false;
        if (!getName().equals(product.getName())) return false;
        if (!getDescription().equals(product.getDescription())) return false;
        return getCategory().equals(product.getCategory());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getDescription().hashCode();
        result = 31 * result + getPrice();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + getCategory().hashCode();
        return result;
    }
}
