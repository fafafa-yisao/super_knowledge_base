package top.wdsama.jpa.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class User2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user2_Id_generator")
    @SequenceGenerator(name = "user2_Id_generator",sequenceName = "id")
    private Long id;

    private String name;

    private LocalDate creatDate;

}
