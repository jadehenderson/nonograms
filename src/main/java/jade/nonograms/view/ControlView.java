package jade.nonograms.view;

import jade.nonograms.controller.Controller;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;

public class ControlView implements FXComponent {

  private Controller _controller;

  public ControlView(Controller controller) {
    _controller = controller;
  }

  @Override
  public Parent render() {
    GridPane buttons = new GridPane();

    Button next = new Button("Next Puzzle!");
    buttons.setHgap(5);
    next.setOnMouseClicked(
        MouseEvent -> {
          if (MouseEvent.getButton() == MouseButton.PRIMARY) {
            _controller.nextPuzzle();
          }
        });

    Button prev = new Button("Previous Puzzle!");
    prev.setOnMouseClicked(
        MouseEvent -> {
          if (MouseEvent.getButton() == MouseButton.PRIMARY) {
            _controller.prevPuzzle();
          }
        });

    Button rand = new Button("Random Puzzle!");
    rand.setOnMouseClicked(
        MouseEvent -> {
          if (MouseEvent.getButton() == MouseButton.PRIMARY) {
            _controller.randPuzzle();
          }
        });

    Button clear = new Button("Reset Puzzle!");
    clear.setOnMouseClicked(
        MouseEvent -> {
          if (MouseEvent.getButton() == MouseButton.PRIMARY) {
            _controller.clearBoard();
          }
        });

    Label label = new Label();
    label.setText(
        "puzzle " + (_controller.getPuzzleIndex() + 1) + " of " + _controller.getPuzzleCount());
    label.setAlignment(Pos.CENTER);
    buttons.add(label, 4, 0, 1, 2);
    prev.setStyle("-fx-background-color: #D8BFD8; ");
    next.setStyle("-fx-background-color: #D8BFD8; ");
    rand.setStyle("-fx-background-color: #D8BFD8; ");
    clear.setStyle("-fx-background-color: #D8BFD8; ");
    buttons.add(prev, 0, 1, 1, 2);
    buttons.add(next, 1, 1, 1, 2);
    buttons.add(rand, 2, 1, 1, 2);
    buttons.add(clear, 3, 1, 1, 2);
    buttons.setAlignment(Pos.TOP_CENTER);
    return buttons;
  }
}
