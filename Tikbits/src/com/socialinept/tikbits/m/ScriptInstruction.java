/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class ScriptInstruction {

    String type, name, resource;
    Float bounds_x, bounds_y, pos_x, pos_y;
    ScriptCommand scriptCommand = ScriptCommand.AddPhysicalObject;

    public ScriptInstruction() {
    }

    public boolean update(GameProcessor gp) {
        PhysicalObject physicalObject = null;
        switch (scriptCommand) {
            case AddPhysicalObject:
                physicalObject = PhysicalObject.readPhysicalObject(resource);
                physicalObject.name = name;
                gp.physicalObjects.add(physicalObject);
                gp.updatables.add(physicalObject);
                gp.drawables.add(physicalObject);
                updatePhysicalObject(physicalObject);
                break;
            case SetPlayerPhysicalObject:
                for (PhysicalObject po : gp.physicalObjects) {
                    if (po.type.equals(type) && po.name.equals(name)) {
                        gp.player.physicalObject = po;
                        return true;
                    }
                }
                return false;
            case UpdatePhysicalObject:
                for (PhysicalObject po : gp.physicalObjects) {
                    if (po.type.equals(type) && po.name.equals(name)) {
                        physicalObject = po;
                        break;
                    }
                }
                if (physicalObject == null) {
                    return false;
                }
                updatePhysicalObject(physicalObject);
        }
        return true;
    }

    private void updatePhysicalObject(PhysicalObject physicalObject) {
        if (bounds_x != null) {
            physicalObject.bounds.x = bounds_x;
        }
        if (bounds_y != null) {
            physicalObject.bounds.y = bounds_y;
        }
        if (pos_x != null) {
            physicalObject.pos.x = pos_x;
        }
        if (pos_y != null) {
            physicalObject.pos.y = pos_y;
        }
    }
}
