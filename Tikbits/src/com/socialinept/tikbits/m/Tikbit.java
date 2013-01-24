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
public class Tikbit extends PhysicalObject{
    public Tikbit(){
       name = "tikbit";
    }

    public static Tikbit readTikbit(String loc){
        FileHandle handle = Gdx.files.internal(loc);
        Json json = new Json();
        return json.fromJson(Tikbit.class, handle);
    }

}
