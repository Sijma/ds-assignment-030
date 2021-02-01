package gr.hua.dit.ds.assignment030.DAO;

import gr.hua.dit.ds.assignment030.Entities.Professors;
import gr.hua.dit.ds.assignment030.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessorsRepository extends JpaRepository<Professors, String>
{
    @Query("SELECT k FROM Professors k WHERE CONCAT(k.personellID, ' ', k.user.username, ' ', k.fname, ' ',k.lname) LIKE %?1%")
    public List<Professors> search(String keyword);
}

