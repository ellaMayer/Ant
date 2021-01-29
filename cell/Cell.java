package cell;

/** this interface presents the cell of a grid. */
public interface Cell {

  /**
   * get representation of state, if theire is a ant on the cell it returns the color and the
   * direction of the ant.
   *
   * @return the color of the cell and the direction of the ant
   */
  String getState();
}
