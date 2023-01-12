package SoutenanceBackend.soutenance;

import SoutenanceBackend.soutenance.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoutenanceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SoutenanceApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;


	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.findAll().size() == 0){
			roleRepository.createRole();
		}
	}
}
