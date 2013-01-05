/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import java.util.ArrayList;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class Map implements IUpdatable, IDrawable {

    DrawingInstruction[] di;
    String name;
    int diCount;

    protected Map() {
//        di = new DrawingInstruction[17];
//        for (int i = 1; i < 17; i++) {
//            di[i] = new DrawingInstruction();
//            di[i].command = DrawCommand.Image;
//            di[i].resource = "data/img/maps/grass.png";
//            di[i].pos.x = (i-1) * 3.5f;
//            di[i].pos.y = -1.5f;
//            di[i].bounds.x = 0;
//            di[i].bounds.width = 4;
//            di[i].bounds.y = 20;
//            di[i].bounds.height = 2;
//        }
//        di[0] = new DrawingInstruction();
//        di[0].command = DrawCommand.Image;
//        di[0].resource = "data/img/maps/bg.png";
//        di[0].pos.x = 0;
//        di[0].pos.y = 0;
//        di[0].bounds.x = 10;
//        di[0].bounds.y = 0;
//        di[0].relative = true;
    }

    public void update(float delta) {
    }

    public DrawingInstruction[] getDrawingInstructions() {
        return di;
    }

    @Override
    public int getDrawingInstructionsCount() {
        return diCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map readMap(String loc){
        FileHandle handle = Gdx.files.internal(loc);
        Json json = new Json();
        return json.fromJson(Map.class, handle);
    }
    public static void main(String[] args){
        Json json = new Json();
        String s = json.prettyPrint(new Map());
        System.out.println(s);
        Map m = json.fromJson(Map.class, s);
    }
}
