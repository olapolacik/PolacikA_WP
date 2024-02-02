package com.mygdx.game;

/**
 * @author Aleksandra Polacik
 * 
 */

public class MazeLink {
    public MazeNode fromNode, toNode;
    public MazeLink(MazeNode from_node, MazeNode to_node)
    {
        fromNode = from_node;
        toNode = to_node;
    }
}
