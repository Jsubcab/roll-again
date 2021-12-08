package rollagain.main.entities;

import java.io.Serializable;
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
public class Orders implements Serializable
{
    @Id
    @SequenceGenerator(
        name = "orders_identity",
        sequenceName = "orders_identity",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "orders_identity"
    )

    @Column(columnDefinition = "serial")
    private Long id;
    //private Date date;
    @JoinColumn(name = "user_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Users user;

    @JoinColumn(name = "product_id", nullable = false)
    @OneToOne(cascade = CascadeType.ALL)
    private Products product;

    public Orders() {

    }
    public Orders(final Long id, final Date date, final Users user)
    {
        this.id = id;
        //this.date = date;
        this.user = user;
    }

    public Orders(final Date date, final Users userOrder, final Products product)
    {
        //this.date = date;
        this.user = userOrder;
        this.product = product;
    }

    public Orders(final Date date, final Users user)
    {
        //this.date = date;
        this.user = user;
    }

/*    public Orders(final Date date)
    {
        this.date = date;
    }*/

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }
/*
    public Date getDate()
    {
        return date;
    }

    public void setDate(final Date date)
    {
        this.date = date;
    }*/

    public Users getUser()
    {
        return user;
    }

    public void setUser(final Users user)
    {
        this.user = user;
    }

    public Users getUserOrder()
    {
        return user;
    }

    public void setUserOrder(final Users userOrder)
    {
        this.user = userOrder;
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
            //", date=" + date +
            ", userOrder=" + user +
            ", product=" + product +
            '}';
    }
}
