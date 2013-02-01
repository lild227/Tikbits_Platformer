/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.screen;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer20;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.socialinept.tikbits.m.DrawingInstruction;
import com.socialinept.tikbits.m.GameProcessor;
import com.socialinept.tikbits.m.Map;

public class GameScreenRenderer {

    Map map;
    GameProcessor gp;
    OrthographicCamera cam;
    SpriteCache cache;
    SpriteBatch batch = new SpriteBatch(10000);
    ImmediateModeRenderer20 renderer = new ImmediateModeRenderer20(false, true, 0);
    int[][] blocks;
    TextureRegion tile;
    Animation bobLeft;
    Animation bobRight;
    Animation bobJumpLeft;
    Animation bobJumpRight;
    Animation bobIdleLeft;
    Animation bobIdleRight;
    Animation bobDead;
    Animation zap;
    TextureRegion cube;
    Animation cubeFixed;
    TextureRegion cubeControlled;
    TextureRegion dispenser;
    Animation spawn;
    Animation dying;
    TextureRegion spikes;
    Animation rocket;
    Animation rocketExplosion;
    TextureRegion rocketPad;
    TextureRegion endDoor;
    TextureRegion movingSpikes;
    TextureRegion laser;
    TexturePool texturePool;
    FPSLogger fps = new FPSLogger();
    int blockX = 16, blockY = 9;

    public GameScreenRenderer(Map map) {
        gp = GameProcessor.getGameProcessor();
        this.map = map;
        this.cam = new OrthographicCamera(blockX, blockY);
        this.cam.position.set(8, 3, 0);
        texturePool = new TexturePool();
        texturePool.getTexture("img/sprites/tikbit.png");
        texturePool.getTexture("img/maps/grass.png");
        texturePool.getTexture("img/maps/bg.png");
        //this.cache = new SpriteCache(this.map.tiles.length * this.map.tiles[0].length, false);
        //this.blocks = new int[(int)Math.ceil(this.map.tiles.length / 24.0f)][(int)Math.ceil(this.map.tiles[0].length / 16.0f)];
        //createAnimations();
        //reateBlocks();
    }
//	private void createBlocks () {
//		int width = map.tiles.length;
//		int height = map.tiles[0].length;
//		for (int blockY = 0; blockY < blocks[0].length; blockY++) {
//			for (int blockX = 0; blockX < blocks.length; blockX++) {
//				cache.beginCache();
//				for (int y = blockY * 16; y < blockY * 16 + 16; y++) {
//					for (int x = blockX * 24; x < blockX * 24 + 24; x++) {
//						if (x > width) continue;
//						if (y > height) continue;
//						int posX = x;
//						int posY = height - y - 1;
//						if (map.match(map.tiles[x][y], Map.TILE)) cache.add(tile, posX, posY, 1, 1);
//						if (map.match(map.tiles[x][y], Map.SPIKES)) cache.add(spikes, posX, posY, 1, 1);
//					}
//				}
//				blocks[blockX][blockY] = cache.endCache();
//			}
//		}
//		Gdx.app.debug("Cubocy", "blocks created");
//	}
//
//	private void createAnimations () {
//		this.tile = new TextureRegion(new Texture(Gdx.files.internal("tile.png")), 0, 0, 20, 20);
//		Texture bobTexture = new Texture(Gdx.files.internal("bob.png"));
//		TextureRegion[] split = new TextureRegion(bobTexture).split(20, 20)[0];
//		TextureRegion[] mirror = new TextureRegion(bobTexture).split(20, 20)[0];
//		for (TextureRegion region : mirror)
//			region.flip(true, false);
//		spikes = split[5];
//		bobRight = new Animation(0.1f, split[0], split[1]);
//		bobLeft = new Animation(0.1f, mirror[0], mirror[1]);
//		bobJumpRight = new Animation(0.1f, split[2], split[3]);
//		bobJumpLeft = new Animation(0.1f, mirror[2], mirror[3]);
//		bobIdleRight = new Animation(0.5f, split[0], split[4]);
//		bobIdleLeft = new Animation(0.5f, mirror[0], mirror[4]);
//		bobDead = new Animation(0.2f, split[0]);
//		split = new TextureRegion(bobTexture).split(20, 20)[1];
//		cube = split[0];
//		cubeFixed = new Animation(1, split[1], split[2], split[3], split[4], split[5]);
//		split = new TextureRegion(bobTexture).split(20, 20)[2];
//		cubeControlled = split[0];
//		spawn = new Animation(0.1f, split[4], split[3], split[2], split[1]);
//		dying = new Animation(0.1f, split[1], split[2], split[3], split[4]);
//		dispenser = split[5];
//		split = new TextureRegion(bobTexture).split(20, 20)[3];
//		rocket = new Animation(0.1f, split[0], split[1], split[2], split[3]);
//		rocketPad = split[4];
//		split = new TextureRegion(bobTexture).split(20, 20)[4];
//		rocketExplosion = new Animation(0.1f, split[0], split[1], split[2], split[3], split[4], split[4]);
//		split = new TextureRegion(bobTexture).split(20, 20)[5];
//		endDoor = split[2];
//		movingSpikes = split[0];
//		laser = split[1];
//	}
    float stateTime = 0;
    Vector3 lerpTarget = new Vector3();

    public void render(float deltaTime) {
        cam.position.lerp(lerpTarget.set(gp.player.physicalObject.pos.x, 3, 0), 2f * deltaTime);
        cam.update();


        cam.update(false);
        renderer.begin(cam.combined, GL10.GL_LINES);
        renderer.end();
//		cache.setProjectionMatrix(cam.combined);
//		Gdx.gl.glDisable(GL10.GL_BLEND);
//		cache.begin();
//		int b = 0;
//		for (int blockY = 0; blockY < 4; blockY++) {
//			for (int blockX = 0; blockX < 6; blockX++) {
//				cache.draw(blocks[blockX][blockY]);
//				b++;
//			}
//		}
//		cache.end();
//		Gdx.app.debug("Cubocy", "blocks: " + b);
        stateTime += deltaTime;
        batch.setProjectionMatrix(cam.combined);
        batch.begin();
        Array<DrawingInstruction> di = gp.getDrawingInstructions();
        for (int i = 0; i < di.size; i++) {
            di.get(i).update();
            switch (di.get(i).command) {
                case Line:
                    renderer.color(di.get(i).fcolor_R, di.get(i).fcolor_G, di.get(i).fcolor_B, di.get(i).fcolor_A);
                    renderer.vertex(di.get(i).bounds.x,
                            di.get(i).bounds.y, 0);
                    renderer.color(di.get(i).fcolor_R, di.get(i).fcolor_G, di.get(i).fcolor_B, di.get(i).fcolor_A);
                    renderer.vertex(di.get(i).bounds.x + di.get(i).bounds.x, di.get(i).bounds.y + di.get(i).bounds.y, 0);
                    break;
                case Image:
                    if (di.get(i).relative) {
                        batch.draw(texturePool.getTexture(di.get(i).resource), 
                                cam.position.x - (cam.viewportWidth / 2) - di.get(i).bounds.x, 
                                di.get(i).bounds.y, 20, 12);
                    } else if (di.get(i).repeat_right) {
                        float count = 1 + blockX / di.get(i).bounds.width;
                        float startX = ((int)((cam.position.x - (cam.viewportWidth / 2)) / di.get(i).bounds.width)) * di.get(i).bounds.width + di.get(i).bounds.x;
                        for (int j = 0; j < count; j++) {
                            batch.draw(texturePool.getTexture(di.get(i).resource), 
                                    startX + di.get(i).bounds.width * j, di.get(i).bounds.y, 
                                    di.get(i).bounds.width, di.get(i).bounds.height);
                        }
                    } else {
                        batch.draw(texturePool.getTexture(di.get(i).resource), di.get(i).bounds.x, di.get(i).bounds.y, di.get(i).bounds.width, di.get(i).bounds.height);
                    }
            }
        }
        batch.end();

        fps.log();
    }

    public void dispose() {
//		cache.dispose();
//		batch.dispose();
//		tile.getTexture().dispose();
//		cube.getTexture().dispose();
    }
}
