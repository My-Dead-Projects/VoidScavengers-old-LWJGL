package com.mikefdorst.voidscavengers.model.util;

import com.mikefdorst.voidscavengers.model.shape.Quad;
import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class VertexStream {
  public static FloatBuffer quadVertices(Quad quad) {
    FloatBuffer buffer = BufferUtils.createFloatBuffer(8);
    buffer.put(quad.bottomLeft().x());
    buffer.put(quad.bottomLeft().y());
    buffer.put(quad.topLeft().x());
    buffer.put(quad.topLeft().y());
    buffer.put(quad.topRight().x());
    buffer.put(quad.topRight().y());
    buffer.put(quad.bottomRight().x());
    buffer.put(quad.bottomRight().y());
    return buffer;
  }
  public static ByteBuffer quadIndices(Quad quad) {
    ByteBuffer buffer = BufferUtils.createByteBuffer(6);
    buffer.put(new byte[] {0, 1, 2, 2, 3, 0});
    return buffer;
  }
  public static FloatBuffer quadColors(Quad quad) {
    FloatBuffer buffer = BufferUtils.createFloatBuffer(12);
    for (int i = 0; i < 4; i++) {
      buffer.put(quad.color.red());
      buffer.put(quad.color.green());
      buffer.put(quad.color.blue());
    }
    return buffer;
  }
}
