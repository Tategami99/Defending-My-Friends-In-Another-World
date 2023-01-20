package com.yuuna.anotherworldtd.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
    public static class IsekaiScreen{

        public static Texture backgroundTexture;
        public static TextureRegion backgroundTextureRegion;
        public static void loadIsekai(){
            backgroundTexture = new Texture("IsekaiScreen/isekaibackground.jpg");
            backgroundTextureRegion = new TextureRegion(backgroundTexture);
        }
        public static void disposeIsekai(){
            backgroundTexture.dispose();
        }


    }
}
