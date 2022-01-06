package rollagain.main.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table
public class Products implements Serializable
{
    @Id
    @SequenceGenerator(
        name = "products_identity",
        sequenceName = "products_identity",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "products_identity"
    )
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
    private String description;
    private String picture;
    private String state;
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="category_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Categories category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="users_id")
    private Users user;

    public Products() {

    }

    public Products(final Long id,
                    final String name,
                    final String description,
                    final String picture,
                    final String state,
                    final Double price)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.state = state;
        this.price = price;
    }

    public Products(final String name,
                    final String description,
                    final String picture,
                    final String state,
                    final Double price)
    {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.state = state;
        this.price = price;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(final String description)
    {
        this.description = description;
    }

    public String getPicture()
    {
        return picture;
    }

    public void setPicture(final String picture)
    {
        this.picture = picture;
    }

    public String getState()
    {
        return state;
    }

    public void setState(final String state)
    {
        this.state = state;
    }

    public Users getUsers()
    {
        return user;
    }

    public void setUsers(final Users users)
    {
        this.user = users;
    }

    public Categories getCategory()
    {
        return category;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(final Double price)
    {
        this.price = price;
    }

    public void setCategory(final Categories category)
    {
        this.category = category;
    }

    @Override
    public String toString()
    {
        return "Products{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", picture='" + picture + '\'' +
            ", state='" + state + '\'' +
            ", price=" + price +
            ", category=" + category +
            ", user=" + user +
            '}';
    }
}
