package jade.nonograms.view;

import jade.nonograms.controller.ControllerImpl;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameBoardView implements FXComponent {

  private ControllerImpl _controller;

  public GameBoardView(ControllerImpl controller) {
    _controller = controller;
  }

  @Override
  public Parent render() {
    GridPane gameboard = new GridPane();
    gameboard.setPrefSize(650, 650);
    gameboard.setHgap(4.0);
    gameboard.setVgap(4.0);

    for (int i = 0; i < _controller.getClues().getHeight(); i++) {
      for (int j = 0; j < _controller.getClues().getWidth(); j++) {
        Circle c = new Circle(25, Color.WHITESMOKE);
        c.setStroke(Color.LIGHTSLATEGRAY);
        int row = i;
        int col = j;

        StackPane stack = new StackPane();
        stack.getChildren().add(c);

        if (_controller.isShaded(row, col)) {
          c.setFill(Color.POWDERBLUE);
        } else if (_controller.isEliminated(row, col)) {
          Label label = new Label();
          label.setText("X");
          stack.getChildren().add(label);
          GridPane.setHalignment(label, HPos.CENTER);
        } else {
          c.setFill(Color.WHITESMOKE);
        }

        c.setOnMouseClicked(
            MouseEvent -> {
              if (MouseEvent.getButton() == MouseButton.PRIMARY) {
                _controller.toggleShaded(row, col);
              } else if (MouseEvent.getButton() == MouseButton.SECONDARY) {
                _controller.toggleEliminated(row, col);
              }
            });
        gameboard.add(stack, j, i, 1, 1);
      }
    }

    if (_controller.isSolved()) {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("congratulations!");
      alert.setHeaderText("you did it!");
      alert.setContentText("you solved the puzzle!");
      alert.show();
    }
    gameboard.setAlignment(Pos.TOP_CENTER);
    gameboard.setPadding(new Insets(4, 4, 4, 4));
    return gameboard;
  }
}
