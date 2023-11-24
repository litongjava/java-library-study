package com.litongjava.ws.schild;

import org.junit.Test;

import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;

public class EncoderTest {

  @Test
  public void testGetSupportedEncodingFormats() throws EncoderException{
    Encoder encoder = new Encoder();
    String[] supportedEncodingFormats = encoder.getSupportedEncodingFormats();
    for (String string : supportedEncodingFormats) {
      System.out.println(string);
    }
  }

}
