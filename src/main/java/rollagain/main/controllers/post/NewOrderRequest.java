package rollagain.main.controllers.post;

import java.util.Date;


public class NewOrderRequest
{
    Long userId;
    Long productId;
    Date date;

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

    public Date getDate()
    {
        return date;
    }

    public void setDate(final Date date)
    {
        this.date = date;
    }

    @Override
    public String toString()
    {
        return "NewOrderRequest{" +
            "userId=" + userId +
            ", productId=" + productId +
            ", date=" + date +
            '}';
    }
}
