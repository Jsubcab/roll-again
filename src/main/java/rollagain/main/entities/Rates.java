package rollagain.main.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table
public class Rates
{
    @Id
    @SequenceGenerator(
        name = "rates_sequence",
        sequenceName = "rates_sequence",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "rates_sequence"
    )
    private Long id;
    private Long id_user;
    private Double rating;
    private String comment;

    public Rates(){

    }

    public Rates(final Long id_user, final Double rating, final String comment)
    {
        this.id_user = id_user;
        this.rating = rating;
        this.comment = comment;
    }

    public Rates(final Long id, final Long id_user, final Double rating, final String comment)
    {
        this.id = id;
        this.id_user = id_user;
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

    public Long getId_user()
    {
        return id_user;
    }

    public void setId_user(final Long id_user)
    {
        this.id_user = id_user;
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
}
