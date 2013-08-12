package com.icbat.game.tradesong.itemActions;

import com.icbat.game.tradesong.LeveledParameter;
import com.icbat.game.tradesong.Tradesong;

public class SpeedUpgrade extends AbstractItemAction {

    String affectedParameter;
    int level;

    public SpeedUpgrade(String affectedParameter, int level) {
        this.affectedParameter = affectedParameter;
        this.level = level;
    }

    @Override
    public boolean use() {
        LeveledParameter param = Tradesong.gameState.getParameterByName(affectedParameter);
        if(param.getLevel() < level) {
            param.setLevel(level);
            param.setCurrentValue(param.getCurrentValue() / 2); // TODO currently, level 1 is better than higher levels, change that

            return true;
        }
        return false;
    }
}
