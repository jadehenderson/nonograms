package jade.nonograms.model;

public interface Board {
  /** Returns true if the cell at the given location is shaded */
  boolean isShaded(int row, int col);

  /** Returns true if the cell at the given location is eliminated with an "x" */
  boolean isEliminated(int row, int col);

  /**
   * Returns true if the cell at the given location is blank (i.e. it is neither shaded nor
   * eliminated)
   */
  boolean isSpace(int row, int col);

  /** Toggles whether the cell at the given location is shaded */
  void toggleCellShaded(int row, int col);

  /** Toggles whether the cell at the given location is eliminated with an "x" */
  void toggleCellEliminated(int row, int col);

  /** Clears the board by marking all the cells blank */
  void clear();
}
