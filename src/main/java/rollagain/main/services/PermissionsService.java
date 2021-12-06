package rollagain.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rollagain.main.entities.Permissions;
import rollagain.main.entities.Rates;
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

    public List<Permissions> getPermissions()
    {
        return permissionsRepository.findAll();
    }

    public void addNewPermission(final Permissions permission)
    {
        Optional<Permissions> permissionsOptional = permissionsRepository
            .findPermissionsByLevel(permission.getLevel());

        if (permissionsOptional.isPresent()) {
            throw new IllegalStateException("ERROR: Email already registered.");
        }
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
