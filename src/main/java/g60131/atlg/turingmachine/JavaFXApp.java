package g60131.atlg.turingmachine;

import g60131.atlg.turingmachine.view.FxStart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.util.Objects;

public class JavaFXApp extends Application {
    public static void main(String[] args) {
        // Lancer JavaFx
        launch();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Turing Machine");
        Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/TuringMachine-assets/Turing-Machine-Logo2.png")));
        stage.getIcons().add(logo); // Set le logo du programme

        ScrollPane root = new ScrollPane();
        var scene = new Scene(root, 1300, 850);

        VBox main = new VBox();
        FxStart start = new FxStart(main, stage);
        main.getChildren().add(start);
        main.setFillWidth(true);
        main.setBackground(Background.fill(Paint.valueOf("#70cd77")));

        root.setContent(main);
        root.setFitToWidth(true);
        root.setFitToHeight(true);
        stage.setScene(scene);
        stage.show();
    }
}