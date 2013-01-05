/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import java.util.Arrays;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class GameProcessor {
    public static GameProcessor gameProcessor = null;
    public Array<PhysicalObject> physicalObjects = null;
    public Array<IUpdatable> updatables = null;
    public Array<IDrawable> drawables = null;
    public Player player = null;
    public Map map = null;
    private GameProcessor(){
        player = new Player();
        player.physicalObject = new Tikbit();
        player.physicalObject.bounds.x = 8-.2f;
        player.physicalObject.pos.x = 8;
        physicalObjects = new Array<PhysicalObject>();
        updatables = new Array<IUpdatable>();
        drawables = new Array<IDrawable>();
        physicalObjects.add(player.physicalObject);
        updatables.add(player.physicalObject);
        map = Map.readMap("data/maps/1/json");
        //map = new Map();
        drawables.add(map);
        drawables.add(player.physicalObject);
    }
    public void add(Object o){
        if(o instanceof IDrawable)
            drawables.add((IDrawable)o);
    }
    public void update(float delta){
        for(IUpdatable u: updatables)
            u.update(delta);
    }
    public static GameProcessor getGameProcessor(){
        if(gameProcessor == null)
            gameProcessor = new GameProcessor();
        return gameProcessor;
    }
    public Array<PhysicalObject> getPhysicalObjects(){
        return physicalObjects;
    }
    public DrawingInstruction[] getDrawingInstructions(){
        DrawingInstruction[] out = null;
        int count = 0;
        for(IDrawable d: drawables)
            count+=d.getDrawingInstructionsCount();
        out = new DrawingInstruction[count];
        count = 0;
        for(IDrawable d: drawables){
            DrawingInstruction[] temp = d.getDrawingInstructions();
            for(int i = 0; i < temp.length; i++)
                out[count++] = temp[i];
        }
        DrawingInstructionComparator dic = new DrawingInstructionComparator();
        Arrays.sort(out, dic);
        return out;
    }

}
