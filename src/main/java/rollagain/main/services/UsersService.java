package rollagain.main.services;

import java.util.List;

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
        System.out.println(user);
    }
}
