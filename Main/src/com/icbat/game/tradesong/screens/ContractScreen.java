package com.icbat.game.tradesong.screens;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.gameObjects.collections.Items;
import com.icbat.game.tradesong.screens.components.ItemBox;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ContractScreen extends BaseInGameScreen {

    public static final int COLSPAN = 3;

    public ContractScreen() {
        Gdx.app.debug("contractScreen", Tradesong.state.inventory().getEditableInventory().toString());
        Stage stage = new Stage();
        Table table = new Table(Tradesong.uiStyles);

        java.util.List<Contract> contractList = Tradesong.state.getContractList();
        int i = 1;
        for (Contract contract : contractList) {
            table.add(new ContractActor(contract)).space(35);
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
            this.add(contract.getRarity() + " Contract", "header").colspan(COLSPAN).row();
            this.add("Requirements").row();
            ItemBox requirementsBox = getHighlightedReqiurementsBox(contract);
            this.add(requirementsBox).colspan(COLSPAN).row();
            this.add("Rewards").row();
            this.add(new Image(TextureAssets.ITEMS.getRegion(4,30)));
            this.add(contract.getRewardMoney() + "").row();
            this.add(ItemBox.make(contract.getRewardItems())).colspan(COLSPAN).row();
            this.add(makeCompletionButton(contract)).colspan(COLSPAN).row();
        }

        public ItemBox getHighlightedReqiurementsBox(Contract contract) {
            LinkedList<String> requirements = contract.getRequirements();
            Collections.sort(requirements);
            ItemBox requirementsBox = ItemBox.make(requirements);

            for (Actor actor : requirementsBox.getChildren()) {
                actor.setColor(Color.RED);
            }

            List<String> matchList = Tradesong.state.inventory().getMatchList(requirements);
            for (Actor actor : requirementsBox.getChildren()) {
                Items.Item item = (Items.Item) actor;
                if (matchList.contains(item.getName())) {
                    matchList.remove(item.getName());
                    item.setColor(Color.GREEN);
                }

            }

            return requirementsBox;
        }

        private TextButton makeCompletionButton(Contract contract) {
            TextButton.TextButtonStyle style;
            boolean canComplete = contract.canComplete(Tradesong.state.inventory().getEditableInventory());
            if (canComplete) {
                style = Tradesong.uiStyles.get("default", TextButton.TextButtonStyle.class);
            } else {
                style = Tradesong.uiStyles.get("disabled", TextButton.TextButtonStyle.class);
            }

            TextButton completionButton = new TextButton("Complete this!", style);
            completionButton.setBackground(new NinePatchDrawable(new NinePatch(Tradesong.getTexture(TextureAssets.POPUP_BG), 2, 2, 2, 2)));
            return completionButton;
        }
    }
}
