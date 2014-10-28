package com.mikefdorst.voidscavengers.model.util;

import com.mikefdorst.voidscavengers.model.shape.Quad;

import java.util.ArrayList;
import java.util.List;

public class VertexStream {
  public static List<Float> quadVertices(Quad quad) {
    List<Float> vertices = new ArrayList<>(8);
    vertices.add(quad.bottomLeft().x());
    vertices.add(quad.bottomLeft().y());
    vertices.add(quad.topLeft().x());
    vertices.add(quad.topLeft().y());
    vertices.add(quad.topRight().x());
    vertices.add(quad.topRight().y());
    vertices.add(quad.bottomRight().x());
    vertices.add(quad.bottomRight().y());
    return vertices;
  }
  public static List<Float> quadColors(Quad quad) {
    List<Float> colors = new ArrayList<>(12);
    for (int i = 0; i < 4; i++) {
      colors.add(quad.color.red());
      colors.add(quad.color.green());
      colors.add(quad.color.blue());
    }
    return colors;
  }
}
