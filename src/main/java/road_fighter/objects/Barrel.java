package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
//import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Barrel extends GameObject implements Updatable, Renderable, Collideable {
	private double posX;
	private double posY;

	private final int width;
//	@SuppressWarnings("unused")
	private final int height = 0;
	private final int imageWidth = 56;
	private final int imageHeight = 56;
	private final int offScreenTolerance = 50;

	private VBox render;
	private Rectangle collider;

	public Barrel(int width) {
		this.width = width;

		Image barrel;
		barrel = new Image("file:src/main/resources/PNG/Objects/barrel_blue.png", imageWidth, imageHeight, false, false);
		ImageView imageView = new ImageView(barrel);
		render = new VBox(imageView);

		render.setViewOrder(1);

		collider = new Rectangle(width, -imageHeight, imageWidth, imageHeight);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
		collider.setStrokeWidth(2);
		
		setPosX(width);
	}

	public double getPosX() {
		return posX;
	}

	private void setPosX(double posX) {
		this.posX = posX;
		render.setTranslateX(posX - width / 2);
		collider.setX(posX - width / 2);
	}
	
	private void setPosY(double posY) {
		this.posY = posY;
		render.setTranslateY(posY - height / 2);
		collider.setY(posY - height / 2);
	}

	@Override
	public void update(double deltaTime) {
//		setPosX(posX + -Config.baseSpeed * deltaTime);
		setPosY(posY + Config.baseSpeed * deltaTime);

		if (isOffScreen()) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}

	@Override
	public VBox getRender() {
		return render;
	}

	public boolean isOffScreen() {
		return posX + width < -offScreenTolerance;
	}

	@Override
	public Shape getCollider() {
		return collider;
	}
	
	@Override
	public void destroy() {	}
	
}
