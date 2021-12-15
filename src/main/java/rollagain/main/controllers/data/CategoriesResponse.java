package rollagain.main.controllers.data;

public class CategoriesResponse
{
    private Long id;
    private String category;
    private ProductsResponse product;

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(final String category)
    {
        this.category = category;
    }

    public ProductsResponse getProduct()
    {
        return product;
    }

    public void setProduct(final ProductsResponse product)
    {
        this.product = product;
    }
}
