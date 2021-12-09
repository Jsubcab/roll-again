package rollagain.main.controllers.post;

public class NewOrderRequest
{
    Long userId;
    Long productId;

    public NewOrderRequest()
    {

    }

    public NewOrderRequest(final Long userId, final Long productId)
    {
        this.userId = userId;
        this.productId = productId;
    }

    public NewOrderRequest(final Long productId)
    {
        this.productId = productId;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(final Long userId)
    {
        this.userId = userId;
    }

    public Long getProductId()
    {
        return productId;
    }

    public void setProductId(final Long productId)
    {
        this.productId = productId;
    }

    @Override
    public String toString()
    {
        return "NewOrderRequest{" +
            "userId=" + userId +
            ", productId=" + productId +
            '}';
    }
}
