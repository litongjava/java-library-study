package com.bill.word.server;

import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.usermodel.PictureType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimplePicturesManager implements PicturesManager {
  @Override
  public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches,
      float heightInches) {
    log.info("return:{}", suggestedName);
    return suggestedName;
  }
}
