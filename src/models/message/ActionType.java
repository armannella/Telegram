package models.message;

public enum ActionType {
    ADDED("added"),
    REMOVED("removed"),
    RENAMED("changed the Chat name to"),
    CREATED("Created This Chat");

    private final String text;

    ActionType(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
