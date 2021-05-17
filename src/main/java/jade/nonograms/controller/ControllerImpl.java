package jade.nonograms.controller;

import jade.nonograms.model.Clues;
import jade.nonograms.model.Model;
import jade.nonograms.model.ModelImpl;
import java.util.Random;

public class ControllerImpl implements Controller {

  private Model _model;

  public ControllerImpl(Model model) {
    if (model == null) {
      throw new IllegalArgumentException();
    }
    _model = model;
  }

  @Override
  public Clues getClues() {
    return ((ModelImpl) _model).getCurrent().getClues();
  }

  @Override
  public boolean isSolved() {
    return _model.isSolved();
  }

  @Override
  public boolean isShaded(int row, int col) {
    return _model.isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return _model.isEliminated(row, col);
  }

  @Override
  public void toggleShaded(int row, int col) {
    _model.toggleCellShaded(row, col);
  }

  @Override
  public void toggleEliminated(int row, int col) {
    _model.toggleCellEliminated(row, col);
  }

  @Override
  public void nextPuzzle() {
    if ((_model.getPuzzleIndex() + 1) != _model.getPuzzleCount()) {
      _model.setPuzzleIndex(_model.getPuzzleIndex() + 1);
    }
  }

  @Override
  public void prevPuzzle() {
    if ((_model.getPuzzleIndex()) != 0) {
      _model.setPuzzleIndex(_model.getPuzzleIndex() - 1);
    }
  }

  @Override
  public void randPuzzle() {
    int currentindex = _model.getPuzzleIndex();
    Random random = new Random();
    int rotate = 1 + random.nextInt(getPuzzleCount() - 1);
    _model.setPuzzleIndex((currentindex + rotate) % getPuzzleCount());
  }

  @Override
  public void clearBoard() {
    _model.clear();
  }

  @Override
  public int getPuzzleIndex() {
    return _model.getPuzzleIndex();
  }

  @Override
  public int getPuzzleCount() {
    return _model.getPuzzleCount();
  }
}
