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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rectangle getCollidableLine() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Rectangle getCollidableSphere() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
