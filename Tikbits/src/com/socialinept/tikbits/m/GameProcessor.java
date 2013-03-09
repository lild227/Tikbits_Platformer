/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.utils.Array;
import java.util.Arrays;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public final class GameProcessor {

    public static GameProcessor gameProcessor = null;
    public Array<PhysicalObject> physicalObjects = null;
    public Array<IUpdatable> updatables = null;
    public Array<IDrawable> drawables = null;
    public Array<ICollidable> collidables = null;
    CollidableEngine collidableEngine = null;
    public Player player = null;
    public Map map = null;
    public Script script = null;

    private GameProcessor() {
        player = new Player();
        script = Script.readScript("scripts/main/1");
        physicalObjects = new Array<PhysicalObject>();
        updatables = new Array<IUpdatable>();
        drawables = new Array<IDrawable>();
        collidables = new Array<ICollidable>();
        map = Map.readMap("maps/1/json");
        map.setGameProcessor(this);
        drawables.add(map);
        collidableEngine = new CollidableEngine();
        runScript();
    }

    public void runScript() {
        script.setGameProcessor(this);
        script.applyScript(this);
        Animation a = new Animation();
        a.setPysicalObject(player.physicalObject);
        updatables.add(a);
    }

    public void setPlayer(Player p) {
        if (player != null) {
            drawables.removeValue(player.physicalObject, true);
        }
    }

    public void add(Object o) {
        if (o instanceof IDrawable) {
            drawables.add((IDrawable) o);
        }
    }

    public void update(float delta) {
        for (IUpdatable u : updatables) {
            u.preupdate(delta);
        }
        for (int i = 0; i < collidables.size; i++) {
            for(int j = i+1; j < collidables.size; j++){
                collidableEngine.processCollidables(collidables.get(i),collidables.get(j));
            }
        }
        for (IUpdatable u : updatables) {
            u.update(delta);
        }
        for (IUpdatable u : updatables) {
            u.postupdate(delta);
        }
    }

    public static GameProcessor getGameProcessor() {
        if (gameProcessor == null) {
            gameProcessor = new GameProcessor();
        }
        return gameProcessor;
    }

    public Array<PhysicalObject> getPhysicalObjects() {
        return physicalObjects;
    }

    /**
     *
     * @return
     */
    public Array<DrawingInstruction> getDrawingInstructions() {
        Array<DrawingInstruction> out;
        int count = 0;
        for (IDrawable d : drawables) {
            count += d.getDrawingInstructionsCount();
        }
        out = new Array<DrawingInstruction>();
        count = 0;
        for (IDrawable d : drawables) {
            Array<DrawingInstruction> temp = d.getDrawingInstructions();
            for (int i = 0; i < temp.size; i++) {
                out.add(temp.get(i));
            }
        }
        DrawingInstructionComparator dic = new DrawingInstructionComparator();
        //Arrays.sort(out, dic);
        return out;
    }
}
