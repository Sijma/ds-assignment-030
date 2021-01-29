package gr.hua.dit.ds.dsassignment030.DAO;


import gr.hua.dit.ds.dsassignment030.Entities.phdCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhdsRepository extends JpaRepository<phdCandidate, String>
{
    @Query("SELECT T FROM phdCandidate T WHERE T.authority='ROLE_CANDIDATE'")
    public List<phdCandidate> search(String keyword);
    List<phdCandidate> findById(int candidateId);
}
