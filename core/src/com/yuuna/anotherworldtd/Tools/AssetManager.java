package com.yuuna.anotherworldtd.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
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
        public static TiledMap endlessMap;
        public static OrthogonalTiledMapRenderer endlessRenderer;
        public static MapProperties endlessMapProperties;

        public static void loadEndless(){
            backgroundTexture = new Texture("IsekaiScreen/isekaibackground.jpg");
            backgroundTextureRegion = new TextureRegion(backgroundTexture);
            endlessMap = new TmxMapLoader().load("EndlessScreen/endlessmap.tmx");
            endlessRenderer = new OrthogonalTiledMapRenderer(endlessMap);
            endlessMapProperties = endlessMap.getProperties();
        }
        public static void disposeEndless(){
            backgroundTexture.dispose();
            endlessMap.dispose();
            endlessRenderer.dispose();
        }
    }

    public static class UserInterfaceAssets{
        public static int baseButtonWidth;
        public static int baseButtonHeight;
        public static Texture settingsTexture;
        public static Drawable settingDrawable;
        public static Texture resumeTexture;
        public static Drawable resumDrawable;
        public static Texture recordScoreTexture;
        public static Drawable recordScoreDrawable;
        public static Texture mainMenuTexture;
        public static Drawable mainMenuDrawable;

        public static void loadUserInterface(){
            settingsTexture = new Texture("UserInterface/settingsbutton.png");
            baseButtonWidth = settingsTexture.getWidth();
            baseButtonHeight = settingsTexture.getHeight();
            settingDrawable = new TextureRegionDrawable(new TextureRegion(settingsTexture));
            resumeTexture = new Texture("UserInterface/resumebutton.png");
            resumDrawable = new TextureRegionDrawable(new TextureRegion(resumeTexture));
            recordScoreTexture = new Texture("UserInterface/recordscorebutton.png");
            recordScoreDrawable = new TextureRegionDrawable(new TextureRegion(recordScoreTexture));
            mainMenuTexture = new Texture("UserInterface/mainmenubutton.png");
            mainMenuDrawable = new TextureRegionDrawable(new TextureRegion(mainMenuTexture));
        }
        public static void disposeUserInterface(){
            settingsTexture.dispose();
            resumeTexture.dispose();
        }
    }
}
