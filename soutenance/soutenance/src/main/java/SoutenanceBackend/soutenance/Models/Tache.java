package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*private Long id;
    private String titre;
    private Boolean statut;
    private Date date;
    private int nbreJour;*/

    private Long id;
    private String titre;
    //private Boolean statut;
    private LocalDate dateAcitivte;
    private LocalTime heureNotif;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EStatut eStatut;

    @ManyToOne
    private User user;
}
