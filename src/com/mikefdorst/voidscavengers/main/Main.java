package com.mikefdorst.voidscavengers.main;

import com.mikefdorst.voidscavengers.model.util.VertexStream;
import com.mikefdorst.voidscavengers.model.shape.Quad;
import com.mikefdorst.voidscavengers.util.Ref;
import com.mikefdorst.voidscavengers.view.MainWindow;
import com.mikefdorst.voidscavengers.view.Renderer;
import com.mikefdorst.voidscavengers.view.shader.Shader;

import static org.lwjgl.opengl.GL11.*;

public class Main {
  public static void main(String[] args) {
    try (MainWindow mainWindow = new MainWindow()) {
      
      Shader plainShader =
        new Shader(Ref.path_to.shader("plain_shader.vert"), Ref.path_to.shader("plain_shader.frag"));
      plainShader.use();
      
      Quad quad = new Quad(0, 0, 0.25, 0.25, 0.3, 0.3, 1);
      Renderer renderer = new Renderer();
      renderer.setVertices(VertexStream.quadVertices(quad))
        .setColors(VertexStream.quadColors(quad))
        .setRenderMode(GL_QUADS);
      
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
