package jade.nonograms.model;

public interface Clues {
  /** Getter method for the number of cells horizontally in each row of the puzzle */
  int getWidth();

  /** Getter method for the number of cells vertically in each column of the puzzle */
  int getHeight();

  /** Getter method to retrieve the clue list for a given row of the puzzle */
  int[] getRowClues(int index);

  /** Getter method to retrieve the clue list for a given column of the puzzle */
  int[] getColClues(int index);

  /** Getter method to retrieve the length of the row clue lists */
  int getRowCluesLength();

  /** Getter method to retrieve the length of the column clue lists */
  int getColCluesLength();
}
