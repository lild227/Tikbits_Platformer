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
    public int getPriority();
    public CollidableType getCollidableType();
    public Rectangle getCollidableRectangle();
    public Vector2[] getCollidableLine();
    public Rectangle getCollidableSphere();
    public void setEnvironmentBoundLeft(float f);
    public void setEnvironmentBoundRight(float f);
    public void setEnvironmentBoundTop(float f);
    public void setEnvironmentBoundBottom(float f);
    public float getEnvironmentBoundLeft();
    public float getEnvironmentBoundRight();
    public float getEnvironmentBoundTop();
    public float getEnvironmentBoundBottom();
    public void isColliding(ICollidable c);
    public void willCollide(ICollidable c);
 }
