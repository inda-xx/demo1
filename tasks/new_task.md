🎯 Weekly Programming Assignment  
Theme: Build a Mini-Arcade Game  

Overview  
During this week you will go from paper sketches to a fully working desktop-console* game that supports: player movement, score keeping, enemy behavior, basic collision detection, and overall game-state management.  
While coding you must:  
1. Read external data files to create game objects.  
2. Apply solid class-design principles.  
3. Add at least one small personal “creative twist”.  

*(You may use plain Java and the standard library only. Graphics are optional and can be ASCII-based.)  

The six exercises below are designed to be completed in order; each builds on the previous one. Submit the requested artefacts after each exercise or all at once, depending on your instructor’s preference.  

──────────────────────────────────────────  

Exercise 1 📚 Concept Check – Game Ingredients (theoretical)  
Goal: Make sure you truly understand the five required game functions and the three learning goals.  
Tasks  
a. In ≤300 words explain, in your own words, why “Using Data from Files to Instantiate Objects” is valuable in games.  
b. List the concrete classes you think the game will need. For each class write one sentence describing its single responsibility (SRP).  
c. Sketch (hand-drawn is fine) how these classes might collaborate during one frame of the game loop.  

Deliverable: 1-page PDF.  

──────────────────────────────────────────  

Exercise 2 🧩 Blueprint – Class & File Design (theoretical → practical bridge)  
Goal: Produce a design you can actually implement.  
Tasks  
1. Choose a simple text file format (CSV, JSON, or your own) to store enemy definitions (type, start-x, start-y, speed, health, points).  
2. Draw a lightweight UML-style diagram containing:  
   • Player, Enemy, ScoreManager, GameState, and any helper classes you need.  
   • Main public methods (signatures only).  
3. Write the file header and two example lines/objects that conform to the chosen format.  
4. Explain (≤150 words) how you will defend against malformed files (exception handling, default values, etc.).  

Deliverable: UML diagram + sample data file + short explanation.  

──────────────────────────────────────────  

Exercise 3 🎮 Hands-On – Player Movement Prototype (basic programming)  
Goal: Implement keyboard input and update the player’s position.  
Starter Code (Player.java – incomplete):  
```java
public class Player {
    private int x, y;          // top-left corner
    private final int speed = 2;

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    /* TODO 1: read a key code (e.g., 'W', 'A', 'S', 'D') and adjust x / y */
    public void move(char key) {
        // YOUR CODE HERE
    }

    /* TODO 2: add getters so other classes can read the position */
}
```  
Tasks  
a. Complete `move(char key)` so that WASD (or arrow keys) move the player.  
b. Add simple bounds-checking so the player cannot leave the screen area (assume 0 ≤ x ≤ 79, 0 ≤ y ≤ 23 for a standard console).  
c. Write a tiny Main class containing a loop that:  
   • Reads one character from `System.in`.  
   • Calls `player.move(key)`.  
   • Prints the player’s new coordinates.  
Hint: `System.console().reader().read()` gives you a single character in plain Java.  

Deliverable: Player.java + demo Main.  

──────────────────────────────────────────  

Exercise 4 📊 Score & HUD Implementation (basic programming)  
Goal: Track points and display them.  
Starter Code (ScoreManager.java – skeletal):  
```java
public class ScoreManager {
    private int score = 0;

    public void addPoints(int pts) {
        // TODO: increase score safely
    }

    public int getScore() {
        // TODO: return current score
        return 0;
    }

    public void reset() {
        // TODO: set score back to zero
    }
}
```  
Tasks  
1. Finish the three methods above.  
2. Decide where in your main loop you should call `addPoints`. (You will connect this to collisions in Exercise 6.)  
3. Print the current score at the top of the console after every frame. Keep the rest of the screen clear for gameplay.  

Deliverable: ScoreManager.java + updated Main loop.  

──────────────────────────────────────────  

Exercise 5 👾 Dynamic Enemy Loader & Behaviour (challenging, file I/O + design)  
Goal: Load enemies from a file and make them move.  
Given File Example (CSV):  
```
#type,x,y,speed,health,points
SLIME,10,5,1,1,50
BAT,30,2,2,1,75
```  
Starter Code (EnemyLoader.java):  
```java
import java.nio.file.*;
import java.util.*;

public class EnemyLoader {

    public static List<Enemy> load(String filename) {
        List<Enemy> enemies = new ArrayList<>();
        // TODO 1: read file line by line, skip comments (#)
        // TODO 2: split by comma, parse fields
        // TODO 3: create Enemy objects and add to list
        return enemies;
    }
}
```  
Tasks  
a. Complete `EnemyLoader.load`. Handle `IOException` and malformed data gracefully (log & skip).  
b. Design the `Enemy` class so that each enemy can:  
   • Move horizontally or vertically (choose a simple rule, e.g., back-and-forth).  
   • Report its bounding box for collision detection.  
c. Instantiate all enemies at program start by calling `EnemyLoader.load`. Store them in an `ArrayList<Enemy>` inside your Game class.  

Deliverable: EnemyLoader.java, Enemy.java, updated Game class.  

──────────────────────────────────────────  

Exercise 6 🏁 Full Game Loop, Collision & Creative Twist (challenging capstone)  
Goal: Glue everything together, detect collisions, manage game states, and add something personal.  
Starter Code (Game.java – trimmed):  
```java
public class Game {

    private Player player;
    private List<Enemy> enemies;
    private ScoreManager scoreMgr;
    private boolean running = true;

    public void init() {
        player = new Player(40, 12);          // center screen
        enemies = EnemyLoader.load("enemies.csv");
        scoreMgr = new ScoreManager();
    }

    public void run() {
        while (running) {
            long frameStart = System.currentTimeMillis();

            update();
            render();

            // cap at ~30 FPS
            long sleep = 33 - (System.currentTimeMillis() - frameStart);
            if (sleep > 0) { try { Thread.sleep(sleep); } catch (InterruptedException ignored) {} }
        }
    }

    private void update() {
        // TODO 1: read input & move player
        // TODO 2: move each enemy
        // TODO 3: check collisions (Player vs Enemy)
        // TODO 4: adjust score / health / game state
    }

    private void render() {
        // TODO: clear console and draw player, enemies, and HUD
    }

    public static void main(String[] args) {
        new Game().init();
        new Game().run();      // intentionally separated for clarity
    }
}
```  
Tasks  
1. Implement AABB (Axis-Aligned Bounding Box) collision detection: two rectangles overlap ⇔ collision.  
2. When the player collides with an enemy:  
   • Award the enemy’s point value.  
   • Remove (or respawn) the enemy.  
3. End the game when the player’s health ≤ 0 or when all enemies are gone. Print a final score screen.  
4. Creative twist (choose ONE, or invent your own of similar scope):  
   • Power-ups randomly spawn and grant temporary invincibility.  
   • Different enemy types with unique movement patterns loaded from the file.  
   • A rudimentary menu system (Start, Help, Quit).  

Deliverable: Complete runnable game (source + data files) + a short README describing your creative addition.  

──────────────────────────────────────────  

Grading Hints  
• Good class design (cohesion, small public interfaces) is worth as much as raw functionality.  
• Failed file reads must not crash the program.  
• Clear console output and sensible keyboard controls will help your instructor test quickly.  
• Creativity is rewarded, but only if the required core features work first.  

Have fun and code safely! 🚀

──────────────────────────────────────────  
Exercise 1 – Conceptual Warm-Up (≈330 words)  
Theme: Data-Driven Objects & Class Responsibilities  

Purpose  
Before touching any code, make sure you can articulate why the three weekly learning goals matter and how they fit into a tiny arcade game. Solid answers here will save you many debugging hours later.

Tasks  
a. Using ≤300 words, argue—in your own words—why “Using Data from Files to Instantiate Objects” is especially useful for games that must scale or change quickly. Illustrate with one concrete example that is NOT “loading enemies.”  
b. List every concrete class you think your mini-arcade game will need (expected: 6-10). For each class, write a single sentence that states its one clear responsibility (Single-Responsibility Principle).  
c. Draw a tiny collaboration sketch (hand-drawn is fine) showing how those classes interact during ONE iteration of the main game loop. Focus on who calls whom; do not include every attribute or method. Label arrows with the message name (e.g., update(), addPoints()).

Submission  
• One-page PDF containing: answers a & b and a photo/scan of sketch c.  
• File name: Ex1_Conceptual_<YourLastName>.pdf  

Evaluation Focus  
• Depth—not length—of explanation (a).  
• Realistic, SRP-friendly class list (b).  
• Clear interaction flow (c).  

Tip  
Pretend you will hand your design to a stranger tomorrow. Would they be able to start coding without asking you dozens of “what does this class actually do?” questions?  

──────────────────────────────────────────  
Exercise 2 – Design Draft (≈360 words)  
Theme: File Format Choice & Lightweight UML  

Purpose  
Translate the abstract ideas from Exercise 1 into an implementable blueprint. By the end you should know exactly what file you will read, what objects you will create from it, and where defensive programming is required.

Tasks  
1. Pick ONE simple text format to describe enemies (CSV, JSON, or your own). Briefly justify the choice in ≤40 words.  
2. Create a lightweight UML-style diagram that shows:  
   • Core game classes (minimum: Player, Enemy, ScoreManager, GameState, EnemyLoader).  
   • Public methods only (name + parameters).  
   • Key fields needed for instantiation from a data file (e.g., speed, health).  
   Keep it on a single page; boxes and arrows are enough—no fancy tooling required.  
3. Draft the first three lines of your chosen data file: a header plus two concrete enemy entries that your Game will eventually load.  
4. In ≤150 words, describe your plan for fault tolerance when reading the file. Address:  
   • Detecting malformed lines.  
   • Recovering or skipping bad data.  
   • Ensuring the game still starts even if the file is partially broken.

Submission  
• One PDF named Ex2_Design_<YourLastName>.pdf containing:  
  – Format justification (1).  
  – UML diagram (2).  
  – Data file snippet (3).  
  – Fault-tolerance plan (4).  

Evaluation Focus  
• Clarity and completeness of the UML (does it map cleanly to your class list from Ex 1?).  
• Data file snippet that exactly matches the diagram’s fields.  
• Realistic, actionable error-handling strategy (no “just catch Exception and ignore”).  

Tip  
Keep the diagram and file snippet nearby while coding the later exercises—they will become your checklist and prevent “mystery bugs” from mismatched field names.

──────────────────────────────────────────  
Exercise 3 – From File to Object (≈420 words)  
Theme: Data-Driven Instantiation & Defensive File-I/O  

Why this exercise?  
You have already drafted your file format and UML.  
Now you will prove that the design really works by turning two text lines on disk into two live Enemy objects in memory—without crashing when the data are ugly.  
Everything you write here will be reused (almost unchanged) in Exercises 5 & 6.

Starter assets  
1. File enemies.csv (empty – you fill it).  
2. Skeleton code (package “arcade”):  

EnemyType.java  
```java
/** DO NOT EDIT enum names once your data file is written. */
public enum EnemyType {
    SLIME, BAT, SNAKE, /* add more if you want a twist */
    UNKNOWN            // fallback for malformed data
}
```

Enemy.java  
```java
public class Enemy {
    private EnemyType type;
    private int x, y, speed, health, points;

    /* TODO – clean constructor; do NOT add behaviour yet   */
    public Enemy(EnemyType type, int x, int y,
                 int speed, int health, int points) {
        // YOUR CODE HERE
    }

    /* TODO – minimal getters only (no setters!)            */
}
```

EnemyLoader.java  
```java
import java.nio.file.*;
import java.util.*;

public class EnemyLoader {

    /* Reads a *single* file and returns a list of Enemies.
       Never returns null; may return an empty list.         */
    public static List<Enemy> load(String filename) {
        List<Enemy> result = new ArrayList<>();
        // TODO 1 – open file safely, stream lines, skip blanks + lines starting with '#'
        // TODO 2 – split each line, validate expected field count
        // TODO 3 – parse ints; map type-string to enum (default: UNKNOWN)
        // TODO 4 – on ANY NumberFormatException or missing field:
        //          print a warning to System.err and SKIP that line
        return result;
    }

    /* Utility – make testing easier. */
    static Enemy fake() {               // Package-private on purpose
        return new Enemy(EnemyType.SLIME, 1, 1, 1, 1, 5);
    }
}
```

Tasks  
1. Complete Enemy and EnemyLoader as indicated.  
2. Populate enemies.csv with:  
   • A header row that matches your constructor order.  
   • Two valid enemies.  
   • One intentionally broken line to prove your error handling works (submit the same file you test with).  
3. Write a tiny “LoaderDemo” class whose main():  
   • Calls EnemyLoader.load("enemies.csv").  
   • Prints “Loaded X enemies” and then each Enemy on its own line (implement Enemy.toString()).  
   • Shows that the broken line is skipped but the program continues.  
4. (Mini-reflection ≤ 120 words) In a README snippet, describe one real-world scenario where bad game data could appear and how your loader copes.

Deliverables (commit or ZIP)  
• EnemyType.java, Enemy.java, EnemyLoader.java, LoaderDemo.java  
• enemies.csv (with header + 3 data rows)  
• README.md (120-word reflection)  

Evaluation highlights  
✔ Zero crashes on malformed input.  
✔ Enum + constructor reflect your UML from Ex 2.  
✔ Clear, self-explanatory console output.  
✔ No business logic yet—this file handles creation only.  

──────────────────────────────────────────  
Exercise 4 – First Playable Slice (≈450 words)  
Theme: Class Integration, Minimal Game Loop & HUD  

Why this exercise?  
Time to stitch your isolated pieces together.  
By the end, you can move a player, spawn enemies loaded from Exercise 3, and watch a live score counter tick.  
There is *no* collision yet—that is saved for Exercise 6.

Provided scaffold (same package “arcade”)  

Player.java  
```java
public class Player {
    private int x, y;           // top-left corner
    private final int speed = 2;
    private final int minX = 0, minY = 0,
                      maxX = 79, maxY = 23;   // console bounds

    public Player(int startX, int startY) {
        this.x = startX;
        this.y = startY;
    }

    /* TODO 1 – move based on single-character input */
    public void move(char key) {
        // Example keys: 'W','A','S','D'  OR  '↑','←','↓','→'
        // Clamp to [min,max] after modifying.
    }

    /* TODO 2 – simple getters (x,y) */
}
```

ScoreManager.java  
```java
public class ScoreManager {
    private int score = 0;

    public void add(int pts) {
        // TODO – guard against negative points
    }

    public int current() {
        return score;
    }

    public void reset() { score = 0; }
}
```

Game.java  
```java
import java.io.IOException;
import java.util.*;

public class Game {

    private Player   player;
    private List<Enemy> enemies;
    private ScoreManager score;

    public void init() throws IOException {
        player  = new Player(40, 12);         // roughly center
        enemies = EnemyLoader.load("enemies.csv");
        score   = new ScoreManager();
    }

    public void run() throws IOException {
        while (true) {
            render();
            update();
        }
    }

    /* TODO – read ONE char from console and move player    */
    private void update() throws IOException {
        int key = System.in.read();           // blocks
        if (key == -1) return;                // EOF -> exit cleanly
        player.move((char) key);
        score.add(1);                         // +1 point per move for now
    }

    /* TODO – ASCII render: clear screen, draw HUD + player + enemies */
    private void render() {
        // Tip: ANSI escape code "\033[H\033[2J" clears most consoles.
        // Must flush System.out after print!
    }

    public static void main(String[] args) throws IOException {
        new Game().init();
        new Game().run();    // separated for clarity
    }
}
```

Tasks  
1. Finish Player.move() with WASD or Arrow-key support and enforce screen bounds.  
2. Implement ScoreManager.add() so score can never fall below 0.  
3. Finish Game.render():  
   • Clear console.  
   • Print “Score: NN” on row 0.  
   • Print each Enemy as “E” at its (x,y).  
   • Print the Player as “P”.  
   For simplicity, ignore overlapping characters for now.  
4. Bonus creativity (+5%): Add one **color** (e.g., green player, red enemies) using ANSI codes or invent a different small flourish.  
5. Manual play-test: run Game, move the player around, verify that  
   • The position never leaves 0-79/0-23.  
   • The score increments exactly once per key-press.  
   • All enemies from enemies.csv appear.  
6. Short reflection (≤100 words) answering: “What part of my class design from Exercise 2 helped the most during this coding step?”

Deliverables  
• Player.java, ScoreManager.java, Game.java (fully compilable)  
• Screenshot (PNG or JPG) of the running console after at least 10 moves.  
• reflection.txt (≤100 words)  

Evaluation highlights  
✔ Clean compile with `javac *.java` and run with `java arcade.Game`.  
✔ No screen flicker (clear + render done once per frame).  
✔ Player obeys bounds; score never negative; enemies visible.  
✔ Console still works if enemies.csv contains the broken line from Ex 3.  
✔ Code respects SRP (no rendering code inside Player or Enemy).  

──────────────────────────────────────────  
One-Week Planning Advice  
Day 1–2: Finish Exercises 1 & 2 (already assigned).  
Day 3–4: Tackle Exercise 3; aim for 100 % file-loading confidence before moving on.  
Day 5: Implement core logic of Exercise 4 (movement + HUD).  
Day 6: Polish rendering, optional colour, run extended manual tests.  
Day 7: Buffer for last-minute bug-fixes, README writing, and submission packaging.  

Stay incremental, run often, and commit early—good luck! 🚀

──────────────────────────────────────────  
Exercise 5 – “The World Fights Back” (≈500 words)  
Theme: File-Driven Enemy Behaviour & Runtime Updates  

Why this exercise?  
Up to now the enemies just sit there. In this step you will (1) finish the data-to-object pipeline by turning static Enemy records into live, moving objects and (2) rehearse defensive coding by re-using the loader you already trust. When you are done the screen should look alive—even before you add collisions.

Learning outcomes  
• Transform file data into fully initialised, behaviour-rich objects.  
• Apply the Single-Responsibility Principle while adding new features.  
• Practise incremental, test-early development.

Starter assets (in package arcade)  

MovementPattern.java – NEW  
```java
/**
 * A tiny interface that hides HOW an enemy moves.
 * CS1-friendly: only one method, no default code.
 */
public interface MovementPattern {
    /** Mutates x/y every frame. Called once per game tick. */
    void move(Enemy e);
}
```

Enemy.java (excerpt – only the new bits shown)  
```java
public class Enemy {
    …                      // fields from Exercise 3 stay intact
    private MovementPattern pattern;    // NEW

    public Enemy(EnemyType type, int x, int y,
                 int speed, int health, int points,
                 MovementPattern pattern) {       // +pattern
        …                                         // existing field inits
        this.pattern = pattern;
    }

    /** Called by Game.update() each frame. */
    public void update() {
        pattern.move(this);
        // Optionally clamp to screen bounds here.
    }

    /* + axis-aligned bounding box helper for Exercise 6 */
    public int left()   { return x; }
    public int right()  { return x + 1; }    // 1×1 sprite
    public int top()    { return y; }
    public int bottom() { return y + 1; }
}
```

EnemyLoader.java (only the part you extend)  
```java
// Current CSV header idea:
// type,x,y,speed,health,points,pattern
//
// Example new pattern column values: HORIZONTAL, VERTICAL, STATIC
```

Tasks  
1. Decide on **three** movement keywords (“HORIZONTAL”, “VERTICAL”, “STATIC” recommended).  
   a. Create one class per keyword that implements MovementPattern.  
   b. HORIZONTAL: move `+speed` until `x==79`, then reverse.  
      VERTICAL:   move `+speed` until `y==23`, then reverse.  
      STATIC:     does nothing.  
   Hint:  A direction field (`+1` or `-1`) inside each pattern class keeps the logic tiny.  

2. Expand EnemyLoader.load():  
   • Read the extra pattern column.  
   • Map the string to a MovementPattern object.  
   • If the keyword is unknown, fall back to STATIC and `System.err.println` a warning.  
   • Update the Enemy constructor call accordingly.  

3. Add `update()` to Enemy (see snippet) and call it from `Game.update()` *before* you process player input.  

4. Rendering tweak: in `Game.render()` draw enemies with different letters:  
   • ‘H’ for horizontal, ‘V’ for vertical, ‘S’ for static.  
   *Optional colour:* one ANSI colour per movement type (green/red/cyan).  

5. Quick manual test: start the game, let it run without touching the keys for 10 s.  
   • At least one enemy should bounce.  
   • The program must never crash if the CSV contains an unrecognised pattern.  

6. Reflection (≤90 words) — commit a file `reflection5.txt`:  
   “Which part of today’s code benefited most from being split into small, single-purpose classes?”

Deliverables  
• MovementPattern.java + three pattern classes  
• Updated Enemy.java, EnemyLoader.java, Game.java  
• Updated enemies.csv with the new column (+ ≥ 3 enemies)  
• Screenshot after ~10 s of unattended runtime  
• reflection5.txt  

Evaluation checklist  
✔ Patterns work exactly as described.  
✔ No duplicated movement code inside Enemy.  
✔ Loader skips bad pattern names without terminating.  
✔ Enemies keep moving even when the player does nothing.  

Suggested schedule: ½ day design → 1 day coding → ½ day manual tests.  

──────────────────────────────────────────  
Exercise 6 – “Finish Line & Personal Touch” (≈550 words)  
Theme: Collision, Game-State Management & Creative Extension  

Why this exercise?  
This is the capstone: you will glue every subsystem together, detect collisions, manage win/lose states, and add a small creative flourish of your own. The new material is limited—Axis-Aligned Bounding Boxes and a simple finite-state machine—well inside CS1 reach yet enough to feel like a *real* game.

Learning outcomes  
• Implement AABB collision detection.  
• Orchestrate multiple interacting objects inside a main loop.  
• Extend existing code creatively without breaking design discipline.

Starter scaffold additions  

GameState.java – NEW  
```java
public enum GameState { RUNNING, WON, LOST, QUIT }
```

Game.java (extra member + snippet)  
```java
private GameState state = GameState.RUNNING;
private int playerHealth = 3;            // simple life system

private void update() throws IOException {
    if (state != GameState.RUNNING) return;   // freeze when not running

    /* 1. Handle input */
    if (System.in.available() > 0) {          // non-blocking read
        char key = (char) System.in.read();
        if (key == 'q' || key == 'Q') state = GameState.QUIT;
        else player.move(key);
    }

    /* 2. Update enemies */
    for (Enemy e : enemies) e.update();

    /* 3. Collision check – TODO by you */
    /* 4. Win/lose logic – TODO by you    */
}
```

Collision helper (static method suggestion)  
```java
static boolean overlaps(Enemy e, Player p) {
    return e.right()  > p.left()  &&
           e.left()   < p.right() &&
           e.bottom() > p.top()   &&
           e.top()    < p.bottom();
}
```

Tasks  
1. Axis-Aligned Bounding Box (AABB) Detection  
   • Give Player the same helper methods (`left()`, `right()`, `top()`, `bottom()`).  
   • Implement `overlaps()` (or an equivalent instance method).  
   • Inside `update()` loop through a *copy* of the enemy list (or use an iterator) to avoid `ConcurrentModificationException`.  
   • On collision:  
     a. Add the enemy’s point value to the score.  
     b. Remove the enemy from the game.  
     c. Decrement `playerHealth`.  

2. Game-State Machine  
   • When `playerHealth` drops below 1 → `state = GameState.LOST`.  
   • When `enemies.isEmpty()`          → `state = GameState.WON`.  
   • When `state != RUNNING` show a one-time final screen in `render()` with  
     “You Win” or “Game Over” + final score. Pressing ‘q’ should always exit.  

3. Creative Twist (choose ONE, or propose another to your TA):  
   a. Power-Ups: 5 % chance per second to spawn a ‘*’ on a random free cell; pick-up gives 5 s invulnerability.  
   b. Multiple enemy sizes: add width/height fields read from CSV; update AABB accordingly.  
   c. Simple menu: at program start display a title screen with options “(P)lay, (H)elp, (Q)uit” and switch states accordingly.  
   Scope guard: your twist should be ≤150 lines of *new* code.  

4. Refinement pass (polish)  
   • Clear separation: logic in `update()`, printing in `render()`.  
   • All array/list modifications are iterator-safe.  
   • No `magic numbers`—make constants (e.g., `MAX_HEALTH`).  

5. README update (≤180 words) explaining:  
   • Which creative twist you implemented.  
   • How to operate the game (keys, quit command, etc.).  
   • Any known limitations or bugs you did **not** fix and why.

Deliverables  
• Updated Player.java, Enemy.java, Game.java, GameState.java  
• Any new classes/files for the creative twist  
• Updated enemies.csv (if your twist adds new columns)  
• README.md (updated)  
• Short video or animated GIF (<15 s) OR two screenshots: gameplay + final screen  

Evaluation checklist  
✔ Collision works for every enemy on screen.  
✔ Game transitions correctly among RUNNING, WON, LOST, QUIT.  
✔ No runtime exceptions after 2 minutes of continuous play.  
✔ Creative twist fits within the stated scope and does not break base features.  
✔ Code remains CS1-friendly (no reflection, no external libraries).  

Time budgeting suggestion  
Day 1: Implement & test AABB.  
Day 2: Add win/lose logic + health HUD.  
Day 3-4: Develop and debug the creative twist.  
Day 5: Play-test, hunt edge cases.  
Day 6: Polish rendering/UI.  
Day 7: Record media, final README, final push.  

Congratulations—complete these two exercises and you will have designed, built, and polished a fully data-driven mini-arcade game from scratch! 🚀