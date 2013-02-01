/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class Section {
    
    Array<DrawingInstruction> di;
    int diCount;
    Rectangle rect;
    
    public void setRectangle(Rectangle r){
        rect = r;
    }
    public Array<DrawingInstruction> getDrawingInstructions() {
        return di;
    }
    public int getDrawingInstructionsCount() {
        return diCount;
    }
    public static Section readSection(String loc){
        FileHandle handle = Gdx.files.internal(loc);
        Json json = new Json();
        return json.fromJson(Section.class, handle);
    }
}
