package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public User RecuperationId ;

    @Autowired
    private UserRepository userRepository;

    private EmailConstructor emailConstructor;
    private JavaMailSender javaMailSender;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;
    private ERole name;

    public UserServiceImpl(UserRepository userRepository, EmailConstructor emailConstructor, JavaMailSender javaMailSender, BCryptPasswordEncoder bCryptPasswordEncoder, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailConstructor = emailConstructor;
        this.javaMailSender = javaMailSender;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String Supprimer(Long id_users) {
        return null;
    }

    @Override
    public String Modifier(Long id,User users) {
        return null;
    }

    @Override
    public List<User> Afficher() {
        return userRepository.findAll();
    }

    @Override
    public User Ajouter(User utilisateur) {


        return userRepository.save(utilisateur);
    }

  /*  @Override
    public void AjouterUnRoleAunUtilisateur(String nomUtilisateur, String nomRole) {
        User utilisateurs=userRepository.findByNomUtilisateur(nomUtilisateur);
        Role role=roleRepository.findByNomRole(nomRole);
        utilisateurs.getRoles().add(role);
    }

    @Override
    public User RetournerUnUtilisateurParSonNom(String nomUtilisateur) {
        return userRepository.findByNomUtilisateur(nomUtilisateur);
    }

    @Override
    public Role AjouterRole(Role role) {
        return roleRepository.save(role);
    }
*/
    @Override
    public User RecuperationDeIdDuUserConnecter(Long id_user) {
        RecuperationId  = userRepository.getReferenceById(id_user);
        return RecuperationId ;
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail);
    }

    @Override
    public void resetPassword(User user) {
        String password = RandomStringUtils.randomAlphanumeric(4);
        String passwordCrypte = bCryptPasswordEncoder.encode(password);
        user.setPassword(passwordCrypte);
        userRepository.save(user);
        javaMailSender.send(emailConstructor.constructResetPasswordEmail(user,password));

    }

    /*    @Override
    public User findByRoles(String roles) {
        return userRepository.findByRoleName(roles);
    }

     */

    @Override
    public void updateUserPassword(User user, String newPassword) {

    }

    @Override
    public User hawa() {
        return RecuperationId ;
    }
}
