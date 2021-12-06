package rollagain.main.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table
public class Products
{
    @Id
    @SequenceGenerator(
        name = "products_identity",
        sequenceName = "products_identity",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "products_identity"
    )
    @Column(columnDefinition = "serial")
    private Long id;
    private String name;
    private String description;
    private String picture;
    private String state;
/*    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Users users;*/
    //@ManyToOne(targetEntity = Categories.class, cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="category_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Categories category;

    public Products() {

    }

    public Products(final Long id,
                    final String name,
                    final String description,
                    final String picture,
                    final String state)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.state = state;
    }

    public Products(final String name,
                    final String description,
                    final String picture,
                    final String state)
    {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.state = state;
    }

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

    public String getState()
    {
        return state;
    }

    public void setState(final String state)
    {
        this.state = state;
    }

/*    public Users getUsers()
    {
        return users;
    }

    public void setUsers(final Users users)
    {
        this.users = users;
    }*/

    public Categories getCategory()
    {
        return category;
    }

    public void setCategory(final Categories category)
    {
        this.category = category;
    }

}
