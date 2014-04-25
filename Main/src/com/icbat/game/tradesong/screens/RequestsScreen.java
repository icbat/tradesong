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
import com.icbat.game.tradesong.gameObjects.Request;
import com.icbat.game.tradesong.gameObjects.collections.Items;
import com.icbat.game.tradesong.screens.components.ItemBox;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class RequestsScreen extends BaseInGameScreen {

    public static final int COLSPAN = 3;
    protected final Stage stage = new Stage();
    protected final Table table = new Table(Tradesong.uiStyles);

    public RequestsScreen() {
        setupStages(stage);
        table.setFillParent(true);
        stage.addActor(table);
        forceRelayout();
    }

    private void forceRelayout() {
        table.clear();
        List<Request> requestList = Tradesong.state.getRequestList();
        int i = 1;
        for (Request request : requestList) {
            table.add(new RequestActor(request)).space(35);
            if (i % 2 == 0) {
                table.row();
            }
            ++i;
        }
    }

    @Override
    public String getScreenName() {
        return "requestsScreen";
    }

    private class RequestActor extends Table {
        public RequestActor(Request request) {
            super(Tradesong.uiStyles);
            this.add(request.getRarity() + " Request", "header").colspan(COLSPAN).row();
            this.add("Requirements").row();
            ItemBox requirementsBox = getHighlightedReqiurementsBox(request);
            this.add(requirementsBox).colspan(COLSPAN).row();
            this.add("Rewards").row();
            this.add(new Image(TextureAssets.ITEMS.getRegion(4,30)));
            this.add(request.getRewardMoney() + "").row();
            this.add(ItemBox.make(request.getRewardItems())).colspan(COLSPAN).row();
            this.add(makeCompletionButton(request)).colspan(COLSPAN).row();
        }

        public ItemBox getHighlightedReqiurementsBox(Request request) {
            LinkedList<String> requirements = request.getRequirements();
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
                    item.setColor(Color.WHITE);
                }
            }

            return requirementsBox;
        }

        private TextButton makeCompletionButton(Request request) {
            TextButton.TextButtonStyle style;
            boolean canComplete = request.canComplete();
            if (canComplete) {
                style = Tradesong.uiStyles.get("default", TextButton.TextButtonStyle.class);
            } else {
                style = Tradesong.uiStyles.get("disabled", TextButton.TextButtonStyle.class);
            }

            TextButton completionButton = new TextButton("Complete this!", style);
            if (canComplete) {
                completionButton.addListener(new RequestCompletionListener(request));
            }
            return completionButton;
        }

        private class RequestCompletionListener extends ClickListener {
            private final Request request;

            public RequestCompletionListener(Request request) {
                this.request = request;
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                request.completeRequest();
                forceRelayout();
            }
        }
    }


}
