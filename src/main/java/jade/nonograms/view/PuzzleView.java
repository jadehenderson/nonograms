package jade.nonograms.view;

import jade.nonograms.controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PuzzleView implements FXComponent {

  private ControllerImpl _controller;

  public PuzzleView(ControllerImpl controller) {
    _controller = controller;
  }

  @Override
  public Parent render() {

    GameBoardView board = new GameBoardView(_controller);
    VerticalCluesView vertical = new VerticalCluesView(_controller);
    HorizontalCluesView horizontal = new HorizontalCluesView(_controller);
    ControlView buttons = new ControlView(_controller);

    BorderPane game = new BorderPane();
    game.setCenter(board.render());
    game.setLeft(vertical.render());
    game.setTop(horizontal.render());
    game.setBottom(buttons.render());

    GridPane rightPadding = new GridPane();
    for (int i = 0; i < _controller.getClues().getHeight(); i++) {
      for (int j = 0; j < _controller.getClues().getRowCluesLength(); j++) {

        Circle c = new Circle(25, Color.WHITESMOKE);
        c.setStroke(Color.WHITESMOKE);
        Label cluelist = new Label();
        StackPane stack = new StackPane();
        stack.getChildren().add(c);
        stack.getChildren().add(cluelist);
        rightPadding.add(stack, j, i, 1, 1);
      }
    }

    rightPadding.setHgap(4.0);
    rightPadding.setVgap(4.0);
    rightPadding.setAlignment(Pos.TOP_LEFT);
    rightPadding.setPadding(new Insets(0, 440, 0, 0));

    game.setRight(rightPadding);

    return game;
  }
}
