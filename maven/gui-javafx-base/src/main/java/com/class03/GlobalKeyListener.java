package com.class03;

import lombok.extern.slf4j.Slf4j;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.lang.reflect.Field;

@Slf4j
public class GlobalKeyListener implements NativeKeyListener {
  @Override
  public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {

  }

  @Override
  public void nativeKeyPressed(NativeKeyEvent e) {
    if (e.getKeyCode() == NativeKeyEvent.VC_F4) {
      preventEvent(e);
      log.info("F4");
    } else if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
      preventEvent(e);
    }
  }

  @Override
  public void nativeKeyReleased(NativeKeyEvent e) {
    log.info("e:{}", e);
  }

  private void preventEvent(NativeKeyEvent e) {
    try {
      Field f = NativeInputEvent.class.getDeclaredField("reserved");
      f.setAccessible(true);
      f.setShort(e, (short) 0x01);
    } catch (Exception ex) {

    }
  }
}
