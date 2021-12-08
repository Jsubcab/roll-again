package rollagain.main.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Permissions
{
    @Id
    @SequenceGenerator(
        name = "permissions_identity",
        sequenceName = "permissions_identity",
        allocationSize = 1
    )

    @GeneratedValue(
        strategy = GenerationType.IDENTITY,
        generator = "permissions_identity"
    )
    @Column(columnDefinition = "serial")
    private Long id;
    private String level;
    @OneToMany(mappedBy = "permission", orphanRemoval = true, cascade= CascadeType.ALL)
    private Set<Users> users = new HashSet<>();

    public Permissions() {

    }
    public Permissions(final Long id, String level)
    {
        this.id = id;
        this.level = level;
    }

    public Permissions(final String level)
    {
        this.level = level;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getLevel()
    {
        return level;
    }

    public void setLevel(final String level)
    {
        this.level = level;
    }

    public Set<Users> getUsers()
    {
        return users;
    }
    {
        this.users = users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;

        for(Users u : users) {
            u.setPermission(this);
        }
    }

    @Override
    public String toString()
    {
        return "Permissions{" +
            "id=" + id +
            ", level='" + level + '\'' +
            '}';
    }
}
