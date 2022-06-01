package road_figther.objects.menu;

import road_figther.Config;
import road_figther.interfaces.Renderable;
import road_figther.utils.GameObject;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class TextoSalir extends GameObject implements Renderable {
	private final int Y = Config.baseHeight * 3 / 5 *0;

	private Text text;
	
	private VBox render;

	public TextoSalir() {
		text = new Text("Press Exit to quit");

		render = new VBox(text);
		render.setAlignment(Pos.BASELINE_LEFT);
		render.setTranslateY(Y);
		// Esto debería heredarse?
		render.setPrefWidth(Config.baseWidth);

		Font font = Font.font("Upheaval TT (BRK)", FontWeight.NORMAL, 25);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(font);
		text.setFill(Color.BLUEVIOLET);
		
		
		
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {	}
	
}
