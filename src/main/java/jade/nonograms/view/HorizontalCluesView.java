package jade.nonograms.view;

import jade.nonograms.controller.ControllerImpl;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class HorizontalCluesView implements FXComponent {
  private ControllerImpl _controller;

  public HorizontalCluesView(ControllerImpl controller) {
    _controller = controller;
  }

  @Override
  public Parent render() {
    GridPane horizontal = new GridPane();
    horizontal.setHgap(4.0);
    horizontal.setVgap(4.0);
    horizontal.setPadding(new Insets(10, 10, 10, 10));

    for (int i = 0; i < _controller.getClues().getColCluesLength(); i++) {
      for (int j = 0; j < _controller.getClues().getWidth(); j++) {
        Circle c = new Circle(25, Color.WHITESMOKE);
        c.setStroke(Color.THISTLE);
        int clues = _controller.getClues().getColClues(j)[i];
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

        horizontal.add(stack, j, i, 1, 1);
      }
    }
    horizontal.setAlignment(Pos.BOTTOM_CENTER);
    return horizontal;
  }
}
