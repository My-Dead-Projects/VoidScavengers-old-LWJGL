package com.mikefdorst.voidscavengers.view.shader;

import com.mikefdorst.voidscavengers.exception.ShaderCompilationError;
import com.mikefdorst.voidscavengers.util.Ref;
import org.lwjgl.opengl.GL20;

public class PlainShader extends Shader {
  public PlainShader() throws ShaderCompilationError {
    super(Ref.path_to.shader("plain_shader.vert"), Ref.path_to.shader("plain_shader.frag"));
  }
  
  @Override
  public void setup() {
    GL20.glBindAttribLocation(shaderProgramHandle, 0, "in_Position");
    GL20.glBindAttribLocation(shaderProgramHandle, 1, "in_Color");
  }
}
