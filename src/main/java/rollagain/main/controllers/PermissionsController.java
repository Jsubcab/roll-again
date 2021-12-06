package rollagain.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rollagain.main.entities.Permissions;
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
    public List<Permissions> getPermissions() {
        return permissionsService.getPermissions();
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
