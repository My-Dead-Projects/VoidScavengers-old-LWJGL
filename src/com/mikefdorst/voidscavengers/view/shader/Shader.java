package com.mikefdorst.voidscavengers.view.shader;

import com.mikefdorst.voidscavengers.exception.FragmentShaderCompilationError;
import com.mikefdorst.voidscavengers.exception.ShaderCompilationError;
import com.mikefdorst.voidscavengers.exception.VertexShaderCompilationError;
import com.mikefdorst.voidscavengers.util.Ref;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public abstract class Shader implements AutoCloseable {
  private int vertexShaderHandle;
  private int fragmentShaderHandle;
  protected int shaderProgramHandle;
  
  public void use() {
    glUseProgram(shaderProgramHandle);
  }
  
  protected Shader(String vertexShaderSourcePath, String fragmentShaderSourcePath) throws ShaderCompilationError {
    
    /**
     * Create shader objects and store their handles
     */
    vertexShaderHandle = glCreateShader(GL_VERTEX_SHADER);
    fragmentShaderHandle = glCreateShader(GL_FRAGMENT_SHADER);
    
    /**
     * Read in vertex shader source and store it in the vertex shader object
     */
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
    
    /**
     * Read in fragment shader source and store it in the fragment shader object
     */
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
    
    /**
     * Compile shaders
     */
    glCompileShader(vertexShaderHandle);
    glCompileShader(fragmentShaderHandle);
    
    /**
     * Poll for compile errors
     */
    if (glGetShaderi(vertexShaderHandle, GL_COMPILE_STATUS) == GL_FALSE) {
      throw new VertexShaderCompilationError(glGetShaderInfoLog(vertexShaderHandle, Ref.shader.info_log_max_length));
    }
    if (glGetShaderi(fragmentShaderHandle, GL_COMPILE_STATUS) == GL_FALSE) {
      throw new FragmentShaderCompilationError(glGetShaderInfoLog(fragmentShaderHandle, Ref.shader.info_log_max_length));
    }
    
    /**
     * Create a shader program, store its handle, and attatch the compiled shaders to it
     */
    shaderProgramHandle = glCreateProgram();
    glAttachShader(shaderProgramHandle, vertexShaderHandle);
    glAttachShader(shaderProgramHandle, fragmentShaderHandle);
    
    /**
     * Bind any attributes needed by the shader.
     * bindAttributeLocations() should be overridden for each shader,
     * as attribute locations are specific to each shader.
     */
    bindAttributes();
    
    /**
     * Link the shader program
     */
    // TODO: Poll for errors after linking and validating the program.
    glLinkProgram(shaderProgramHandle);
    
    /**
     * Validate the shader program
     */
    glValidateProgram(shaderProgramHandle);
  }
  
  public abstract void bindAttributes();
  
  @Override
  public void close() throws Exception {
    glDeleteShader(vertexShaderHandle);
    glDeleteShader(fragmentShaderHandle);
    glDeleteProgram(shaderProgramHandle);
  }
}
