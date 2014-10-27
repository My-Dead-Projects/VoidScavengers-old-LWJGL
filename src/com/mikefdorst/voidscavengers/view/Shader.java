package com.mikefdorst.voidscavengers.view;

import com.mikefdorst.voidscavengers.exception.FragmentShaderCompilationError;
import com.mikefdorst.voidscavengers.exception.ShaderCompilationError;
import com.mikefdorst.voidscavengers.exception.VertexShaderCompilationError;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class Shader implements AutoCloseable {
  private int vertexShaderHandle;
  private int fragmentShaderHandle;
  private int shaderProgramHandle;
  
  public void use() {
    glUseProgram(shaderProgramHandle);
  }
  
  public Shader(String vertexShaderSourcePath, String fragmentShaderSourcePath) throws ShaderCompilationError {
    vertexShaderHandle = glCreateShader(GL_VERTEX_SHADER);
    fragmentShaderHandle = glCreateShader(GL_FRAGMENT_SHADER);
    StringBuilder source = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(vertexShaderSourcePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        source.append(line).append('\n');
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    glShaderSource(vertexShaderHandle, source);
    source = new StringBuilder();
    try (BufferedReader reader = new BufferedReader(new FileReader(fragmentShaderSourcePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        source.append(line).append('\n');
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    glShaderSource(fragmentShaderHandle, source);
    glCompileShader(vertexShaderHandle);
    glCompileShader(fragmentShaderHandle);
    
    if (glGetShaderi(vertexShaderHandle, GL_COMPILE_STATUS) == GL_FALSE) {
      throw new VertexShaderCompilationError();
    }
    if (glGetShaderi(fragmentShaderHandle, GL_COMPILE_STATUS) == GL_FALSE) {
      throw new FragmentShaderCompilationError();
    }
    
    shaderProgramHandle = glCreateProgram();
    glAttachShader(shaderProgramHandle, vertexShaderHandle);
    glAttachShader(shaderProgramHandle, fragmentShaderHandle);
    glLinkProgram(shaderProgramHandle);
    glValidateProgram(shaderProgramHandle);
  }

  @Override
  public void close() throws Exception {
    glDeleteShader(vertexShaderHandle);
    glDeleteShader(fragmentShaderHandle);
    glDeleteProgram(shaderProgramHandle);
  }
}
