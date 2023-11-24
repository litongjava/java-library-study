package com.billrobot.remote.view;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToolKitTest {

  @Test
  public void test() {
    Toolkit toolKit = Toolkit.getDefaultToolkit();
    Dimension dimension = toolKit.getScreenSize();
    log.info("width:" + dimension.width + ",height:" + dimension.height);
  }
}
