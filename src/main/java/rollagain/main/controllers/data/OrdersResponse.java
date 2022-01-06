package rollagain.main.controllers.data;

import java.time.LocalDate;
import java.util.Date;


public class OrdersResponse
{
    private Long id;
    private Date date;
    private UsersResponse user;
    private UsersResponse userSeller;
    private ProductsResponse products;

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

    public UsersResponse getUser()
    {
        return user;
    }

    public void setUser(final UsersResponse user)
    {
        this.user = user;
    }

    public ProductsResponse getProducts()
    {
        return products;
    }

    public void setProducts(final ProductsResponse products)
    {
        this.products = products;
    }

    public UsersResponse getUserSeller()
    {
        return userSeller;
    }

    public void setUserSeller(final UsersResponse userSeller)
    {
        this.userSeller = userSeller;
    }
}
