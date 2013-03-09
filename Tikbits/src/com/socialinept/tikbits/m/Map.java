/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.OrderedMap;


/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class Map implements IUpdatable, IDrawable {

    Array<ExtendedRectangle> sectionResources;
    OrderedMap<ExtendedRectangle, Section> sections;
    String name;
    int diCount;
    GameProcessor gameProcessor;

    protected Map() {
        sections = new OrderedMap<ExtendedRectangle, Section>();
    }

    @Override
    public void preupdate(float delta) {
    }
    @Override
    public void update(float delta) {
    }
    @Override
    public void postupdate(float delta) {
    }

    public GameProcessor getGameProcessor() {
        return gameProcessor;
    }

    public void setGameProcessor(GameProcessor gameProcessor) {
        this.gameProcessor = gameProcessor;
        for(Section s: sections.values()){
            for(ICollidable c: s.c)
                gameProcessor.collidables.add(c);
        }
    }
    
    @Override
    public Array<DrawingInstruction> getDrawingInstructions() {
        Array<DrawingInstruction> diList = new Array<DrawingInstruction>();
        for (ExtendedRectangle r : sections.keys()) {
            Array<DrawingInstruction> temp = sections.get(r).getDrawingInstructions();
            diList.addAll(temp);
        }
        return diList;
    }

    @Override
    public int getDrawingInstructionsCount() {
        Array<DrawingInstruction> diList = new Array<DrawingInstruction>();
        for (ExtendedRectangle r : sections.keys()) {
            Array<DrawingInstruction> temp = sections.get(r).getDrawingInstructions();
            diList.addAll(temp);
        }
        return diList.size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Map readMap(String loc) {
        FileHandle handle = Gdx.files.internal(loc);
        Json json = new Json();
        Map out = json.fromJson(Map.class, handle);
        for (ExtendedRectangle r : out.sectionResources) {
            out.sections.put(r, Section.readSection(r.resource));
        }
        return out;
    }

    public static void main(String[] args) {
        Json json = new Json();
        Map m = new Map();
        m.sectionResources = new Array<ExtendedRectangle>();
        m.sectionResources.add(new ExtendedRectangle(0, 10, 10, 0, "map/1/sect/1"));
        m.sectionResources.add(new ExtendedRectangle(10, 10, 20, 0, "map/1/sect/2"));
        System.out.println(json.prettyPrint(new Rectangle(0, 10, 10, 10)));
        String s = json.toJson(m);
        System.out.println(s);
    }
    
}
    class ExtendedRectangle extends Rectangle{
        String resource;
        public ExtendedRectangle(){
            resource = "";
        }
        public ExtendedRectangle(float x, float y, float w, float h, String r){
            super(x,y,w,h);
            resource = r;
        }
    }