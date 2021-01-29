package utils;

/** This utils contains universal proporties. Here the valid colors are defined. */
public class Utils {

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String COLOR_0 = "\u001B[47m";
  public static final String COLOR_1 = "\u001B[37;40m";
  public static final String COLOR_2 = "\u001B[42m";
  public static final String COLOR_3 = "\u001B[41m";
  public static final String COLOR_4 = "\u001B[37;44m";
  public static final String COLOR_5 = "\u001B[43m";
  public static final String COLOR_6 = "\u001B[46m";
  public static final String COLOR_7 = "\u001B[45m";
  public static final String COLOR_8 = "\u001B[36;41m";
  public static final String COLOR_9 = "\u001B[31;44m";
  public static final String COLOR_10 = "\u001B[34;43m";
  public static final String COLOR_11 = "\u001B[32;45m";

  public static final String help =
      "Langton's ant is a two-dimensional universal Turing machine. \n"
          + "The squares on the grid are colored in a given sequenz. "
          + "In the beginning every square is white. \n"
          + "After the ant was on a fiel, it will be colored. \n"
          + "The configuration determines the color and the direction in "
          + "which the ant turns. \n\n"
          + "Commands: \n\n"
          + "new x y x: new grid with x colums, y rows and c as configuration. "
          + "Configuration consists "
          + "of R's and L's. Maximum length is 12. \n"
          + "ant i j: puts an new ant at column i ant row j. \n"
          + "unant: removes ant from grid. \n"
          + "step: ant walks one step \n"
          + "step n: ant walks n steps forward \n"
          + "step -n: reset to state before 5 steps \n"
          + "print: prints the grid. \n"
          + "clear: Resets the whole grid \n"
          + "resize: increases or decreases the grid symmetrically \n"
          + "quit: terminates the program.";

  /**
   * Creates a String of the coordinates x and y the to variables will bes separeted by a ,.
   *
   * @param x coordinate on x axis
   * @param y coordinate on y axis
   * @return String representation
   */
  public static String makeCoordinate(int x, int y) {
    return x + "," + y;
  }

  /**
   * gets the x value of the String position.
   *
   * @param position position will be presented in this form (x,y)
   * @return x value of the String
   */
  public static int getRow(String position) {
    return Integer.parseInt(position.split(",")[1]);
  }

  /**
   * gets the y value of the String position.
   *
   * @param position position will be presented in this form (x,y)
   * @return y value of the String
   */
  public static int getCol(String position) {
    return Integer.parseInt(position.split(",")[0]);
  }
}
