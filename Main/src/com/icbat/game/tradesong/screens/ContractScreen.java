package com.icbat.game.tradesong.screens;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.screens.components.ItemBox;

public class ContractScreen extends BaseInGameScreen {

    public static final int COLSPAN = 3;

    public ContractScreen() {
        Stage stage = new Stage();
        Table table = new Table(Tradesong.uiStyles);

        java.util.List<Contract> contractList = Tradesong.state.getContractList();
        int i = 1;
        for (Contract contract : contractList) {
            table.add(new ContractActor(contract));
            if (i % 2 == 0) {
                table.row();
            }
            ++i;
        }
        stage.addActor(table);
        table.setFillParent(true);
        setupStages(stage);
    }

    @Override
    public String getScreenName() {
        return "contractsScreen";
    }

    private class ContractActor extends Table {
        public ContractActor(Contract contract) {
            super(Tradesong.uiStyles);
            this.add(contract.getRarity() + " Contract").colspan(COLSPAN).space(10).row();
            this.add("Requirements");
            this.add(ItemBox.make(contract.getRequirements())).colspan(COLSPAN).row();
            this.add("Reward Money");
            this.add(new Image(TextureAssets.ITEMS.getRegion(4,30)));
            this.add(contract.getRewardMoney() + "").row();
            this.add("Rewards");
            this.add(ItemBox.make(contract.getRewardItems())).colspan(COLSPAN).row();
        }
    }
}
