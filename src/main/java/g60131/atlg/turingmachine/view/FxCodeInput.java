package g60131.atlg.turingmachine.view;

import g60131.atlg.turingmachine.model.TuringMachine;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FxCodeInput extends HBox {
    private final VBox triangle = new VBox();
    private final VBox square = new VBox();
    private final VBox circle = new VBox();
    private final Label triangleOutput = new Label("?");
    private final Label squareOutput = new Label("?");
    private final Label circleOutput = new Label("?");
    private final int[] proposedNb = new  int[3];
    private Button setNumber;
    private final TuringMachine turingMachine;
    public FxCodeInput(TuringMachine turingMachine) {
        this.turingMachine = turingMachine;
        this.setSpacing(10);
        this.setAlignment(Pos.BOTTOM_LEFT);
        triangle.setSpacing(15);
        square.setSpacing(15);
        circle.setSpacing(15);
        this.getChildren().addAll(triangle, square, circle);
        this.setAlignment(Pos.BOTTOM_CENTER);
        initTriangle();
        initSquare();
        initCircle();
        initSetProposedNb();
    }

    /**
     * Disables input elements after setting a number.
     */
    public void setDisable() {
        for (Node element : triangle.getChildren()) {
            element.setDisable(true);
        }
        for (Node element : square.getChildren()) {
            element.setDisable(true);
        }
        for (Node element : circle.getChildren()) {
            element.setDisable(true);
        }
        setNumber.setDisable(true);
        triangleOutput.setDisable(false);
        squareOutput.setDisable(false);
        circleOutput.setDisable(false);
    }

    /**
     * Enables input elements for the user to set a number.
     */
    public void setEnable() {
        for (Node element : triangle.getChildren()) {
            element.setDisable(false);
        }
        for (Node element : square.getChildren()) {
            element.setDisable(false);
        }
        for (Node element : circle.getChildren()) {
            element.setDisable(false);
        }
        setNumber.setDisable(false);
    }

    /**
     * Clears the displayed numbers and proposedNb array.
     */
    public void clearNumber() {
        triangleOutput.setText("?");
        squareOutput.setText("?");
        circleOutput.setText("?");
        Arrays.fill(proposedNb, 0);
    }

    /**
     * Initializes the "Set Number" button and its functionality.
     */
    private void initSetProposedNb() {
        setNumber = new Button("Set Number");
        setNumber.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    for (int digit : proposedNb) {
                        if(digit == 0) {
                            throw new IllegalArgumentException("Please choose a code");
                        }
                    }
                    int[] tmp = {proposedNb[0], proposedNb[1], proposedNb[2]};
                    turingMachine.setRoundProposedNb(tmp);
                } catch (Exception e) {
                    alert(e.getMessage());
                }
            }
        });
        this.getChildren().add(setNumber);
    }

    /**
     * Sets the displayed code based on the proposed numbers.
     * @param proposedNb The array of proposed numbers.
     */
    public void setCode(int[] proposedNb) {
        triangleOutput.setText("" + proposedNb[0]);
        squareOutput.setText("" + proposedNb[1]);
        circleOutput.setText("" + proposedNb[2]);
    }

    /**
     * Initializes the input elements for the triangle shape.
     */
    private void initTriangle() {
        Polygon triangleShape = new Polygon();
        double size = 50.0;
        double height = size * Math.sqrt(3) / 2;
        triangleShape.getPoints().addAll(
                150.0, 150.0 - height / 2,
                150.0 - size / 2, 150.0 + height / 2,
                150.0 + size / 2, 150.0 + height / 2
        );

        triangleShape.setFill(Color.DEEPSKYBLUE);
        triangle.setAlignment(Pos.CENTER);
        triangleOutput.setTextAlignment(TextAlignment.CENTER);
        triangleOutput.setFont(new Font(25));

        triangle.getChildren().addAll(triangleShape, triangleOutput);
        triangle.getChildren().addAll(initButtons(0, triangleOutput));
    }

    /**
     * Initializes the input elements for the square shape.
     */
    private void initSquare() {
        Rectangle squareShape = new Rectangle(50, 50, Color.rgb(239, 213, 46));
        squareOutput.setTextAlignment(TextAlignment.CENTER);
        squareOutput.setFont(new Font(25));
        square.setAlignment(Pos.CENTER);

        square.getChildren().addAll(squareShape, squareOutput);
        square.getChildren().addAll(initButtons(1, squareOutput));
    }

    /**
     * Initializes the input elements for the circle shape.
     */
    private void initCircle() {
        Circle circleShape = new Circle(25);
        circleShape.setFill(Color.BLUEVIOLET);
        circleOutput.setTextAlignment(TextAlignment.CENTER);
        circleOutput.setFont(new Font(25));
        circle.setAlignment(Pos.CENTER);

        circle.getChildren().addAll(circleShape, circleOutput);
        circle.getChildren().addAll(initButtons(2, circleOutput));
    }

     /**
     * Init buttons 1 to 5 for digit code
     * @param tableIndex the index of the digit code
     * @param output The label to add the number who was selected
     * @return return a list of buttons
     */
    private List<Button> initButtons(int tableIndex, Label output) {
        List<Button> buttons = new LinkedList<>();
        for (int i = 1; i <= 5; i++) {
            Button btn = new Button("" + i);
            int finalI = i;
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    proposedNb[tableIndex] = finalI;
                    output.setText("" + finalI);
                }
            });
            buttons.add(btn);
        }
        return buttons;
    }

    /**
     * Displays an alert with the given error message.
     * @param message The error message to be displayed.
     */
    private void alert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Incorrect");
        alert.setContentText(message);
        alert.show();
    }
}
