package SoutenanceBackend.soutenance.controllers;


import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.RoleRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.Security.jwt.JwtUtils;
import SoutenanceBackend.soutenance.request.LoginRequest;
import SoutenanceBackend.soutenance.request.SignupRequest;
import SoutenanceBackend.soutenance.response.JwtResponse;
import SoutenanceBackend.soutenance.response.MessageResponse;
import SoutenanceBackend.soutenance.serviceImpl.EmailConstructor;
import SoutenanceBackend.soutenance.serviceImpl.UserDetailsImpl;
import SoutenanceBackend.soutenance.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8100", maxAge = 3600, allowCredentials = "true")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  private UserService userService;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  JavaMailSender mailSender;

  @Autowired
  EmailConstructor emailConstructor;


  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

//  °°°°°°°°°°°°°°°AFFICHER LES UTILISATEURS°°°°°°°°°°°°°°°°°°°°°°°°

  //  °°°°°°°°°°°°°°°AFFICHER LES UTILISATEURS°°°°°°°°°°°°°°°°°°°°°°°°

  @GetMapping("/listeUser")
  public List<User> read(){
    return userService.Afficher();
  }

  //  °°°°°°°°°°°°°°°MODIFIER UN UTILISATEUR°°°°°°°°°°°°°°°°°°°°°°°°
  @PutMapping("/user/{id}")
  public ResponseEntity<?> updateUser(@Valid @RequestBody User updateUser, @PathVariable("id") Long id) {
    Optional<User> optionalUser = userRepository.findById(id);
    if (!optionalUser.isPresent()) {
      return ResponseEntity.badRequest().body(new MessageResponse("Erreur: Utilisateur non trouvé."));
    }
    User user = optionalUser.get();
    user.setEmail(updateUser.getEmail());
    user.setNom(updateUser.getNom());
    user.setPrenom(updateUser.getPrenom());
    user.setUsername(updateUser.getNumero());
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("Utilisateur modifier avec succès"));
  }
  //=============AFFICHER LA LISTES DES USERS A PARTIR DE LEURS ROLES==============

  @PostMapping("/byRole")
  public Set<User> getUsersByRole(@Param("roles") String roles) {
    if(roles.equals("user")){
      Role role = roleRepository.findByName(ERole.ROLE_USER).get();
     return role.getUsers();
    } else if (roles.equals("admin")) {
      Role role = roleRepository.findByName(ERole.ROLE_ADMIN).get();
      return role.getUsers();
    }else {
      return null;
    }

  }


  /*@Autowired
  EmailSenderService emailSenderService;*/
//°°°°°°°°°°°°°°°°°°°°°°°°S'AUTHENTIFIER°°°°°°°°°°°°°°°°°°°°°°°°°°°°
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getNumeroOrEmail(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    userService.RecuperationDeIdDuUserConnecter(userDetails.getId());
    return ResponseEntity.ok(new JwtResponse(jwt,
                         userDetails.getId(), 
                         userDetails.getUsername(), 
                         userDetails.getEmail(),
                         userDetails.getNom(),
            userDetails.getPrenom(),
                         roles));
  }
  //°°°°°°°°°°°°°°°°°°°°°°°°CREER UN COMPTE°°°°°°°°°°°°°°°°°°°°°°°°°°°°
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByNumero(signUpRequest.getNumero())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Erreur: Ce numero existe déjà!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Erreur: Ce email existe déjà!"));
    }


    // Create new user's account
    if (signUpRequest.getPassword().equals(signUpRequest.getConfirmpassword())){
      User user = new User(signUpRequest.getNumero(),
              signUpRequest.getEmail(),
              encoder.encode(signUpRequest.getPassword()), signUpRequest.getNom(), signUpRequest.getPrenom());

      Set<String> strRoles = signUpRequest.getRole();
      Set<Role> roles = new HashSet<>();

      if (strRoles == null) {
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Erreur: Role is not found."));
        roles.add(userRole);
      } else {
        strRoles.forEach(role -> {
          switch (role) {
            case "admin":
              Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                      .orElseThrow(() -> new RuntimeException("Erreur: Role is not found."));
              roles.add(adminRole);

              break;

            default:
              Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                      .orElseThrow(() -> new RuntimeException("Erreur: Role is not found."));
              roles.add(userRole);
          }
        });
      }

      user.setRoles(roles);
      user.setNom(signUpRequest.getNom());
      user.setPrenom(signUpRequest.getPrenom());
      //user.setUsername(signUpRequest.getUsername());
      user.setNumero(signUpRequest.getNumero());
      user.setConfirmNotification(signUpRequest.getConfirmNotification());
      System.out.println(user.getRoles().contains("cvhbjnklmkjhgbvvvvvvvvvvvvvvvvhhhhhh  "  + roleRepository.findByName(ERole.ROLE_ADMIN)));
      if(user.getRoles().contains(roleRepository.findByName(ERole.ROLE_ADMIN))){
        System.out.println();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        mailSender.send(emailConstructor.constructNewUserEmail(user));
      }else {
        userRepository.save(user);
      }


      return ResponseEntity.ok(new MessageResponse("Utilisateur enregistrer avec succès!"));
    }else {
      return ResponseEntity.badRequest().body(new MessageResponse("Les mots de passe ne sont les pas mêmes "));
    }

  }





  //=============AJOUTER UN ADMIN PAR UN ADMIN===================
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/ajouterUnAdmin/{idUser}")
  public ResponseEntity<?> AjouterAdminParAdmin(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByNumero(signUpRequest.getNumero())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Erreur: Ce numero existe déjà!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Erreur: Ce email existe déjà!"));
    }


    // Create new user's account
    if (signUpRequest.getPassword().equals(signUpRequest.getConfirmpassword())){
      User user = new User(signUpRequest.getNumero(),
              signUpRequest.getEmail(),
              encoder.encode(signUpRequest.getPassword()), signUpRequest.getNom(), signUpRequest.getPrenom());

      Set<String> strRoles = signUpRequest.getRole();
      Set<Role> roles = new HashSet<>();

      if (strRoles == null) {
        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Erreur: Role is not found."));
        roles.add(userRole);
      } else {
        strRoles.forEach(role -> {
          switch (role) {
            case "admin":
              Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                      .orElseThrow(() -> new RuntimeException("Erreur: Role is not found."));
              roles.add(adminRole);

              break;

            default:
              Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                      .orElseThrow(() -> new RuntimeException("Erreur: Role is not found."));
              roles.add(userRole);
          }
        });
      }

      user.setRoles(roles);
      user.setNom(signUpRequest.getNom());
      user.setPrenom(signUpRequest.getPrenom());
      //user.setUsername(signUpRequest.getUsername());
      user.setNumero(signUpRequest.getNumero());
      user.setConfirmNotification(signUpRequest.getConfirmNotification());
      System.out.println(user.getRoles().contains("cvhbjnklmkjhgbvvvvvvvvvvvvvvvvhhhhhh  "  + roleRepository.findByName(ERole.ROLE_ADMIN)));
      if(user.getRoles().contains(roleRepository.findByName(ERole.ROLE_ADMIN))){
        System.out.println();
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        mailSender.send(emailConstructor.constructNewUserEmail(user));
      }else {
        userRepository.save(user);
      }


      return ResponseEntity.ok(new MessageResponse("Utilisateur enregistrer avec succès!"));
    }else {
      return ResponseEntity.badRequest().body(new MessageResponse("Les mots de passe ne sont les pas mêmes "));
    }

  }



  //°°°°°°°°°°°°°°°°°°°°°°°°°REINITIALISER PASSWORD°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°°//

  @PostMapping("/resetPassword/{email}")
  public ResponseEntity<String> resetPassword(@PathVariable("email") String email) {
    User user = userService.findByEmail(email);
    if (user == null) {
      return new ResponseEntity<String>("Email non fourni", HttpStatus.BAD_REQUEST);
    }
    userService.resetPassword(user);
    return new ResponseEntity<String>("Email envoyé!", HttpStatus.OK);
  }

  //::::::::::::::::::::::::::::::::::::::::Changer mot de passe:::::::::::::::::::::::::::::::::::::::::::::::://

  @PostMapping("/changePassword")
  public ResponseEntity<String> changePassword(@RequestBody HashMap<String, String> request) {
    String emailOrNumero = request.get("emailOrNumero");
    User user = userRepository.findByNumero(emailOrNumero);

    if (user == null) {
      return new ResponseEntity<>("Utilisateur non fourni!", HttpStatus.BAD_REQUEST);
    }
    String currentPassword = request.get("currentpassword");
    String newPassword = request.get("newpassword");
    String confirmpassword = request.get("confirmpassword");
    if (!newPassword.equals(confirmpassword)) {
      return new ResponseEntity<>("PasswordNotMatched", HttpStatus.BAD_REQUEST);
    }
    String userPassword = user.getPassword();
    try {
      if (newPassword != null && !newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
        if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
          userService.updateUserPassword(user, newPassword);
        }
      } else {
        return new ResponseEntity<>("IncorrectCurrentPassword", HttpStatus.BAD_REQUEST);
      }
      return new ResponseEntity<>("Mot de passe changé avec succès!", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>("Error Occured: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }




}
