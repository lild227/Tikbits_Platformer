/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

/**
 *
 * @author lild227
 */
public class Animation implements IUpdatable{
    PhysicalObject po;
    AnimationCondition startCondition, endCondition;
    public void setPysicalObject(PhysicalObject p){
        po = p;
    }
    /**
     *
     * @param t
     */
    @Override
    public void update(float t){
        if(po.pos.x < 20) {
            po.vel.x = 5;
        }
    }
}
