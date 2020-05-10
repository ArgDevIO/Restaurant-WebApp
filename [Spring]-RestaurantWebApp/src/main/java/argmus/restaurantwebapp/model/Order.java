package argmus.restaurantwebapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "delivery_price")
    private int deliveryPrice;

    @Column(name = "total_price")
    private int totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        if (getDeliveryPrice() != order.getDeliveryPrice()) return false;
        if (getTotalPrice() != order.getTotalPrice()) return false;
        if (getId() != null ? !getId().equals(order.getId()) : order.getId() != null) return false;
        if (getUser() != null ? !getUser().equals(order.getUser()) : order.getUser() != null) return false;
        if (getAddress() != null ? !getAddress().equals(order.getAddress()) : order.getAddress() != null) return false;
        return getPaymentType() != null ? getPaymentType().equals(order.getPaymentType()) : order.getPaymentType() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPaymentType() != null ? getPaymentType().hashCode() : 0);
        result = 31 * result + getDeliveryPrice();
        result = 31 * result + getTotalPrice();
        return result;
    }
}
