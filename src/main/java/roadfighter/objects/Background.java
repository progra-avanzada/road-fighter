package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import roadfighter.interfaces.Renderable;
import roadfighter.interfaces.Updatable;
import roadfighter.utils.GameObject;

public abstract class Background extends GameObject implements Updatable, Renderable {

	protected VBox render;
	protected double posX = 0;

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() { }

}
