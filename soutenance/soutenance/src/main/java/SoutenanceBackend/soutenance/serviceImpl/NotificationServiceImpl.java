package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.Notification;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.NotificationRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private UserRepository userRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Notification creer(Notification notification) {
        //on verifie si l'attribut confirmNotification est a true
        User user = notification.getUser();
        if(user.getConfirmNotification()){
            return notificationRepository.save(notification);
        }else {
            return null;
        }


    }

    @Override
    public List<Notification> lire() {

        return notificationRepository.findAll();
    }

    @Override
    public Notification modifier(Long id, Notification notification) {

        return notificationRepository.findById(id)
                .map(n ->{
                    n.setDate(notification.getDate());
                    n.setTitre(notification.getTitre());


                    return notificationRepository.save(n);
                } ).orElseThrow(() -> new RuntimeException("notification non trouvé"));


    }

    @Override
    public String supprimer(Long id) {

         notificationRepository.deleteById(id);
        return "notification supprimer";
    }
}
