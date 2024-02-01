/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mygdx.game;

public class MazeLink {
    public MazeNode fromNode, toNode;
    public MazeLink(MazeNode from_node, MazeNode to_node)
    {
        fromNode = from_node;
        toNode = to_node;
    }
}
