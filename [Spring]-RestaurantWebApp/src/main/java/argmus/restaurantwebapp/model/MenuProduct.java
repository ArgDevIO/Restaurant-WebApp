package argmus.restaurantwebapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Entity
@Table(name = "Menu_Products")
public class MenuProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;
    @NotBlank(message = "Product description is required")
    private String description;
    private int price;
    private boolean active;

    @JsonInclude(Include.NON_NULL)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private MenuCategory category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProducts;

    @JsonInclude(Include.NON_NULL)
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss")
    private Date created_At;
    @JsonInclude(Include.NON_NULL)
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
        if (!(o instanceof MenuProduct)) return false;

        MenuProduct product = (MenuProduct) o;

        if (getPrice() != product.getPrice()) return false;
        if (isActive() != product.isActive()) return false;
        if (getId() != null ? !getId().equals(product.getId()) : product.getId() != null) return false;
        if (getName() != null ? !getName().equals(product.getName()) : product.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(product.getDescription()) : product.getDescription() != null)
            return false;
        return getCategory() != null ? getCategory().equals(product.getCategory()) : product.getCategory() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getPrice();
        result = 31 * result + (isActive() ? 1 : 0);
        result = 31 * result + (getCategory() != null ? getCategory().hashCode() : 0);
        return result;
    }
}
