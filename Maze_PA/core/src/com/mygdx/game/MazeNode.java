package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.awt.Point;
import java.awt.Rectangle;

public class MazeNode {
    public static final int north = 0;
    public static final int south = north + 1;
    public static final int east = south + 1;
    public static final int west = east + 1;


    // The node's neighbors in order North, South, East, West.
    public MazeNode[] neighbors = new MazeNode[4];

    // The predecessor in the spanning tree.
    public MazeNode predecessor = null;

    // The node's bounds.
    public Rectangle bounds;

    public int realWidth;
    public int realHeight;


    // Return this node's center.
    public Point getCenter()
    {
        return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
    }

    // Constructor.
    public MazeNode(int x, int y, int realWidth, int realHeight)
    {
        bounds = new Rectangle(x, y, 1, 1);
        this.realWidth = realWidth;
        this.realHeight = realHeight;
    }

    public Rectangle getRealBounds()
    {
        return new Rectangle(
                bounds.x * realWidth,
                bounds.y * realHeight,
                bounds.width * realWidth,
                bounds.height * realHeight);
    }

    public boolean isWall(int side)
    {
        return (neighbors[side] == null) || ((neighbors[side].predecessor != this) && (neighbors[side] != this.predecessor));
    }

    public boolean isDoor(int side)
    {
        return !isWall(side);
    }

    // Draw the walls that don't cross a predecessor link.
    public void drawWalls(ShapeRenderer shapeRenderer)
    {
        for (int side = 0; side < 4; side++)
        {
            if (isWall(side))
            {
                drawWall(shapeRenderer, side);
            }
        }
    }

    // Draw one side of our bounding box.
    private void drawWall(ShapeRenderer shapeRenderer, int side)
    {
        Rectangle realBounds = getRealBounds();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1f);

        switch (side)
        {
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
    public void drawDoors(ShapeRenderer shapeRenderer)
    {
        for (int side = 0; side < 4; side++)
        {
            if (isDoor(side))
            {
                drawDoor(shapeRenderer, side);

            }
        }
    }

    // Draw one side of our bounding box.
    private void drawDoor(ShapeRenderer shapeRenderer , int side)
    {
        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1f);
        switch (side)
        {
            case north:
                //g.drawLine(bounds.x, bounds.y, bounds.x + bounds.width, bounds.y);
                break;
            case south:
                //g.drawLine(bounds.x, bounds.y + bounds.height, bounds.x + bounds.width, bounds.y + bounds.height);
                break;
            case east:
                //g.drawLine(bounds.x + bounds.width, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height);
                break;
            case west:
                //g.drawLine(bounds.x, bounds.y, bounds.x, bounds.y + bounds.height);
                break;
        }
    }


    public MazeNode getNeighbor(int direction) {
        return neighbors[direction];
    }

}
