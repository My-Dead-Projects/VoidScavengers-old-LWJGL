package com.mikefdorst.voidscavengers.view;

import com.mikefdorst.voidscavengers.view.shader.Shader;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Renderer implements AutoCloseable {
  private final int vertexSize = 2;
  private final int colorSize = 3;
  
  private int vertexCount;
  private int vertexBufferHandle;
  private int colorBufferHandle;
  private int vertexArrayObject;
  private int renderMode = GL_LINE_LOOP;
  
  private Shader shader;
  
  public Renderer setShader(Shader shader) {
    this.shader = shader;
    return this;
  }
  
  private void setBuffer(int handle, List<Float> elements) {
    FloatBuffer buffer = BufferUtils.createFloatBuffer(elements.size());
    for (Float element : elements) {
      buffer.put(element);
    }
    buffer.flip();
    
    glBindVertexArray(vertexArrayObject);
    
    glBindBuffer(GL_ARRAY_BUFFER, handle);
    glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    glVertexAttribPointer(0, vertexSize, GL_FLOAT, false, 0, 0);
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    
    glBindVertexArray(0);
  }
  
  public Renderer setVertices(List<Float> vertices) {
    vertexCount = vertices.size() / vertexSize;
    setBuffer(vertexBufferHandle, vertices);
    return this;
  }
  
  public Renderer setColors(List<Float> colors) {
    setBuffer(colorBufferHandle, colors);
    return this;
  }
  
  public int renderMode() {
    return renderMode;
  }
  
  public Renderer setRenderMode(int rm) {
    renderMode = rm;
    return this;
  }
  
  public Renderer() {
    vertexBufferHandle = glGenBuffers();
    colorBufferHandle = glGenBuffers();
    vertexArrayObject = glGenVertexArrays();
  }
  
  public void render() {
    shader.use();
    
    glBindVertexArray(vertexArrayObject);
    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);
    
    glBindBuffer(GL_ARRAY_BUFFER, vertexBufferHandle);
    
    glDrawArrays(renderMode, 0, vertexCount);
    
    glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
    glBindVertexArray(0);
    glUseProgram(0);
  }
  
  @Override
  public void close() throws Exception {
    glDeleteBuffers(vertexBufferHandle);
    glDeleteBuffers(colorBufferHandle);
  }
}
