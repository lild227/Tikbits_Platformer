/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class PhysicalObject implements IUpdatable, IDrawable, ICollidable {

    static float ACCELERATION = 2f;
    static float JUMP_VELOCITY = 10;
    static float GRAVITY = 20.0f;
    static float MAX_VEL = 6f;
    static float DAMP = 0.90f;
    float[] enviro_bounds = new float[4];
    public Vector2 pos = new Vector2();
    public Rectangle bounds = new Rectangle();
    public Vector2 accel = new Vector2();
    public Vector2 vel = new Vector2();
    public String type = "";
    public String name = "";
    public int id = 0;
    float stateTime = 0;
    int diCount = 0;
    Array<DrawingInstruction> di;
    boolean grounded = false;

    public PhysicalObject() {
        accel.y = -GRAVITY;
        enviro_bounds[0] = Float.MAX_VALUE;
        enviro_bounds[1] = Float.MAX_VALUE;
        enviro_bounds[2] = -Float.MAX_VALUE;
        enviro_bounds[3] = -Float.MAX_VALUE;
    }

    public void preupdate(float deltaTime) {
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
        vel.mul(deltaTime);
    }

    /**
     *
     * @param deltaTime
     */
    @Override
    public void update(float deltaTime) {
        tryMove();
    }

    @Override
    public void postupdate(float deltaTime) {
        grounded = bounds.y == (enviro_bounds[2]+bounds.height);
        vel.mul(1.0f / deltaTime);
        accel.mul(1.0f / deltaTime);
        enviro_bounds[0] = Float.MAX_VALUE;
        enviro_bounds[1] = Float.MAX_VALUE;
        enviro_bounds[2] = -Float.MAX_VALUE;
        enviro_bounds[3] = -Float.MAX_VALUE;
    }

    @Override
    public int getDrawingInstructionsCount() {
        return diCount;
    }

    @Override
    public Array<DrawingInstruction> getDrawingInstructions() {
        return di;
    }

    private void tryMove() {
        bounds.x = vel.x > 0
                ? Math.min(bounds.x + vel.x, enviro_bounds[1]-bounds.width)
                : Math.max(bounds.x + vel.x, enviro_bounds[3]);
        bounds.y = vel.y > 0
                ? Math.min(bounds.y + vel.y, enviro_bounds[0])
                : Math.max(bounds.y + vel.y, enviro_bounds[2]+bounds.height);
        pos.x = bounds.x - 0.2f;
        pos.y = bounds.y;
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

    public void updatePhysicalObjectDrawingInstructions() {
        for (int i = 0; i < di.size; i++) {
            if (di.get(i) != null && di.get(i) instanceof PhysicalObjectDrawingInstruction) {
                ((PhysicalObjectDrawingInstruction) di.get(i)).setPhysicalObject(this);
            }
        }
    }

    public static void main(String[] args) {
        PhysicalObject po = new PhysicalObject();
        po.di = new Array<DrawingInstruction>();
        Json json = new Json();
        //po.di[0] = new PhysicalObjectDrawingInstruction(po);
        String s = json.prettyPrint(po);
        System.out.println(s);
    }

    @Override
    public void isColliding(ICollidable c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void willCollide(ICollidable c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Vector2 getVelocity() {
        return vel;
    }

    @Override
    public Vector2 getAcceleration() {
        return accel;
    }

    @Override
    public Rectangle getCollidableRectangle() {
        return bounds;
    }

    @Override
    public CollidableType getCollidableType() {
        return CollidableType.RECTANGLE;
    }

    @Override
    public Vector2[] getCollidableLine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rectangle getCollidableSphere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int getPriority() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEnvironmentBoundLeft(float f) {
        enviro_bounds[3] = f;
    }

    @Override
    public void setEnvironmentBoundRight(float f) {
        enviro_bounds[1] = f;
    }

    @Override
    public void setEnvironmentBoundTop(float f) {
        enviro_bounds[0] = f;
    }

    @Override
    public void setEnvironmentBoundBottom(float f) {
        enviro_bounds[2] = f;
    }

    @Override
    public float getEnvironmentBoundLeft() {
        return enviro_bounds[3];
    }

    @Override
    public float getEnvironmentBoundRight() {
        return enviro_bounds[1];
    }

    @Override
    public float getEnvironmentBoundTop() {
        return enviro_bounds[0];
    }

    @Override
    public float getEnvironmentBoundBottom() {
        return enviro_bounds[2];
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
