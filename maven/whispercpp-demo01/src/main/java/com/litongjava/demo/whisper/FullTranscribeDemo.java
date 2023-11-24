package com.litongjava.demo.whisper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import io.github.ggerganov.whispercpp.WhisperCpp;
import io.github.ggerganov.whispercpp.bean.WhisperSegment;
import io.github.ggerganov.whispercpp.params.WhisperFullParams;
import io.github.ggerganov.whispercpp.params.WhisperSamplingStrategy;

public class FullTranscribeDemo {

  private static WhisperCpp whisper = new WhisperCpp();
  private static boolean modelInitialised = false;

  public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
    new FullTranscribeDemo().index();

  }

  private void index() {
    String modelName = "E:\\code\\cpp\\project-ping\\whisper.cpp\\models\\ggml-base.en.bin";

    if (!modelInitialised) {
      try {
        whisper.initContext(modelName);
        modelInitialised = true;
      } catch (FileNotFoundException ex) {
        System.out.println("Model " + modelName + " not found");
        return;
      }
    }

    // Given
    File file = new File("E:\\code\\cpp\\project-ping\\whisper.cpp\\samples\\jfk.wav");
    URL url;
    try {
      url = file.toURI().toURL();
      float[] audioData = this.toAudioData(url);
      WhisperFullParams params = whisper.getFullDefaultParams(WhisperSamplingStrategy.WHISPER_SAMPLING_GREEDY);
      List<WhisperSegment> segments = whisper.fullTranscribeWithTime(params, audioData);
      segments.forEach(System.out::println);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public float[] toAudioData(URL url) throws UnsupportedAudioFileException, IOException {
    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
    byte[] b = new byte[audioInputStream.available()];
    float[] floats = new float[b.length / 2];

    try {
      audioInputStream.read(b);

      for (int i = 0, j = 0; i < b.length; i += 2, j++) {
        int intSample = (int) (b[i + 1]) << 8 | (int) (b[i]) & 0xFF;
        floats[j] = intSample / 32767.0f;
      }
      // Then
    } finally {
      audioInputStream.close();
    }
    return floats;
  }

}
