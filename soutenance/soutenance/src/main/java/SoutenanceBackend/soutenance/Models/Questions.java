package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Entity est une annotation qui indique que la classe correspond à une table de la base de données.
public class Questions {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private LocalDateTime timestamp;
    private Long nbrereponse = Long.valueOf(0);


    @ManyToOne
    private User user;



}


