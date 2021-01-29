package view;

import ant.Ant;
import grid.Grid;
import grid.SingleAntGrid;
import utils.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import cell.SingleCell;

/**
 * This project is a simulation of Langton's ant. It's a two-dimensional universal Turing machine.
 * this class implements the user interface. The input will be checked and processed. With the
 * command "help" their will appear a help text which explains the functionality. In this class the
 * representation of the grid will be build.
 */
public final class Shell {

  private static Grid grid;
  private static boolean quit = false;
  private static int width;
  private static int height;

  /**
   * This is the main-method, which will coordinate the inputs of the user.
   *
   * @param args is not in use
   * @throws IOException for error input
   */
  public static void main(String[] args) throws IOException {
    BufferedReader stdin =
        new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

    execute(stdin);
  }

  private static void execute(BufferedReader stdin) throws IOException {
    while (!quit) {
      System.out.print("ant> ");
      try {
        String input = stdin.readLine();
        if (input == null) {
          break;
        }
        String[] tokens = input.trim().split("\\s+");
        String command = tokens[0];
        switch (command) {
          case "new":
            if (checkConfiguration(tokens[3])) {
              width = Integer.parseInt(tokens[1]);
              height = Integer.parseInt(tokens[2]);
              if (width < 0 || height < 0) {
                System.out.println("Error! width and height have to be > 0");
                continue;
              }
              grid = new SingleAntGrid(width, height, tokens[3]);
            }
            break;
          case "ant":
            Ant ant = new Ant("<");
            int x = Integer.parseInt(tokens[1]);
            int y = Integer.parseInt(tokens[2]);
            if (x < 0 || x >= width || y < 0 || y >= height) {
              System.out.println(
                  "Error! width has to be between [0,"
                      + width
                      + "[, height has to be between [0,"
                      + height
                      + "[");
              continue;
            }
            grid.setAnt(ant, x, y);
            break;
          case "step":
            if (tokens.length == 1) {
              grid.performStep();
            } else if (Integer.parseInt(tokens[1]) < 0) {
              grid.reset(Integer.parseInt(tokens[1]));
            } else {
              grid.performStep(Integer.parseInt(tokens[1]));
            }
            System.out.println(grid.getStepCount());
            break;
          case "clear":
            grid.clear();
            break;
          case "print":
            print();
            break;
          case "quit":
            quit = true;
            break;
          case "resize":
            int newWidth = Integer.parseInt(tokens[1]);
            int newHeight = Integer.parseInt(tokens[2]);
            if (newWidth < 0 || newHeight < 0) {
              System.out.println("Error! The height and width must be at least 0!");
              continue;
            }
            width = newWidth;
            height = newHeight;
            grid.resize(width, height);
            break;
          case "unant":
            grid.clearAnts();
            break;
          case "help":
            System.out.println(Utils.help);
            break;
          default:
            {
              System.out.println(
                  "Error! java.lang.IllegalStateException: No valid command specified!");
            }
        }
      } catch (NumberFormatException ex) {
        System.out.println("Error! NumberFormatException. Assure to enter an integer!");
      }
    }
  }

  private static boolean checkConfiguration(String conf) {
    try {
      if (conf.length() < 2 || conf.length() > 12) {
        throw new Exception();
      }
      for (int i = 0; i < conf.length(); i++) {
        char confi = conf.charAt(i);
        if (conf.charAt(i) != 'L') {
          if (conf.charAt(i) != 'R') {
            throw new Exception();
          }
        }
      }
      return true;
    } catch (Exception ex) {
      System.out.println("Error! Config not parsable.");
      return false;
    }
  }

  private static void print() {
    List<SingleCell> col = grid.getColumn(0);
    for (int i = 0; i < col.size(); i++) {
      List<SingleCell> rows = grid.getRow(i);
      for (SingleCell cell : rows) {
        String state = cell.getState();
        String[] tokens = state.split("_");
        if (tokens[1].equals("10")) {
          System.out.print(tokens[0] + "A" + Utils.ANSI_RESET);
          continue;
        }
        if (tokens[1].equals("11")) {
          System.out.print(tokens[0] + "B" + Utils.ANSI_RESET);
          continue;
        }
        System.out.print(tokens[0] + tokens[1] + Utils.ANSI_RESET);
      }
      System.out.println("");
    }
  }
}
