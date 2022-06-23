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

public class GPS extends GameObject implements Renderable {
	private final int Y = Config.playerHeight / 2;
	private final int posX;
	private int height = 90;
	private double km = 0;

	private Text kmText;
	private Text text;
	private VBox render;
	private Rectangle cuadro;

	public GPS(int posX) {
		this.posX = posX;

		text = new Text("METROS\nRESTANTES:");
		text.setY(Y - 70);
		text.setX(10);

		kmText = new Text("" + km);
		kmText.setY(Y - 10);
		kmText.setX(5);

		cuadro = new Rectangle(Config.sizeBar - 10, height);
		cuadro.setFill(Color.BLACK);
		cuadro.setY(Y - 90);
		cuadro.setViewOrder(3);

		Group group = new Group();
		group.getChildren().add(text);
		group.getChildren().add(kmText);
		group.getChildren().add(cuadro);

		render = new VBox(group);
		render.setSpacing(5);
		render.setAlignment(Pos.CENTER_LEFT);
		render.setTranslateY(Y);
		render.setTranslateX(this.posX);
		// Esto debería heredarse?
		render.setPrefWidth(Config.baseWidth);

		Font font = Font.loadFont(ClassLoader.getSystemResource("font/road-fighter.ttf").toString(), 23);
		kmText.setTextAlignment(TextAlignment.LEFT);
		kmText.setFont(font);
		kmText.setFill(Color.WHITE);

		Font font1 = Font.loadFont(ClassLoader.getSystemResource("font/road-fighter.ttf").toString(), 13);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(font1);
		text.setFill(Color.WHITE);

		DropShadow ds = new DropShadow();
		ds.setColor(Color.RED);
		kmText.setEffect(ds);
		text.setEffect(ds);

	}

	@Override
	public Node getRender() {
		return render;
	}

//	public void increase() {
//		this.update();
//	}

	public double getGPS() {

		return km;
	}

	public void update(double distanciaRecorrida) {
		km = Math.round((Config.distanciaMax - distanciaRecorrida) * 100.0 / 1000) / 100.0;
		kmText.setText(km + " Km");
	}

	@Override
	public void destroy() {
	}

}
