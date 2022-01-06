package rollagain.main.controllers.data;

import java.util.Date;

public class RatesResponse
{
    private Long id;
    private Double rating;
    private Date date;
    private String comment;
    private UsersResponse user;

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public Double getRating()
    {
        return rating;
    }

    public void setRating(final Double rating)
    {
        this.rating = rating;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(final String comment)
    {
        this.comment = comment;
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
}
