package com.mygdx.game;

import java.util.ArrayList;
import java.util.Random;

public class MazeBuilder {
    private MazeNode[][] nodes;
    private static final int nodeSize = 60;
    private int startX;
    private int startY;
    public MazeBuilder setStartPoint(int x, int y) {
        startX = x;
        startY = y;
        return this;
    }
    public MazeBuilder buildMaze(int mazeSize){
        return buildMaze(mazeSize, mazeSize);
    }

    public MazeBuilder buildRandomDoors()
    {
        if(nodes == null || nodes.length == 0)
            return this;
        MazeNode root = nodes[0][0];
        Random rand = new Random();

        // Set the root node's predecessor so we know it's in the tree.
        root.predecessor = root;

        // Make a list of candidate links.
        ArrayList<MazeLink> links = new ArrayList<MazeLink>();

        // Add the root's links to the links list.
        for (MazeNode neighbor : root.neighbors)
        {
            if (neighbor != null)
                links.add(new MazeLink(root, neighbor));
        }

        // Add the other nodes to the tree.
        while (links.size() > 0)
        {
            // Pick a random link.
            //int link_num = rand.nextInt(0, links.size());
            int link_num = rand.nextInt(links.size());

            MazeLink link = links.get(link_num);
            links.remove(link_num);

            // Add this link to the tree.
            link.toNode.predecessor = link.fromNode;

            // Remove any links from the list that point
            // to nodes that are already in the tree.
            // (That will be the newly added node.)
            for (int i = links.size() - 1; i >= 0; i--)
            {
                if (links.get(i).toNode.predecessor != null)
                    links.remove(i);
            }

            // Add to_node's links to the links list.
            for (MazeNode neighbor : link.toNode.neighbors)
            {
                if ((neighbor != null) && (neighbor.predecessor == null))
                    links.add(new MazeLink(link.toNode, neighbor));
            }
        }
        return this;
    }

    public MazeBuilder buildMaze(int xCount, int yCount)
    {
        nodes = new MazeNode[yCount][xCount];
        return this;
    }
    
    public MazeBuilder buildAllRooms() {
        int yCount = nodes.length;
        int xCount = nodes[0].length;
        for (int y = 0; y < yCount; y++)
        {
            for (int x = 0; x < xCount; x++)
            {
                nodes[y][x] = new MazeNode(x, y, nodeSize, nodeSize);
            }
        }
        calculateNeighBors();
        return this;
    }

    public MazeBuilder buildRoom(int x, int y){
        nodes[y][x] = new MazeNode(x, y, nodeSize, nodeSize);
        calculateNeighBors();
        return this;
    }
    
    private MazeBuilder calculateNeighBors(){
        int yCount = nodes.length;
        int xCount = nodes[0].length;
        for (int r = 0; r < yCount; r++)
        {
            for (int x = 0; x < xCount; x++)
            {
                if(nodes[r][x] == null)
                    continue;
                if (r > 0)
                    nodes[r][x].neighbors[MazeNode.north] = nodes[r - 1][x];
                if (r < yCount - 1)
                    nodes[r][x].neighbors[MazeNode.south] = nodes[r + 1][x];
                if (x > 0)
                    nodes[r][x].neighbors[MazeNode.west] = nodes[r][x - 1];
                if (x < xCount - 1)
                    nodes[r][x].neighbors[MazeNode.east] = nodes[r][x + 1];
            }
        }
        return this;
    }

    public Maze getMaze()
    {
        return new Maze(nodes);
    }
}
