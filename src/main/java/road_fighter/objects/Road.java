package road_fighter.objects;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import road_fighter.Config;
import road_fighter.interfaces.Collideable;
import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;

public class Road extends GameObject implements Updatable, Renderable, Collideable {
	private double width = 128;
	private double height = 128;
	private double posX = (Config.baseWidth - width * 3) / 2;
	private double posY = 0;

	private VBox render;
	private VBox renderSalida;
	private VBox renderLlegada;

	private Rectangle colliderLeft;
	private Rectangle colliderRight;

	public Road() {
		Image backgroundImage1 = new Image("file:src/main/resources/PNG/Tiles/Asphalt road/road_asphalt21.png", width,
				height, false, false);
		Image backgroundImage2 = new Image("file:src/main/resources/PNG/Tiles/Asphalt road/road_asphalt22.png", width,
				height, false, false);
		Image backgroundImage3 = new Image("file:src/main/resources/PNG/Tiles/Asphalt road/road_asphalt23.png", width,
				height, false, false);

		ImagePattern image_pattern1 = new ImagePattern(backgroundImage1, width, height, width, height, false);
		ImagePattern image_pattern2 = new ImagePattern(backgroundImage2, width, height, width, height, false);
		ImagePattern image_pattern3 = new ImagePattern(backgroundImage3, width, height, width, height, false);

		Rectangle roadLeft = new Rectangle(width, Config.baseHeight + height);
		Rectangle roadCenter = new Rectangle(width, Config.baseHeight + height);
		Rectangle roadRight = new Rectangle(width, Config.baseHeight + height);
		roadCenter.setX(width);
		roadRight.setX(width * 2);
		roadLeft.setFill(image_pattern1);
		roadCenter.setFill(image_pattern2);
		roadRight.setFill(image_pattern3);

		Group groupRoad = new Group();
		groupRoad.getChildren().add(roadLeft);
		groupRoad.getChildren().add(roadCenter);
		groupRoad.getChildren().add(roadRight);

		groupRoad.setTranslateY(-height);

		render = new VBox(groupRoad);
		render.setTranslateX(posX);
		render.setViewOrder(3);

		/// Salida
		Image backgroundImage1S = new Image("file:src/main/resources/PNG/Tiles/Asphalt road/road_asphalt69.png", width,
				height, false, false);
		Image backgroundImage2S = new Image("file:src/main/resources/PNG/Tiles/Asphalt road/road_asphalt70.png", width,
				height, false, false);
		Image backgroundImage3S = new Image("file:src/main/resources/PNG/Tiles/Asphalt road/road_asphalt71.png", width,
				height, false, false);

		ImageView roadLeftS = new ImageView(backgroundImage1S);
		ImageView roadCenterS = new ImageView(backgroundImage2S);
		ImageView roadRightS = new ImageView(backgroundImage3S);

//		Rectangle roadLeftS = new Rectangle(width, Config.baseHeight + height);
//		Rectangle roadCenterS = new Rectangle(width, Config.baseHeight + height);
//		Rectangle roadRightS = new Rectangle(width, Config.baseHeight + height);
		roadCenterS.setX(width);
		roadRightS.setX(width * 2);
//		roadLeftS.setFill(image_pattern1S);
//		roadCenterS.setFill(image_pattern2S);
//		roadRightS.setFill(image_pattern3S);

		Group groupRoadS = new Group();
		groupRoadS.getChildren().add(roadLeftS);
		groupRoadS.getChildren().add(roadCenterS);
		groupRoadS.getChildren().add(roadRightS);

		groupRoadS.setTranslateY(Config.baseHeight - height);

		renderSalida = new VBox(groupRoadS);
		renderSalida.setTranslateX(posX);
		renderSalida.setViewOrder(2);

		colliderLeft = new Rectangle(posX, 0, 20, Config.baseHeight);
		colliderRight = new Rectangle(posX + 3 * width - 20, 0, 20, Config.baseHeight);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
//		if(Config.distanciaActual == 0)
			
		posY += Config.baseSpeed * deltaTime;
		render.setTranslateY(posY % height);
	}

	@Override
	public Shape getCollider() {
		Shape shape = Shape.union(colliderRight, colliderLeft);
		shape.setFill(null);
		shape.setStroke(Color.FUCHSIA);
		shape.setStrokeWidth(1);
		return shape;
	}

	@Override
	public void destroy() {
	}

}
