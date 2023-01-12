package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    public User Hawani;

    @Autowired
    private UserRepository userRepository;
    @Override
    public String Supprimer(Long id_users) {
        return null;
    }

    @Override
    public String Modifier(User users) {
        return null;
    }

    @Override
    public List<User> Afficher() {
        return null;
    }

    @Override
    public User Ajouter(User utilisateur) {
        return null;
    }

    @Override
    public User HawaMethode(Long id_user) {
        Hawani = userRepository.getReferenceById(id_user);
        return Hawani;
    }

    @Override
    public User hawa() {
        return Hawani;
    }
}
