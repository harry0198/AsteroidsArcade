package com.harrydrummond.asteroids;

import com.harrydrummond.asteroids.controller.GameController;
import com.harrydrummond.asteroids.game.FixedGameLoop;
import com.harrydrummond.asteroids.game.GameLoop;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefSize(500,500);

        Scene scene = new Scene(pane, 500,500, Color.BLACK);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        GameController controller = new GameController(pane);
        GameLoop loop = new FixedGameLoop(controller);
        loop.run();

    }

    public static void main(String[] args) {
        launch();
    }
}