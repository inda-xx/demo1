**🎮 Weekly Programming Challenge: Build Your Own 2D Arcade Game in Java!**

Welcome to your most exciting challenge yet! This week, you’ll be designing and implementing a simple 2D arcade-style game using Java. The game will include a player-controlled character, enemies, points to collect, and a scoring system. You will apply object-oriented principles, read game data from files, and creatively solve problems to make your game functional and fun to play.

Your game should include the following key features:
- **Player Movement and Controls**: Allow the player to navigate the game space using keyboard input.
- **Scoring System**: Track and display the player’s score based on actions like collecting items or defeating enemies.
- **Enemy Interactions**: Design enemy behavior and determine how they interact with the player.
- **Game State Management**: Manage different states like "Start", "Playing", "Game Over", and potentially "Pause".
- **Basic Collision Detection**: Detect when the player collides with an enemy or collectible item.

Over the course of this week, you will complete **6 exercises** that gradually guide you through the theoretical foundations and practical implementation of your game. Each exercise contributes directly to your final game project, so make sure to complete them in order!

---

### 🧠 Exercise 1: Game World Design Document

**Objective**: Define the structure and rules of your game in a written design document.

**Instructions**:
- Write a short description of your game concept (1-2 paragraphs).
- Define:
  - The player’s goal
  - How enemies behave
  - How the scoring system works
  - When the game ends
- Discuss how the game will transition between different states (e.g., Start → Playing → Game Over).

**Focus Concepts**:
- Programming Creatively
- Designing Classes (planning responsibilities)

**Deliverable**: A 1-2 page game design document (PDF or .txt) that outlines the core mechanics and logic.

---

### 🧱 Exercise 2: Class Blueprinting 🧾

**Objective**: Plan your object-oriented design before you start coding.

**Instructions**:
- Identify the main classes you will need. (Hint: Player, Enemy, Game, GameState, ScoreManager, etc.)
- For each class, define:
  - Its attributes (fields)
  - Its responsibilities (methods)
  - How it interacts with other classes

**Starter Template**:
```java
public class Player {
    private int x, y;
    private int score;

    public void move(String direction) {
        // Update x and y based on direction
    }

    public void draw(Graphics g) {
        // Render the player
    }
}
```

**Focus Concepts**:
- Designing Classes (cohesion, clear interfaces)

**Deliverable**: A UML-style diagram or text document listing your class designs.

---

### 🎮 Exercise 3: Reading Game Entities from File 📂

**Objective**: Load enemy and item data from a file to populate your game world dynamically.

**Instructions**:
- Create a text file (`entities.txt`) that contains data for enemies and collectible items. For example:
  ```
  ENEMY 100 200 ZIGZAG
  ENEMY 300 400 FOLLOW
  ITEM 250 250 COIN
  ```
- Write a loader class that reads this file and instantiates the appropriate objects.

**Starter Template**:
```java
public class EntityLoader {
    public static List<GameEntity> loadEntities(String filename) {
        List<GameEntity> entities = new ArrayList<>();
        // Use Scanner or BufferedReader to read each line
        // Parse each line and create appropriate objects
        return entities;
    }
}
```

**Focus Concepts**:
- Using Data from Files to Instantiate Objects (file format, parsing, exception handling)

**Deliverable**: Java class that loads at least two different types of game entities from a file.

---

### 🕹️ Exercise 4: Player Movement and Scoring System 🧍➕🏆

**Objective**: Implement player movement with keyboard input and a simple scoring system.

**Instructions**:
- Use `KeyListener` or `KeyBindings` to control the player.
- Add a `ScoreManager` class to manage and display the player's score.
- Increase score when the player collects an item.

**Starter Template**:
```java
public class ScoreManager {
    private int score;

    public void addPoints(int points) { score += points; }
    public int getScore() { return score; }

    public void draw(Graphics g) {
        g.drawString("Score: " + score, 10, 20);
    }
}
```

**Focus Concepts**:
- Designing cohesive classes
- Basic creative programming and interaction

**Deliverable**: A working player character that can move and collect points.

---

### ⚔️ Exercise 5: Enemies and Collision Detection ☠️⚡

**Objective**: Add enemies that move and interact with the player via collision detection.

**Instructions**:
- Implement at least two enemy types with different behaviors (e.g., random movement, chasing player).
- Detect collisions between player and enemies.
- End the game or decrease score/health on collision.

**Starter Template**:
```java
public boolean isColliding(GameEntity a, GameEntity b) {
    Rectangle rectA = new Rectangle(a.getX(), a.getY(), a.getWidth(), a.getHeight());
    Rectangle rectB = new Rectangle(b.getX(), b.getY(), b.getWidth(), b.getHeight());
    return rectA.intersects(rectB);
}
```

**Focus Concepts**:
- Basic collision detection
- Using class hierarchies (e.g., GameEntity superclass)
- Creative problem-solving for enemy behavior

**Deliverable**: Functional enemies with interaction logic and collision detection.

---

### 🧩 Exercise 6: Game State Manager and Final Assembly 🔁🧠

**Objective**: Implement a `GameStateManager` and bring all components together.

**Instructions**:
- Implement game states: START, PLAYING, GAME_OVER.
- Display appropriate screens based on state.
- Allow restart after game over.
- Organize your code in a `Game` class that integrates all components.

**Starter Template**:
```java
public enum GameState {
    START, PLAYING, GAME_OVER
}

public class GameStateManager {
    private GameState currentState;

    public void update() {
        switch (currentState) {
            case START:
                // Show title screen
                break;
            case PLAYING:
                // Run game loop
                break;
            case GAME_OVER:
                // Show final score
                break;
        }
    }
}
```

**Focus Concepts**:
- Designing Classes (state management)
- Programming Creatively (user experience, game polish)
- Integration of file-based data loading

**Deliverable**: Your fully functional game!

---

### ✅ Weekly Submission Checklist:

- [ ] Exercise 1: Game Design Document
- [ ] Exercise 2: Class Blueprints
- [ ] Exercise 3: File-based Entity Loading
- [ ] Exercise 4: Player and Scoring
- [ ] Exercise 5: Enemies

Certainly! Below are the final versions of **Exercise 1** and **Exercise 2** tailored for first-year CS students. These focus on theoretical and conceptual understanding aligned with the learning goals, and set the stage for the more hands-on coding exercises to follow.

---

## 🧠 Exercise 1: Game World Design Document 🎨

**Objective**: Plan your game creatively and define its core mechanics and rules.

Before writing any code, it’s essential to think like a game designer. This exercise challenges you to imagine the world you’re building and define how it behaves. You’ll write a short design document that outlines the purpose, key systems, and flow of your game.

### ✍️ Instructions:
Create a 1–2 page document (PDF or plain text) addressing the following:

1. **Game Concept (1–2 paragraphs)**  
   Describe the theme and setting of your game. Is it a space adventure? A haunted maze? A jungle treasure hunt?

2. **Define Game Mechanics**  
   - What is the player’s goal?
   - What types of enemies or hazards exist, and how do they behave?
   - How does the player earn or lose points?
   - What causes the game to end?

3. **Game States**  
   Describe how the game transitions between the following states:
   - Start Screen
   - Playing
   - Game Over
   - (Optional) Pause or Level Complete

### 🧠 Conceptual Questions:
- What makes a game fun or engaging?
- How can program design reflect real-world systems (e.g., scoreboards, enemy AI)?
- Why is it important to plan a game before coding it?

### 🎯 Learning Goals:
- **Programming Creatively**: Think innovatively about game mechanics.
- **Designing Classes (Planning)**: Begin identifying logical components and their responsibilities.

### ✅ Deliverable:
Submit a 1–2 page document (.pdf or .txt) that clearly communicates your game idea and design decisions. You’ll refer back to this as you build your game, so make it detailed but clear.

---

## 🧱 Exercise 2: Class Blueprinting 🧾

**Objective**: Analyze and design your game’s structure using object-oriented principles.

Now that you’ve planned your game world, it’s time to think about its underlying architecture. This exercise guides you in outlining the key classes that will drive your game’s logic and behavior.

### ✍️ Instructions:

1. **List Your Core Classes**  
   Identify at least 4–6 main classes. Common examples include:
   - `Player`
   - `Enemy`
   - `Game`
   - `GameStateManager`
   - `ScoreManager`
   - `Item` or `Collectible`

2. **For Each Class, Define**:
   - Fields (attributes): What data does this class store?
   - Methods (behaviors): What does this class do?
   - Relationships: Which other classes does it interact with?

3. **Optional**: Draw a simple UML-style diagram or write a bullet-point version of your design.

### 🧠 Conceptual Questions:
- What makes a class well-designed (think: single responsibility, cohesion)?
- How does object-oriented thinking help you organize game behavior?
- What are the advantages of planning class interactions early?

### 🔍 Example (Text Format):
```
Class: Player
Fields: x, y, score, speed
Methods: move(), draw(), collectItem()
Interacts with: Game, Item, Enemy
```

### 🎯 Learning Goals:
- **Designing Classes**: Practice defining responsibilities and understanding interaction between components.
- **Programming Creatively**: Structure your game in a way that supports your unique design.

### ✅ Deliverable:
Submit a document (.pdf or .txt) that outlines your planned classes, their key attributes and methods, and how they interact. This will serve as your roadmap for coding in future exercises.

---

These exercises set the stage for a deeper understanding of both object-oriented design and creative programming while staying within a manageable scope for first-year students.

Absolutely! Below are thoughtfully crafted versions of **Exercise 3** and **Exercise 4**, designed to transition students smoothly from their theoretical planning (Exercises 1–2) into hands-on Java coding. These exercises build upon their class blueprints and game design document, while introducing file I/O, object instantiation, and user interaction in a manageable and pedagogically sound way.

---

## 📂 Exercise 3: Loading Game Entities from a File

**Objective**: Learn how to load game data dynamically from a text file and instantiate appropriate objects in Java.

In real-world games and applications, data often comes from external sources such as files or databases. In this exercise, you will simulate that by loading your game’s enemies and collectible items from a text file and turning that data into real, interactive game objects.

---

### 🧭 Instructions:

1. **Create an Entity Data File**
   - Create a file called `entities.txt` in your project’s root or `resources` directory.
   - Each line in the file should represent either an enemy or an item, e.g.:
     ```
     ENEMY 100 200 ZIGZAG
     ENEMY 300 400 FOLLOW
     ITEM 250 250 COIN
     ITEM 150 100 GEM
     ```

2. **Design a Base Class for Game Entities**
   - Create an abstract or base class called `GameEntity`.
   - Define common fields: `x`, `y`, `type` (e.g., "ENEMY" or "ITEM").
   - Add methods like `draw(Graphics g)` and `update()` as needed.

3. **Create Specific Subclasses**
   - Create at least two subclasses: `Enemy` and `Item`.
   - Add fields for special behaviors (e.g., enemy movement type, item value).
   - Implement basic constructors and methods.

4. **Implement the EntityLoader Class**
   - Write a class called `EntityLoader` with a static method `loadEntities(String filename)`.
   - Use `Scanner` or `BufferedReader` to read each line of the file.
   - Parse the data and create the appropriate subclass (`Enemy` or `Item`) based on the first word in each line.
   - Store these objects in a `List<GameEntity>` and return it.

---

### 🧰 Starter Scaffolding:

```java
public abstract class GameEntity {
    protected int x, y;
    protected String type;

    public GameEntity(int x, int y, String type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public abstract void draw(Graphics g);
    public abstract void update();
}
```

```java
public class EntityLoader {
    public static List<GameEntity> loadEntities(String filename) {
        List<GameEntity> entities = new ArrayList<>();
        // TODO: Read file, parse lines, create objects
        return entities;
    }
}
```

---

### 🧠 Conceptual Questions:

- Why is reading from a file useful in games and applications?
- What are the risks when loading external data (e.g., malformed lines or missing files)?
- How can inheritance help organize different types of game entities?

---

### 🎯 Learning Goals:

- ✅ Using Data from Files to Instantiate Objects
- ✅ Understanding file parsing and exception handling
- ✅ Applying class hierarchies in practice

---

### ✅ Deliverable:

Submit the following:
- Your `entities.txt` file with at least 4 entities
- `GameEntity`, `Enemy`, and `Item` class implementations
- `EntityLoader` class that correctly populates a list from the file

---

## 🕹️ Exercise 4: Player Movement & Scoring System

**Objective**: Implement basic player movement using keyboard input and introduce a scoring system that updates as the player interacts with game items.

Now that your game world can be populated from files, it's time to bring your player to life! In this exercise, you'll implement movement controls and a dynamic scoring system.

---

### 🧭 Instructions:

1. **Create the Player Class**
   - Use your blueprint from Exercise 2.
   - Implement movement using `KeyListener` or `KeyBindings` (recommended for smoother behavior).
   - Restrict movement to within screen boundaries.

2. **Implement the ScoreManager**
   - Create a `ScoreManager` class to manage and track points.
   - Add methods like `addPoints(int amount)`, `getScore()`, and `draw(Graphics g)`.

3. **Detect Interaction with Items**
   - If the player "touches" an item (basic bounding box collision), remove the item and award points.
   - You can simplify collision by comparing the player’s and item’s x/y coordinates and width/height.

4. **Display the Score**
   - Use the `Graphics` object to display the score on the screen.

5. **Optional Challenge**: Add sound or visual feedback when an item is collected.

---

### 🧰 Starter Scaffolding:

```java
public class Player {
    private int x, y, speed;
    private int width, height;

    public void move(String direction) {
        // TODO: Update player position based on direction
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, width, height);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
```

```java
public class ScoreManager {
    private int score = 0;

    public void addPoints(int points) {
        score += points;
    }

    public void draw(Graphics g) {
        g.drawString("Score: " + score, 10, 20);
    }
}
```

---

### 🧠 Conceptual Questions:

- How does separating responsibilities (e.g., score vs. movement) improve your code?
- What are the tradeoffs between different input-handling approaches (`KeyListener` vs `KeyBindings`)?
- How could this be extended to support player health or lives?

---

### 🎯 Learning Goals:

- ✅ Designing cohesive classes for different responsibilities
- ✅ Using object interaction (Player <-> Item)
- ✅ Introducing user input for dynamic interaction

---

### ✅ Deliverable:

Submit:
- Fully functional `Player` class with movement
- `ScoreManager` class
- Code that detects collisions between Player and Items
- Screenshot or short screen recording (optional but encouraged!)

---

These two exercises serve as a crucial bridge: students move from abstract design into applied, hands-on programming, while still keeping the complexity manageable. By the end of Exercise 4, students will have a playable, interactive prototype that lays the foundation for enemy AI and game state management in Exercises 5 and 6.

Absolutely! Below are the final two exercises — **Exercise 5: Enemies and Collision Detection**, and **Exercise 6: Game State Manager and Final Assembly** — carefully designed to be challenging yet manageable for first-year computer science students. These tasks consolidate all prior concepts and provide scaffolding to ensure students can succeed while stretching their understanding just slightly beyond the CS1 curriculum.

---

## ⚔️ Exercise 5: Enemies and Collision Detection ☠️⚡

**Objective**: Implement enemy behavior and handle collision detection between game entities.

In this exercise, you’ll bring your game to life by adding enemies that move around the game world and interact with the player. You’ll also implement collision detection so that enemies can affect the player (e.g., ending the game or reducing their score), and further refine your object-oriented design by leveraging class hierarchies and polymorphism.

---

### 🧭 Instructions:

1. **Enhance the Enemy Class**
   - Use your `Enemy` class from Exercise 3.
   - Add a `behaviorType` field (e.g., `"ZIGZAG"`, `"FOLLOW"`) loaded from your entity file.
   - Implement at least two movement behaviors:
     - `"ZIGZAG"`: Moves left-to-right and down in a zigzag pattern.
     - `"FOLLOW"`: Moves toward the player’s current position.
   - Add a `move(Player player)` method to update the enemy’s position.

2. **Implement Collision Detection**
   - Use bounding box collision logic to check if the player has collided with any enemy.
   - If a collision is detected:
     - End the game (or)
     - Reduce the player’s score (or)
     - Trigger a “Game Over” condition

3. **Update the Game Loop**
   - In your main game loop or update method:
     - Update each enemy’s position using its behavior.
     - Check for collisions between the player and each enemy.

4. **Visual Feedback**
   - Change the enemy color or display a message when a collision occurs.
   - Optional: Add a “health” system or knockback effect.

---

### 🧰 Starter Scaffolding:

```java
public class Enemy extends GameEntity {
    private String behaviorType;
    private int speed = 2;

    public Enemy(int x, int y, String behaviorType) {
        super(x, y, "ENEMY");
        this.behaviorType = behaviorType;
    }

    public void move(Player player) {
        if (behaviorType.equals("ZIGZAG")) {
            // TODO: Implement zigzag movement
        } else if (behaviorType.equals("FOLLOW")) {
            // TODO: Move toward player’s x, y
        }
    }

    public void update(Player player) {
        move(player);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 30, 30);
    }
}
```

```java
public boolean isColliding(GameEntity a, GameEntity b) {
    Rectangle rectA = new Rectangle(a.getX(), a.getY(), 30, 30);
    Rectangle rectB = new Rectangle(b.getX(), b.getY(), 30, 30);
    return rectA.intersects(rectB);
}
```

---

### 🧠 Conceptual Questions:

- How does polymorphism help you implement different enemy behaviors?
- Why is collision detection important in interactive programs?
- How would you extend this system to support enemy damage or player health?

---

### 🎯 Learning Goals:

- ✅ Use class hierarchies and polymorphism
- ✅ Implement basic enemy AI
- ✅ Perform collision detection and respond to game events

---

### ✅ Deliverable:

Submit:
- Updated `Enemy` class with at least two behaviors
- Collision detection logic
- Evidence that collisions affect gameplay (e.g., game over screen, score change)
- Optional: Screenshot or short video demo

---

## 🧩 Exercise 6: Game State Manager & Final Game Assembly 🔁🧠

**Objective**: Implement a clean game state management system and combine all components into a playable, polished experience.

This is your final and most exciting challenge: combine all your hard work into a fully functioning 2D arcade game! You'll use a state manager to control transitions between the start screen, gameplay, and game over screen. You'll also polish your game architecture so it can be reused and extended in the future.

---

### 🧭 Instructions:

1. **Implement Game States**
   - Create an enum `GameState` with at least three states:
     - `START`
     - `PLAYING`
     - `GAME_OVER`
   - Create a `GameStateManager` class to manage the current state.
   - Add methods like `startGame()`, `endGame()`, and `restartGame()`.

2. **Update Game Loop**
   - In your main update/draw loop:
     - If `START`: Show title screen, wait for key press to begin.
     - If `PLAYING`: Run full game logic (player, items, enemies, collisions).
     - If `GAME_OVER`: Show final score and restart option.

3. **Integrate All Components**
   - Ensure the following components work together:
     - Entity loading from file
     - Player movement and scoring
     - Enemy behavior and collisions
     - Game state transitions

4. **Polish and Test**
   - Add visual feedback for game states (e.g., “Press SPACE to start”, “You Win!” or “Game Over”).
   - Optional: Add sound effects or background music (bonus).
   - Optional: Add a "Pause" state or basic level progression.

---

### 🧰 Starter Scaffolding:

```java
public enum GameState {
    START, PLAYING, GAME_OVER
}
```

```java
public class GameStateManager {
    private GameState currentState = GameState.START;

    public GameState getCurrentState() { return currentState; }

    public void startGame() { currentState = GameState.PLAYING; }

    public void endGame() { currentState = GameState.GAME_OVER; }

    public void restartGame() { currentState = GameState.START; }
}
```

```java
public class Game {
    private GameStateManager stateManager = new GameStateManager();

    public void update() {
        switch (stateManager.getCurrentState()) {
            case START:
                // Show start screen
                break;
            case PLAYING:
                // Update game objects
                break;
            case GAME_OVER:
                // Display final score
                break;
        }
    }

    public void draw(Graphics g) {
        // Draw based on state
    }
}
```

---

### 🧠 Conceptual Questions:

- Why is a state machine helpful in managing game flow?
- How did your object-oriented design help you stay organized?
- If you added a new state (like “Paused” or “LevelComplete”), how would you do it?

---

### 🎯 Learning Goals:

- ✅ Implement game state transitions using enums and control structures
- ✅ Integrate modular components into a cohesive system
- ✅ Apply creative thinking to polish and finalize your game

---

