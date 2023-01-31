package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LegumesFruits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @Size(max = 9000)
    private String description;
    private String photo;
    private String dureeFloraisaon;
    //de janvier à decembre sous forme de checkbox avec possibilité de selectionner plusieurs mois

    private String arrosage;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EperiodeNormal periodeNormal;


    @OneToOne
    @JoinColumn(name = "id_tutoriel")
    private Tutoriels tutoriels;



    @ManyToOne
    private TypeLegumeFruit typeLegumeFruit;

    @ManyToOne
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "legumesfruit_activite",
            joinColumns = @JoinColumn(name = "legumefruit_id"),
            inverseJoinColumns = @JoinColumn(name = "activite_id"))
    private Set<Activites> activites = new HashSet<>();
}
