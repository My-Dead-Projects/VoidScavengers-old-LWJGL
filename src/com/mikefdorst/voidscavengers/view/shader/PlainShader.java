package com.mikefdorst.voidscavengers.view.shader;

import com.mikefdorst.voidscavengers.exception.ShaderCompilationError;
import com.mikefdorst.voidscavengers.util.Ref;

public class PlainShader extends Shader {
  public PlainShader() throws ShaderCompilationError {
    super(Ref.path_to.shader("plain_shader.vert"), Ref.path_to.shader("plain_shader.frag"));
  }
}
