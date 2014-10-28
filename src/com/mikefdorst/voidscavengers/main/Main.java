package com.mikefdorst.voidscavengers.main;

import com.mikefdorst.voidscavengers.util.Ref;
import com.mikefdorst.voidscavengers.view.MainWindow;
import com.mikefdorst.voidscavengers.view.Shader;

import static org.lwjgl.opengl.GL11.*;

public class Main {
  public static void main(String[] args) {
    try (MainWindow mainWindow = new MainWindow()) {
      
      Shader plainShader =
        new Shader(Ref.path_to.shader("plain_shader.vert"), Ref.path_to.shader("plain_shader.frag"));
      plainShader.use();
      
      while (mainWindow.isOpen()) {
        glClear(GL_COLOR_BUFFER_BIT);
        
        glBegin(GL_TRIANGLES);
        glColor3f(1, 0, 0);
        glVertex2f(-0.5f, -0.5f);
        glColor3f(0, 1, 0);
        glVertex2f(0.5f, -0.5f);
        glColor3f(0, 0, 1);
        glVertex2f(0, 0.5f);
        glEnd();
        
        mainWindow.update();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
