package com.mikefdorst.voidscavengers.view;

import com.mikefdorst.voidscavengers.util.Ref;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class MainWindow implements AutoCloseable {
  
  public MainWindow() throws LWJGLException {
    Display.setDisplayMode(new DisplayMode(Ref.main_window.width, Ref.main_window.height));
    Display.setTitle(Ref.main_window.title);
    Display.create();
    
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(Ref.main_window.aspect_ratio, -Ref.main_window.aspect_ratio, 1, -1, -1, 1);
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
