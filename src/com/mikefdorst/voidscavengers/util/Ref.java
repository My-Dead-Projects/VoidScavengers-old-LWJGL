package com.mikefdorst.voidscavengers.util;

public class Ref {
  public static class main_window {
    public static final String title = "Void Scavengers";
    public static final int width = 1200;
    public static final int height = 800;
    public static final double aspect_ratio = (double)width / (double)height;
    public static final int target_fps = 60;
  }
  public static class path_to {
    private static final String shader_base_path = "src/com/mikefdorst/voidscavengers/view/shader/";
    public static String shader(String filename) {
      return shader_base_path + filename;
    }
  }
  public static class shader {
    public static final int info_log_max_length = 4096;
    public static final String translation_var_name = "translation";
  }
}
