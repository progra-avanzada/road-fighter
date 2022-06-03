package road_fighter.objects;

import java.util.concurrent.atomic.AtomicInteger;

import road_fighter.interfaces.Renderable;
import road_fighter.interfaces.Updatable;
import road_fighter.utils.GameObject;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class FpsInfo extends GameObject implements Renderable, Updatable {
	private final int Y = 5;

	private Text text;
	private VBox render;
	private AtomicInteger fps;

	public FpsInfo(AtomicInteger fps) {
		this.fps = fps;
		text = new Text();

		render = new VBox(text);
		render.setTranslateY(Y);

		text.setFont(Font.font("MONOSPACED"));
		text.setFill(Color.BLACK);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double deltaTime) {
		text.setText("FPS: " + fps);
	}

	@Override
	public void destroy() {	}

}
