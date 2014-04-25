package com.tradesonggame.observation;

/***/
public class Notification {
    private String action;
    private Object contents;

    protected Notification(String action, Object contents) {
        this.action = action;
        this.contents = contents;
    }

    public String getAction() {
        return action;
    }

    public Object getContents() {
        return contents;
    }
}
