package rollagain.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rollagain.main.entities.Permissions;
import rollagain.main.repositories.PermissionsRepository;


@Service
public class PermissionsService
{
    @Autowired
    private final PermissionsRepository permissionsRepository;

    public PermissionsService(final PermissionsRepository permissionsRepository)
    {
        this.permissionsRepository = permissionsRepository;
    }

    public Permissions getPermissionsByLevel(String level)
    {
        return permissionsRepository.findPermissionsByLevel(level);
    }

    public List<Permissions> getPermissions()
    {
        return permissionsRepository.findAll();
    }

    public void addNewPermission(final Permissions permission)
    {
        permissionsRepository.save(permission);
    }

    public void deletePermission(final Long permissionId)
    {
        boolean exists = permissionsRepository.existsById(permissionId);
        if (!exists) {
            throw new IllegalStateException("Permission with id " + permissionId + " does not exists.");
        }
        permissionsRepository.deleteById(permissionId);
    }
}
