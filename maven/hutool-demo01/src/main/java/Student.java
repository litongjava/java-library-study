import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author litongjava <litongjava@qq.com>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  private Integer clazz;
  private String name;
  private Integer age;
}
