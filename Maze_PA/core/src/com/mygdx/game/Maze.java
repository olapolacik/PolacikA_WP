package com.mygdx.game;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Maze {

    private MazeNode[][] nodes;

    public Maze(MazeNode[][] nodes) {
        this.nodes = nodes;
    }

    public MazeNode[][] getNodes() {
        return nodes;
    }

    public void draw(ShapeRenderer shapeRenderer)
    {
        int yCount = nodes.length;
        int xCount = nodes[0].length;
        for (int r = 0; r < yCount; r++)
        {
            for (int c = 0; c < xCount; c++)
            {
                nodes[r][c].drawWalls(shapeRenderer);
                nodes[r][c].drawDoors(shapeRenderer);
            }
        }
    }

    public MazeNode getRootNode() { return nodes[0][0]; }
}
