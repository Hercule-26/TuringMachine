module g60131.atlg.turingmachine {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens g60131.atlg.turingmachine to javafx.fxml;
    exports g60131.atlg.turingmachine;
}