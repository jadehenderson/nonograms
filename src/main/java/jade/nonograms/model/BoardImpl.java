package jade.nonograms.model;

public class BoardImpl implements Board {

  private Clues _clue;
  private int[][] board;
  private int shaded = 1;
  private int eliminated = 2;
  private int space = 0;

  public BoardImpl(Clues clue) {
    if (clue == null) {
      throw new IllegalArgumentException();
    }
    _clue = clue;
    board = new int[clue.getHeight()][clue.getWidth()];
  }

  @Override
  public boolean isShaded(int row, int col) {
    if (row < 0 || row >= _clue.getHeight() || col < 0 || col >= _clue.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    return board[row][col] == shaded;
  }

  @Override
  public boolean isEliminated(int row, int col) {
    if (row < 0 || row >= _clue.getHeight() || col < 0 || col >= _clue.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    return board[row][col] == eliminated;
  }

  @Override
  public boolean isSpace(int row, int col) {
    if (row < 0 || row >= _clue.getHeight() || col < 0 || col >= _clue.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    return board[row][col] == space;
  }

  @Override
  public void toggleCellShaded(int row, int col) {
    if (row < 0 || row >= _clue.getHeight() || col < 0 || col >= _clue.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (this.isShaded(row, col)) {
      board[row][col] = space;
    } else {
      board[row][col] = shaded;
    }
  }

  @Override
  public void toggleCellEliminated(int row, int col) {
    if (row < 0 || row >= _clue.getHeight() || col < 0 || col >= _clue.getWidth()) {
      throw new IndexOutOfBoundsException();
    }
    if (this.isEliminated(row, col)) {
      board[row][col] = space;
    } else {
      board[row][col] = eliminated;
    }
  }

  @Override
  public void clear() {
    board = new int[board.length][board[0].length];
  }
}
