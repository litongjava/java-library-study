package com.litongjava.ws.schild.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.litongjava.utils.ffmpeg.AudioUtils;
import com.litongjava.utils.ffmpeg.MediaUtils;
import com.litongjava.utils.ffmpeg.VideoToAudioUtils;
import com.litongjava.utils.file.filter.ExtensionFilenameFilter;
import com.litongjava.utils.json.fastjson.FastJsonUtils;
import com.litongjava.ws.schild.utils.BaiduAsrUtils;

import lombok.extern.slf4j.Slf4j;
import ws.schild.jave.EncoderException;
import ws.schild.jave.InputFormatException;

/**
 * 將视频文件批量转为文字
 * 
 * @author litongjava
 */
@Slf4j
public class VideoToTextBatchDemo {
  public static void main(String[] args) throws InputFormatException, EncoderException, IOException {
    previous();
  }

  public static void previous() throws InputFormatException, EncoderException, IOException {
    String sourceFolderPath = "H:\\video\\软件使用学习视频\\MAYA建模";
    String targetFolderPath = sourceFolderPath;
    // 1.视频转为mp3音频
    log.info("开始转换为mp3");
    ExtensionFilenameFilter videoExtensionName = new ExtensionFilenameFilter(".mp4");
    List<String> batchTransform = VideoToAudioUtils.batchTransform(sourceFolderPath, targetFolderPath,
        videoExtensionName);
    log.info("转换完成mp3");
    // 2.对音频进行拆分
    log.info("开始拆分mp3文件");
    List<String> mp3FileList = new ArrayList<String>();
    for (String mp3FullPath : batchTransform) {
      String[] segmentLengthLimit = AudioUtils.segmentLengthLimit(mp3FullPath, 60);
      for (String string : segmentLengthLimit) {
        mp3FileList.add(string);
      }
    }
    log.info("拆分mp3文件完成");
//    String mp3FolderPath="H:\\video\\软件使用学习视频\\MAYA建模\\P01.MAYA：界面讲解_chunks";
//    File mp3FolderFile = new File(mp3FolderPath);
//    ExtensionFilenameFilter extensionFilenameFilter = new ExtensionFilenameFilter(".mp3");
//    String[] list = mp3FolderFile.list(extensionFilenameFilter);
//    List<String> mp3FileList = Arrays.asList(list);
    // 3.mp3转为pcm
    log.info("开始转换为pcm文件");
    List<String> pcmFileList = new ArrayList<String>();
    for (String string : mp3FileList) {
      String dstFilePath = string.replace(".mp3", ".pcm");
      MediaUtils.transform2Pcm(string, dstFilePath);
      pcmFileList.add(dstFilePath);
    }
    log.info("转换为pcm文件完成");
    // 4.识别pcm
    // 将pcm文件列表保存到json文件
    FastJsonUtils.writeToFile("pcm-list.json", pcmFileList);
    List<JSONObject> jsonList = new ArrayList<>();
    for (String pcmFullPath : pcmFileList) {
      org.json.JSONObject jsonObject = BaiduAsrUtils.asr(pcmFullPath, "pcm", 16000, null);
      jsonList.add(jsonObject);
    }

    FastJsonUtils.writeToFile("recognized.json", jsonList);

  }
}
