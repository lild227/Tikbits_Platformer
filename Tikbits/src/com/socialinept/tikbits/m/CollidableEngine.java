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
public class CollidableEngine {

    public CollidableEngine() {
    }

    public void processCollidables(ICollidable c1, ICollidable c2) {
        switch (c1.getCollidableType()) {
            case RECTANGLE:
                switch (c2.getCollidableType()) {
                    case RECTANGLE:
                        break;
                    case LINE:
                        lineToRect(c2, c1);
                        break;
                }
                break;
            case LINE:
                switch (c2.getCollidableType()) {
                    case RECTANGLE:
                        lineToRect(c1, c2);
                        break;
                    case LINE:
                        break;
                }
                break;
        }
    }

    public void lineToRect(ICollidable c0, ICollidable c1) {
        Vector2[] line = c0.getCollidableLine();
        Rectangle rect = c1.getCollidableRectangle();
        float dx = line[1].x - line[0].x, dy = line[1].y - line[0].y;
        if (dy == 0) {
            if (rect.y - rect.height >= line[0].y
                    && rect.y - rect.height + c1.getVelocity().y <= line[0].y) {
                c1.setEnvironmentBoundBottom(line[0].y);
            }
        }
    }
}
