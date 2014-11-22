package com.mikefdorst.voidscavengers.view;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_VERTEX_ARRAY;
import static org.lwjgl.opengl.GL11.glDisableClientState;
import static org.lwjgl.opengl.GL15.*;

public class Renderer implements AutoCloseable {
  private final int vertexSize = 2;
  private final int colorSize = 3;
  
  private int vertexCount;
  private int vertexBufferHandle;
  private int colorBufferHandle;
  private int renderMode = GL_LINE_LOOP;
  
  private void setBuffer(int handle, List<Float> elements) {
    FloatBuffer buffer = BufferUtils.createFloatBuffer(elements.size());
    for (Float element : elements) {
      buffer.put(element);
    }
    buffer.flip();
    
    glBindBuffer(GL_ARRAY_BUFFER, handle);
    glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    glBindBuffer(GL_ARRAY_BUFFER, 0);
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
  }
  
  public void render() {
    glBindBuffer(GL_ARRAY_BUFFER, vertexBufferHandle);
    glVertexPointer(vertexSize, GL_FLOAT, 0, 0);
    
    glBindBuffer(GL_ARRAY_BUFFER, colorBufferHandle);
    glColorPointer(colorSize, GL_FLOAT, 0, 0);
    
    glEnableClientState(GL_VERTEX_ARRAY);
    glEnableClientState(GL_COLOR_ARRAY);
    glDrawArrays(renderMode, 0, vertexCount);
    glDisableClientState(GL_COLOR_ARRAY);
    glDisableClientState(GL_VERTEX_ARRAY);
  }
  
  @Override
  public void close() throws Exception {
    glDeleteBuffers(vertexBufferHandle);
    glDeleteBuffers(colorBufferHandle);
  }
}
