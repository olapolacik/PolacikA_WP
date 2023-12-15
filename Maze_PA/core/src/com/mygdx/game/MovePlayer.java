package com.mygdx.game;


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

        if (canMove(direction)) {
            playerNode = nextNode;
        }
    }

//    public boolean canMove(int direction) {
//        MazeNode nextNode = playerNode.getNeighbor(direction);
//        boolean canMove = nextNode != null && (nextNode.isDoor(direction) || !nextNode.isWall(direction));
//        return canMove;
//    }

public boolean canMove(int direction) {
    MazeNode nextNode = playerNode.getNeighbor(direction);

    boolean canMove = nextNode != null && (nextNode.isDoor(direction) || !nextNode.isWall(direction));

    if (canMove) {
        System.out.println("Can move in direction " + direction + ": true");
        System.out.println("Is door: " + nextNode.isDoor(direction));
        System.out.println("Is wall: " + nextNode.isWall(direction));
    } else {
        System.out.println("Can move in direction " + direction + ": false");
        System.out.println("Is door: false");
        System.out.println("Is wall: true");
    }

    return canMove;
}



    void updatePosition() {
        x = playerNode.getRealBounds().x;
        y = playerNode.getRealBounds().y;
        System.out.println("Updated Player Position: x=" + x + ", y=" + y);
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
