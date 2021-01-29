# Ant

Old Project from 2018:

Langton's ant is a two-dimensional universal Turing machine. The squares on the grid are colored in a given sequenz. In the beginning every square is white. After the ant was on a field, it will be colored.
The configuration determines the color and the direction in which the ant turns.

Commands:
- new x y c: new grid with x colums, y rows and c as configuration (Configuration consists R's (turn right) and L's (turn left) - MAX: 12
- ant i j: puts an new ant at column i ant row j
- unant: removes ant from grid
- step: ant walks one step
- step n: ant walks n steps forward
- step -n: reset to state before 5 steps
- print: prints the grid
- clear: Resets the whole grid
- resize: increases or decreases the grid symmetrically
- quit: terminates the program
