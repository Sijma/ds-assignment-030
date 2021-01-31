package gr.hua.dit.ds.assignment030.DAO;

import gr.hua.dit.ds.assignment030.Entities.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhdsRepository extends JpaRepository<Candidates, Integer>
{
    @Query("SELECT t FROM Candidates t WHERE CONCAT(t.candidateId, ' ', t.supervisorId, ' ', t.username, ' ',t.fname, ' ', t.lname, ' '," +
            " t.sp, ' ', t.tsp, ' ', t.cp, ' ', t.tcp, ' ', t.tp, ' ', t.ttp ) LIKE %?1%")
    public List<Candidates> search(String keyword);
}