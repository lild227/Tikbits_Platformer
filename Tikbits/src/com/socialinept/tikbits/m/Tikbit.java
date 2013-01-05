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
public class Tikbit extends PhysicalObject{
    int  diCount = 1;
    DrawingInstruction[] di;
    public Tikbit(){
       name = "tikbit";
       id = 0;
       bounds.width = 1;
       bounds.height = 1;
       di = new DrawingInstruction[1];
        DrawingInstruction dit = new DrawingInstruction();
        dit.command = DrawCommand.Image;
        dit.resource = "data/img/sprites/tikbit.png";
        di[0] = dit;
    }

    @Override
    public DrawingInstruction[] getDrawingInstructions() {
        DrawingInstruction dit = di[0];
        dit.bounds.x = bounds.x;
        dit.bounds.y = bounds.y;
        dit.bounds.width = 1;
        dit.bounds.height = 1;
        return di;
    }

    @Override
    public int getDrawingInstructionsCount() {
        return diCount;
    }
}
