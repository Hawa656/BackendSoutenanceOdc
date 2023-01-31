package SoutenanceBackend.soutenance;

import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.TypeLegumeFruitRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Importing required classes
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

// Annotation
//L'annotation @EnableScheduling facilite Spring Boot avec la capacité d'exécution de tâches planifiées.
@EnableScheduling

@SpringBootApplication
public class SoutenanceApplication implements CommandLineRunner {

	public SoutenanceApplication(RoleRepository roleRepository, TypeLegumeFruitRepository typeLegumeFruitRepository) {
		this.roleRepository = roleRepository;
		this.typeLegumeFruitRepository = typeLegumeFruitRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SoutenanceApplication.class, args);
	}

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;
	@Autowired
	private TypeLegumeFruitRepository typeLegumeFruitRepository;

	@Autowired
	private UserRepository userRepository;


	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.findAll().size() == 0){
			roleRepository.createRole();
			typeLegumeFruitRepository.creerTypeLegumeFruit();
			userRepository.createAdminParDefaut();
		}

	}


}
