package rollagain.main.controllers.data;

import java.util.Set;


public class PermissionsResponse
{
    private Long id;
    private String level;
    private Set<UsersResponse> users;

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

    public Set<UsersResponse> getUsers()
    {
        return users;
    }

    public void setUsers(final Set<UsersResponse> users)
    {
        this.users = users;
    }
}
