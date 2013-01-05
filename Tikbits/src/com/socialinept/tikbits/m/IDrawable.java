/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import java.util.ArrayList;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public interface IDrawable {
    public DrawingInstruction[] getDrawingInstructions();
    public int getDrawingInstructionsCount();
 }
