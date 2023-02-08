package SoutenanceBackend.soutenance.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String video;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user ;

    @OneToOne
    @JoinColumn(name = "id_legumesFruits")
    @JsonIgnore
    private LegumesFruits legumesFruits;
}
