import java.util.Set;

import org.apache.commons.compress.utils.Sets;

public class SetsDemo01 {
  
  public static void main(String[] args) {
    Set<String> projectTypes = Sets.newHashSet("1", "2", "3", "4", "5", "6", "7");
    System.out.println(projectTypes);
  }
}
