package gr.hua.dit.ds.assignment030.DAO;

import gr.hua.dit.ds.assignment030.Entities.Candidates;
import gr.hua.dit.ds.assignment030.Entities.Professors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhdsRepository extends JpaRepository<Candidates, String>
{
    @Query("SELECT t FROM Candidates t WHERE CONCAT(t.candidateId, ' ', t.professor.personellID, ' ', t.user.username, ' ',t.fname, ' ', t.lname, ' '," +
            " t.sp, ' ', t.tsp, ' ', t.cp, ' ', t.tcp, ' ', t.tp, ' ', t.ttp ) LIKE %?1%")
    public List<Candidates> search(String keyword);
}