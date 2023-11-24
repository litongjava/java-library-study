import org.junit.Test;

public class VoiceNameTest {

  @Test
  public void test() {
    VoiceName[] values = VoiceName.values();
    for (VoiceName voiceName : values) {
      System.out.println(voiceName);
    }
  }
}
