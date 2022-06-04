package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Collidator;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.AudioResources;
import road_fighter.utils.GameObject;
import road_fighter.utils.IndividualSpriteAnimation;
import road_fighter.utils.SpriteAnimation;
//import road_fighter.utils.Utils;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
//import javafx.geometry.Rectangle2D;
//import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class Car extends GameObject implements Updatable, Renderable, Collidator {
	private final double ROTATION_FREE_FALL = 20;
	private final double ROTATION_THRESHOLD = 0.6;
	private final double ROTATION_SPEED = 250;

	private Score score;
	private Speedometer speedometer;
	private GPS gps;

	private final int width = 31;
	private final int height = 71;
	private final int widthExplosion = 130;
	private final int heightExplosion = 130;

	private double posX;
	private double posY;
	private double velY = 0;
	private double rotation = 0;
	private double timeStandby = 0;

	private boolean idle = true;
	private boolean dead = false;
	private boolean grounded = false;
	private boolean freeFall = false;
	private boolean directionRight = false;
	private boolean directionLeft = false;
	private boolean accelerated = false;

	private Image imageBase;
	private Image imageExplosion0;
	private Image imageExplosion1;
	private Image imageExplosion2;
	private Image imageExplosion3;
	private Image imageExplosion4;
	private Image imageExplosion5;
	private Image imageExplosion6;
	private Image imageExplosion7;
	private Image imageExplosion8;
	private Image imageExplosion9;
	private Image imageExplosion10;
	private Image imageExplosion11;
	private Image imageExplosion12;
	private Image imageExplosion13;
	private Image imageExplosion14;
	private Image imageExplosion15;
	private Image imageExplosion16;
	private Image imageExplosion17;
	private Image imageExplosion18;
	private Image imageExplosion19;
	private Image imageExplosion20;
	private Image imageExplosion21;
	private Image imageExplosion22;
	private Image imageExplosion23;
	private Image imageExplosion24;
//	private Image image1;
//	private Image image2;
//	private Image image3;
//	private Image image4;

	private AudioClip dieAudio;
	private AudioClip hitAudio;
	private AudioClip wingAudio;
	private AudioClip engineAudio;
	private AudioClip screechingTiresAudio;

	private ImageView render;

	private Rectangle collider;
	private final double colliderTolerance = 0.75;
	private final int colliderWidth = (int) (width * colliderTolerance);
	private final int colliderHeight = (int) (height * colliderTolerance);

	private final IndividualSpriteAnimation flappyAnimation;
	private final RotateTransition idleAnimation;
	private final IndividualSpriteAnimation explosionAnimation;
	private final Duration translateDuration = Duration.millis(1000);
	private boolean isComputer = false;
	

	public Car(int x, int y, Score score, Speedometer speedometer, GPS gps, boolean computer) {
		posY = y;
		posX = x;
		this.score = score;
		this.speedometer = speedometer;
		this.gps = gps;
		this.isComputer  = computer;

		initImages();
		initAudios();
		render = new ImageView(imageBase);
		render.setViewOrder(1);

		collider = new Rectangle(posX - colliderWidth / 2, posY - colliderHeight / 2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);

		flappyAnimation = initFlappyAnimation();
		idleAnimation = initIdleAnimation();
		explosionAnimation = initExplosionAnimation();

	}

	private void initImages() {
		imageBase = new Image("file:src/main/resources/PNG/Cars/car_black_1.png", width, height, false, false);
		imageExplosion0 = new Image("file:src/main/resources/spriteExplosion/sprite0.png", height, height, 
				false, false);
		imageExplosion1 = new Image("file:src/main/resources/spriteExplosion/sprite1.png", height, height, 
				false, false);
		imageExplosion2 = new Image("file:src/main/resources/spriteExplosion/sprite2.png", height, height, 
				false, false);
		imageExplosion3 = new Image("file:src/main/resources/spriteExplosion/sprite3.png", height, height, 
				false, false);
		imageExplosion4 = new Image("file:src/main/resources/spriteExplosion/sprite4.png", height, height, 
				false, false);
		imageExplosion5 = new Image("file:src/main/resources/spriteExplosion/sprite5.png", height, height, 
				false, false);
		imageExplosion6 = new Image("file:src/main/resources/spriteExplosion/sprite6.png", height, height, 
				false, false);
		imageExplosion7 = new Image("file:src/main/resources/spriteExplosion/sprite7.png", height, height, 
				false, false);
		imageExplosion8 = new Image("file:src/main/resources/spriteExplosion/sprite8.png", height, height, 
				false, false);
		imageExplosion9 = new Image("file:src/main/resources/spriteExplosion/sprite9.png", height, height, 
				false, false);
		imageExplosion10 = new Image("file:src/main/resources/spriteExplosion/sprite10.png", height, height, 
				false, false);
		imageExplosion11 = new Image("file:src/main/resources/spriteExplosion/sprite11.png", height, height, 
				false, false);
		imageExplosion12 = new Image("file:src/main/resources/spriteExplosion/sprite12.png", height, height, 
				false, false);
		imageExplosion13 = new Image("file:src/main/resources/spriteExplosion/sprite13.png", height, height, 
				false, false);
		imageExplosion14 = new Image("file:src/main/resources/spriteExplosion/sprite14.png", height, height, 
				false, false);
		imageExplosion15 = new Image("file:src/main/resources/spriteExplosion/sprite15.png", height, height, 
				false, false);
		imageExplosion16 = new Image("file:src/main/resources/spriteExplosion/sprite16.png", height, height, 
				false, false);
		imageExplosion17 = new Image("file:src/main/resources/spriteExplosion/sprite17.png", height, height, 
				false, false);
		imageExplosion18 = new Image("file:src/main/resources/spriteExplosion/sprite18.png", height, height, 
				false, false);
		imageExplosion19 = new Image("file:src/main/resources/spriteExplosion/sprite19.png", height, height, 
				false, false);
		imageExplosion20 = new Image("file:src/main/resources/spriteExplosion/sprite20.png", height, height, 
				false, false);
		imageExplosion21 = new Image("file:src/main/resources/spriteExplosion/sprite21.png", height, height, 
				false, false);
		imageExplosion22 = new Image("file:src/main/resources/spriteExplosion/sprite22.png", height, height, 
				false, false);
		imageExplosion23 = new Image("file:src/main/resources/spriteExplosion/sprite23.png", height, height, 
				false, false);
		imageExplosion24 = new Image("file:src/main/resources/spriteExplosion/sprite24.png", height, height, 
				false, false);
		
//		image1 = new Image("file:src/main/resources/PNG/Cars/car_blue_1.png", width, height, false, false);
//		image2 = new Image("file:src/main/resources/PNG/Cars/car_green_1.png", width, height, false, false);
//		image3 = new Image("file:src/main/resources/PNG/Cars/car_yellow_1.png", width, height, false, false);
//		image4 = new Image("file:src/main/resources/PNG/Cars/car_red_1.png", width, height, false, false);

//		Color[][] posibleColos = { original, blue, yellow };

//		int randomIndex = (int) Math.floor(Math.random() * 3);
//		Color[] colorRandom = posibleColos[randomIndex];
//
//		imageUp = Utils.reColor(imageUp, original, colorRandom);
//		imageBase = Utils.reColor(imageBase, original, colorRandom);
//		imageDown = Utils.reColor(imageDown, original, colorRandom);
	}

	private void initAudios() {
		dieAudio = AudioResources.getDieAudio();
		hitAudio = AudioResources.getHitAudio();
		wingAudio = AudioResources.getWingAudio();
		engineAudio = AudioResources.getEngineAudio();
		screechingTiresAudio = AudioResources.getScreechingTiresAudio();
	}

	private IndividualSpriteAnimation initExplosionAnimation() {

		IndividualSpriteAnimation sprite = new IndividualSpriteAnimation(new Image[] {
				imageBase,
				imageExplosion0,
				imageExplosion1,
				imageExplosion2,
				imageExplosion3,
				imageExplosion4,
				imageExplosion5,
				imageExplosion6,
				imageExplosion7,
				imageExplosion8,
				imageExplosion9,
				imageExplosion10,
				imageExplosion11
		}, render, Duration.millis(1000));
		sprite.setCustomFrames(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
		sprite.setCycleCount(1);
//		sprite.se
		
		return sprite;
	}

	private IndividualSpriteAnimation initFlappyAnimation() {

		IndividualSpriteAnimation individualSpriteAnimation = new IndividualSpriteAnimation(
				new Image[] { imageBase, imageExplosion1, imageBase }, render, Duration.millis(500));
		individualSpriteAnimation.setCustomFrames(new int[] { 0, 1, 2, 1 });
		individualSpriteAnimation.setCycleCount(Animation.INDEFINITE);
//		individualSpriteAnimation.play();
		return individualSpriteAnimation;
	}

	private RotateTransition initIdleAnimation() {

		RotateTransition rotateTransition = new RotateTransition();

		rotateTransition.setCycleCount(Animation.INDEFINITE);
		rotateTransition.setDuration(Duration.millis(1000));
		rotateTransition.setNode(render);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(50);
		rotateTransition.setAutoReverse(false);
		rotateTransition.play();
		return rotateTransition;
	}

	@Override
	public void update(double deltaTime) {
		timeStandby += deltaTime;
		setY(posY + velY * deltaTime);

		if (!dead) {
			if(Config.baseSpeed > 0)
				Config.distanciaActual += (Config.baseSpeed / 2) / 20;
			gps.update();
			speedometer.update();
			int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
			setX(posX + direction * Config.baseSpeed * deltaTime);
			if(isComputer)
				randomDirection();
			if(accelerated)
				if(Config.baseSpeed < 500)
					Config.baseSpeed += 10;
			if(!accelerated)
				if(Config.baseSpeed > 0)
					Config.baseSpeed -= 5;
		} if(dead) {
			explosionAnimation.play();
			
			posY += Config.baseSpeed * deltaTime;

			render.setTranslateY((posY % height));
		}

//		if (!idle) {
//			if (!grounded) {
//				velY += Config.gravity * deltaTime;
//			}
//
//			if (timeStandby > 0) {
//				setRotation(Math.min(-30 + timeStandby * ROTATION_SPEED, 90));
//			}

//			if (rotation > ROTATION_FREE_FALL && !freeFall) {
//				freeFall();
//			}
	}

	private void checkHorizontal() {
		if (!dead) {
			if (directionLeft) {
				render.setRotate(-15);
			} else if (directionRight) {
				render.setRotate(15);
			} else {
				render.setRotate(0);
			}
		}
	}

	public void setDirectionRight(boolean b) {
		this.directionRight = b;
		checkHorizontal();
	}

	public void setDirectionLeft(boolean b) {
		this.directionLeft = b;
		checkHorizontal();
	}

//	private void freeFall() {
//		freeFall = true;
//		flappyAnimation.stop();
//		render.setImage(imageBase);
//	}

//	public void push() {
//		if (!dead) {
//			wingAudio.play();
//			idle = false;
//			freeFall = false;
//			idleAnimation.jumpTo(translateDuration.divide(2));
//			idleAnimation.stop();
//			flappyAnimation.play();
//			velY = -Config.jumpForce;
//			timeStandby = -ROTATION_THRESHOLD;
//			setRotation(-20);
//		}
//	}

	public void pushLeft() {
		if (!dead) {
//			wingAudio.play();
//			idle = false;
//			freeFall = false;
//			idleAnimation.jumpTo(translateDuration.divide(2));
//			idleAnimation.stop();
			flappyAnimation.play();
			setDirectionLeft(true);
//			velY = -Config.jumpForce;
//			timeStandby = -ROTATION_THRESHOLD;
//			setRotation(-20);
		}
	}

	public void pushRight() {
		if (!dead) {
//			wingAudio.play();
//			idle = false;
//			freeFall = false;
//			idleAnimation.jumpTo(translateDuration.divide(2));
//			idleAnimation.stop();
			flappyAnimation.play();
			setDirectionRight(true);
//			velY = -Config.jumpForce;
//			timeStandby = -ROTATION_THRESHOLD;
//			setRotation(20);
		}
	}

	public void stopRotationAnimation() {
		idleAnimation.stop();
	}

	public boolean isDead() {
		return dead;
	}

	private void setY(double posY) {
		this.posY = posY;
		render.setY(posY - height / 2);
		collider.setY(posY - colliderHeight / 2);

	}

	private void setX(double posX) {
		this.posX = posX;
		render.setX(posX - width / 2);
		collider.setX(posX - colliderWidth / 2);

	}

	private void setRotation(double rotation) {
		this.rotation = rotation;
		render.setRotate(rotation);
	}

	@Override
	public ImageView getRender() {
		return render;
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	@Override
	public void collide(Collideable collideable) {
		if (!grounded) {
			if (collideable.getClass() == ScoreCollider.class) {
				score.increase();
				((ScoreCollider) collideable).remove();
			} else {
				if (!dead) {
					screechingTiresAudio.play();
					dead = true;
				}
				if (collideable.getClass() == Ground.class) {
					setY(((Rectangle) ((Ground) collideable).getCollider()).getY() - height / 2);
					velY = 0;
					grounded = true;
				}
			}
		}
	}

	public double getX() {
		return posX;
	}

	public double getY() {
		return posY;
	}

	public int getHeight() {
		return height;
	}

	@Override
	public void destroy() {
	}

	public void accelerate(boolean b) {
		// TODO Auto-generated method stub
		accelerated = b;
	}
	
	public void randomDirection() {
		stopRotationAnimation();
		if(posX < Config.roadLeft + Config.platformWidth + 70) {
			this.directionLeft = false;
			this.directionRight = true;
		}
		if(posX > Config.roadRight - Config.platformWidth - 70) {
			this.directionRight = false;
			this.directionLeft = true;
		}
	}
}
