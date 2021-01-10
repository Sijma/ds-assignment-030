package gr.hua.dit.ds.dsassignment030.DAO;

import org.springframework.data.repository.CrudRepository;

import gr.hua.dit.ds.dsassignment030.Entities.Uni_employees;

public interface UserRepository extends CrudRepository<Uni_employees, Integer>
{

}
