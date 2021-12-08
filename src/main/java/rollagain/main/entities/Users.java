package rollagain.main.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table
public class Users implements Serializable
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
    private String username;
    private String password;
    private String city;
    private String zipcode;
    private Integer phone;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="permission_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Permissions permission;

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private Set<Products> products = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade=CascadeType.ALL)
    private Set<Orders> orders = new HashSet<>();

    public Users() {

    }

    public Users(final Long id,
                 final String username,
                 final String password,
                 final String city,
                 final String zipcode,
                 final Integer phone,
                 final String email)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.city = city;
        this.zipcode = zipcode;
        this.phone = phone;
        this.email = email;
    }

    public Users(final String username,
                 final String password,
                 final String city,
                 final String zipcode,
                 final Integer phone,
                 final String email)
    {
        this.username = username;
        this.password = password;
        this.city = city;
        this.zipcode = zipcode;
        this.phone = phone;
        this.email = email;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(final String city)
    {
        this.city = city;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(final String zipcode)
    {
        this.zipcode = zipcode;
    }

    public Integer getPhone()
    {
        return phone;
    }

    public void setPhone(final Integer phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

    public Permissions getPermission()
    {
        return permission;
    }

    public void setPermission(final Permissions permission)
    {
        this.permission = permission;
    }

    public Set<Products> getProducts()
    {
        return products;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;

        for(Products p : products) {
            p.setUsers(this);
        }
    }

    public Set<Orders> getOrders()
    {
        return orders;
    }

    public void setOrders(Set<Orders> orders) {
        this.orders = orders;

        for(Orders o : orders) {
            o.setUser(this);
        }
    }

    @Override
    public String toString()
    {
        return "Users{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", city='" + city + '\'' +
            ", zipcode='" + zipcode + '\'' +
            ", phone=" + phone +
            ", email='" + email + '\'' +
            ", permission=" + permission +
            ", products=" + products +
            '}';
    }
}
