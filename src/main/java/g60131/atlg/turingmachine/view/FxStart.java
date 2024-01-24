package g60131.atlg.turingmachine.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class FxStart extends VBox {
    private ChoiceBox<String> choiceBox;
    private final List<List<String>> problems = new LinkedList<>();
    private FxGame game;
    private final VBox root;
    public FxStart(VBox root, Stage stage) {
        this.root = root;
        this.setSpacing(15);
        this.setAlignment(Pos.TOP_CENTER);

        bufferReader();
        initTitle();
        initLogo();
        initChoiceBox();
        initButton(stage);
    }

    /**
     * Initializes the title label with the specified text and styling.
     */
    private void initTitle() {
        Label title = new Label("Welcome to Turing Machine");
        title.setPadding(new Insets(20, 0, 0, 0));
        title.setFont(Font.font("Arial", FontWeight.BOLD, 25));
        title.setTextFill(Color.ALICEBLUE);
        this.getChildren().add(title);
    }

    /**
     * Initializes the logo view with an image and specific dimensions.
     */
    private void initLogo() {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/TuringMachine-assets/Turing-Machine-Logo.png")));
        ImageView logoView = new ImageView(image);
        logoView.setPreserveRatio(true);
        logoView.setFitWidth(350);
        this.getChildren().add(logoView);
    }

    /**
     * Initializes the ChoiceBox with options based on the available problems.
     */
    private void initChoiceBox() {
        ObservableList<String> options = FXCollections.observableArrayList();
        for (List<String> list : problems) {
            String str = "Problem: " + list.get(0) + " Difficulty: " + list.get(1) + " Luck: " + list.get(2);
            options.add(str);
        }
        choiceBox = new ChoiceBox<>(options);
        choiceBox.setMinWidth(250); // La longueur du ChoiceBox
        this.getChildren().add(choiceBox);
    }

    /**
     * Initializes the "Launch Game" button and sets up its event handler.
     * @param stage The JavaFX stage.
     */
    private void initButton(Stage stage) {
        Button startButton = new Button("Launch Game");
        this.getChildren().add(startButton);
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (choiceBox.getValue() != null) {
                    String[] whichProblem = choiceBox.getValue().split("\\s+");
                    int index = 0;
                    List<String> problem = problems.get(index);
                    while (!problem.get(0).equals(whichProblem[1])) {
                        index++;
                        problem = problems.get(index);
                    }
                    initController(problem, stage);
                } else {
                    alert();
                }
            }
        });
    }

    /**
     * Initializes the game controller and switches to the game view.
     * @param problem The selected problem.
     * @param stage The JavaFX stage.
     */
    private void initController(List<String> problem, Stage stage) {
        game = new FxGame(convertToInteger(problem), stage, this, root);
        root.getChildren().add(game);
        root.getChildren().remove(this);
    }

    /**
     * Reads problem data from a CSV file and populates the 'problems' list.
     */
    private void bufferReader() {
        String csvFile = "src/main/resources/TuringMachine-assets/known_problems.csv";
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ",");
                List<String> problemStr = new LinkedList<>();
                while (tokenizer.hasMoreTokens()) {
                    String column = tokenizer.nextToken();
                    problemStr.add(column);
                }
                problems.add(problemStr);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Problem with the csv file reader");
        }
    }

    /**
     * Converts a list of string representations of numbers to a list of integers.
     * @param problemSplitStr The list of string representations.
     * @return The list of integers.
     * @throws IllegalArgumentException If the conversion fails.
     */
    private List<Integer> convertToInteger(List<String> problemSplitStr) {
        List<Integer> commandToInt = new LinkedList<>();
        for (String s : problemSplitStr) {
            try {
                commandToInt.add(Integer.parseInt(s));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Incorrect problem value (conversion to integer)");
            }
        }
        return commandToInt;
    }

    /**
     * Displays an alert for an incorrect value or missing selection.
     */
    private void alert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Value incorrect");
        alert.setContentText("Please choice a problem");
        alert.show();
    }
}