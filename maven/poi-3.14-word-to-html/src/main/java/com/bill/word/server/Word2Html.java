package com.bill.word.server;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Word2Html {
  public void convertFromDoc(String docPath, String targetDir) {

    String path = docPath.replace(".doc", ".html");
    InputStream inputStream = null;
    String md5Hex = null;
    try {
      inputStream = new FileInputStream(docPath);
      md5Hex = DigestUtils.md5Hex(inputStream);
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    int available = 0;
    try {
      available = inputStream.available();
    } catch (IOException e3) {
      e3.printStackTrace();
    }
    log.info("available={}", available);
    System.out.println();

    try {
      available = inputStream.available();
    } catch (IOException e3) {
      e3.printStackTrace();
    }
    log.info("available={}", available);
    System.out.println(inputStream == null);
    HWPFDocument wordDocument = null;
    try {
      wordDocument = new HWPFDocument(inputStream);
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    WordToHtmlConverter wordToHtmlConverter = null;
    try {
      wordToHtmlConverter = new WordToHtmlConverter(
          DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
    } catch (ParserConfigurationException e1) {
      e1.printStackTrace();
    }
    wordToHtmlConverter.setPicturesManager(new PicturesManager() {
      public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches,
          float heightInches) {
        return suggestedName;
      }
    });
    wordToHtmlConverter.processDocument(wordDocument);
    List pics = wordDocument.getPicturesTable().getAllPictures();
    if (pics != null) {
      for (int i = 0; i < pics.size(); i++) {
        Picture pic = (Picture) pics.get(i);
        try {
          pic.writeImageContent(new FileOutputStream(path + File.separator + pic.suggestFullFileName()));
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    Document htmlDocument = wordToHtmlConverter.getDocument();
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    DOMSource domSource = new DOMSource(htmlDocument);
    StreamResult streamResult = new StreamResult(outStream);
    TransformerFactory tf = TransformerFactory.newInstance();
    Transformer serializer = null;
    try {
      serializer = tf.newTransformer();
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    }
    serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
    serializer.setOutputProperty(OutputKeys.INDENT, "yes");
    serializer.setOutputProperty(OutputKeys.METHOD, "html");
    try {
      serializer.transform(domSource, streamResult);
    } catch (TransformerException e) {
      e.printStackTrace();
    }
    try {
      outStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    String content = new String(outStream.toByteArray());
    try {
      FileUtils.writeStringToFile(new File(path, "1.html"), content, "utf-8");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
