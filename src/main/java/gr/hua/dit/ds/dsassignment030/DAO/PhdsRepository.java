package gr.hua.dit.ds.dsassignment030.DAO;


import gr.hua.dit.ds.dsassignment030.Entities.phdCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhdsRepository extends JpaRepository<phdCandidate, String> {
    @Query("SELECT p FROM phdCandidate p WHERE CONCAT(p.id,' ',p.personellID,' ',p.username,' ', p.password, ' ',p.authority,' ',p.fname,' ',p.lname,' ',p.surveilancePoints,' ',p.tsp,' ',p.cp,' ',p.tcp,' ',p.tp,' ',p.ttp) LIKE %?1%")
    public List<phdCandidate> search(String keyword);
}
