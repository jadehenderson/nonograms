package jade.nonograms.model;

public class CluesImpl implements Clues {

  private final int _height;
  private final int _width;
  private final int[][] _row;
  private final int[][] _col;

  public CluesImpl(int[][] rowClues, int[][] colClues) {
    if (rowClues == null || colClues == null) {
      throw new IllegalArgumentException();
    }
    _row = rowClues;
    _col = colClues;
    _height = _row.length;
    _width = _col.length;
  }

  @Override
  public int getWidth() {
    return _width;
  }

  @Override
  public int getHeight() {
    return _height;
  }

  @Override
  public int[] getRowClues(int index) {
    return _row[index];
  }

  @Override
  public int[] getColClues(int index) {
    return _col[index];
  }

  @Override
  public int getRowCluesLength() {
    return _row[0].length;
  }

  @Override
  public int getColCluesLength() {
    return _col[0].length;
  }
}
