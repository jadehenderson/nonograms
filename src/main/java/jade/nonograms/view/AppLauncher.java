package jade.nonograms.view;

import jade.nonograms.PuzzleLibrary;
import jade.nonograms.controller.ControllerImpl;
import jade.nonograms.model.Clues;
import jade.nonograms.model.Model;
import jade.nonograms.model.ModelImpl;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppLauncher extends Application {
  @Override
  public void start(Stage stage) {

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int width = gd.getDisplayMode().getWidth();
    int height = gd.getDisplayMode().getHeight();
    stage.setTitle("Nonograms!");
    List<Clues> clues = PuzzleLibrary.create();
    ModelImpl model = new ModelImpl(clues);
    ControllerImpl controller = new ControllerImpl(model);
    PuzzleView view = new PuzzleView(controller);
    Scene scene = new Scene(view.render(), width, height);
    stage.setFullScreen(true);
    stage.setScene(scene);
    // lambda
    model.addObserver(
        (Model m) -> {
          scene.setRoot(view.render());
        });
    stage.show();
  }
}
