/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.socialinept.tikbits.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;

/**
 *
 * @author Andrew McCall <andrewnmccall@gmail.com>
 */
public class TexturePool {
    HashMap<String, Texture> textures = null;
    public TexturePool(){
        textures = new HashMap<String, Texture>();
    }
    public Texture getTexture(String s){
        Texture out = textures.get(s);
        if(out == null){
            out = new Texture(Gdx.files.internal(s));
            textures.put(s, out);
        }
        return out;
    }
}
