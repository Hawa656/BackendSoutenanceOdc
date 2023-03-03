package SoutenanceBackend.soutenance.services;

import SoutenanceBackend.soutenance.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    String Supprimer(Long id_users);  // LA METHODE PERMETTANT DE SUPPRIMER UN UTILISATEUR

    Optional<User> findUserbyId(Long id);
    String Modifier(Long id,User users);   // LA METHODE PERMETTANT DE MODIFIER UN UTILISATEUR

    List<User> Afficher();       // LA METHODE PERMETTANT D'AFFICHER UN UTILISATEUR

    User Ajouter(User utilisateur); // LA METHODE PERMETTANT D'AJOUTER UN UTILISATEUR
    /*void AjouterUnRoleAunUtilisateur(String nomUtilisateur, String nomRole);

    User RetournerUnUtilisateurParSonNom(String nomUtilisateur);

    Role AjouterRole(Role role);*/

    User RecuperationDeIdDuUserConnecter(Long id_user);
//    Recuperer par email
    User getByEmail(String email);
//    retrouver par email
    User findByEmail(String userEmail);
    //Renitialiser le mot de passe
     void resetPassword(User user);
//    List<User> findByRole(String roleName);
    /*User findByRoles(String roles);*/

    public void updateUserPassword(User user, String newPassword);

    User hawa();
}
