import cn.hutool.core.lang.UUID;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UUIDDemo {
  public static void main(String[] args) {
    String uuid = UUID.fastUUID().toString();
    System.out.println(uuid);
    
    uuid= UUID.randomUUID().toString();
    System.out.println(uuid);
    
    uuid = UUID.randomUUID(false).toString();
    System.out.println(uuid);
    
  }

}
