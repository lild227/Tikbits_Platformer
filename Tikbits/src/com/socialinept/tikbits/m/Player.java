/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class Player {
    public PhysicalObject physicalObject = null;
    protected Player(){
    }

    public boolean tryMove(float x, float y){
        if(physicalObject != null){
            physicalObject.vel.x = x;
            physicalObject.vel.y = y;
        }
        return true;
    }
    public boolean tryMoveX(float x){
        if(physicalObject != null)
            physicalObject.vel.x = x;
        return true;
    }
    public boolean tryMoveY(float y){
        if((physicalObject != null && physicalObject.pos.y == 0) || y < 0)
            physicalObject.vel.y = y;
        return true;
    }
    public PhysicalObject getPhysicalObject(){
        return physicalObject;
    }
}
