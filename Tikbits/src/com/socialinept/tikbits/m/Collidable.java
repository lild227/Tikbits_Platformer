/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author lild227
 */
public class Collidable implements ICollidable{
    Rectangle bounds;
    Vector2 vel, acc;
    Vector2[] line;
    CollidableType collidableType;
    public Collidable(){
        bounds = new Rectangle();
        vel = new Vector2();
        acc = new Vector2();
    }
    @Override
    public Vector2 getVelocity() {
        return vel;
    }

    @Override
    public Vector2 getAcceleration() {
        return acc;
    }

    @Override
    public Rectangle getCollidableRectangle() {
        return bounds;
    }

    @Override
    public void isColliding(ICollidable c) {
    }

    @Override
    public void willCollide(ICollidable c) {
    }

    @Override
    public CollidableType getCollidableType() {
        return collidableType;
    }

    @Override
    public Vector2[] getCollidableLine() {
        return line;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEnvironmentBoundRight(float f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEnvironmentBoundTop(float f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setEnvironmentBoundBottom(float f) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getEnvironmentBoundLeft() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getEnvironmentBoundRight() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getEnvironmentBoundTop() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public float getEnvironmentBoundBottom() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
