package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.Collection;

/***/
public class VerticalWorkshopDisplay extends Table {
    public VerticalWorkshopDisplay(Collection<Workshop> workshops) {
        for (Workshop workshop : workshops) {
            this.add(workshop.getActor()).row();
        }
    }
}
