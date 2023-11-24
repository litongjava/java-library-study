package com.litongjava.ws.schild.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
import com.litongjava.jfinal.models.voidetotext.model.Recognized;
import com.litongjava.utils.json.fastjson.FastJsonUtils;
import com.litongjava.ws.schild.utils.BaiduAsrUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 将视频转为文本并保存到数据库
 * 
 * @author litongjava
 *
 */
@Slf4j
public class AudioToTextAndSaveDb {
  public static void main(String[] args) throws IOException {
    // 1.读取json文件,获取文件路径
    List<String> pcmFileList = FastJsonUtils.readFileToList("pcm-file-list.json", String.class);
    // 识别两个文件测试一下
    // List<String> pcmFileList = new ArrayList<>();
    // pcmFileList.add("H:\\video\\软件使用学习视频\\MAYA建模\\P01.MAYA：界面讲解_chunks\\0000.pcm");
    // pcmFileList.add("H:\\video\\软件使用学习视频\\MAYA建模\\P01.MAYA：界面讲解_chunks\\0001.pcm");
    int size = pcmFileList.size();
    log.info("pcmFileList size:{}", size);
    // 2.将pcm文件转为文本
    List<Recognized> recognizedList = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      log.info("开始处理:{}",i);
      String string = pcmFileList.get(i);
      JSONObject jsonObject = BaiduAsrUtils.asr(string, "PCM", 16000, null);
      Recognized recognized = new Recognized();
      recognized.setPcmFile(string);
      recognized.setText(jsonObject.toString());
      recognizedList.add(recognized);
    }
    // 3.保存到数据库
    List<Record> insertList = new ArrayList<Record>();

    for (Recognized recognized : recognizedList) {
      Record e = new Record();
      e.set("pcm_file", recognized.getPcmFile());
      e.set("text", recognized.getText());
      insertList.add(e);
    }

    String[] datasource1 = { "jdbc:sqlite:D:/sqllite/java-se-ws-schild-voide-to-audio.db", // url
        "", ""// user and password
    };

    DruidPlugin plugin1 = new DruidPlugin(datasource1[0], datasource1[1], datasource1[2]);
    ActiveRecordPlugin arp1 = new ActiveRecordPlugin(plugin1);
    plugin1.start();
    arp1.start();

    // 插入数据库
    log.info("开始批量保存数据");
    Db.batchSave("recognized", insertList, 2000);
    log.info("批量保存数据完成");
  }
}
