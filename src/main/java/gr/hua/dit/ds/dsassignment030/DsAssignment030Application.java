package gr.hua.dit.ds.dsassignment030;

import gr.hua.dit.ds.dsassignment030.config.MySQL;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DsAssignment030Application {

	public static void main(String[] args)
	{
		MySQL.makeJDBCConnection();
		SpringApplication.run(DsAssignment030Application.class, args);
	}

}