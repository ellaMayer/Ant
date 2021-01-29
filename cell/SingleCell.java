package cell;

import ant.Ant;
import utils.Utils;

import java.util.List;

/**
 * this class implements the interface cell. Every cell has a state and possibly a ant. You can
 * update and reduce the state of the cell.
 */
public class SingleCell implements Cell {

  private int index;
  private Ant ant;

  private List<String> colours;

  public SingleCell(List<String> colours) {
    this.index = 0;
    this.colours = colours;
  }

  /** updates the state of a cell. */
  public void updateState() {
    this.index = (this.index + colours.size() + 1) % colours.size();
    this.ant = null;
  }

  /**
   * reduces the state of the cell and set the ant on the cell before.
   *
   * @param orientation direction of the ant
   * @param position position where the ant was before
   */
  public void reduceState(String orientation, String position) {
    this.index = (this.index + colours.size() - 1) % colours.size();
    this.ant = new Ant(orientation);
    ant.setPosition(Utils.getCol(position), Utils.getRow(position));
  }

  /**
   * set ant on cell.
   *
   * @param object ant
   * @return true when ant is set
   */
  public boolean setAnt(Ant object) {
    this.ant = object;
    return true;
  }

  @Override
  public String getState() {
    if (this.ant != null) {
      return colours.get(index) + "_" + ant.getOrientation();
    } else {
      return colours.get(index) + "_" + index;
    }
  }

  /**
   * set the orientation of the ant. The orientation depends on the configuration
   *
   * @param conf configuration
   */
  public void setOrientation(String conf) {
    for (int i = 0; i < conf.length(); i++) {
      if (index == i) {
        ant.setOrientation(conf.charAt(i));
        return;
      }
    }
  }

  /**
   * returns cell
   *
   * @return ant on the cell
   */
  public Ant getAnt() {
    return ant;
  }
}
