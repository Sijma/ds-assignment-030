package gr.hua.dit.ds.assignment030.DAO;

import gr.hua.dit.ds.assignment030.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, String>
{
    @Query("SELECT p FROM Users p WHERE CONCAT(p.username, ' ', p.password, ' ', p.enabled, ' ',p.authority) LIKE %?1%")
    public List<Users> search(String keyword);
}
