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
public class User1 {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "user1_id_generator")
    @TableGenerator(
            name = "user1_id_generator", // 生成器名称
            table = "user1_id", // 指定ID生成器表名
            pkColumnName = "pkn",
            pkColumnValue = "pkv",
            valueColumnName = "vcn",
            initialValue = 2,
            allocationSize = 10)
    private Long id;

    private String name;

    private LocalDate creatDate;

}
