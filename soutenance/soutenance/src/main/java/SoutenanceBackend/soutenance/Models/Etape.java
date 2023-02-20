package SoutenanceBackend.soutenance.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "etapes")
public class Etape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String etape;
    private  String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutoriels_id")
    private Tutoriels tutoriels;
}
