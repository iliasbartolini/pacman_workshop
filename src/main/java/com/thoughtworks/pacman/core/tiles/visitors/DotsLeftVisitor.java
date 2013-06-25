package com.thoughtworks.pacman.core.tiles.visitors;

import com.thoughtworks.pacman.core.TileVisitor;
import com.thoughtworks.pacman.core.tiles.Dot;
import com.thoughtworks.pacman.core.tiles.EmptyTile;
import com.thoughtworks.pacman.core.tiles.Wall;

public class DotsLeftVisitor implements TileVisitor<Integer> {
    @Override
    public Integer visit(Dot dot) {
        return dot.isEaten() ? 0 : 1;
    }

    @Override
    public Integer visit(Wall wall) {
        return 0;
    }

    @Override
    public Integer visit(EmptyTile emptyTile) {
        return 0;
    }
}