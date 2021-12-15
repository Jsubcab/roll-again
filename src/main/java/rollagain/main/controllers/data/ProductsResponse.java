package rollagain.main.controllers.data;

public class ProductsResponse
{

    private Long id;
    private String name;
    private String description;
    private String picture;
    private String state;
    private Double price;
    private UsersResponse user;

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

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(final Double price)
    {
        this.price = price;
    }

    public String getState()
    {
        return state;
    }

    public void setState(final String state)
    {
        this.state = state;
    }

    public UsersResponse getUser()
    {
        return user;
    }

    public void setUser(final UsersResponse user)
    {
        this.user = user;
    }
}
