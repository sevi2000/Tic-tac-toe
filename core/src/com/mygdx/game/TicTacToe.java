package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import space.earlygrey.shapedrawer.ShapeDrawer;

import java.util.ArrayList;

public class TicTacToe extends Game {
	SpriteBatch batch;
	FitViewport viewport;
	OrthographicCamera camera;
	ShapeDrawer sd;
	Stage stg;
	boolean display = true;
	BitmapFont font;

	ArrayList<FieldElement> elements =  new ArrayList<>();

	@Override
	public void create () {

		batch = new SpriteBatch();
		Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.drawPixel(0, 0);
		Texture texture = new Texture(pixmap); // remember to dispose of later
		pixmap.dispose();
		TextureRegion region = new TextureRegion(texture, 0, 0, 1, 1);
		sd = new ShapeDrawer(batch, region);
		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();
		System.out.println("Width: " + width + "Height : " + height);
		stg = new Stage(new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()),batch);
		Gdx.input.setInputProcessor(stg);
		for (int i = 0; i < 9;i++) {
			FieldElement elt = new FieldElement(Type.BLANK);
			elt.setPosition(i / 3 * Gdx.graphics.getWidth()/3,(i%3)* Gdx.graphics.getHeight()/3);
			elements.add(elt);
			stg.addActor(elt);
		}
		font = new BitmapFont();

	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 1, 1, 1);
		batch.begin();
		sd.setColor(Color.BLACK);
		sd.filledRectangle(Gdx.graphics.getWidth()/3,0,1,Gdx.graphics.getHeight());
		sd.setColor(Color.OLIVE);
		sd.filledRectangle(Gdx.graphics.getWidth()/3*2,0,1,Gdx.graphics.getHeight());
		sd.setColor(Color.RED);
		sd.filledRectangle(0,Gdx.graphics.getWidth()/3,Gdx.graphics.getWidth(),1);
		sd.setColor(Color.BLUE);
		sd.filledRectangle(0,Gdx.graphics.getWidth()/3*2,Gdx.graphics.getWidth(),1);

		batch.end();
		stg.draw();
		if (
				checkTriplet(elements.get(0),elements.get(1),elements.get(2)) ||//first col
						checkTriplet(elements.get(0),elements.get(3),elements.get(6)) ||// last line
						checkTriplet(elements.get(0),elements.get(4),elements.get(8)) ||// / diag
						checkTriplet(elements.get(1),elements.get(4),elements.get(7))|| // mid line
						checkTriplet(elements.get(2),elements.get(5),elements.get(8))|| // first line
						checkTriplet(elements.get(3),elements.get(4),elements.get(5))|| // mid col
						checkTriplet(elements.get(6),elements.get(7),elements.get(8))||// last col
						checkTriplet(elements.get(2),elements.get(4),elements.get(6))// \ diag



		){
			String won = FieldElement.lastTurn.not() == Type.O? "X wins": "O wins";
			System.out.println(won);
			//font.draw(batch, won, 10, 460); // Draw text at (100, 100)
		}
		if (display){
		for (FieldElement elt: elements) {
			System.out.println("x : " + elt.getX() + " y : " + elt.getY());
		}
		display = false;
		}

	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public boolean checkTriplet(FieldElement f1,FieldElement f2, FieldElement f3){
		return f1.type == f2.type &&  f2.type == f3.type && f1.type != Type.BLANK;
	}
}
