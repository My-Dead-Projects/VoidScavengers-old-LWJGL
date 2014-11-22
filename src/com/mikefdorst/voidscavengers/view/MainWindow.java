package com.mikefdorst.voidscavengers.view;

import com.mikefdorst.voidscavengers.util.Ref;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class MainWindow implements AutoCloseable {
  
  public MainWindow() throws LWJGLException {
    Display.setDisplayMode(new DisplayMode(Ref.main_window.width, Ref.main_window.height));
    Display.setTitle(Ref.main_window.title);
    Display.create(new PixelFormat(), new ContextAttribs(3, 2)
      .withForwardCompatible(true)
      .withProfileCore(true));
    
    GL11.glViewport(0, 0, Ref.main_window.width, Ref.main_window.height);
  }
  
  public void update() {
    Display.update();
    Display.sync(Ref.main_window.target_fps);
  }
  
  public boolean isOpen() {
    return !Display.isCloseRequested();
  }
  
  @Override
  public void close() throws Exception {
    Display.destroy();
  }
}
