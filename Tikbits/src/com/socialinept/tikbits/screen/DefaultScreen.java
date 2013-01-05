/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.screen;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public abstract class DefaultScreen  implements Screen {
	Game game;

	public DefaultScreen (Game game) {
		this.game = game;
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void show () {
	}

	@Override
	public void hide () {
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}