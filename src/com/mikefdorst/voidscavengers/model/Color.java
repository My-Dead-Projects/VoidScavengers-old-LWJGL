package com.mikefdorst.voidscavengers.model;

import java.util.Random;

public class Color {
  private float red, green, blue, alpha;
  
  public float red() {
    return red;
  }
  
  public float green() {
    return green;
  }
  
  public float blue() {
    return blue;
  }
  
  public float alpha() {
    return alpha;
  }
  
  public Color setRed(double red) {
    this.red = (float) red;
    return this;
  }
  
  public Color setGreen(double green) {
    this.green = (float) green;
    return this;
  }
  
  public Color setBlue(double blue) {
    this.blue = (float) blue;
    return this;
  }
  
  public Color setAlpha(double alpha) {
    this.alpha = (float) alpha;
    return this;
  }
  
  public Color() {
    red = green = blue = alpha = 1;
  }
  
  public Color(float r, float g, float b) {
    red = r;
    green = g;
    blue = b;
    alpha = 1;
  }
  
  public Color(double r, double g, double b, double a) {
    red = (float) r;
    green = (float) g;
    blue = (float) b;
    alpha = (float) a;
  }
  
  public void randomize() {
    Random rgen = new Random();
    red = rgen.nextFloat();
    green = rgen.nextFloat();
    blue = rgen.nextFloat();
  }
  
  public static Color random() {
    Color c = new Color();
    c.randomize();
    return c;
  }
  
  public static final Color whiteColor = new Color(1, 1, 1);
  public static final Color blackColor = new Color(0, 0, 0);
  public static final Color redColor = new Color(1, 0, 0);
  public static final Color greenColor = new Color(0, 1, 0);
  public static final Color blueColor = new Color(0, 0, 1);
}
