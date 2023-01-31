package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Tache;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.TacheRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.TacheService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class TacheServiceImpl implements TacheService {
    private TacheRepository tacheRepository;
    private UserRepository userRepository;

    public TacheServiceImpl(TacheRepository tacheRepository, UserRepository userRepository) {
        this.tacheRepository = tacheRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Tache creer(Tache tache) {
        //on verifie si l'attribut confirmNotification est a true
        User user = tache.getUser();
        if(user.getConfirmNotification()){
            return tacheRepository.save(tache);
        }else {
            return null;
        }


    }

    @Override
    public List<Tache> lire() {

        return tacheRepository.findAll();
    }

    @Override
    public Tache modifier(Long id, Tache tache) {

        return tacheRepository.findById(id)
                .map(n ->{
                    n.setDate(tache.getDate());
                    n.setTitre(tache.getTitre());


                    return tacheRepository.save(n);
                } ).orElseThrow(() -> new RuntimeException("notification non trouvé"));


    }

    @Override
    public String supprimer(Long id) {

         tacheRepository.deleteById(id);
        return "notification supprimer";
    }

    @Override
    public Tache getDateExit(Date date) {
        return tacheRepository.findByDate(date);
    }
}
