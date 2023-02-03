package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.User;

import java.util.List;

public interface UserService {
    String Supprimer(Long id_users);  // LA METHODE PERMETTANT DE SUPPRIMER UN UTILISATEUR

    String Modifier(Long id,User users);   // LA METHODE PERMETTANT DE MODIFIER UN UTILISATEUR

    List<User> Afficher();       // LA METHODE PERMETTANT D'AFFICHER UN UTILISATEUR

    User Ajouter(User utilisateur); // LA METHODE PERMETTANT D'AJOUTER UN UTILISATEUR

    User RecuperationDeIdDuUserConnecter(Long id_user);
//    Recuperer par email
    User getByEmail(String email);
//    retrouver par email
    User findByEmail(String userEmail);
    //Renitialiser le mot de passe
     void resetPassword(User user);

    public void updateUserPassword(User user, String newPassword);

    User hawa();
}
