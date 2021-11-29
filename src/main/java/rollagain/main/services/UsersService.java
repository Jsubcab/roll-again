package rollagain.main.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rollagain.main.entities.Users;
import rollagain.main.repositories.UserRepository;


@Service
public class UsersService
{
    @Autowired
    private final UserRepository userRepository;

    public UsersService(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public List<Users> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(final Users user)
    {
        Optional<Users> usersOptional = userRepository
            .findUsersByEmail(user.getEmail());

        if (usersOptional.isPresent()) {
            throw new IllegalStateException("ERROR: Email already registered.");
        }
        userRepository.save(user);
    }

    public void deleteUser(final Long userId)
    {
            boolean exists = userRepository.existsById(userId);
            if (!exists) {
                throw new IllegalStateException("user with id " + userId + " does not exists.");
            }
            userRepository.deleteById(userId);
    }
}
