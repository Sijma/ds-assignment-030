package gr.hua.dit.ds.dsassignment030.Services;

import gr.hua.dit.ds.dsassignment030.DAO.UsersRepository;
import gr.hua.dit.ds.dsassignment030.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersService
{
    @Autowired
    private UsersRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final String[] roles = new String[]{"ROLE_CANDIDATE", "ROLE_ADMIN", "ROLE_SECRETARY", "ROLE_PROF", "ROLE_ASSEMBLY"};

    public void register(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthority(roles[Integer.parseInt(user.getAuthority()) - 1]);
         repo.save(user);
    }

    public boolean checkIfUserExist(String username) {
        return repo.findById(username).isPresent();
    }

    public List<User> listAll(String keyword) {
        if (keyword != null) {
            return repo.search(keyword);
        }
        return repo.findAll();
    }

    public User get(String username) {
        return repo.findById(username).get();
    }

    public void delete(String username) {
        repo.deleteById(username);
    }
}
