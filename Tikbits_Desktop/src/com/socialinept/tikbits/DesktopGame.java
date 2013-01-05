package com.socialinept.tikbits;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopGame {
        public static void main (String[] args) {
          LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
          cfg.title = "Lestroga";
          cfg.useGL20 = true;
          cfg.width = 800;
          cfg.height = 450;
          new LwjglApplication(new Tikbits(), cfg);
        }
}