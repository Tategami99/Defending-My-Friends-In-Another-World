package com.yuuna.anotherworldtd.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class AssetManager {

    public static class IsekaiAssets{
        public static Texture backgroundTexture;
        public static TextureRegion backgroundTextureRegion;
        public static Texture endlessModeTexture;
        public static Drawable endlessModeDrawable;
        public static Texture storyModeTexture;
        public static Drawable storyModeDrawable;
        public static Texture quitGameTexture;
        public static Drawable quitGameDrawable;

        public static void loadIsekai(){
            backgroundTexture = new Texture("IsekaiScreen/isekaibackground.jpg");
            backgroundTextureRegion = new TextureRegion(backgroundTexture);
            endlessModeTexture = new Texture("IsekaiScreen/endlessmodebutton.png");
            endlessModeDrawable = new TextureRegionDrawable(new TextureRegion(endlessModeTexture));
            storyModeTexture = new Texture("IsekaiScreen/storymodebutton.png");
            storyModeDrawable = new TextureRegionDrawable(new TextureRegion(storyModeTexture));
            quitGameTexture = new Texture("IsekaiScreen/quitgamebutton.png");
            quitGameDrawable = new TextureRegionDrawable(new TextureRegion(quitGameTexture));
        }
        public static void disposeIsekai(){
            backgroundTexture.dispose();
            endlessModeTexture.dispose();
            storyModeTexture.dispose();
            quitGameTexture.dispose();
        }
    }

    public static class EndlessAssets{
        public static Texture backgroundTexture;
        public static TextureRegion backgroundTextureRegion;

        public static void loadEndless(){
            backgroundTexture = new Texture("IsekaiScreen/isekaibackground.jpg");
            backgroundTextureRegion = new TextureRegion(backgroundTexture);
        }

        public static void disposeEndless(){
            backgroundTexture.dispose();
        }
    }
}
