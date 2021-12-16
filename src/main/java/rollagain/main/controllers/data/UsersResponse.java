package rollagain.main.controllers.data;

import java.util.Set;


public class UsersResponse
{
    private Long id;
    private String username;
    private String city;
    private String zipcode;
    private Integer phone;
    private String email;
    private Set<ProductsResponse> products;

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

    public Set<ProductsResponse> getProducts()
    {
        return products;
    }

    public void setProducts(final Set<ProductsResponse> products)
    {
        this.products = products;
    }
}
