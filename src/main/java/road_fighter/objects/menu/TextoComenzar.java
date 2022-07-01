package road_fighter.objects.menu;

import road_fighter.Config;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import road_fighter.interfaces.Renderable;
import road_fighter.utils.GameObject;

public class TextoComenzar extends GameObject implements Renderable {
	private final int Y = Config.baseHeight * 3 / 5;

	private Text text;
	private VBox render;
	private Font font;

	public TextoComenzar(String textString) {
		font = Font.loadFont(ClassLoader.getSystemResource("font/GAMERIA.ttf").toString(), 50);

		text = new Text(textString);
		render = new VBox(text);
		render.setAlignment(Pos.TOP_CENTER);
		render.setPrefWidth(Config.baseWidth);

		render.setTranslateY(Y);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(font);
		text.setFill(Color.GHOSTWHITE);
	}
	
	public void setText(String textString) {
		text.setText(textString);
	}
	
	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {
		text.setVisible(false);
	}
	
}
