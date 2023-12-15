package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class MazeGame extends ApplicationAdapter {
    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    private Maze maze;
    private MovePlayer movePlayer;

    private MazeNode mazeNode;
    private MazeNode[] mazeTab;

    //Textures
    Texture cat_left;
    Texture cat_right;
    Texture cat_up;
    Texture cat_down;
    Texture ball;
    private Texture currentCatTexture; //do przechowywania biezacej tekstury


    //speed cat
    private static final int CAT_SPEED = 10;

    private float x, y;

    private MazeBuilder mazeBuilder;

    //private MazeNode x,y;


    @Override
    public void create() {
        batch = new SpriteBatch();

        //textures
        cat_left = new Texture("cat_left.png");
        cat_right = new Texture("cat_right.png");
        cat_up = new Texture("cat_up.png");
        cat_down = new Texture("cat_down.png");
        ball = new Texture("ball.png");

        //currentCatTexture = cat_left;


        shapeRenderer = new ShapeRenderer();

        MazeBuilder mazeBuilder = new MazeBuilder();

        maze = mazeBuilder
                .buildMaze(10, 10)
                .buildAllRooms()
                .buildRandomDoors()
                .getMaze();

        movePlayer = new MovePlayer(maze);

        maze.draw(shapeRenderer);
    }

//    @Override
//    public void render() {
//        ScreenUtils.clear(1, 1, 1, 1);
//
//        float oldX = x;
//        float oldY = y;
//
//        //MazeNode oldX = x;
//        //MazeNode oldY = y;
//
///*
//        float deltaTime = Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
//        {
//
//            // jezeli drzwi
//            if (mazeNode.isDoor(MazeNode.east))
//            {
//               currentCatTexture = cat_right; // Zmiana tekstury na cat_right
//                //x += CAT_SPEED * deltaTime;   //
//            }
//            // jezeli sciana
//            else if (mazeNode.isWall(MazeNode.east))
//            {
//                x = oldX;
//                x += CAT_SPEED * deltaTime;   //
//
//            }*/
//
//
//        float deltaTime = Gdx.graphics.getDeltaTime();
//        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
//        {
//            currentCatTexture = cat_right; // Zmiana tekstury na cat_right
//            x += CAT_SPEED * deltaTime;
//
//        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
//            x-= CAT_SPEED * deltaTime;
//            currentCatTexture = cat_left; // Zmiana tekstury na cat_left
//        }
//        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
//            if(mazeNode.isDoor(1)) {
//                y += CAT_SPEED * deltaTime;
//                currentCatTexture = cat_up; // Zmiana tekstury na cat_up
//            }
//        } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
//            y -= CAT_SPEED * deltaTime;
//            currentCatTexture = cat_down; // Zmiana tekstury na cat_down
//
//        }
//
//        // Sprawdź, czy nowa pozycja wykracza poza granice okna
//        if (x < 0 || x > Gdx.graphics.getWidth() - cat_left.getWidth()) {
//            x = oldX;  // Przywróć poprzednią pozycję
//        }
//        if (y < 0 || y > Gdx.graphics.getHeight() - cat_left.getHeight()) {
//           y = oldY;  // Przywróć poprzednią pozycję
//        }
//
//        batch.begin();
//
//        batch.draw(currentCatTexture, x, y);
//
//        batch.end();
//
//        maze.draw(shapeRenderer);
//    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 1);

        float deltaTime = Gdx.graphics.getDeltaTime();

        // Move the player using MovePlayer class
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (movePlayer.canMove(MazeNode.east)) {
                movePlayer.movePlayer(MazeNode.east);
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (movePlayer.canMove(MazeNode.west)) {
                movePlayer.movePlayer(MazeNode.west);
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (movePlayer.canMove(MazeNode.south)) {
                movePlayer.movePlayer(MazeNode.south);
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (movePlayer.canMove(MazeNode.north)) {
                movePlayer.movePlayer(MazeNode.north);
            }
        }


        movePlayer.updatePosition();

        // Umieść piłkę na środku aktualnego pokoju
        float roomCenterX = movePlayer.getX() + 60 / 2 - ball.getWidth() / 2;
        float roomCenterY = movePlayer.getY() + 60 / 2 - ball.getHeight() / 2;

        batch.begin();
        batch.draw(ball, roomCenterX, roomCenterY);
        batch.end();


        maze.draw(shapeRenderer);
    }




    @Override
    public void dispose() {
        batch.dispose();
        cat_left.dispose();
        shapeRenderer.dispose();
    }
}
