package jade.nonograms.controller;

import jade.nonograms.model.Clues;

public interface Controller {
  /** Gets the clues for the active puzzle board */
  Clues getClues();

  /** Returns true only if the active puzzle board satisfies the puzzle's clues */
  boolean isSolved();

  /** Returns true only if the specified cell in the active puzzle board is "shaded" */
  boolean isShaded(int row, int col);

  /** Returns true only if the specified cell in the active puzzle board is "eliminated" */
  boolean isEliminated(int row, int col);

  /** Toggles whether a specific cell is "shaded" in the active puzzle board */
  void toggleShaded(int row, int col);

  /** Toggles whether a specific cell is "eliminated" in the active puzzle board */
  void toggleEliminated(int row, int col);

  /** Changes the currently active puzzle to the next puzzle in the library */
  void nextPuzzle();

  /** Changes the currently active puzzle to the previous puzzle in the library */
  void prevPuzzle();

  /** Changes the currently active puzzle to a random puzzle in the library */
  void randPuzzle();

  /** Clears the currently active puzzle board */
  void clearBoard();

  /** Getter method for the currently active puzzle's zero-based index within the puzzle library */
  int getPuzzleIndex();

  /** Getter method for the total number of puzzles in the puzzle library */
  int getPuzzleCount();
}
