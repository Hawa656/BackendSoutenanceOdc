package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tutoriels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    @Size(max = 9000)
    private String description;
    //sous forme select
    private  String etatDeLaTerre;
    private  String espacementEntreGraine;
    private Boolean semis;
    private  Boolean bouture;
    //private String hauteur;

}
