package gr.hua.dit.ds.dsassignment030.Services;

import gr.hua.dit.ds.dsassignment030.DAO.uniemployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class SecretaryService {

    @Autowired
    private uniemployeesRepository repo;

}
