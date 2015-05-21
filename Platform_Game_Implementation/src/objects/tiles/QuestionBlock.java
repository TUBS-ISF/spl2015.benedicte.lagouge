package objects.tiles;

import java.awt.image.BufferedImage;
import java.util.Random;

import objects.creatures.Coin;
import objects.creatures.Mushroom;
import objects.creatures.Score;
import util.ImageManipulator;

import core.animation.Animation;
import core.sound.specific.MarioSoundManager22050Hz;
import core.tile.GameTile;
import core.tile.TileMap;


public class QuestionBlock extends GameTile {

	private String sounds;
	private MarioSoundManager22050Hz soundManager;
	private TileMap map;

	private Animation active;
	private Animation dead;
	private boolean isActive;
	private boolean hasCoin;
	private boolean hasMushroom;

	public QuestionBlock(int pixelX, int pixelY, TileMap map, MarioSoundManager22050Hz soundManager, boolean hasCoin,
			boolean hasMushroom,String ss) {

		// int pixelX, int pixelY, Animation anim, Image img
		super(pixelX, pixelY, null, null);
		this.sounds = ss;
		setIsSloped(false);
		isActive = true;
		this.hasCoin = hasCoin;
		this.hasMushroom = hasMushroom;
		this.soundManager = soundManager;
		this.map = map;

		BufferedImage q[] = { ImageManipulator.loadImage("items/Question_Block_0.png"), ImageManipulator.loadImage("items/Question_Block_1.png"),
				ImageManipulator.loadImage("items/Question_Block_2.png"), ImageManipulator.loadImage("items/Question_Block_3.png"),
				ImageManipulator.loadImage("items/Question_Block_Dead.png") };

		Random r = new Random();
		active = new Animation(r.nextInt(20) + 140).addFrame(q[0]).addFrame(q[1]).addFrame(q[2]).addFrame(q[3]);
		dead = new Animation(2000).addFrame(q[4]);
		setAnimation(active);
	}

	public void update(int time) {
		super.update(time);
		if(getOffsetY() != 0) { setOffsetY(getOffsetY() + 2); }
	}

	public void doAction() {
		if(isActive) {
			if(hasCoin) {
				setOffsetY(-10);
				if(this.sounds.equals("yes"))
					soundManager.playCoin();

				Coin newCoin = new Coin(getPixelX(), getPixelY());
				Score score = new Score(getPixelX(), getPixelY());
				map.creaturesToAdd().add(newCoin);
				map.creaturesToAdd().add(score);
				newCoin.shoot();
			} else if(hasMushroom) {
				setOffsetY(-10);
				if(this.sounds.equals("yes"))
					soundManager.playItemSprout();
				
				Mushroom shroom = new Mushroom(getPixelX(), getPixelY()-26);
				map.creaturesToAdd().add(shroom);
			}
			setAnimation(dead);
			isActive = false;
		}
	}
}