package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * @author Aleksandra Połacik
 */

public class MazeNode {
    public static final int north = 0;
    public static final int south = north + 1;
    public static final int east = south + 1;
    public static final int west = east + 1;

    // The node's neighbors in order North, South, East, West.
    public MazeNode[] neighbors = new MazeNode[4];

    // The predecessor in the spanning tree.
    public MazeNode predecessor = null;

    // Information about doors in each direction.
    private boolean[] doors = new boolean[4];

    // The node's bounds.
    public Rectangle bounds;

    public int realWidth;
    public int realHeight;

    // Return this node's center.
    public Point getCenter() {
        return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
    }

    // Constructor.
    public MazeNode(int x, int y, int realWidth, int realHeight) {
        bounds = new Rectangle(x, y, 1, 1);
        this.realWidth = realWidth;
        this.realHeight = realHeight;
    }

    public Rectangle getRealBounds() {
        return new Rectangle(
                bounds.x * realWidth,
                bounds.y * realHeight,
                bounds.width * realWidth,
                bounds.height * realHeight);
    }

    // Set information about doors in a given direction.
    public void setOpenDoor(int direction, boolean isOpen) {
        doors[direction] = isOpen;
    }

    // Check if there is a wall in a given direction.
    public boolean isWall(int side) {
        MazeNode neighbor = neighbors[side];
        return neighbor == null || !doors[side];
    }

    // Check if there is a door in a given direction.
    public boolean isDoor(int side) {
        return doors[side];
    }

    // Draw the walls that don't cross a predecessor link.
    public void drawWalls(ShapeRenderer shapeRenderer) {
        for (int side = 0; side < 4; side++) {
            if (isWall(side)) {
                drawWall(shapeRenderer, side);
            }
        }
    }

    // Draw one side of our bounding box.
    private void drawWall(ShapeRenderer shapeRenderer, int side) {
        Rectangle realBounds = getRealBounds();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0f, 0f, 0f, 1f);

        switch (side) {
            case north:
                shapeRenderer.line(realBounds.x,
                        realBounds.y,
                        realBounds.x + realBounds.width,
                        realBounds.y);
                break;
            case south:
                shapeRenderer.line(
                        realBounds.x,
                        realBounds.y + realBounds.height,
                        realBounds.x + realBounds.width,
                        realBounds.y + realBounds.height);
                break;
            case east:
                shapeRenderer.line(
                        realBounds.x + realBounds.width,
                        realBounds.y,
                        realBounds.x + realBounds.width,
                        realBounds.y + realBounds.height);
                break;
            case west:
                shapeRenderer.line(
                        realBounds.x,
                        realBounds.y,
                        realBounds.x,
                        realBounds.y + realBounds.height);
                break;
        }
        shapeRenderer.end();
    }

    // Draw the doors
    public void drawDoors(ShapeRenderer shapeRenderer) {
        for (int side = 0; side < 4; side++) {
            if (isDoor(side)) {
                drawDoor(shapeRenderer, side);
            }
        }
    }

    // Draw one side of our bounding box.
    private void drawDoor(ShapeRenderer shapeRenderer, int side) {
        shapeRenderer.setColor(0f, 0f, 0f, 1f);
        switch (side) {
            case north:
                // Draw the door in the north direction.
                break;
            case south:
                // Draw the door in the south direction.
                break;
            case east:
                // Draw the door in the east direction.
                break;
            case west:
                // Draw the door in the west direction.
                break;
        }
    }

    public MazeNode getNeighbor(int direction) {
        return neighbors[direction];
    }
}
