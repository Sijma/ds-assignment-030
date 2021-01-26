package gr.hua.dit.ds.dsassignment030.DAO;
import gr.hua.dit.ds.dsassignment030.Entities.professor;
import gr.hua.dit.ds.dsassignment030.Entities.uniEployees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface uniemployees extends JpaRepository<uniEployees, String> {
    @Query("SELECT p FROM uniEployees p WHERE CONCAT(p.personellID,' ',p.username, ' ', p.password, ' ',p.authority) LIKE %?1%")
    public List<uniEployees> search(String keyword);
}
