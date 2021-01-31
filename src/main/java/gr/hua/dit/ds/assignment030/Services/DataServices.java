package gr.hua.dit.ds.assignment030.Services;

import gr.hua.dit.ds.assignment030.DAO.PhdsRepository;
import gr.hua.dit.ds.assignment030.DAO.UsersRepository;
import gr.hua.dit.ds.assignment030.Entities.Candidates;
import gr.hua.dit.ds.assignment030.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataServices
{
    @Autowired
    private PhdsRepository phdRepo;

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final String[] roles = new String[]{"ROLE_ADMIN", "ROLE_SECRETARY", "ROLE_CANDIDATE", "ROLE_PROF"};

    public void register(Users users)
    {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        users.setAuthority(roles[Integer.parseInt(users.getAuthority()) - 1]);
        userRepo.save(users);
    }

    public boolean checkIfUserExist(String username) {
        return userRepo.findById(username).isPresent();
    }

    public List<Users> listAllUsers(String keyword) {
        if (keyword != null) {
            return userRepo.search(keyword);
        }
        return userRepo.findAll();
    }

    public List<Candidates> listAllCandidates(String keyword) {
        if (keyword != null) {
            return phdRepo.search(keyword);
        }
        return phdRepo.findAll();
    }

    public void assignUser(String username)
    {
        String authority = getUser(username).getAuthority();

    }

    public Users getUser(String username) {
        return userRepo.findById(username).get();
    }

    public Candidates getCandidate(int id) {
        return phdRepo.findById(id).get();
    }

    public void deleteUser(String username) {
        userRepo.deleteById(username);
    }

    public void deleteCandidate(int id) {
        phdRepo.deleteById(id);
    }
}
