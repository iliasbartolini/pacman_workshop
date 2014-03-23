package com.thoughtworks.pacman.core.maze;

import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.visitors.DotsLeftVisitor;
import com.thoughtworks.pacman.core.tiles.visitors.ScoreTileVisitor;

import java.awt.*;
import java.util.Map;

public class Maze {
    private final Map<TileCoordinate, Tile> tilesMap;
    private final int width;
    private final int height;

    Maze(int width, int height, Map<TileCoordinate, Tile> tiles) {
        this.width = width;
        this.height = height;
        this.tilesMap = tiles;
    }

    public boolean canMove(TileCoordinate tileCoordinate) {
        return tileAt(tileCoordinate).isMovable();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Dimension getDimension() {
        return new Dimension(width * Tile.SIZE, height * Tile.SIZE);
    }

    public int getScore() {
        ScoreTileVisitor scoreVisitor = new ScoreTileVisitor();
        int totalScore = 0;
        for (Tile tile : tilesMap.values()) {
            totalScore += tile.visit(scoreVisitor);
        }
        return totalScore;
    }

    public boolean hasDotsLeft() {
        DotsLeftVisitor dotsLeftVisitor = new DotsLeftVisitor();
        int dotsLeft = 0;
        for (Tile tile : tilesMap.values()) {
            dotsLeft += tile.visit(dotsLeftVisitor);
        }
        return dotsLeft > 0;
    }

    public Tile tileAt(TileCoordinate tileCoordinate) {
        Tile tile = tilesMap.get(tileCoordinate);
        if(tile != null){
            return tile;
        } else {
            return new EmptyTile(tileCoordinate);
        }
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                result.append(tilesMap.get(new TileCoordinate(x,y)));
            }
            result.append("\n");
        }

        return result.toString();
    }
}
