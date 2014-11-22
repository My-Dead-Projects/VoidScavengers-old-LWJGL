package com.mikefdorst.voidscavengers.view;

import com.mikefdorst.voidscavengers.exception.ShaderCompilationError;
import com.mikefdorst.voidscavengers.view.shader.DefaultShader;
import com.mikefdorst.voidscavengers.view.shader.Shader;
import org.lwjgl.opengl.GL15;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class Renderer implements AutoCloseable {
  private final int vertexSize = 2;
  private final int colorSize = 3;
  private int indexCount;
  
  private int vertexArrayHandle;
  private int vertexBufferHandle;
  private int colorBufferHandle;
  private int indexBufferHandle;
  private int renderMode = GL_LINE_LOOP;
  
  private Shader shader;

  public Renderer() throws ShaderCompilationError {
    vertexArrayHandle = glGenVertexArrays();
    vertexBufferHandle = glGenBuffers();
    colorBufferHandle = glGenBuffers();
    indexBufferHandle = glGenBuffers();
    shader = new DefaultShader();
  }
  
  public Renderer setShader(Shader shader) {
    this.shader = shader;
    return this;
  }
  
  private void setBuffer(int attributeIndex, int stride, int handle, FloatBuffer buffer) {
    buffer.flip();
    glBindVertexArray(vertexArrayHandle);
    glBindBuffer(GL_ARRAY_BUFFER, handle);
    glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
    glVertexAttribPointer(attributeIndex, stride, GL_FLOAT, false, 0, 0);
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    glBindVertexArray(0);
  }
  
  public Renderer setIndices(ByteBuffer buffer) {
    buffer.flip();
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBufferHandle);
    GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
    GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    return this;
  }
  
  public Renderer setVertices(FloatBuffer vertices) {
    setBuffer(0, vertexSize, vertexBufferHandle, vertices);
    return this;
  }
  
  public Renderer setColors(FloatBuffer colors) {
    setBuffer(1, colorSize, colorBufferHandle, colors);
    return this;
  }
  
  public Renderer setRenderMode(int rm) {
    renderMode = rm;
    return this;
  }
  
  public void render() {
    shader.use();
    glClear(GL_COLOR_BUFFER_BIT);
    glBindVertexArray(vertexArrayHandle);
    glEnableVertexAttribArray(0);
    glEnableVertexAttribArray(1);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indexBufferHandle);
    glDrawElements(renderMode, indexCount, GL_UNSIGNED_BYTE, 0);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    glDisableVertexAttribArray(1);
    glDisableVertexAttribArray(0);
    glBindVertexArray(0);
  }
  
  @Override
  public void close() throws Exception {
    glDeleteBuffers(indexBufferHandle);
    glDeleteBuffers(vertexBufferHandle);
    glDeleteBuffers(colorBufferHandle);
    glDeleteVertexArrays(vertexArrayHandle);
    shader.close();
  }
}
