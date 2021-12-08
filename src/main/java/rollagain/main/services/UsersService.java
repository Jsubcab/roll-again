package rollagain.main.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import rollagain.main.entities.Categories;
import rollagain.main.entities.Permissions;
import rollagain.main.entities.Products;
import rollagain.main.entities.Rates;
import rollagain.main.entities.Users;
import rollagain.main.repositories.PermissionsRepository;
import rollagain.main.repositories.RatesRepository;
import rollagain.main.repositories.UserRepository;
import rollagain.main.services.enums.EnumPermissions;
import rollagain.main.services.enums.EnumStateProducts;


@Service
public class UsersService
{
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RatesRepository ratesRepository;

    @Autowired
    private final PermissionsRepository permissionsRepository;

    public UsersService(final UserRepository userRepository,
                        final RatesRepository ratesRepository, final PermissionsRepository permissionsRepository)
    {
        this.userRepository = userRepository;
        this.ratesRepository = ratesRepository;
        this.permissionsRepository = permissionsRepository;
    }

    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    public Users getUserById(Long userId) {
        Users user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exist."));
        return user;
    }

    public void addNewUser(final Users user)
    {
        Users usersOptional = userRepository
            .findUsersByEmail(user.getEmail());

        if (usersOptional.getEmail().equals(user.getEmail()))
        {
            throw new IllegalStateException("ERROR: Email already registered.");
        }

            Permissions permissionUser = permissionsRepository.findByLevel(EnumPermissions.USER.toString().toLowerCase());
            userRepository.save(createNewUser(user, permissionUser));

/*        Permissions permissions = permissionsRepository.findByLevel(user.getPermission().getLevel().toLowerCase());

       if (permissions != null) {
           userRepository.save(createNewUser(user, permissions));
        }*/
    }

    private Users createNewUser(Users users, Permissions permissions) {
        Users newUser = new Users();
        newUser.setUsername(users.getUsername());
        newUser.setPassword(users.getPassword());
        newUser.setCity(users.getCity());
        newUser.setZipcode(users.getZipcode());
        newUser.setPhone(users.getPhone());
        newUser.setEmail(users.getEmail());
        newUser.setPermission(permissions);

        return newUser;
    }

    public void deleteUser(final Long userId)
    {
            boolean exists = userRepository.existsById(userId);
            if (!exists) {
                throw new IllegalStateException("user with id " + userId + " does not exists.");
            }
            userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId,
                           final Users newUserData) {
       Users user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalStateException(
                "user with id " + userId + " does not exist."));

        if (newUserData.getUsername() != null && newUserData.getUsername().length() > 0 && !Objects.equals(user.getUsername(),
            newUserData.getUsername())) {
            user.setUsername(newUserData.getUsername());
        }

        if (newUserData.getEmail() != null && newUserData.getEmail().length() > 0 && !Objects.equals(user.getEmail(),
            newUserData.getEmail())) {
            Users userOptional = userRepository.findUsersByEmail(newUserData.getEmail());
            if (userOptional.getEmail().equals(user.getEmail()))
            {
                throw new IllegalStateException("email taken.");
            }
            user.setEmail(newUserData.getEmail());
        }

        if (newUserData.getCity() != null && newUserData.getCity().length() > 0 && !Objects.equals(user.getCity(),
            newUserData.getCity())) {
            user.setCity(newUserData.getCity());
        }

        if (newUserData.getPassword() != null && newUserData.getPassword().length() > 0 && !Objects.equals(user.getPassword(),
            newUserData.getPassword())) {
            user.setPassword(newUserData.getPassword());
        }

        if (newUserData.getPhone() != null && newUserData.getPhone().toString().length() > 0 && !Objects.equals(user.getPhone(),
            newUserData.getPhone())) {
            user.setPhone(newUserData.getPhone());
        }

        if (newUserData.getZipcode() != null && newUserData.getZipcode().length() > 0 && !Objects.equals(user.getZipcode(),
            newUserData.getZipcode())) {
            user.setZipcode(newUserData.getZipcode());
        }

        if (newUserData.getZipcode() != null && newUserData.getZipcode().length() > 0 && !Objects.equals(user.getZipcode(),
            newUserData.getZipcode())) {
            user.setZipcode(newUserData.getZipcode());
        }

/*        if (newUserData.getPermission() != null && !Objects.equals(user.getPermission(),
            newUserData.getPermission())) {
            user.setPermission(newUserData.getPermission());

        } ADDS A NEW LINE IN PERMISSION*/
        userRepository.flush();
    }

    public List<Rates> getRates()
    {
        return ratesRepository.findAll();
    }

    public List<Rates> getRatesById(Long userId)
    {
        return ratesRepository.findRatesById(userId);
    }

    public void addNewRate(final Rates rates, Long userId)
    {
        if (rates.getRating() > 5 || rates.getRating() < 0 || rates.getRating().isNaN()) {
            throw new IllegalStateException("Rates should be between 0 and 5");
        }
        rates.setUser(getUserById(userId));
        ratesRepository.save(rates);
    }

    public void deleteRate(final Long rateId)
    {
        boolean exists = ratesRepository.existsById(rateId);
        if (!exists) {
            throw new IllegalStateException("Rate with id " + rateId + " does not exists.");
        }
        ratesRepository.deleteById(rateId);
    }

    @Transactional
    public void updateRate(final Long rateId,
                           Double newRating,
                           String newComment)
    {
        Rates rate = ratesRepository.findById(rateId)
            .orElseThrow(() -> new IllegalStateException(
                "Rate with id " + rateId + " does not exist."));

        if (newRating != null && newRating > 0 && newRating <= 5 && !Objects.equals(rate.getRating(),
            newRating)) {
            rate.setRating(newRating);
        }

        if (newComment != null && newComment.length() > 0 && !Objects.equals(rate.getComment(),
            newComment)) {
            rate.setComment(newComment);
        }
    }

    public Permissions getPermissionById(Long userId)
    {
        return userRepository.findPermissionById(userId);
    }


}
