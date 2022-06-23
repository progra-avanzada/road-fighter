package road_fighter.objects;

import road_fighter.Config;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.GameObject;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Speedometer extends GameObject implements Renderable {
	private final int Y = Config.playerHeight;
	private int height = 90;
	private int speed = 0;

	private Text speedText;
	private Text text;
	private VBox render;
	private Rectangle cuadro;

	public Speedometer(int posX) {

		text = new Text("VELOCIMETRO:");
		text.setY(Y - 70);
		text.setX(10);

		speedText = new Text("" + speed);
		speedText.setY(Y - 10);
		speedText.setX(10);

		cuadro = new Rectangle(Config.sizeBar, height);
		cuadro.setFill(Color.BLACK);
		cuadro.setY(Y - 90);
		cuadro.setViewOrder(3);

		Group group = new Group();
		group.getChildren().add(text);
		group.getChildren().add(speedText);
		group.getChildren().add(cuadro);

		render = new VBox(group);
		render.setSpacing(5);
		render.setAlignment(Pos.CENTER_LEFT);
		render.setTranslateY(Y);
		render.setTranslateX(posX);
		// Esto debería heredarse?
		render.setPrefWidth(Config.baseWidth);

		Font font = Font.loadFont(ClassLoader.getSystemResource("font/flappy-bird-numbers.ttf").toString(), 50);
		speedText.setTextAlignment(TextAlignment.CENTER);
		speedText.setFont(font);
		speedText.setFill(Color.WHITE);
		Font font1 = Font.loadFont(ClassLoader.getSystemResource("font/road-fighter.ttf").toString(), 15);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(font1);
		text.setFill(Color.WHITE);

		DropShadow ds = new DropShadow();
		ds.setColor(Color.WHITE);
		speedText.setEffect(ds);

	}

	@Override
	public Node getRender() {
		return render;
	}

//	public void increase() {
//		this.update();
//	}

	public int getSpeed() {
		return speed;
	}

	public void update(double currentSpeed) {
		speed = (int) currentSpeed / 2;
		speedText.setText("" + speed);
	}

	@Override
	public void destroy() {
	}

}
