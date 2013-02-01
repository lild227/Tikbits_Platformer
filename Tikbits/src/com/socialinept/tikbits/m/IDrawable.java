/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.utils.Array;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public interface IDrawable {
    public Array<DrawingInstruction> getDrawingInstructions();
    public int getDrawingInstructionsCount();
 }
