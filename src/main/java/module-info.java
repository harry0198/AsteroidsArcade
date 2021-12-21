module com.harrydrummond.asteroids {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;

    opens com.harrydrummond.asteroids to javafx.fxml;
    exports com.harrydrummond.asteroids;
    exports com.harrydrummond.asteroids.geometry;
    exports com.harrydrummond.asteroids.game;
    exports com.harrydrummond.asteroids.controller;
    exports com.harrydrummond.asteroids.sprites;
    opens com.harrydrummond.asteroids.game to javafx.fxml;
    exports com.harrydrummond.asteroids.components;
    exports com.harrydrummond.asteroids.controller.keycontrollers;
}