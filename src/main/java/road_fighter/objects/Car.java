package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Collidator;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.AudioResources;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;
import road_fighter.utils.IndividualSpriteAnimation;
import road_fighter.utils.SpriteAnimation;
//import road_fighter.utils.Utils;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
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

	private Road road;
	private Score score;
	private Speedometer speedometer;
	private GPS gps;

	private final int width = 31;
	private final int height = 71;
	private final int widthExplosion = 130;
	private final int heightExplosion = 130;
	private final int posXInicial;

	private int offScreenTolerance = height;
	private int invulnerabilityTime = 0;
	private int deadTime = 0;
	private double distanciaRecorrida = 0;

	private double posX;
	private double posY;
	private double speed = 0;

	private double rotation = 0;
//	private double timeStandby = 0;

//	private boolean idle = true;
//	private boolean dead = false;
//	private boolean grounded = false;
	private boolean directionRight = false;
	private boolean directionLeft = false;
	private boolean accelerated = false;
	private boolean isComputer = false;

	/// IMAGENES
	private Image imageBase;
	private Image imageExplosion;
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

	/// AUDIO
	private AudioClip dieAudio;
	private AudioClip hitAudio;
	private AudioClip wingAudio;
	private AudioClip engineAudio;
	private AudioClip screechingTiresAudio;
	private AudioClip carCrashAudio;

	/// RENDER
	private ImageView render;

	/// COLLIDER
	private Rectangle collider;
	private final double colliderTolerance = 0.75;
	private final int colliderWidth = (int) (width * colliderTolerance);
	private final int colliderHeight = (int) (height * colliderTolerance);

	/// ANIMATIONS
	private final IndividualSpriteAnimation invulnerabilityAnimation;
	private final RotateTransition rotateAnimation;
	private final IndividualSpriteAnimation explosionAnimation;
	private final Duration translateDuration = Duration.millis(1000);

	public Car(int x, int y, Road road, Score score, Speedometer speedometer, GPS gps, boolean computer) {
		posY = y;
		posX = x;
		posXInicial = x;
		this.road = road;
		this.score = score;
		this.speedometer = speedometer;
		this.gps = gps;
		this.isComputer = computer;

		initImages();
		initAudios();
		render = new ImageView(imageBase);
		render.setViewOrder(1);
		render.setTranslateY(posY - height / 2);

		collider = new Rectangle(posX - colliderWidth / 2, posY - colliderHeight / 2, colliderWidth, colliderHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);

		invulnerabilityAnimation = initInvulnerabilityAnimation();
		rotateAnimation = initRotateAnimation();
		explosionAnimation = initExplosionAnimation();
	}

	private void initImages() {
		imageBase = new Image("file:src/main/resources/PNG/Cars/car_black_1.png", width, height, false, false);
		imageExplosion0 = new Image("file:src/main/resources/spriteExplosion/sprite0.png", width, height, false, false);
		imageExplosion1 = new Image("file:src/main/resources/spriteExplosion/sprite1.png", width, height, false, false);
		imageExplosion2 = new Image("file:src/main/resources/spriteExplosion/sprite2.png", width, height, false, false);
		imageExplosion3 = new Image("file:src/main/resources/spriteExplosion/sprite3.png", width, height, false, false);
		imageExplosion4 = new Image("file:src/main/resources/spriteExplosion/sprite4.png", width, height, false, false);
		imageExplosion5 = new Image("file:src/main/resources/spriteExplosion/sprite5.png", width, height, false, false);
		imageExplosion6 = new Image("file:src/main/resources/spriteExplosion/sprite6.png", width, height, false, false);
		imageExplosion7 = new Image("file:src/main/resources/spriteExplosion/sprite7.png", width, height, false, false);
		imageExplosion8 = new Image("file:src/main/resources/spriteExplosion/sprite8.png", width, height, false, false);
		imageExplosion9 = new Image("file:src/main/resources/spriteExplosion/sprite9.png", width, height, false, false);
		imageExplosion10 = new Image("file:src/main/resources/spriteExplosion/sprite10.png", width, height, false,
				false);
		imageExplosion11 = new Image("file:src/main/resources/spriteExplosion/sprite11.png", width, height, false,
				false);
		imageExplosion12 = new Image("file:src/main/resources/spriteExplosion/sprite12.png", width, height, false,
				false);
		imageExplosion13 = new Image("file:src/main/resources/spriteExplosion/sprite13.png", width, height, false,
				false);
		imageExplosion14 = new Image("file:src/main/resources/spriteExplosion/sprite14.png", width, height, false,
				false);
		imageExplosion15 = new Image("file:src/main/resources/spriteExplosion/sprite15.png", width, height, false,
				false);
		imageExplosion16 = new Image("file:src/main/resources/spriteExplosion/sprite16.png", width, height, false,
				false);
		imageExplosion17 = new Image("file:src/main/resources/spriteExplosion/sprite17.png", width, height, false,
				false);
		imageExplosion18 = new Image("file:src/main/resources/spriteExplosion/sprite18.png", width, height, false,
				false);
		imageExplosion19 = new Image("file:src/main/resources/spriteExplosion/sprite19.png", width, height, false,
				false);
		imageExplosion20 = new Image("file:src/main/resources/spriteExplosion/sprite20.png", width, height, false,
				false);
		imageExplosion21 = new Image("file:src/main/resources/spriteExplosion/sprite21.png", width, height, false,
				false);
		imageExplosion22 = new Image("file:src/main/resources/spriteExplosion/sprite22.png", width, height, false,
				false);
		imageExplosion23 = new Image("file:src/main/resources/spriteExplosion/sprite23.png", width, height, false,
				false);
		imageExplosion24 = new Image("file:src/main/resources/spriteExplosion/sprite24.png", width, height, false,
				false);

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
		carCrashAudio = AudioResources.getCarCrashAudio();
	}

	private IndividualSpriteAnimation initExplosionAnimation() {

		IndividualSpriteAnimation sprite = new IndividualSpriteAnimation(new Image[] { imageBase, imageExplosion0,
				imageExplosion1, imageExplosion2, imageExplosion3, imageExplosion4, imageExplosion5, imageExplosion6,
				imageExplosion7, imageExplosion8, imageExplosion9, imageExplosion10, imageExplosion11, imageExplosion12,
				imageExplosion13, imageExplosion14, imageExplosion15, imageExplosion16, imageExplosion17,
				imageExplosion18, imageExplosion19, imageExplosion20, imageExplosion21, imageExplosion22,
				imageExplosion23, imageExplosion24 }, render, Duration.millis(3000));
		sprite.setCycleCount(1);
		sprite.setCustomFrames(
				new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 });

		return sprite;
	}

	private IndividualSpriteAnimation initInvulnerabilityAnimation() {

		IndividualSpriteAnimation individualSpriteAnimation = new IndividualSpriteAnimation(
				new Image[] { imageBase, imageExplosion24 }, render, Duration.millis(200));
		individualSpriteAnimation.setCustomFrames(new int[] { 0, 1, 0 });
		individualSpriteAnimation.setCycleCount(Animation.INDEFINITE);
		return individualSpriteAnimation;
	}

	private RotateTransition initRotateAnimation() {

		RotateTransition rotateTransition = new RotateTransition();

		rotateTransition.setCycleCount(Animation.INDEFINITE);
		rotateTransition.setDuration(Duration.millis(1000));
		rotateTransition.setNode(render);
		rotateTransition.setByAngle(360);
		rotateTransition.setCycleCount(50);
		rotateTransition.setAutoReverse(false);
		return rotateTransition;
	}

	@Override
	public void update(double deltaTime) {

		if (road != null)
			road.update(deltaTime, speed);
		if (deadTime > 0) {
			invulnerabilityAnimation.stop();
			deadTime--;
			speed = 0;
			explosionAnimation.play();
			if (deadTime == 0) {
				setX(posXInicial);
				render.setRotate(0);
			}
		} else {
			if (invulnerabilityTime > 0) {
				explosionAnimation.stop();
				invulnerabilityAnimation.play();
				invulnerabilityTime--;
				carActions(deltaTime);
				if(invulnerabilityTime == 0)
					render.setImage(imageBase);
			} else {

				invulnerabilityAnimation.stop();
				carActions(deltaTime);
			}
		}

		/// SI SALE DE LA PANTALLA
		if (isOffScreen()) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}

	private void carActions(double deltaTime) {
		if (speed > 0)
			distanciaRecorrida += speed / 400;
		if (gps != null)
			gps.update(distanciaRecorrida);
		if (speedometer != null)
			speedometer.update(speed);
		int direction = directionLeft ? -1 : (directionRight ? 1 : 0);
		setX(posX + direction * speed * deltaTime);
		if (accelerated)
			if (speed < 500) {
				speed += 10;
				if (speed > 500)
					speed = 500;
			}
		if (!accelerated)
			if (speed > 0)
				speed -= 5;
	}

	public double getSpeed() {
		return speed;
	}

	private void checkHorizontal() {
		if (!isDead()) {
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

	public void stopRotationAnimation() {
		rotateAnimation.stop();
	}

	public void playRotationAnimation() {
		rotateAnimation.play();
	}

	public boolean isDead() {
		return (deadTime > 0);
	}

	public void setY(double posY) {
		this.posY = posY;
		render.setTranslateY(posY - height / 2);
		collider.setY(posY - colliderHeight / 2);
	}

	public void setX(double posX) {
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

		if (collideable.getClass() == ScoreCollider.class) {
			if (score != null)
				score.increase();
			((ScoreCollider) collideable).remove();
		} else if ((collideable.getClass() == Barrel.class || collideable.getClass() == Car.class)
				&& invulnerabilityTime > 0) {

		} else if (!isDead()) {
			carCrashAudio.play();
			deadTime = 400;
			explosionAnimation.play();
			invulnerabilityTime = 500;
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

	public boolean isOffScreen() {
		return posY - Config.baseHeight > offScreenTolerance || posY < -offScreenTolerance;
	}

	public boolean isAccelerated() {
		return accelerated;
	}

	public void accelerate(boolean b) {
		// TODO Auto-generated method stub
		accelerated = b;
	}

	public double getDistanciaRecorrida() {
		return distanciaRecorrida;
	}

	public void setDistanciaRecorrida(int distanciaRecorrida) {
		this.distanciaRecorrida = distanciaRecorrida;
	}

	public boolean isDirectionRight() {
		return directionRight;
	}

	public boolean isDirectionLeft() {
		return directionLeft;
	}

	public int getInvulnerabilityTime() {
		return invulnerabilityTime;
	}

	public void setInvulnerabilityTime(int invulnerabilityTime) {
		this.invulnerabilityTime = invulnerabilityTime;
	}

	public int getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(int deadTime) {
		this.deadTime = deadTime;
	}
}
