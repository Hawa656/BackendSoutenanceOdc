package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Tache;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.TacheRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.TacheService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
                    n.setDateAcitivte(tache.getDateAcitivte());
                    n.setTitre(tache.getTitre());
                    n.setHeureNotif(tache.getHeureNotif());
                    return tacheRepository.save(n);
                } ).orElseThrow(() -> new RuntimeException("notification non trouvé"));


    }

    @Override
    public String supprimer(Long id) {

         tacheRepository.deleteById(id);
        return "notification supprimer";
    }

    @Override
    public List<Tache> lireTawheUserConecter(Long idUser) {
        return tacheRepository.ListeTache(idUser);
    }

 /*   @Override
    public Tache getDateExit(LocalDate date) {
        return tacheRepository.findByDateAcitivte(date);
    }*/
}
