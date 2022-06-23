package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.GameObject;
import road_fighter.utils.GameObjectBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
//import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Barrel extends GameObject implements Renderable, Collideable {
	private double posX;
	private double posY = 0;
	private final int posDistancia;


//	@SuppressWarnings("unused")
	private final int imageSize = Config.barrelSize;
	private final int offScreenTolerance = imageSize;

	private VBox render;
	private Rectangle collider;

	public Barrel(int posX, int distancia) {

		this.posX = posX;
		this.posDistancia = distancia;

		Image barrel;
		barrel = new Image("file:src/main/resources/PNG/Objects/barrel_blue.png", imageSize, imageSize, false, false);
		ImageView imageView = new ImageView(barrel);
		render = new VBox(imageView);

		render.setViewOrder(1);

		collider = new Rectangle(0, 0, imageSize, imageSize);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
		collider.setStrokeWidth(2);

		setPosX(posX);
	}

	public int getPosDistancia() {
		return posDistancia;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	private void setPosX(double posX) {
		this.posX = posX;
		render.setTranslateX(posX);
		collider.setX(posX);
	}

	public void setPosY(double posY) {
		this.posY = posY;
		render.setTranslateY(posY - imageSize / 2);
		collider.setY(posY - imageSize / 2);
	}

	public void updateBarrel(double velocidad, double deltaTime) {
		
		setPosY(posY + velocidad * deltaTime);

		if (isOffScreen()) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}

	@Override
	public VBox getRender() {
		return render;
	}

	public boolean isOffScreen() {
		return posY - Config.baseHeight > offScreenTolerance;
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	@Override
	public void destroy() {
	}

}
