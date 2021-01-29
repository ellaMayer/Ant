package ant;

import utils.Utils;

/**
 * this class presents the ant, which will move in the grid. A ant has a specific orientation. Which
 * determine the next step.
 */
public class Ant {

  private int xAxis;
  private int yAxis;
  private int index = 0;
  private String orientation;
  private String[] orientations = {"<", "v", ">", "^"};

  /**
   * a ant will be created with a specific orientation.
   *
   * @param orientation line of sight
   */
  public Ant(String orientation) {
    this.orientation = orientation;
    for (int i = 0; i < orientations.length; i++) {
      if (orientation.equals(orientations[i])) {
        this.index = i;
        return;
      }
    }
  }

  /**
   * sets the position of the ant on the grid.
   *
   * @param x xaxis
   * @param y yaxis
   */
  public void setPosition(int x, int y) {
    this.xAxis = x;
    this.yAxis = y;
  }

  /**
   * returns position.
   *
   * @return position of ant
   */
  public String getPosition() {
    return Utils.makeCoordinate(xAxis, yAxis);
  }

  /**
   * returns column on which the ant sits.
   *
   * @return column
   */
  public int getCol() {
    return this.xAxis;
  }

  /**
   * returns row on which the ant sits.
   *
   * @return row
   */
  public int getRow() {
    return this.yAxis;
  }

  /**
   * returns orientation.
   *
   * @return returns orientation od the ant
   */
  public String getOrientation() {
    return orientation;
  }

  /**
   * sets orientation of the ant, which depens on the configuration.
   *
   * @param conf configuration
   */
  public void setOrientation(char conf) {
    int step = (conf == 'L') ? 1 : -1;
    index = (index + orientations.length + step) % orientations.length;
    orientation = orientations[index];
  }
}
