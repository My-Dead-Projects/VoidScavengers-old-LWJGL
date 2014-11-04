package com.mikefdorst.voidscavengers.model.shape;

import com.mikefdorst.voidscavengers.model.primitive.Color;
import com.mikefdorst.voidscavengers.model.primitive.Vec2D;

public class Quad {
  public Vec2D position;
  public Vec2D size;
  public Color color;
  
  public Vec2D bottomLeft() {
    return new Vec2D(position.x() - size.x()/2, position.y() - size.x()/2);
  }
  
  public Vec2D bottomRight() {
    return new Vec2D(position.x() + size.x()/2, position.y() - size.x()/2);
  }
  
  public Vec2D topLeft() {
    return new Vec2D(position.x() - size.x()/2, position.y() + size.x()/2);
  }
  
  public Vec2D topRight() {
    return new Vec2D(position.x() + size.x()/2, position.y() + size.x()/2);
  }
  
  public void move(double x, double y) {
    position.setX(position.x() + x).setY(position.y() + y);
  }
  
  private void init() {
    position = new Vec2D();
    size = new Vec2D();
    color = new Color();
  }
  
  public Quad() {
    init();
  }
  
  public Quad(Vec2D position, Vec2D size) {
    this.position = position;
    this.size = size;
  }
  
  public Quad(double x, double y, double w, double h) {
    init();
    position.setX(x).setX(y);
    size.setX(w).setY(h);
  }
  
  public Quad(Vec2D position, Vec2D size, Color color) {
    this.position = position;
    this.size = size;
    this.color = color;
  }
  
  public Quad(double x, double y, double w, double h, double r, double g, double b, double a) {
    init();
    position.setX(x).setY(y);
    size.setX(w).setY(h);
    color.setRed(r).setGreen(g).setBlue(b).setAlpha(a);
  }
  
  public Quad(double x, double y, double w, double h, double r, double g, double b) {
    init();
    position.setX(x).setY(y);
    size.setX(w).setY(h);
    color.setRed(r).setGreen(g).setBlue(b);
  }
}
