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
public interface ICollidable {
    public Vector2 getVelocity();
    public Vector2 getAcceleration();
    public CollidableType getCollidableType();
    public Rectangle getCollidableRectangle();
    public Rectangle getCollidableLine();
    public Rectangle getCollidableSphere();
    public void isColliding(ICollidable c);
    public void willCollide(ICollidable c);
 }
