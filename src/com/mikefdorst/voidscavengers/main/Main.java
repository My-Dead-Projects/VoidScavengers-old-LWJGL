package com.mikefdorst.voidscavengers.main;

import com.mikefdorst.voidscavengers.view.MainWindow;

public class Main {
  public static void main(String[] args) {
    try (MainWindow mainWindow = new MainWindow()) {
      
      while (mainWindow.isOpen()) {
        
        
        
        mainWindow.update();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
