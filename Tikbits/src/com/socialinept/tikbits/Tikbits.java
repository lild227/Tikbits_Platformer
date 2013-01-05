package com.socialinept.tikbits;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.socialinept.tikbits.screen.GameScreen;

public class Tikbits extends Game {
    @Override
        public void create () {
		setScreen(new GameScreen(this));
        }
        public ApplicationListener getApplicationListener(){
            return this;
        }
}