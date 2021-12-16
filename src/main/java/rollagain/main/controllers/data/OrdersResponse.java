package rollagain.main.controllers.data;

import java.time.LocalDate;
import java.util.Date;


public class OrdersResponse
{
    private Long id;
    private LocalDate date;
    private UsersResponse user;
    private ProductsResponse products;

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(final LocalDate date)
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
}
