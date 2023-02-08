package SoutenanceBackend.soutenance;

import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.TypeLegumeFruitRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// Importing required classes
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

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

	/*@Bean
	PasswordEncoder passwordEncoder(){
		return 	new BCryptPasswordEncoder();
	}*/

	/*@Bean
	CommandLineRunner start(UserService utilisateurService){
		return args -> {
//			utilisateurService.AjouterRole(new Role(null,"user"));
//			utilisateurService.AjouterRole(new Role(null,"Admin"));

			User user = utilisateurService.Ajouter(new User( "Coulibaly", "hawa", "83014690", "hawacoulibaly656@gmail.com", "12345678", new ArrayList<User>(use)));

			//User user = utilisateurService.Ajouter(new User("Coulibaly", "hawa", "83014690", "hawacoulibaly656@gmail.com", "12345678", "1", new ArrayList<>()));
			utilisateurService.AjouterUnRoleAunUtilisateur("hawa","ROLE_ADMIN");






		};
	}*/
	@Override
	public void run(String... args) throws Exception {
		if (roleRepository.findAll().size() == 0){
			roleRepository.createRole();
			typeLegumeFruitRepository.creerTypeLegumeFruit();
			userRepository.createAdminParDefaut();
		}

	}


}
