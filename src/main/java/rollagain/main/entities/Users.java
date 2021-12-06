package rollagain.main.entities;

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
public class Users
{
    @Id
    @SequenceGenerator(
        name = "users_identity",
        sequenceName = "users_identity",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "users_identity"
    )
    @Column(columnDefinition = "serial")
    private Long id;
    private String username;
    private String password;
    private String city;
    private String zipcode;
    private Integer phone;
    private String email;
/*    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Permissions permission;*/

    public Users() {

    }

    public Users(final Long id,
                 final String username,
                 final String password,
                 final String city,
                 final String zipcode,
                 final Integer phone,
                 final String email)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.city = city;
        this.zipcode = zipcode;
        this.phone = phone;
        this.email = email;
    }

    public Users(final String username,
                 final String password,
                 final String city,
                 final String zipcode,
                 final Integer phone,
                 final String email)
    {
        this.username = username;
        this.password = password;
        this.city = city;
        this.zipcode = zipcode;
        this.phone = phone;
        this.email = email;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(final String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(final String password)
    {
        this.password = password;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(final String city)
    {
        this.city = city;
    }

    public String getZipcode()
    {
        return zipcode;
    }

    public void setZipcode(final String zipcode)
    {
        this.zipcode = zipcode;
    }

    public Integer getPhone()
    {
        return phone;
    }

    public void setPhone(final Integer phone)
    {
        this.phone = phone;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }

/*    public Permissions getPermission()
    {
        return permission;
    }

    public void setPermission(final Permissions permission)
    {
        this.permission = permission;
    }*/

    @Override
    public String toString()
    {
        return "Users{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", city='" + city + '\'' +
            ", zipcode='" + zipcode + '\'' +
            ", phone=" + phone +
            ", email='" + email + '\'' +
           // ", permission=" + permission +
            '}';
    }
}
