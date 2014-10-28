package com.mikefdorst.voidscavengers.main;

import com.mikefdorst.voidscavengers.util.Ref;
import com.mikefdorst.voidscavengers.view.MainWindow;
import com.mikefdorst.voidscavengers.view.Renderer;
import com.mikefdorst.voidscavengers.view.Shader;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Main {
  public static void main(String[] args) {
    try (MainWindow mainWindow = new MainWindow()) {
      
      Shader plainShader =
        new Shader(Ref.path_to.shader("plain_shader.vert"), Ref.path_to.shader("plain_shader.frag"));
      plainShader.use();
      
      Renderer renderer = new Renderer();
      List<Float> vertices = new ArrayList<>(6);
      vertices.add(-0.5f);
      vertices.add(-0.5f);
      vertices.add( 0.5f);
      vertices.add(-0.5f);
      vertices.add( 0.0f);
      vertices.add( 0.5f);
      List<Float> colors = new ArrayList<>(9);
      colors.add(1f);
      colors.add(0f);
      colors.add(0f);
      colors.add(0f);
      colors.add(1f);
      colors.add(0f);
      colors.add(0f);
      colors.add(0f);
      colors.add(1f);
      
      renderer.setVertices(vertices).setColors(colors).setRenderMode(GL_TRIANGLES);
      
      while (mainWindow.isOpen()) {
        glClear(GL_COLOR_BUFFER_BIT);
        
        renderer.render();
        
        mainWindow.update();
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
