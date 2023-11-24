package com.utils;

import java.io.File;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncodingAttributes;

import ws.schild.jave.EncoderException;

import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

public class JaveUtil {
  public static MultimediaInfo getMediaInfo(String path) {
    File source = new File(path);
    // 创建媒体对象
    MultimediaObject multimediaObject = new MultimediaObject(source);
    // 创建媒体信息对象
    MultimediaInfo info = null;
    try {
      info = multimediaObject.getInfo();
    } catch (InputFormatException e) {
      e.printStackTrace();
    } catch (EncoderException e) {
      e.printStackTrace();
    }
    return info;
  }

  public static long getTime(String add) {
    MultimediaInfo info = getMediaInfo(add);
    long time = info.getDuration();
    return time;
  }

  public static String getFormat(String add) throws EncoderException {
    MultimediaInfo info = getMediaInfo(add);
    return info.getFormat();
  }

  public static void mp32Wav(String mp3Path, String wavPath, Integer sampleRate, Integer channels, Integer bitRate) {
    File source = new File(mp3Path);
    File target = new File(wavPath);

    MultimediaObject multimediaObject = new MultimediaObject(source);

    AudioAttributes audio = new AudioAttributes();
    audio.setSamplingRate(sampleRate);
    audio.setChannels(channels);
    audio.setBitRate(bitRate);

    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setAudioAttributes(audio);

    cncode(multimediaObject, target, attrs);
  }

  private static void cncode(MultimediaObject multimediaObject, File target, EncodingAttributes attrs) {
    Encoder encoder = new Encoder();
    try {
      encoder.encode(multimediaObject, target, attrs);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InputFormatException e) {
      e.printStackTrace();
    } catch (EncoderException e) {
      e.printStackTrace();
    }
  }

  public static void mp32Mp3(String sorucePath, String targetPath, Integer sampleRate, Integer channels,
      Integer bitRate) {
    File source = new File(sorucePath);
    File target = new File(targetPath);

    MultimediaObject multimediaObject = new MultimediaObject(source);

    AudioAttributes audio = new AudioAttributes();
    audio.setSamplingRate(sampleRate);
    audio.setChannels(channels);
    audio.setBitRate(bitRate);

    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setAudioAttributes(audio);
    attrs.setFormat("mp3");
    cncode(multimediaObject, target, attrs);
  }
}
