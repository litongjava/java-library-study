package com.litongjava.ws.schild.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.litongjava.utils.ffmpeg.AudioUtils;
import com.litongjava.utils.ffmpeg.MediaUtils;
import com.litongjava.utils.ffmpeg.VideoToAudioUtils;
import com.litongjava.utils.json.fastjson.FastJsonUtils;
import com.litongjava.ws.schild.utils.BaiduAsrUtils;

import lombok.extern.slf4j.Slf4j;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

/**
 * 音频转为文字
 * 
 * @author
 *
 */
@Slf4j
public class AudioToTextDemo {
  public static void main(String[] args) {
    // 1.MP4转mp3
    String mp4Path = "G:\\迅雷下载\\Android_AI应用与开发—项目式教学\\02.05.智能情感灯-云端人脸分析应用开发.mp4";
    try {
      videoToText(mp4Path);
    }catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void videoToText(String mp4Path) throws InputFormatException, EncoderException, IOException, InterruptedException {
    String mp3Path = mp4Path.replace("mp4","mp3");
    log.info("开始转换mp4文件");
    VideoToAudioUtils.transform(mp4Path, mp3Path);
    // 2.mp3拆分
    log.info("开始拆分mp3文件");
    List<String> mp3FileList = new ArrayList<String>();
    String[] segmentLengthLimit = AudioUtils.segmentLengthLimit(mp3Path, 60);
    for (String string : segmentLengthLimit) {
      mp3FileList.add(string);
    }
    // 3.mp3转pcm
    log.info("开始转换为pcm文件");
    log.info("mp3 count:{}",mp3FileList.size());
    List<String> pcmFileList = new ArrayList<String>();
    for (String string : mp3FileList) {
      String dstFilePath = string.replace(".mp3", ".pcm");
      MediaUtils.transform2Pcm(string, dstFilePath);
      pcmFileList.add(dstFilePath);
    }
    // 4.识别pcm
    FastJsonUtils.writeToFile("pcm-file-list.json", pcmFileList);
    List<JSONObject> jsonList = new ArrayList<>();
    for (String pcmFullPath : pcmFileList) {
      org.json.JSONObject jsonObject = BaiduAsrUtils.asr(pcmFullPath, "pcm", 16000, null);
      jsonList.add(jsonObject);
    }
    // 识别结果写入文件
    FastJsonUtils.writeToFile("recognized.json", jsonList);
    // 遍历输出识别结果
    for (JSONObject jsonObject : jsonList) {
      JSONArray jsonArray = jsonObject.getJSONArray("result");
      int length = jsonArray.length();
      for (int i = 0; i < length; i++) {
        String string = jsonArray.getString(i);
        System.out.println(string);
      }
    }
  }
}
