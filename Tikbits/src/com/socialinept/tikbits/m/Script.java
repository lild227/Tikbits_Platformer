/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class Script {
    GameProcessor gameProcessor;
    ScriptInstruction[] si;
    
    public Script(){
        
    }
    public GameProcessor getGameProcessor() {
        return gameProcessor;
    }

    public void setGameProcessor(GameProcessor gameProcessor) {
        this.gameProcessor = gameProcessor;
    }
    
    public void init(){
        
    }
    
    public void applyScript(GameProcessor gp){
        for(ScriptInstruction s: si){
            s.update(gp);
        }
    }
    public static Script readScript(String loc) {
        FileHandle handle = Gdx.files.internal(loc);
        Json json = new Json();
        Script out;
        out = json.fromJson(Script.class, handle);
        return out;
    }
    public static void main(String[] args){
        Json json = new Json();
        Script script = new Script();
        script.si = new ScriptInstruction[2];
        
        script.si[0] = new ScriptInstruction();
        script.si[0].scriptCommand = ScriptCommand.AddPhysicalObject;
        script.si[0].resource = "sprites/tikbit/json";
        script.si[0].name = "player";
        script.si[0].bounds_x = 8-.2f;
        script.si[0].pos_x = 8f;
        
        script.si[1] = new ScriptInstruction();
        script.si[1].scriptCommand = ScriptCommand.SetPlayerPhysicalObject;
        script.si[1].type = "tikbit";
        script.si[1].name = "player";
        
        String s = json.prettyPrint(script);
        System.out.println(s);
    }
}
