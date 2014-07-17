package com.example.geodefense;

import com.example.geodefense.Graphics.PixmapFormat;



public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        
        //load assets
//        Assets.base = g.newPixmap("base.png",PixmapFormat.ARGB_8888);
//        Assets.basebullet = g.newPixmap("basebullet.png",PixmapFormat.RGB_565);
//        Assets.wall = g.newPixmap("basewall.png",PixmapFormat.RGB_565);
//        Assets.bullet = g.newPixmap("bulletgeneral.png",PixmapFormat.RGB_565);
//        Assets.clickedMenuButton = g.newPixmap("clickedmenu.png",PixmapFormat.RGB_565);
//        Assets.enemyCircle = g.newPixmap("enemycircle.png",PixmapFormat.RGB_565);
//        Assets.enemySquare = g.newPixmap("enemysquare.png",PixmapFormat.RGB_565);
//        Assets.enemyTriangle = g.newPixmap("enemytriangle.png",PixmapFormat.RGB_565);
//        Assets.friendlyCircle = g.newPixmap("friendlycircle.png",PixmapFormat.RGB_565);
//        Assets.friendlySquare = g.newPixmap("friendlysquare.png",PixmapFormat.RGB_565);
//        Assets.friendlyTriangle = g.newPixmap("friendlytriangle.png",PixmapFormat.RGB_565);
//        Assets.background = g.newPixmap("mapone.png",PixmapFormat.RGB_565);
//        Assets.pauseButton = g.newPixmap("pausebutton.png",PixmapFormat.RGB_565);
//        Assets.pauseScreen = g.newPixmap("pausescreen.png",PixmapFormat.ARGB_8888);
//        Assets.ui = g.newPixmap("ui.png",PixmapFormat.RGB_565);
//        Assets.unclickedMenuButton = g.newPixmap("unclickedmenu.png", PixmapFormat.RGB_565);



        
        //Settings.load(game.getFileIO()); //load high scores
        
        //game.setScreen(new MainMenuScreen(game)); //set to main game screen instead
    }
    
    public void present(float deltaTime) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void dispose() {

    }
}