package gr.hua.dit.ds.assignment030.Services;

import gr.hua.dit.ds.assignment030.DAO.PhdsRepository;
import gr.hua.dit.ds.assignment030.DAO.ProfessorsRepository;
import gr.hua.dit.ds.assignment030.DAO.UsersRepository;
import gr.hua.dit.ds.assignment030.Entities.Candidates;
import gr.hua.dit.ds.assignment030.Entities.Professors;
import gr.hua.dit.ds.assignment030.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class DataServices
{
    @Autowired
    private PhdsRepository phdRepo;

    @Autowired
    private ProfessorsRepository proRepo;

    @Autowired
    private UsersRepository userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void registerUser(Users users)
    {
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userRepo.save(users);
    }

    public void updateUser(Users user, String username)
    {
        user.setUsername(username);
        userRepo.save(user);
    }

    public void registerProf(Professors prof)
    {
        proRepo.save(prof);
    }

    public void updateProf(Professors prof, String id)
    {
        prof.setPersonellID(id);
        proRepo.save(prof);
    }

    public void registerCan(Candidates can)
    {
        phdRepo.save(can);
    }

    public void updateCan(Candidates can, String id)
    {
        can.setCandidateId(id);
        phdRepo.save(can);
    }

    public boolean checkIfUserExist(String username) {
        return userRepo.findById(username).isPresent();
    }

    public List<Users> listAllUsers(String keyword) {
        if (keyword != null) {
            return userRepo.search(keyword);
        }
        return (List<Users>) userRepo.findAll();
    }

    public List<Candidates> listAllCandidates(String keyword) {
        if (keyword != null) {
            return phdRepo.search(keyword);
        }
        return (List<Candidates>) phdRepo.findAll();
    }

    public boolean superIdExists(String id)
    {
        boolean result;
        try
        {
            result = proRepo.existsById(id);
            return result;
        }
        catch (IllegalArgumentException e)
        {
            return false;
        }
    }

    public void assignUser(String username)
    {
        String authority = getUser(username).getAuthority();
    }

    public Users getUser(String username) {
        return userRepo.findById(username).get();
    }

    public void deleteUser(String username) {
        userRepo.deleteById(username);
    }

    public Candidates getCandidate(String id) {
        return phdRepo.findById(id).get();
    }

    public void deleteCandidate(String id) {
        phdRepo.deleteById(id);
    }

    public Professors getProfessor(String id) { return proRepo.findById(id).get(); }

    public void deleteProfessor(String id) {
        proRepo.deleteById(id);
    }

}
