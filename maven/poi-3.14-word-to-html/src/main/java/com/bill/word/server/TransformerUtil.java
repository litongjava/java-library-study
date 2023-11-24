package com.bill.word.server;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;

public class TransformerUtil {
  private static TransformerFactory factory = null;
  private static Transformer transformer = null;
  static {
    factory = TransformerFactory.newInstance();
    try {
      transformer = factory.newTransformer();
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    }
    transformer = initProperty(transformer);
  }

  public static Transformer initProperty(Transformer transformer) {
    transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    return transformer;
  }

  public static Transformer newTransformer() {
    Transformer transformer = null;
    try {
      transformer = factory.newTransformer();
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    }
    transformer = initProperty(transformer);
    return transformer;
  }

  public static Transformer getTransformer() {
    return transformer;
  }
}
