package jade.nonograms.model;

public class Puzzle {

  private Clues _clues;
  private BoardImpl _board;

  public Puzzle(Clues clues) {
    if (clues == null) {
      throw new IllegalArgumentException();
    }
    _clues = clues;
    _board = new BoardImpl(clues);
  }

  public Clues getClues() {
    return _clues;
  }

  public Board getBoard() {
    return _board;
  }
}
