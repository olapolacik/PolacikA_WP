package com.mygdx.game;


/**
 * @author Aleksandra Połacik
 */

public class MovePlayer {
    private MazeNode playerNode;
    private Maze maze;
    /// Player position in real coordinates
    private float x, y;


    public MovePlayer(Maze maze) {
        this.maze = maze;
        this.playerNode = maze.getRootNode();
        updatePosition();
    }

    public void movePlayer(int direction) {
        MazeNode nextNode = playerNode.getNeighbor(direction);

        if (nextNode != null && playerNode.isDoor(direction)) {
            playerNode = nextNode;
            updatePosition();
        }
    }


    public boolean canMove(int direction) {
        boolean canMove = playerNode.isDoor(direction) && playerNode.getNeighbor(direction) != null;
        return canMove;
    }



    void updatePosition() {

        x = playerNode.getRealBounds().x;
        y = playerNode.getRealBounds().y;
    }



    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }


}
