package com.litongjava.whipser.cpp.jni.demo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.github.givimad.whisperjni.WhisperContext;
import io.github.givimad.whisperjni.WhisperFullParams;
import io.github.givimad.whisperjni.WhisperJNI;

public class Demo01 {

  public static void main(String[] args) throws IOException, UnsupportedAudioFileException {

    WhisperJNI whisper = new WhisperJNI();
    WhisperJNI.loadLibrary();

    float[] samples = readJFKFileSamples();
    Path path = Paths.get("E:\\code\\cpp\\project-ping\\whisper.cpp\\models", "ggml-tiny.bin");
    WhisperContext ctx = whisper.init(path);
    WhisperFullParams params = new WhisperFullParams();
    params.printProgress = false;
    int result = whisper.full(ctx, params, samples, samples.length);
    if (result != 0) {
      throw new RuntimeException("Transcription failed with code " + result);
    }
    int numSegments = whisper.fullNSegments(ctx);
    for (int i = 0; i < numSegments; i++) {
      String text = whisper.fullGetSegmentText(ctx, 0);
      long start = whisper.fullGetSegmentTimestamp0(ctx, i);
      long end = whisper.fullGetSegmentTimestamp1(ctx, i);
      System.out.println(start + "-->" + end);
      System.out.println(i + ":" + text);

    }
    ctx.close();
  }

  private static float[] readJFKFileSamples() throws UnsupportedAudioFileException, IOException {
    File file = new File(
        "E:\\code\\java\\project-litongjava\\ai-server\\ai-server-tio-boot\\src\\main\\resources\\audios\\jfk.wav");
    @SuppressWarnings("deprecation")
    URL url = file.toURL();
    return toAudioData(url);
  }

  public static float[] toAudioData(URL url) throws UnsupportedAudioFileException, IOException {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
    byte[] b = new byte[audioInputStream.available()];
    try {
      audioInputStream.read(b);
      return toFloat(b);
    } finally {
      audioInputStream.close();
    }
  }

  public static float[] toFloat(byte[] b) {
    float[] floats = new float[b.length / 2];
    for (int i = 0, j = 0; i < b.length; i += 2, j++) {
      int intSample = (int) (b[i + 1]) << 8 | (int) (b[i]) & 0xFF;
      floats[j] = intSample / 32767.0f;
    }
    return floats;
  }

}
