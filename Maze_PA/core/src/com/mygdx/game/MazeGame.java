package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * @author Aleksandra Połacik
 */

public class MazeGame extends ApplicationAdapter {
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Maze maze;
    private MovePlayer movePlayer;
    private Stage stage;
    private Label label;
    private Table table;
    private boolean gameCompleted;

    //===========================TEKSTURY
    private Texture catUP;
    private Texture catDOWN;
    private Texture catRIGHT;
    private Texture catLEFT;
    private Texture catTexture;
    private Texture portal;
    private Texture bomb;
    private List<MazeBomb> bombs;


    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        BitmapFont font = new BitmapFont();
        font.getData().setScale(2);
        Label.LabelStyle style = new Label.LabelStyle(font, Color.BLACK);
        label = new Label("BRAWO UKONCZYLES LABIRYNT!", style);
        table.add(label).center();

        label.setVisible(false);


        MazeBuilder mazeBuilder = new MazeBuilder();
        maze = mazeBuilder
                .buildMaze(10, 10)
                .buildAllRooms()
                .buildRandomDoors()
                .getMaze();

        movePlayer = new MovePlayer(maze);

        catUP = new Texture("cat_up.png");
        catDOWN = new Texture("cat_down.png");
        catRIGHT = new Texture("cat_right.png");
        catLEFT = new Texture("cat_left.png");
        portal = new Texture("portal.png");
        bomb = new Texture("bomb.png");

        catTexture = catUP;


    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 1, 1, 0);

        float deltaTime = Gdx.graphics.getDeltaTime();

        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            if (movePlayer.canMove(MazeNode.east)) {
                movePlayer.movePlayer(MazeNode.east);
                catTexture = catRIGHT;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
            if (movePlayer.canMove(MazeNode.west)) {
                movePlayer.movePlayer(MazeNode.west);
                catTexture = catLEFT;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if (movePlayer.canMove(MazeNode.south)) {
                movePlayer.movePlayer(MazeNode.south);
                catTexture = catUP;
            }
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if (movePlayer.canMove(MazeNode.north)) {
                movePlayer.movePlayer(MazeNode.north);
                catTexture = catDOWN;
            }
        }


        movePlayer.updatePosition();

        float playerX = movePlayer.getX();
        float playerY = movePlayer.getY();

        float roomCenterX = playerX + 60 / 2 - catTexture.getWidth() / 2;
        float roomCenterY = playerY + 60 / 2 - catTexture.getHeight() / 2;


        // Pobierz szerokość i wysokość okna gry
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Ustaw pozycję tekstury w prawym górnym rogu
        float textureX = screenWidth - portal.getWidth(); // prawy kraniec ekranu minus szerokość tekstury
        float textureY = screenHeight - portal.getHeight(); // górny kraniec ekranu minus wysokość tekstury


        if (!gameCompleted) {
            if (playerX < textureX + portal.getWidth() &&
                    playerX + catTexture.getWidth() > textureX &&
                    playerY < textureY + portal.getHeight() &&
                    playerY + catTexture.getHeight() > textureY) {
                gameCompleted = true;
                label.setVisible(true);
            }
        }


        batch.begin();
        batch.draw(catTexture, roomCenterX, roomCenterY);
        batch.draw(portal, textureX, textureY);
        batch.end();


        // Sprawdź, czy tekstura kota i portalu znajdują się w tym samym miejscu
        if (playerX < textureX + portal.getWidth() &&
                playerX + catTexture.getWidth() > textureX &&
                playerY < textureY + portal.getHeight() &&
                playerY + catTexture.getHeight() > textureY) {
            label.setVisible(true);

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    Gdx.app.exit();  // Zamknij aplikację
                }
            }, 2);  // Zamknięcie po 2 sekundach
        }

        // Rysuj interfejs użytkownika
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        maze.draw(shapeRenderer);


    }

    @Override
    public void dispose() {
        batch.dispose();
        catTexture.dispose();
        shapeRenderer.dispose();
    }
}
