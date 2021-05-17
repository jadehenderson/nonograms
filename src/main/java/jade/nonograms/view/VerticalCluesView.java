package jade.nonograms.view;

import jade.nonograms.controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class VerticalCluesView implements FXComponent {

  private ControllerImpl _controller;

  public VerticalCluesView(ControllerImpl controller) {
    _controller = controller;
  }

  @Override
  public Parent render() {
    GridPane vertical = new GridPane();
    vertical.setHgap(4.0);
    vertical.setVgap(4.0);

    for (int i = 0; i < _controller.getClues().getHeight(); i++) {
      for (int j = 0; j < _controller.getClues().getRowCluesLength(); j++) {

        Circle c = new Circle(25, Color.WHITESMOKE);
        c.setStroke(Color.THISTLE);
        int clues = _controller.getClues().getRowClues(i)[j];
        Label cluelist = new Label();
        StackPane stack = new StackPane();
        if (clues == 0) {
          c.setStroke(Color.WHITESMOKE);
          cluelist.setText("");
        } else {
          cluelist.setText("" + clues);
        }
        stack.getChildren().add(c);
        stack.getChildren().add(cluelist);
        vertical.add(stack, j, i, 1, 1);
      }
    }
    vertical.setAlignment(Pos.TOP_RIGHT);
    vertical.setPadding(new Insets(0, 0, 0, 440));
    return vertical;
  }
}
