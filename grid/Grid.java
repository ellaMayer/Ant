package grid;

import ant.Ant;
import cell.SingleCell;

import java.util.List;
import java.util.Map;

/** this Interface presents the grid. It defines the functionality of the grid. */
public interface Grid {

  /**
   * set an ant on a cell at the grid. The position is presented by a x-axis value called col and a
   * y-axis value called row
   *
   * @param object ant which will be set
   * @param col x value
   * @param row y value
   */
  void setAnt(Ant object, int col, int row);

  /**
   * get all ants on grid.
   *
   * @return ants
   */
  Map<String, Ant> getAnts();

  /** delete all ants on grid. */
  void clearAnts();

  /**
   * compute next step. ant leaves the current cell. The cell is colored. The ant goes one step in
   * the right direction afterwards she turns left or right. The color and direction depens on the
   * configuration.
   */
  void performStep();

  /**
   * The ant makes a certain number of steps.
   *
   * @param number number of steps
   */
  void performStep(int number); // compute next n steps

  /**
   * The states of the cell will be reset. The grid has the state like n steps befor.
   *
   * @param number determines to which state the grid is set
   */
  void reset(int number); // reset to state n steps ago

  /**
   * returns width.
   *
   * @return returns width
   */
  int getWidth(); // x-dimension

  /**
   * returns height.
   *
   * @return returns height.
   */
  int getHeight(); // y-dimension

  /**
   * returns all colums at position i.
   *
   * @param i index of column
   * @return List of cells
   */
  List<SingleCell> getColumn(int i); // get column i (starting at 0)

  /**
   * returns all row at position j.
   *
   * @param j index of row
   * @return List of cells
   */
  List<SingleCell> getRow(int j); // get row j (starting at 0)

  /**
   * enlarges or reduces the grid symmetrically. For example if you want to insert 5 colums and 7
   * rows, you will add 3 col at the right and 2 add the left, 4 rows at the bottom and 3 at the
   * top.
   *
   * @param cols new width of the grid
   * @param rows new height of the grid
   */
  void resize(int cols, int rows); // resize grid

  /** resets the states of all states in the grid. All ants will be deleted. */
  void clear(); // clear grid

  /** @return stepCount */
  int getStepCount(); // get number of steps
}
