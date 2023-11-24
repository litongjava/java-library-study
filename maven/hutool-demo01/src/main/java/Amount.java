import java.math.BigDecimal;

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
public class Amount {
  private String type;
  private BigDecimal value;
}
