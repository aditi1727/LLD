package org.example.entity;

import java.util.List;

public class Cursor {
    private int row;
    private int col;

    public Cursor(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void moveUp(List<StringBuilder> lines) {
        if (row > 0) {
            row--;
            col = Math.min(col, lines.get(row).length());
        }
    }

    public void moveDown(List<StringBuilder> lines) {
        if (row < lines.size() - 1) {
            row++;
            col = Math.min(col, lines.get(row).length());
        }
    }

    public void moveLeft(List<StringBuilder> lines) {
        if (col > 0) {
            col--;
        } else if (row > 0) {
            row--;
            col = lines.get(row).length();
        }
    }

    public void moveRight(List<StringBuilder> lines) {
        if (col < lines.get(row).length()) {
            col++;
        } else if (row < lines.size() - 1) {
            row++;
            col = 0;
        }
    }
}
