package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import space.earlygrey.shapedrawer.ShapeDrawer;

import javax.swing.*;

public class FieldElement extends Actor {
    Type type;
    public static Type lastTurn = Type.X;
    FieldElement(Type type){
        super();
        setSize(Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/3);
        this.type = type;
        addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                if (FieldElement.this.type == Type.BLANK){
                    FieldElement.this.type = lastTurn.not();
                    lastTurn = FieldElement.this.type;
                }
            }
        });
    }
    @Override
    public void draw(Batch batch, float parentAlpha){
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.drawPixel(0, 0);
        Texture texture = new Texture(pixmap); // remember to dispose of later
        pixmap.dispose();
        TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
        ShapeDrawer sd = new ShapeDrawer(batch, region);
        sd.setDefaultLineWidth(10);
        switch (type) {
            case X -> {
                sd.setColor(Color.BLUE);
                sd.line(getX(Align.topLeft),getY(Align.topLeft),getX(Align.bottomRight),getY(Align.bottomRight));
                sd.line(getX(Align.topRight),getY(Align.topRight),getX(Align.bottomLeft),getY(Align.bottomLeft));
            }
            case O -> {
                sd.setColor(Color.RED);
                sd.circle(this.getX(Align.center),this.getY(Align.center),getHeight()/2 - sd.getDefaultLineWidth()/2);
            }
        }

    }
}
