package gr.hua.dit.ds.dsassignment030.DAO;

import gr.hua.dit.ds.dsassignment030.Entities.User;
import gr.hua.dit.ds.dsassignment030.Entities.phdCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsersRepository extends JpaRepository<phdCandidate, String>
{
    @Query("SELECT p FROM User p WHERE CONCAT(p.username, ' ', p.password, ' ', p.enabled, ' ',p.authority) LIKE %?1%")
    public List<User> search(String keyword);
}
