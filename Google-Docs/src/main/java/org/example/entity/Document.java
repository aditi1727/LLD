package org.example.entity;

public class Document {
    private final String id;
    private final String name;
    private final String userId;
    ;
    private final TextEditor editor;

    private boolean isSaved = false;

    public Document(String id, String name, String ownerId) {
        this.id = id;
        this.name = name;
        this.userId = ownerId;
        this.editor = new TextEditor();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getUserId() { return userId; }
    public TextEditor getEditor() { return editor; }
    public void markAsSaved() {
        isSaved = true;
    }
    public boolean isSaved() {
        return isSaved;
    }
}
