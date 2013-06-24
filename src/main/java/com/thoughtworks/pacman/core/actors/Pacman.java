package com.thoughtworks.pacman.core.actors;

import com.thoughtworks.pacman.core.Actor;
import com.thoughtworks.pacman.core.Direction;
import com.thoughtworks.pacman.core.SpacialCoordinate;
import com.thoughtworks.pacman.core.Tile;
import com.thoughtworks.pacman.core.TileCoordinate;
import com.thoughtworks.pacman.core.maze.Maze;

public class Pacman extends Actor {
    private Direction desiredDirection;
    private Direction previousDirection;
    private boolean dead = false;

    public Pacman(Maze maze) {
        this(maze, new SpacialCoordinate(14 * Tile.SIZE, 26 * Tile.SIZE + Tile.SIZE / 2), Direction.LEFT);
    }

    protected Pacman(Maze maze, SpacialCoordinate center, Direction direction) {
        super(maze, center, direction);
        this.desiredDirection = direction;
    }

    public void die() {
        this.dead = true;
    }

    public boolean isDead() {
        return dead;
    }

    public void setNextDirection(Direction direction) {
        this.desiredDirection = direction;
    }

    public Direction getPreviousDirection() {
        return previousDirection;
    }

    public boolean isMoving() {
        return currentDirection != Direction.NONE;
    }

    @Override
    protected Direction getNextDirection(TileCoordinate tileCoordinate) {
        if (allowMove(tileCoordinate, desiredDirection)) {
            currentDirection = desiredDirection;
        } else if (!allowMove(tileCoordinate, currentDirection)) {
            previousDirection = currentDirection;
            currentDirection = Direction.NONE;
        }
        return currentDirection;
    }

    private boolean allowMove(TileCoordinate tileCoordinate, Direction direction) {
        TileCoordinate nextTile = tileCoordinate.add(direction.tileDelta());
        return maze.canMove(nextTile);
    }
}
