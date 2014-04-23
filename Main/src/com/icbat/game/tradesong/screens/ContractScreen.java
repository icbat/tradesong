package com.icbat.game.tradesong.screens;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    protected final Stage stage = new Stage();
    protected final Table table = new Table(Tradesong.uiStyles);

    public ContractScreen() {
        setupStages(stage);
        table.setFillParent(true);
        stage.addActor(table);
        forceRelayout();
    }

    private void forceRelayout() {
        table.clear();
        List<Contract> contractList = Tradesong.state.getContractList();
        int i = 1;
        for (Contract contract : contractList) {
            table.add(new ContractActor(contract)).space(35);
            if (i % 2 == 0) {
                table.row();
            }
            ++i;
        }
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
            boolean canComplete = contract.canComplete();
            if (canComplete) {
                style = Tradesong.uiStyles.get("default", TextButton.TextButtonStyle.class);
            } else {
                style = Tradesong.uiStyles.get("disabled", TextButton.TextButtonStyle.class);
            }

            TextButton completionButton = new TextButton("Complete this!", style);
            if (canComplete) {
                completionButton.addListener(new ContractCompletionListener(contract));
            }
            return completionButton;
        }

        private class ContractCompletionListener extends ClickListener {
            private final Contract contract;

            public ContractCompletionListener(Contract contract) {
                this.contract = contract;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//                contract.completeContract();
                forceRelayout();
            }
        }
    }


}
