package grid;

import ant.Ant;
import cell.SingleCell;
import utils.Utils;

import java.util.*;

/** this class implements the interface grid and specifies the rules for a single ant. */
public class SingleAntGrid implements Grid {

  private int width;
  private int height;
  private LinkedHashMap<String, SingleCell> grid = new LinkedHashMap<>();
  private final String configuration;
  private ArrayDeque<String> ants = new ArrayDeque<>();
  private Ant ant;
  private List<String> colours = new ArrayList<>();
  private int stepCount;
  private int xOffset;
  private int yOffset;

  /**
   * creates a new grid with a given width and a given height. every cell in the grid is white.
   *
   * @param width width
   * @param height height
   * @param configuration configuration contains R's and L's
   */
  public SingleAntGrid(int width, int height, String configuration) {
    this.width = width;
    this.height = height;
    this.configuration = configuration;

    String[] allColours = {
      Utils.COLOR_0,
      Utils.COLOR_1,
      Utils.COLOR_2,
      Utils.COLOR_3,
      Utils.COLOR_4,
      Utils.COLOR_5,
      Utils.COLOR_6,
      Utils.COLOR_7,
      Utils.COLOR_8,
      Utils.COLOR_9,
      Utils.COLOR_10,
      Utils.COLOR_11
    };

    colours.addAll(Arrays.asList(allColours).subList(0, configuration.length()));
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        grid.put(Utils.makeCoordinate(x, y), new SingleCell(colours));
      }
    }
  }

  @Override
  public void setAnt(Ant object, int col, int row) {
    if (ant != null) {
      int oldRow = ant.getRow();
      int oldCol = ant.getCol();
      SingleCell cell = grid.get(Utils.makeCoordinate(oldCol, oldRow));
      cell.updateState();
    }
    col -= xOffset;
    row -= yOffset;
    if (grid.get(Utils.makeCoordinate(col, row)).setAnt(object)) {
      object.setPosition(col, row);
      ant = object;
      ants.push(ant.getPosition() + ":" + ant.getOrientation());
    }
  }

  @Override
  public Map<String, Ant> getAnts() {
    Map<String, Ant> ants = new HashMap<>();
    Ant ant;
    for (int x = 0 - xOffset; x < width - xOffset; x++) {
      for (int y = 0 - yOffset; y < height - yOffset; y++) {
        if ((ant = grid.get(Utils.makeCoordinate(x, y)).getAnt()) != null) {
          ants.put(Utils.makeCoordinate(x, y), ant);
        }
      }
    }
    return ants;
  }

  @Override
  public void performStep() {
    if (ant == null) {
      return;
    }
    String orientation = ant.getOrientation();
    int row = ant.getRow();
    int col = ant.getCol();
    SingleCell cell = grid.get(Utils.makeCoordinate(col, row));
    cell.updateState();

    switch (orientation) {
      case "<":
        col = (col + yOffset + width - 1) % this.width - yOffset;
        break;
      case ">":
        col = (col + xOffset + width + 1) % this.width - xOffset;
        break;
      case "v":
        row = (row + yOffset + height + 1) % this.height - yOffset;
        break;
      case "^":
        row = (row + yOffset + height - 1) % this.height - yOffset;
        break;
      default:
        break;
    }

    ant.setPosition(col, row);
    cell = grid.get(Utils.makeCoordinate(col, row));
    cell.setAnt(ant);
    cell.setOrientation(configuration);
    ants.push(ant.getPosition() + ":" + ant.getOrientation());
    stepCount++;
  }

  @Override
  public void performStep(int number) {
    if (number < 0) {
      reset(number);
    } else {
      while (number > 0) {
        this.performStep();
        number--;
      }
    }
  }

  @Override
  public void reset(int number) {
    if (number + stepCount < 0) {
      number = -stepCount;
    }
    while (number < 0) {
      String[] tokens = ants.getFirst().trim().split(":");
      grid.get(tokens[0]).setAnt(null);
      ants.pop();
      String[] newTokens = ants.getFirst().trim().split(":");
      grid.get(newTokens[0]).reduceState(newTokens[1], newTokens[0]);
      ant = grid.get(newTokens[0]).getAnt();
      stepCount--;
      number++;
    }
  }

  @Override
  public List<SingleCell> getColumn(int i) {
    i = i - xOffset;
    List<SingleCell> column = new ArrayList<>();
    for (int y = 0 - yOffset; y < height - yOffset; y++) {
      column.add(grid.get(Utils.makeCoordinate(i, y)));
    }
    return column;
  }

  @Override
  public List<SingleCell> getRow(int j) {
    j = j - yOffset;
    List<SingleCell> row = new ArrayList<>();
    for (int x = 0 - xOffset; x < width - xOffset; x++) {
      row.add(grid.get(Utils.makeCoordinate(x, j)));
    }
    return row;
  }

  @Override
  public void resize(int cols, int rows) {
    int offsetLeft = (cols - width) / 2;
    int offsetRight = cols - width - offsetLeft;
    int offsetTop = (rows - height) / 2;
    int offsetBottom = rows - height - offsetTop;

    for (int x = 0 - xOffset - offsetLeft; x < 0 - xOffset; x++) {
      for (int y = 0 - yOffset - offsetTop; y < height - yOffset + offsetBottom; y++) {
        grid.put(Utils.makeCoordinate(x, y), new SingleCell(colours));
      }
    }

    for (int x = width - xOffset; x < width - xOffset + offsetRight; x++) {
      for (int y = 0 - yOffset - offsetTop; y < height - yOffset + offsetBottom; y++) {
        grid.put(Utils.makeCoordinate(x, y), new SingleCell(colours));
      }
    }

    for (int y = 0 - yOffset - offsetTop; y < 0 - yOffset; y++) {
      for (int x = 0 - xOffset; x < width - xOffset; x++) {
        grid.put(Utils.makeCoordinate(x, y), new SingleCell(colours));
      }
    }

    for (int y = height - yOffset; y < height - yOffset + offsetBottom; y++) {
      for (int x = 0 - xOffset; x < width - xOffset; x++) {
        grid.put(Utils.makeCoordinate(x, y), new SingleCell(colours));
      }
    }

    yOffset += offsetTop;
    xOffset += offsetLeft;
    width = cols;
    height = rows;
  }

  @Override
  public void clearAnts() {
    grid.get(ant.getPosition()).setAnt(null);
    ant = null;
    this.ants = new ArrayDeque<>();
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void clear() {
    new SingleAntGrid(this.width, this.height, configuration);
  }

  @Override
  public int getStepCount() {
    return this.stepCount;
  }
}
