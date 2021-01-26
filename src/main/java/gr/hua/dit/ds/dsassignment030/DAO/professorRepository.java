package gr.hua.dit.ds.dsassignment030.DAO;

import gr.hua.dit.ds.dsassignment030.Entities.phdCandidate;
import gr.hua.dit.ds.dsassignment030.Entities.professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface professorRepository extends JpaRepository<professor, String> {
    @Query("SELECT p FROM professor p WHERE CONCAT(p.personellID,' ',p.username, ' ', p.password, ' ',p.authority) LIKE %?1%")
    public List<professor> search(String keyword);
}
