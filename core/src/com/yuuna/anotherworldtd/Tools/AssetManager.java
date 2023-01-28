package com.yuuna.anotherworldtd.Tools;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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

        public static Texture mageAllyButtonTexture;
        public static Drawable mageAllyButtonDrawable;
        public static Texture katanaAllyButtonTexture;
        public static Drawable katanaAllyButtonDrawable;

        public static void loadUserInterface(){
            baseButtonWidth = 64;
            baseButtonHeight = 64;

            //top menu
            settingsTexture = new Texture("UserInterface/settingsbutton.png");
            settingDrawable = new TextureRegionDrawable(new TextureRegion(settingsTexture));

            //pause menu
            resumeTexture = new Texture("UserInterface/resumebutton.png");
            resumDrawable = new TextureRegionDrawable(new TextureRegion(resumeTexture));
            recordScoreTexture = new Texture("UserInterface/recordscorebutton.png");
            recordScoreDrawable = new TextureRegionDrawable(new TextureRegion(recordScoreTexture));
            mainMenuTexture = new Texture("UserInterface/mainmenubutton.png");
            mainMenuDrawable = new TextureRegionDrawable(new TextureRegion(mainMenuTexture));

            //ally buttons
            mageAllyButtonTexture = new Texture("UserInterface/mageallybutton.png");
            mageAllyButtonDrawable = new TextureRegionDrawable(new TextureRegion(mageAllyButtonTexture));
            katanaAllyButtonTexture = new Texture("UserInterface/katanaallybutton.png");
            katanaAllyButtonDrawable = new TextureRegionDrawable(new TextureRegion(katanaAllyButtonTexture));

        }
        public static void disposeUserInterface(){
            settingsTexture.dispose();

            resumeTexture.dispose();
            recordScoreTexture.dispose();
            mainMenuTexture.dispose();

            mageAllyButtonTexture.dispose();
        }
    }

    public static class AllyAssets{
        public static class MageAllyAssets{
            public static Texture mageAllyIdleTexture;
            public static Animation<TextureRegion> mageAllyIdleAnimation;

            public static void mageAllyLoad(){
                mageAllyIdleTexture = new Texture("Allies/mageallyidle.png");
                mageAllyIdleAnimation = createAnimation(mageAllyIdleTexture, 4, 1, 0.1f);
            }
            public static void mageAllyDispose(){
                mageAllyIdleTexture.dispose();
            }

            public static Animation<TextureRegion> createAnimation( Texture texture, Integer FRAME_COLS, Integer FRAME_ROWS, Float frameInterval){
                TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/FRAME_COLS, texture.getHeight()/FRAME_ROWS);
                TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
                int index = 0;
                for (int i = 0; i < FRAME_ROWS; i++){
                    for (int j = 0; j < FRAME_COLS; j++){
                        frames[index++] = tmp[i][j];
                    }
                }
        
                Animation<TextureRegion> animation = new Animation<TextureRegion>(frameInterval, frames);
                return animation;
            }
        }
        public static class KatanaAllyAssets{
            public static Texture katanaAllyIdleTexture;
            public static Animation<TextureRegion> katanaAllyIdleAnimation;

            public static void katanaAllyLoad(){
                katanaAllyIdleTexture = new Texture("Allies/katanaallyidle.png");
                katanaAllyIdleAnimation = createAnimation(katanaAllyIdleTexture, 4, 1, 0.1f);
            }
            public static void katanaAllyDispose(){
                katanaAllyIdleTexture.dispose();
            }

            public static Animation<TextureRegion> createAnimation( Texture texture, Integer FRAME_COLS, Integer FRAME_ROWS, Float frameInterval){
                TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/FRAME_COLS, texture.getHeight()/FRAME_ROWS);
                TextureRegion[] frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
                int index = 0;
                for (int i = 0; i < FRAME_ROWS; i++){
                    for (int j = 0; j < FRAME_COLS; j++){
                        frames[index++] = tmp[i][j];
                    }
                }
        
                Animation<TextureRegion> animation = new Animation<TextureRegion>(frameInterval, frames);
                return animation;
            }
        }
    }
}
