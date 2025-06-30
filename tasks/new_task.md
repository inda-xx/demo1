![Task Image](images/task_image_20250630_153146.png)

=====================================================
Weekly Project: “Maze Master” 🧭
=====================================================

Scenario  
You have been hired by an escape-room start-up to write the core of an educational game called *Maze Master*.  
Players load any text file that describes a maze, then ask the program to show (or play through) the shortest path from the entrance to the exit.  
Your mission this week is to design the data model, read mazes from files, and prototype the path-finding features.

Learning goals you will practice  
• Using Data from Files to Instantiate Objects  
• Designing Classes  
• Programming Creatively  

Project output (end of week)  
• A runnable Java program (CLI or simple GUI) that  
  1. reads a maze description from a text file;  
  2. displays the maze in the console and/or GUI;  
  3. computes and highlights at least one optimal path;  
  4. lets the player “walk” the maze step by step or watch an auto-solver animation.  
• Short report (max. 1 page) describing your design decisions.

Maze file format (fixed for the whole project)  
• Plain text, rectangular grid, one character per cell.  
  ‘S’ = start, ‘E’ = exit, ‘#’ = wall, ‘.’ = corridor. Example (6×10):

S....#....  
###.#.###.  
#...#.##..  
#.###....#  
#.#E###..#  
#........#

-----------------------------------------------------
Exercises (submit each checkpoint to Moodle)
-----------------------------------------------------

Exercise 1 📑 “Map the Problem Space” — Theory (10 min)
Goal: Identify classes and responsibilities.  
Deliverable: A UML class diagram (hand-drawn is fine) containing at least *Maze*, *Cell*, *Player*, and *MazeSolver* plus arrows indicating relations.

Guiding questions  
• Which class should own the 2-D grid?  
• Where should path-finding algorithms live?  
• How can you keep the UI independent of the model?

Exercise 2 🗂️ “Files In, Objects Out” — Theory & Micro-code (20 min)
Goal: Plan how to transform file characters into *Cell* objects safely.

Task  
1. Sketch pseudocode for a static MazeLoader.load(String filename) method that  
   a) validates file dimensions,  
   b) throws MazeFormatException on errors,  
   c) returns a fully-constructed Maze.  
2. Explain (max. 5 lines) why checked exceptions are useful here.

Scaffolding snippet  
public class MazeLoader {
    public static Maze load(String fileName) throws MazeFormatException, IOException {
        // TODO: open file, read line by line, create Cells,
        // throw MazeFormatException if more than one 'S' or 'E', etc.
        return null; // placeholder
    }
}

Exercise 3 🔨 “Build the Skeleton” — Coding (1–2 h)
Goal: Implement core data classes with minimal behaviour.

Requirements  
• Write classes Maze, Cell, and Coordinate.  
• Maze must offer:  
  int getWidth(), int getHeight(), Cell getCell(Coordinate c)  
  List<Coordinate> getNeighbours(Coordinate c) // excluding walls  
• Override toString() in Maze so System.out.println(myMaze) shows the grid.

Hint snippet (interface only, fill later)  
public class Maze {
    private Cell[][] grid;
    // Constructor
    public Maze(int width, int height) { /* allocate grid */ }
}

Exercise 4 🚶 “Manual Walk Mode” — Coding & Creativity (1–2 h)
Goal: Let the player move via WASD keys in the console.

Minimum features  
• Show current maze each turn, marking the player position ‘P’.  
• Reject illegal moves into walls/outside border.  
• Detect victory when ‘E’ reached.

Creative twist (optional)  
Add a “fog-of-war” mode that reveals cells only after the player has visited them.

Exercise 5 🧠 “Teach the Maze How to Think” — Algorithmic Challenge (2–3 h)
Goal: Implement an auto-solver. Choose ONE algorithm:

A) Breadth-First Search (guarantees shortest path)  
B) A* (requires a heuristic; more efficient)  

Requirements  
• Provide MazeSolver.solve(Maze m) → List<Coordinate> path or Optional.empty() if impossible.  
• Count and print number of explored nodes.  
• Re-use getNeighbours from Exercise 3, do NOT modify Maze.

Skeleton example  
public interface MazeSolver {
    Optional<List<Coordinate>> solve(Maze maze);
}

Exercise 6 🎨 “Animation & Flair” — Creative Programming (open-ended, 2–4 h)
Pick at least TWO enhancements:

1. Text animation: Re-print the maze every 100 ms showing the solver “flow”.  
2. Color output (ANSI codes) or basic Swing GUI.  
3. Design your own maze file format (weighted cells, teleporters, etc.) and adapt the loader.  
4. Allow the user to switch between manual play and auto-solver mid-game.  
5. Add statistics (speed, path length, branching factor) and write them to “stats.csv”.

Be prepared to demo your chosen add-ons in class.

-----------------------------------------------------
Submission checklist
-----------------------------------------------------
[ ] Exercises 1–6 completed  
[ ] Code compiles with `javac *.java` and runs with `java Main …`  
[ ] README with compilation/run instructions  
[ ] Report.pdf (≤ 1 page)  

Have fun becoming a Maze Master!

=====================================================
Exercise 1 📑  “Map the Problem Space” — Class Design
=====================================================
Word count ≈ 340  
Learning-goal focus: Designing Classes

Background  
Before touching the keyboard it is vital to picture which “things” exist in the program and what
each thing must know or do.  A clear mental model helps you write code that is easy to extend
(next week you will add scores, timers, maybe monsters!).

Task  
1. Draw a simple UML-style class diagram (hand-drawn, draw.io, or any tool) that contains at
   least the following four classes and their relations:
   • Maze                • Cell  
   • Player              • MazeSolver  
2. For every class in your diagram write one short sentence that answers BOTH questions:  
   “What does the class represent in the real world?” and “What is its single main
   responsibility in the code?”  
3. Add any additional classes or interfaces that you think are justified, but explain in one
   bullet why each extra element earns its place (e.g., “Coordinate keeps x/y together and
   avoids passing two ints around”).  
4. Using arrows, indicate:  
   • Aggregation/Composition (which class owns which objects?)  
   • Dependencies (which class only uses another temporarily?)  
   • Inheritance hierarchies, if any.  
   Keep the notation lightweight; stick-figures and hand-written arrows are OK as long as they
   are readable.

Guiding questions  
• Should the two-dimensional grid live inside Maze or somewhere else?  
• Where should the path-finding algorithm reside so that you can later plug in different
  strategies (BFS, A*, …) without rewriting Maze?  
• How will the user-interface layer (CLI or GUI) communicate with the model without
  “knowing” implementation details?

Deliverable (submit as a single PDF or image file)  
– UML diagram + the explanatory sentences/bullets.  
Assessment will reward clear allocation of responsibilities, proper use of relations, and concise
rationale for design choices.



=====================================================
Exercise 2 🗂️  “Files In, Objects Out” — Robust Maze Loading
=====================================================
Word count ≈ 330  
Learning-goal focus: Using Data from Files to Instantiate Objects

Background  
Mazes arrive as plain-text files; your program must translate a sequence of characters into a
living network of Cells.  Good software does not blindly trust external data—the loader must
validate, report errors clearly, and leave the rest of the program in a consistent state.

Task (conceptual, minimal code)  
1. Write high-level pseudocode (≈ 10–15 lines) for a static method  
      MazeLoader.load(String fileName)  
   The pseudocode should show the *steps* rather than Java syntax details.  It must cover:  
   a) opening and closing the file safely;  
   b) detecting non-rectangular input, multiple ‘S’ or ‘E’ symbols, illegal characters;  
   c) constructing the Maze and its Cell objects only after validation succeeds;  
   d) propagating problems through a checked exception MazeFormatException.  
2. In 4–6 lines of prose, argue why *checked* exceptions—as opposed to unchecked
   RuntimeExceptions—are appropriate for format errors in this context.  Hint: think about who
   should be forced to react and how that improves program robustness.  
3. List two additional integrity checks you could add if the format became richer (e.g., weighted
   cells, teleporters).  One sentence each is enough.

Scaffolding snippet (for reference only, do NOT implement yet)  
public class MazeLoader {
    public static Maze load(String fileName)
            throws MazeFormatException, IOException {
        // your pseudocode goes here (submit in .txt or .pdf)
    }
}

Deliverable  
– A single PDF or text file containing:  
  • the pseudocode;  
  • your exception rationale;  
  • the two extra integrity-check ideas.  

Evaluation focuses on completeness of validation steps, clarity of reasoning about exceptions,
and creativity of suggested future checks.

=====================================================
Exercise 3 🔨  “Bring the Model to Life” — Coding & Micro-Tests  
=====================================================
Approx. workload: 2–3 h   Learning-goal focus:  
• Using Data from Files to Instantiate Objects  
• Designing Classes  

Context  
Your UML diagram (Ex 1) and loading strategy (Ex 2) are on paper; time to commit them to
Java.  You will implement the **core data model** plus a handful of micro-tests that prove it
already works with real maze files.  Think of this exercise as pouring a concrete foundation:
it is not flashy, but everything that comes later (player movement, auto-solver, GUI) relies
on these classes behaving correctly and predictably.

Task A – Implement the three fundamental classes  
1. `Coordinate` – an *immutable* value object.  
   • fields `int row, col`  
   • methods `equals`, `hashCode`, `toString` (row-major “(r,c)”)  
   • one factory: `public static Coordinate of(int row, int col)`  

2. `Cell` – represents one square in the maze.  
   • enum `Type { START, EXIT, WALL, CORRIDOR }` (avoid magic chars in the code)  
   • fields: `Type type; Coordinate coord;`  
   • queries: `boolean isWalkable()` (true for START, EXIT, CORRIDOR)  

3. `Maze` – owns the 2-D grid.  
   Required API (copy/signature only; fill in body):  
   ```java
   public class Maze {
       public Maze(int width, int height);          // allocate grid
       public int  getWidth();
       public int  getHeight();
       public Cell getCell(Coordinate c);          // O(1)
       public List<Coordinate> getNeighbours(Coordinate c);
       @Override public String toString();         // prints same chars that were loaded
   }
   ```  
   Contract for `getNeighbours`: return *walkable* orthogonal neighbours (N/E/S/W), preserve
   in-file order **N-E-S-W**.

Task B – Plug in the loader stub  
• Copy your `MazeLoader` skeleton from Ex 2 into the project.  
• Implement only enough code to support the *happy path* (valid rectangular text file).  
  – Read the file, create `Cell`s with correct `Type`, store them inside a `Maze`.  
  – Defer *all* validation checks to the next exercise; if you detect a problem, throw
    `new UnsupportedOperationException("validation not implemented yet")`.  
  – You must still close the file properly (`try-with-resources`).  

Task C – Micro-tests (compilation target: `TestMaze.java`)  
Write a tiny CLI program or JUnit test class that  
1. loads `sample_maze.txt` (provided in `resources/`),  
2. prints width, height, and the maze picture (`toString()`),  
3. prints the neighbours of `(0,0)` and of the exit cell.  

Expected console output (the exact whitespace may differ):  
```
Size = 10×6
S....#....
###.#.###.
#...#.##..
#.###....#
#.#E###..#
#........#
Neighbours of (0,0) : [(0,1)]
Neighbours of (4,3) : [(3,3), (5,3)]
```

Deliverables (push to repository & tag `exercise-3`)  
• `Coordinate.java   Cell.java   Maze.java`  
• partially-implemented `MazeLoader.java`  
• `sample_maze.txt` (copy the example from the brief)  
• test file (`TestMaze.java` or `*.java` with JUnit)  
• short `README.md` (max. 10 lines) describing how to compile & run the test  

Assessment criteria  
✓ Correct encapsulation and immutability of Coordinate  
✓ No magic numbers/chars leaked outside Cell.Type  
✓ `getNeighbours` honours walkability and order  
✓ Model classes compile *without* any other project parts  
✓ Test run reproduces the expected output



=====================================================
Exercise 4 🚶  “Manual Walk Mode” — Integrative Programming  
=====================================================
Approx. workload: 3–4 h   Learning-goal focus:  
• Using Data from Files to Instantiate Objects  
• Programming Creatively  

Bridge to future work  
This exercise glues together the loader, the model, and a *very small* user interface.  When
you later swap the human player for an algorithm (Ex 5) or add colours/animations (Ex 6), the
controller you write now will already provide the required hooks.

Functional requirements  
1. **CLI Controller** (`MazeGame.java` or similar)  
   • Program start: `java MazeGame <mazeFile>`  
   • Loads the maze with `MazeLoader.load(...)`.  
   • Places the player on the start cell.  
   • Enters an input loop, accepting single-letter commands (uppercase or lowercase):  
      W = up, A = left, S = down, D = right, Q = quit.  
   • After each command:  
      a) validate the move (no walking through walls/borders)  
      b) update position  
      c) reprint the maze, marking the player with the character ‘P’  
   • Detect and announce victory when the player steps onto the exit.

2. **Decouple UI from model**  
   • The Maze class must remain *unmodified* (no knowledge of ‘P’ or user input).  
   • Hint: write a helper `String render(Maze m, Coordinate playerPos)` inside the controller.  

3. **Error handling**  
   • Catch `IOException` & `MazeFormatException` around the loader and print a *friendly*
      message before exiting.  
   • On invalid key press print “Unknown command (use WASDQ)”.

4. **Polish** (choose at least *one* mini-enhancement, ±15 min effort)  
   a) Move counter and display “You reached the exit in 42 moves!”.  
   b) Allow “R” to restart the same maze without reloading the file.  
   c) Freeze the final path and print CONGRATULATIONS in ASCII art.  
   Document the selected extra in the header comment of `MazeGame.java`.

Stretch goal (not graded but fun)  
Implement a “fog-of-war”: keep a `Set<Coordinate> visited`, and in the renderer print unknown
cells as ‘ ? ’.

Deliverables (push & tag `exercise-4`)  
• `MazeGame.java` (or `CliController.java`, your choice)  
• *any* helper classes added (e.g., `Direction` enum)  
• Updated `README.md` with run instructions:  
  `javac *.java`   then   `java MazeGame resources/sample_maze.txt`

Assessment criteria  
✓ Game starts and accepts commands without crashing  
✓ Player cannot move through walls; victory detection works  
✓ Model classes from Ex 3 were **not** modified  
✓ Error messages are user-friendly  
✓ At least one mini-enhancement implemented and documented  
✓ Code is commented, ≤ 120 non-blank lines for the controller (brevity counts)

Time-management tip  
Finish Exercise 4 no later than Day 4 of the week.  You will then have three days left for the
auto-solver (Ex 5) and for the creative sparkle of Ex 6.

Happy exploring – may your paths be optimal!

=====================================================
Exercise 5 🧠  “Teach the Maze How to Think” — Algorithmic Auto-Solver  
=====================================================
Approx. workload: 3–4 h   Learning-goal focus:  
• Designing Classes • Programming Creatively (algorithm design)  

Context  
Your maze can now be loaded and walked manually. In this exercise you give the program
its own “brain”: a search algorithm that discovers a shortest path without user help.  
You will also measure the algorithm’s exploration effort—laying the ground for statistics or
animations in Exercise 6.

Task A – Choose ONE search strategy  
1. Breadth-First Search (BFS) — guarantees the first found path is the shortest.  
2. A* Search — generally faster, but you must design an admissible heuristic h(n).  
   Hint: Manhattan distance is admissible in a 4-connected grid.

Task B – Design the solver interface (keep UI-agnostic)  
Create an interface exactly as below; do not add fields or static helpers here.

```java
public interface MazeSolver {
    /**
     * Tries to find a path from the unique START to the EXIT.
     * @param maze read-only model (must NOT be modified)
     * @return Optional containing the path (inclusive of S and E) or empty if none
     */
    Optional<List<Coordinate>> solve(Maze maze);
}
```

Task C – Implement ONE concrete solver class  
• `class BfsMazeSolver implements MazeSolver` – OR – `class AStarMazeSolver implements MazeSolver`.  
• Store at least these runtime statistics as public read-only fields or getters:  
  – exploredNodeCount (int)  
  – pathLength        (int, 0 if no path)  
  – elapsedMillis     (long)  

Contract requirements  
1. May call ONLY the public API of Maze & Cell (no grid-hacking).  
2. Must terminate on unsolvable mazes and return Optional.empty().  
3. `exploredNodeCount` must equal the number of distinct coordinates removed from the
   frontier/queue.  

Task D – Quick-n-dirty driver (`SolveDemo.java`)  
Write a tiny CLI utility that  
```
java SolveDemo <mazeFile> [bfs|astar]
```
• Loads the maze, instantiates the chosen solver, prints  
  – “Path found? yes/no”  
  – “Path length : …” (cells)  
  – “Nodes explored : …”  
  – “Time           : … ms”  
• If a path exists, reprints the maze with the path marked by ‘*’.  
  (Reuse your renderer; do NOT modify Maze.)

Hint scaffolding (empty body)  
```java
public final class Algorithms {
    public static List<Coordinate> reconstructPath(
            Map<Coordinate, Coordinate> parent, Coordinate end) {
        // TODO: follow parent links back to START, reverse list
        return Collections.emptyList();
    }
}
```

Task E – Micro-tests  
Add JUnit or CLI tests that assert  
• BFS on `sample_maze.txt` returns a path of length 18.  
• Solver reports ≥ 18 explored nodes (tie-breaking may vary).  
• An “all-walls” 6×6 maze is unsolvable and triggers Optional.empty().

Deliverables (push & tag `exercise-5`)  
– `MazeSolver.java` – concrete solver class – `Algorithms.java` (helper)  
– `SolveDemo.java` – test file(s) – updated `README.md`

Assessment criteria  
✓ Interface respected, model remains immutable  
✓ Correct shortest path for BFS OR admissible heuristic for A*  
✓ Exploration counter accurate  
✓ Driver prints clear, formatted statistics  
✓ Tests compile and pass with `javac` / `java` or JUnit  
✓ Code ≤ 160 non-blank lines for the solver (clarity beats cleverness)

Time-management tip  
Aim to finish the first working version within 2 hours, then spend the remaining time cleaning
up, commenting, and writing tests. You will reuse the solver in Exercise 6.  



=====================================================
Exercise 6 🎨  “Polish & Present” — Creative Integration  
=====================================================
Approx. workload: 3–5 h   Learning-goal focus:  
• Programming Creatively • Using Data from Files to Instantiate Objects  

Goal  
Transform your functional—but plain—program into a small game / demo that is pleasant to
watch and easy to extend.  You will integrate the manual mode, the auto-solver, and at least
two visual or interactive enhancements of your choice.

Part 1 – Unified Controller (`MazeMasterApp.java`)  
1. Program start:  
   ```
   java MazeMasterApp <mazeFile>            # starts in MANUAL mode
   java MazeMasterApp <mazeFile> --auto bfs # starts and auto-solves
   ```  
2. Features (all via console unless you optionally choose Swing):  
   • Switch between modes at runtime:  
     - Press ‘F’ to toggle “follow solver” (auto) / “free walk” (manual).  
   • Press ‘H’ to print help (list of keys).  
   • Maintain ONE player position that is updated by either human moves or the solver.  

Part 2 – Pick AT LEAST TWO enhancements  
(Each bullet ≈ 30–60 min effort.  Implement cleanly; brief is minimal on purpose so you can be creative.)

1. Text animation  
   - Every 100 ms clear the console (ANSI “\033[H\033[2J”) and redraw, colouring the current
     frontier in cyan and the confirmed path in green.

2. ANSI colours  
   - Walls gray, corridors white, start bright blue, exit bright red, player yellow.

3. Basic Swing GUI  
   - A `JFrame` showing a grid of `JPanel`s painted with background colours.  
   - Arrow keys = move; space = start/stop solver thread.

4. Extended file format  
   - Add teleporters labelled ‘A’..‘Z’: stepping on ‘A’ instantly moves player to the matching
     ‘A’. Adapt MazeLoader and *document* the new rules in README.

5. Statistics logger  
   - After each run append one CSV line: `<file>,<mode>,<pathLen>,<nodes>,<ms>,<timestamp>`.

6. Fog-of-war  
   - Unknown cells print as ‘?’ until first seen by player or solver. Persist between modes.

Part 3 – Tiny showcase script (`demo.sh` or Windows `.bat`)  
Automate a 30-second showcase: compile, start the program on `sample_maze.txt`, auto-solve
with animation on, then quit.  Keep it simple; grader will read the script.

Deliverables (push & tag `exercise-6`)  
• `MazeMasterApp.java` (+ any new helper classes)  
• Implementation of at least two chosen enhancements  
• `demo.sh` or `.bat` with clear comments  
• Final `README.md` (include: enhancements implemented, run instructions, known bugs)  
• Optional: short GIF or screenshot in `/docs/` folder (not graded but nice!)

Assessment criteria  
✓ Manual ↔ auto mode switch works without crashing  
✓ Solver from Ex 5 reused (no duplication)  
✓ At least two enhancements function and are documented  
✓ Code respects existing model APIs; new UI code is cleanly separated  
✓ Console re-render or GUI runs at ≥ 5 fps (eyeball test)  
✓ Showcase script executes without manual tweaks  
✓ Overall project compiles with one `javac *.java` and launches with one `java` command

Stretch ideas (ignore if short on time)  
• Music/SFX with JavaClip; • Maze generator; • Multiple difficulty levels; • Leaderboard.

Congratulations! Completing Exercise 6 means you have built a tiny but complete game engine
that loads external data, models it with clean classes, applies classic AI search, and offers a
user-friendly presentation. You are now officially a Maze Master 🏆.