package rollagain.main.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
public class Orders
{
    @Id
    @SequenceGenerator(
        name = "users_identity",
        sequenceName = "users_identity",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "users_identity"
    )
    @Column(columnDefinition = "serial")
    private Long id;
    private Date date;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Users userOrder;

    @JoinColumn(name = "products_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Products product;

    public Orders() {

    }
    public Orders(final Long id, final Date date, final Users user)
    {
        this.id = id;
        this.date = date;
        this.userOrder = user;
    }

    public Orders(final Date date, final Users userOrder, final Products product)
    {
        this.date = date;
        this.userOrder = userOrder;
        this.product = product;
    }

    public Orders(final Date date, final Users user)
    {
        this.date = date;
        this.userOrder = user;
    }

    public Orders(final Date date)
    {
        this.date = date;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(final Date date)
    {
        this.date = date;
    }

    public Users getUser()
    {
        return userOrder;
    }

    public void setUser(final Users user)
    {
        this.userOrder = user;
    }

    public Users getUserOrder()
    {
        return userOrder;
    }

    public void setUserOrder(final Users userOrder)
    {
        this.userOrder = userOrder;
    }

    public Products getProduct()
    {
        return product;
    }

    public void setProduct(final Products product)
    {
        this.product = product;
    }

    @Override
    public String toString()
    {
        return "Orders{" +
            "id=" + id +
            ", date=" + date +
            ", userOrder=" + userOrder +
            ", product=" + product +
            '}';
    }
}
