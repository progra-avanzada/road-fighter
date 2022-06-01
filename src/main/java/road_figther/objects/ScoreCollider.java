package road_figther.objects;

import road_figther.Config;
import road_figther.interfaces.Collideable;
import road_figther.interfaces.Updatable;
import road_figther.utils.GameObject;
import road_figther.utils.GameObjectBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ScoreCollider extends GameObject implements Updatable, Collideable {
	private Rectangle collider;
	
	private double posX;

	public ScoreCollider(int x) {
		posX = x;
		collider = new Rectangle(x, 0, Config.baseWidth / 10, Config.baseHeight - Config.groundHeight);
		collider.setFill(null);
		collider.setStroke(Color.WHITE);
		collider.setStrokeWidth(2);
	}

	@Override
	public void update(double deltaTime) {
		posX -= Config.baseSpeed * deltaTime;
		collider.setX(posX);
	}

	@Override
	public Shape getCollider() {
		return collider;
	}

	public void remove() {
		GameObjectBuilder.getInstance().remove(this);
	}

	@Override
	public void destroy() {	}
	
}
