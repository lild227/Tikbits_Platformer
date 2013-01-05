/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public abstract class PhysicalObject implements IUpdatable, IDrawable {

    static int IDLE = 0;
    static int RUN = 1;
    static int AIRBORN = 2;
    static int SPAWN = 3;
    static int DYING = 4;
    static int DEAD = 5;
    static int LEFT = -1;
    static int RIGHT = 1;
    static float ACCELERATION = 2f;
    static float JUMP_VELOCITY = 10;
    static float GRAVITY = 20.0f;
    static float MAX_VEL = 6f;
    static float DAMP = 0.90f;
    public Vector2 pos = new Vector2();
    public Vector2 accel = new Vector2();
    public Vector2 vel = new Vector2();
    public Rectangle bounds = new Rectangle();
    public String name = "";
    public int id = 0;
    int state = SPAWN;
    float stateTime = 0;
    int dir = LEFT;
    boolean grounded = false;

    public void update(float deltaTime) {
        accel.y = -GRAVITY;
        accel.mul(deltaTime);
        vel.add(accel.x, accel.y);
        if (accel.x == 0)
            vel.x *= DAMP;
        if (vel.x > MAX_VEL)
            vel.x = MAX_VEL;
        if (vel.x < -MAX_VEL)
            vel.x = -MAX_VEL;
        if(pos.y <= 0 && vel.y < 0)
            vel.y = 0;
        vel.mul(deltaTime);
        tryMove();
        vel.mul(1.0f / deltaTime);

        if (state == SPAWN) {
            if (stateTime > 0.4f) {
                state = IDLE;
            }
        }

        if (state == DYING) {
            if (stateTime > 0.4f) {
                state = DEAD;
            }
        }

        stateTime += deltaTime;
    }

    private void tryMove() {
        bounds.x += vel.x;
        bounds.y += vel.y;
//		fetchCollidableRects();
//		for (int i = 0; i < r.length; i++) {
//			Rectangle rect = r[i];
//			if (bounds.overlaps(rect)) {
//				if (vel.x < 0)
//					bounds.x = rect.x + rect.width + 0.01f;
//				else
//					bounds.x = rect.x - bounds.width - 0.01f;
//				vel.x = 0;
//			}
//		}
//
//		bounds.y += vel.y;
//		fetchCollidableRects();
//		for (int i = 0; i < r.length; i++) {
//			Rectangle rect = r[i];
//			if (bounds.overlaps(rect)) {
//				if (vel.y < 0) {
//					bounds.y = rect.y + rect.height + 0.01f;
//					grounded = true;
//					if (state != DYING && state != SPAWN) state = Math.abs(accel.x) > 0.1f ? RUN : IDLE;
//				} else
//					bounds.y = rect.y - bounds.height - 0.01f;
//				vel.y = 0;
//			}
//		}

        pos.x = bounds.x - 0.2f;
        pos.y = Math.max(0, bounds.y);
    }
}
