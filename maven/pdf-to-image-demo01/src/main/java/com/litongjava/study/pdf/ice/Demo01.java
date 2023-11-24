package com.litongjava.study.pdf.ice;
/**
 * @author litongjava <litongjava@qq.com>
 *
 */

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.util.GraphicsRenderingHints;

public class Demo01 {
  public static void main(String[] args) throws PDFException, PDFSecurityException, IOException {
    String filePath = "1.pdf";
    Document document = new Document();
    document.setFile(filePath);
    //测试结果,经过5倍放大后可以看清字体,但是部分文字无法提取
    //测试结果,经过10倍放大后可以看清字体,部分文字提取成功
    float scale = 10f;// 缩放比例
    float rotation = 0f;// 旋转角度

    for (int i = 0; i < document.getNumberOfPages(); i++) {
      // getPageImage
      BufferedImage image = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN,
          //
          org.icepdf.core.pobjects.Page.BOUNDARY_CROPBOX, rotation, scale);

      RenderedImage rendImage = image;

      try {
        File file = new File("images/" + i + ".png");
        ImageIO.write(rendImage, "png", file);
      } catch (IOException e) {
        e.printStackTrace();
      }
      image.flush();
    }
    document.dispose();
  }
}
