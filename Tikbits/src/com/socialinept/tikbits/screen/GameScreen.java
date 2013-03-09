/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.socialinept.tikbits.m.GameProcessor;
import com.socialinept.tikbits.m.Map;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class GameScreen extends DefaultScreen {

    Map map;
    GameScreenRenderer renderer;
    //OnscreenControlRenderer controlRenderer;
    GameProcessor gameProcessor = null;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        gameProcessor = GameProcessor.getGameProcessor();
        map = gameProcessor.map;
        renderer = new GameScreenRenderer(map);
    }
    boolean pointerDown = false;
    float x0, y0, x1, y1;
    private void processKeys() {
        if(Gdx.input.isTouched(0) && !pointerDown){
            x0 = Gdx.input.getX(0);
            y0 = Gdx.input.getY(0);
            pointerDown = true;
        }
        if(!Gdx.input.isTouched(0)) {
            pointerDown = false;
        }
        if(pointerDown && x0 > Gdx.input.getX(0)) {
            gameProcessor.player.tryMove(-10, 0);
        }
        if(pointerDown && x0 < Gdx.input.getX(0)) {
            gameProcessor.player.tryMove(10, 0);
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            gameProcessor.player.tryMoveX(10);
        }
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            gameProcessor.player.tryMoveX(-10);
        }
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            gameProcessor.player.tryMoveY(15);
        }
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            gameProcessor.player.tryMoveY(-2);
        }
    }

    @Override
    public void render(float delta) {
        processKeys();
        delta = Math.min(0.06f, Gdx.graphics.getDeltaTime());
        gameProcessor.update(delta);
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        renderer.render(delta);
        int n = Runtime.getRuntime().availableProcessors();
        System.out.println(n+" "+java.lang.Thread.activeCount());
        //controlRenderer.render();

//		if (map.bob.bounds.overlaps(map.endDoor.bounds)) {
//			game.setScreen(new GameOverScreen(game));
//		}

//		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
//			game.setScreen(new MainMenu(game));
//		}
    }

    @Override
    public void hide() {
        Gdx.app.debug("Cubocy", "dispose game screen");
        renderer.dispose();
        //controlRenderer.dispose();
    }
}
