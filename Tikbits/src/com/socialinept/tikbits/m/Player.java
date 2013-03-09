/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class Player {

    public PhysicalObject physicalObject = null;
    String physicalObject_loc;

    protected Player() {
    }

    public boolean tryMove(float x, float y) {
        if (physicalObject != null) {
            physicalObject.vel.x = x;
            physicalObject.vel.y = y;
        }
        return true;
    }

    public boolean tryMoveX(float x) {
        if (physicalObject != null) {
            physicalObject.vel.x = x;
        }
        return true;
    }

    public boolean tryMoveY(float y) {
        if ((physicalObject != null && physicalObject.grounded) || y < 0) {
            physicalObject.vel.y = y;
        }
        return true;
    }

    public PhysicalObject getPhysicalObject() {
        return physicalObject;
    }

    public static Player readPlayer(String loc) {
        FileHandle handle = Gdx.files.internal(loc);
        Json json = new Json();
        Player out;
        out = json.fromJson(Player.class, handle);
        out.postJSON();
        return out;
    }

    public void postJSON() {
        if (physicalObject_loc != null) {
            physicalObject = PhysicalObject.readPhysicalObject(physicalObject_loc);
        }
    }
}
