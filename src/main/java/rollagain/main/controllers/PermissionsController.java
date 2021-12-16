package rollagain.main.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.controllers.data.PermissionsResponse;
import rollagain.main.controllers.data.UsersResponse;
import rollagain.main.entities.Permissions;
import rollagain.main.entities.Users;
import rollagain.main.services.PermissionsService;


@RestController
@RequestMapping(path = "api/permissions")
public class PermissionsController
{
    private final PermissionsService permissionsService;

    @Autowired
    public PermissionsController(final PermissionsService permissionsService)
    {
        this.permissionsService = permissionsService;
    }

    //PERMISSIONS
    @GetMapping
    public List<PermissionsResponse> getPermissions() {

        final List<Permissions> permissionsList = permissionsService.getPermissions();
        if (CollectionUtils.isEmpty(permissionsList)) {
            return Collections.emptyList();
        }

        List<PermissionsResponse> response = new ArrayList<>();
        for (Permissions p : permissionsList) {
            PermissionsResponse newPermissions = createPermissionsResponse(p);
            response.add(newPermissions);
        }
        return response;

    }

    private PermissionsResponse createPermissionsResponse(final Permissions p)
    {
        PermissionsResponse newPermission = new PermissionsResponse();
        newPermission.setId(p.getId());
        newPermission.setLevel(p.getLevel());

        if (p.getUsers() != null) {
            final Set<UsersResponse> productsResponses = p.getUsers().stream()
                .map(user ->  createUsersResponse(user))
                .collect(Collectors.toSet());
            newPermission.setUsers(productsResponses);
        }
        return newPermission;
    }

    private UsersResponse createUsersResponse(final Users u)
    {
        UsersResponse usersResponse = new UsersResponse();
        usersResponse.setId(u.getId());
        usersResponse.setUsername(u.getUsername());
        usersResponse.setEmail(u.getEmail());
        usersResponse.setCity(u.getCity());
        usersResponse.setZipcode(u.getZipcode());
        usersResponse.setPhone(u.getPhone());

        return usersResponse;
    }

    @PostMapping
    public void registerNewPermission(@RequestBody Permissions permission) {
        permissionsService.addNewPermission(permission);
    }

    @DeleteMapping(path = "{permissionId}")
    public void deleteRateById(@PathVariable("permissionId") Long permissionId) {
        permissionsService.deletePermission(permissionId);
    }

}
