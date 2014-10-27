package com.mikefdorst.voidscavengers.main;

import com.mikefdorst.voidscavengers.view.MainWindow;

import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

public class Main {
  public static void main(String[] args) {
    try (MainWindow mainWindow = new MainWindow()) {
      
      while (mainWindow.isOpen()) {
        glClear(GL_COLOR_BUFFER_BIT);
        
        
        
        mainWindow.update();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
