/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class PhysicalObject implements IUpdatable, IDrawable {

    static int IDLE = 0;
    static int RUN = 1;
    static int AIRBORN = 2;
    static int SPAWN = 3;
    static int DYING = 4;
    static int DEAD = 5;
    static int LEFT = -1;
    static int RIGHT = 1;
    static float ACCELERATION = 2f;
    static float JUMP_VELOCITY = 10;
    static float GRAVITY = 20.0f;
    static float MAX_VEL = 6f;
    static float DAMP = 0.90f;
    public Vector2 pos = new Vector2();
    public Vector2 accel = new Vector2();
    public Vector2 vel = new Vector2();
    public Rectangle bounds = new Rectangle();
    public String name = "";
    public int id = 0;
    int state = SPAWN;
    float stateTime = 0;
    int dir = LEFT;
    int diCount = 0;
    DrawingInstruction[] di;
    boolean grounded = false;
    
    public PhysicalObject(){
        
    }
    
    /**
     *
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        accel.y = -GRAVITY;
        accel.mul(deltaTime);
        vel.add(accel.x, accel.y);
        if (accel.x == 0) {
            vel.x *= DAMP;
        }
        if (vel.x > MAX_VEL) {
            vel.x = MAX_VEL;
        }
        if (vel.x < -MAX_VEL) {
            vel.x = -MAX_VEL;
        }
        if (pos.y <= 0 && vel.y < 0) {
            vel.y = 0;
        }
        vel.mul(deltaTime);
        tryMove();
        vel.mul(1.0f / deltaTime);

        if (state == SPAWN) {
            if (stateTime > 0.4f) {
                state = IDLE;
            }
        }

        if (state == DYING) {
            if (stateTime > 0.4f) {
                state = DEAD;
            }
        }

        stateTime += deltaTime;
    }

    @Override
    public int getDrawingInstructionsCount() {
        return diCount;
    }

    @Override
    public DrawingInstruction[] getDrawingInstructions() {
        return di;
    }

    private void tryMove() {
        bounds.x += vel.x;
        bounds.y += vel.y;
        pos.x = bounds.x - 0.2f;
        pos.y = Math.max(0, bounds.y);
    }

    public static PhysicalObject readPhysicalObject(String loc) {
        FileHandle handle = Gdx.files.internal(loc);
        Json json = new Json();
        PhysicalObject out;
        out = json.fromJson(PhysicalObject.class, handle);
        out.postJSON();
        return out;
    }
    public void postJSON() {
        updatePhysicalObjectDrawingInstructions();
    }
    public void updatePhysicalObjectDrawingInstructions(){
        for(int i = 0; i < di.length; i++) {
            if(di[i] != null && di[i] instanceof PhysicalObjectDrawingInstruction) {
                ((PhysicalObjectDrawingInstruction)di[i]).setPhysicalObject(this);
            }
        }
    }
    public static void main(String[] args){
        PhysicalObject po = new PhysicalObject();
        po.di = new DrawingInstruction[1];
        Json json = new Json();
        //po.di[0] = new PhysicalObjectDrawingInstruction(po);
        String s = json.prettyPrint(po);
        System.out.println(s);        
    }
}

class PhysicalObjectDrawingInstruction extends DrawingInstruction {

    PhysicalObject po;

    public PhysicalObjectDrawingInstruction() {
    }

    public void setPhysicalObject(PhysicalObject po) {
        this.po = po;
    }
    
    @Override
    public void update() {
        bounds.x = po.bounds.x;
        bounds.y = po.bounds.y;
        bounds.width = 1;
        bounds.height = 1;
    }
}
