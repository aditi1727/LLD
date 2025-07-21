package org.example.entity;

import org.example.Enums.Direction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TextEditor {
    private final List<StringBuilder> lines;
    private final Cursor cursor;
    private final Deque<List<StringBuilder>> undoStack;
    private final Deque<List<StringBuilder>> redoStack;

    public TextEditor() {
        lines = new ArrayList<>();
        lines.add(new StringBuilder());
        cursor = new Cursor(0, 0);
        undoStack = new ArrayDeque<>();
        redoStack = new ArrayDeque<>();
    }

    private void saveStateForUndo() {
        List<StringBuilder> snapshot = new ArrayList<>();
        for (StringBuilder line : lines) {
            snapshot.add(new StringBuilder(line));
        }
        undoStack.push(snapshot);
        redoStack.clear();
    }

    public void append(char c) {
        saveStateForUndo();
        lines.get(cursor.getRow()).insert(cursor.getCol(), c);
        cursor.moveRight(lines);
    }

    public void replace(char c) {
        saveStateForUndo();
        int row = cursor.getRow();
        int col = cursor.getCol();
        if (col < lines.get(row).length()) {
            lines.get(row).setCharAt(col, c);
        } else {
            lines.get(row).append(c);
        }
        cursor.moveRight(lines);
    }

    public void moveArrow(Direction direction) {
        switch (direction) {
            case UP:
                cursor.moveUp(lines);
                break;
            case DOWN:
                cursor.moveDown(lines);
                break;
            case LEFT:
                cursor.moveLeft(lines);
                break;
            case RIGHT:
                cursor.moveRight(lines);
                break;
        }
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(copy(lines));
            restore(undoStack.pop());
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(copy(lines));
            restore(redoStack.pop());
        }
    }

    private List<StringBuilder> copy(List<StringBuilder> source) {
        List<StringBuilder> copy = new ArrayList<>();
        for (StringBuilder sb : source) {
            copy.add(new StringBuilder(sb));
        }
        return copy;
    }

    private void restore(List<StringBuilder> snapshot) {
        lines.clear();
        for (StringBuilder sb : snapshot) {
            lines.add(new StringBuilder(sb));
        }
    }

    public String getText() {
        StringBuilder sb = new StringBuilder();
        for (StringBuilder line : lines) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
