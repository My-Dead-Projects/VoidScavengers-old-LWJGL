package com.mikefdorst.voidscavengers.model;

public class Vec2D {
  private float x, y;
  
  public float x() {
    return x;
  }
  
  public float y() {
    return y;
  }
  
  public Vec2D setX(double val) {
    x = (float) val;
    return this;
  }

  public Vec2D setY(double val) {
    y = (float) val;
    return this;
  }
  
  public Vec2D() {
    
  }
  
  public Vec2D(double x, double y) {
    this.x = (float) x;
    this.y = (float) y;
  }
}
