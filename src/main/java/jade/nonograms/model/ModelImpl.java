package jade.nonograms.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

  private List<Puzzle> availablepuzzles;
  private List<ModelObserver> observers;
  private int puzzleindex;

  public ModelImpl(List<Clues> clues) {
    availablepuzzles = new ArrayList<>();
    for (Clues list : clues) {
      Puzzle createpuzzle = new Puzzle(list);
      availablepuzzles.add(createpuzzle);
    }
    observers = new ArrayList<>();
    puzzleindex = 0;
  }

  @Override
  public int getPuzzleCount() {
    return availablepuzzles.size();
  }

  @Override
  public int getPuzzleIndex() {
    return puzzleindex;
  }

  @Override
  public void setPuzzleIndex(int index) {
    if (index < 0 || index > getPuzzleCount()) {
      throw new IndexOutOfBoundsException();
    }
    puzzleindex = index;
    notification();
  }

  @Override
  public void addObserver(ModelObserver observer) {
    observers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    observers.remove(observer);
  }

  @Override
  public boolean isSolved() {
    for (int i = 0; i < getCurrent().getClues().getHeight(); i++) {
      int shadedrows = 0;
      for (int j = 0; j < getCurrent().getClues().getWidth(); j++) {
        if (getCurrent().getBoard().isShaded(i, j)) {
          shadedrows++;
        }
      }
      int[] rowcount = getCurrent().getClues().getRowClues(i);
      int cluecount = 0;
      for (int k = 0; k < rowcount.length; k++) {
        cluecount += rowcount[k];
      }
      if (shadedrows != cluecount) {
        return false;
      }
    }
    for (int i = 0; i < getCurrent().getClues().getWidth(); i++) {
      int shadedcols = 0;
      for (int j = 0; j < getCurrent().getClues().getHeight(); j++) {
        if (getCurrent().getBoard().isShaded(j, i)) {
          shadedcols++;
        }
      }
      int[] colcount = getCurrent().getClues().getColClues(i);
      int cluecount = 0;
      for (int k = 0; k < colcount.length; k++) {
        cluecount += colcount[k];
      }
      if (shadedcols != cluecount) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean isShaded(int row, int col) {
    return getCurrent().getBoard().isShaded(row, col);
  }

  @Override
  public boolean isEliminated(int row, int col) {
    return getCurrent().getBoard().isEliminated(row, col);
  }

  @Override
  public boolean isSpace(int row, int col) {
    return getCurrent().getBoard().isSpace(row, col);
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    getCurrent().getBoard().toggleCellShaded(row, col);
    notification();
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    getCurrent().getBoard().toggleCellEliminated(row, col);
    notification();
  }

  @Override
  public void clear() {
    getCurrent().getBoard().clear();
    notification();
  }

  @Override
  public int getWidth() {
    return getCurrent().getClues().getWidth();
  }

  @Override
  public int getHeight() {
    return getCurrent().getClues().getHeight();
  }

  @Override
  public int[] getRowClues(int index) {
    return getCurrent().getClues().getRowClues(index);
  }

  @Override
  public int[] getColClues(int index) {
    return getCurrent().getClues().getColClues(index);
  }

  @Override
  public int getRowCluesLength() {
    return getCurrent().getClues().getRowCluesLength();
  }

  @Override
  public int getColCluesLength() {
    return getCurrent().getClues().getColCluesLength();
  }

  public void notification() {
    for (ModelObserver o : observers) {
      o.update(this);
    }
  }

  public Puzzle getCurrent() {
    return this.availablepuzzles.get(puzzleindex);
  }
}
