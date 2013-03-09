/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public interface IUpdatable {
    public abstract void preupdate(float time);
    public abstract void update(float time);
    public abstract void postupdate(float time);
}
