package rollagain.main.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
public class Rates implements Serializable
{
    @Id
    @SequenceGenerator(
        name = "rates_identity",
        sequenceName = "rates_identity",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "rates_identity"
    )
    @Column(columnDefinition = "serial")
    private Long id;
    private Date date;
    private Double rating;
    private String comment;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Users user;

    public Rates(){

    }

    public Rates(final Double rating, final String comment)
    {
        this.rating = rating;
        this.comment = comment;
    }

    public Rates(final Long id,final Double rating, final String comment)
    {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
    }

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

    public Users getUser()
    {
        return user;
    }

    public void setUser(final Users user)
    {
        this.user = user;
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
        return "Rates{" +
            "id=" + id +
            ", date=" + date +
            ", rating=" + rating +
            ", comment='" + comment + '\'' +
            ", user=" + user +
            '}';
    }
}
