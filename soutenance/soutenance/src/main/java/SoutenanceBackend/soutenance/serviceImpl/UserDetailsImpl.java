package SoutenanceBackend.soutenance.serviceImpl;

import SoutenanceBackend.soutenance.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String numero;

    private String email;

    @JsonIgnore
    private String password;
    //°°°°°°°°°°°°°a partir de là°°°°°°°°°°°°°°°°°°°°

    private String nom;

    private  String prenom;
    private Boolean confirmNotification;

    private  String username;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String numero, String email, String password,String nom,String prenom,Boolean confirmNotification,
                           Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.numero = numero;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.nom= nom;
        this.prenom= prenom;
        //this.username= username;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getNumero(),
                user.getEmail(),
                user.getPassword(),
                //user.getUsername(),
                user.getPrenom(),
                user.getNom(),
                user.getConfirmNotification(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return numero;
    }


    public String getNom() {
        return nom;
    }


    public String getPrenom() {
        return prenom;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
