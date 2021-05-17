package jade.nonograms.model;

public interface Model extends Board, Clues {
  /** Getter method for the total number of puzzles in the puzzle list */
  int getPuzzleCount();

  /** Getter method for the index of the active puzzle in the puzzle list */
  int getPuzzleIndex();

  /** Setter method for the the index of the active puzzle in the puzzle list */
  void setPuzzleIndex(int index);

  /**
   * Adds an observer to the active observer list. An event is fired and all active observers are
   * notified every time a Model field value changes
   */
  void addObserver(ModelObserver observer);

  /** Removes an observer from the active observer list */
  void removeObserver(ModelObserver observer);

  /** Returns true only if the active puzzle is solved */
  boolean isSolved();
}
