# Pit-Stop Panic 🏎️💥

## 📋 Overview
Imagine you’re the strategist for an autonomous Formula-1 team. Your AI driver is blisteringly fast, but its tires degrade with every lap. Box too early and you lose time; stay out too long and—BOOM—the tire explodes, ending the race. In this mini-project you will build the core logic for a tire-management game where players (or another AI) must decide the perfect moment to pit before catastrophe strikes.

To make the simulation realistic, your program will read live “telemetry” files containing tire temperature, pressure, and wear percentages, then turn those numbers into Java objects that model the tires, the car, and the pit crew. Clever class design will let you extend the game later with new track types, weather systems, or even multiplayer competition. By the end, you’ll have a playable console prototype that showcases both solid software engineering and your own creative flair.

## 🎯 Learning Objectives
By completing this task, you will:
- Read structured data files (CSV/JSON) and use their contents to instantiate Tire, Car, and Race objects.
- Design cohesive, well-encapsulated classes that separate game state, I/O, and presentation logic.
- Exercise creative programming by inventing game mechanics (e.g., scoring, random events) and user feedback.
- Handle I/O exceptions gracefully to maintain data integrity and game stability.
- Document and test your code so that new teammates can extend it easily.

## 📚 Prerequisites
Before starting this task, you should be familiar with:
- Java classes, objects, and basic inheritance
- File I/O using java.io or java.nio (e.g., Scanner, Files API)
- Exception handling (try/catch)
- An IDE such as IntelliJ or VS Code with the Java extension

## 🚀 Getting Started
1. Download the starter repository from the course LMS (folder: `pit-stop-panic`).
2. Open the project in your IDE and run `Main.java`; you should see a simple welcome banner.
3. Inspect the provided sample data file `stint_01.csv`. No code changes yet—just get a feel for the format.
4. Create a new branch called `exercise-1`.

## Exercises

### Exercise 1: From Rubber to Data 📖
**⏱️ Estimated Time:** 20-30 minutes

**🎯 Goal:** Understand how raw telemetry can map to object attributes you’ll model in code.

**📝 Instructions:**
Answer the following questions in `EX1_answers.md` (no coding yet):
1. Open `stint_01.csv`. Identify at least four distinct tire metrics and describe what real-world phenomenon each represents.
2. Which of these metrics would you track per tire corner (FL, FR, RL, RR) versus per car? Justify your reasoning.
3. Propose a Java data type (int, double, enum, etc.) for each metric and explain why it is appropriate.
4. Consider corrupted data: give two realistic examples and outline how your program should respond without crashing.

**💡 Hints:**
<details>
<summary>Click for hint</summary>

Think about how doctors monitor vital signs. Blood pressure, heart rate, and temperature each require different units and acceptable ranges—tires are similar! Also, imagine what happens if a sensor briefly fails: will you discard the whole file or skip that line?

</details>

**✅ Success Criteria:**
- [ ] All four questions answered clearly with correct technical vocabulary
- [ ] Each metric paired with a justified Java data type
- [ ] At least two thoughtful strategies for handling corrupted data

### Exercise 2: Blueprinting the Pit Lane 🔍
**⏱️ Estimated Time:** 30-40 minutes

**🎯 Goal:** Translate your conceptual understanding into a robust class design that will guide implementation.

**📝 Instructions:**
1. Draw (hand-sketch or use a tool) a UML-style diagram featuring at least these classes: `Tire`, `Car`, `TelemetryReader`, and `RaceManager`. Indicate relationships (composition, aggregation, dependency).
2. For each class, list primary fields and methods in `EX2_design.md`. Include visibility modifiers (+/–).
3. Identify where in your design the Open/Closed Principle could apply if you later add a `WeatherSystem`.
4. Write brief pseudocode in `EX2_pseudocode.md` illustrating how `RaceManager` will:
   a. Read one telemetry line  
   b. Update `Car` and `Tire` objects  
   c. Decide whether to pit  

You may embed or link your diagram image.

**💡 Hints:**
<details>
<summary>Click for hint</summary>

Aim for single responsibility: `TelemetryReader` should know how to parse files, not how to decide strategy. Beware of circular dependencies—use listeners or callbacks if needed.

```java
public interface Strategy {
    boolean shouldPit(Car car);
}
```

This tiny interface can decouple decision logic from the core simulation.

</details>

**✅ Success Criteria:**
- [ ] UML diagram includes correct relationships and visibility
- [ ] Fields/methods align with responsibilities discussed in Exercise 1
- [ ] Pseudocode demonstrates a clear, linear flow from I/O to decision-making

### Exercise 3: Bringing Tires to Life 🏗️  
⏱️ Estimated Time: 45-60 minutes  

🎯 Goal: Implement the core data classes (`Tire` and `TelemetryReader`) so your program can transform one line of CSV telemetry into four fully-initialized `Tire` objects.

📝 Instructions:  
1. Locate `src/model/Tire.java` and `src/io/TelemetryReader.java`. Both files contain TODOs.  
2. In `Tire.java`  
   a. Add the private fields you decided on in Exercise 2 (e.g., wear, temperature, pressure, position).  
   b. Write a full constructor that validates its arguments (throw `IllegalArgumentException` for impossible values).  
   c. Implement getters only—no setters for now; tires shouldn’t mutate themselves directly.  
   d. Override `toString()` for easy debugging.  
3. In `TelemetryReader.java`  
   a. Finish the `Tire[] parseLine(String csv)` method so it  
      • splits the line,  
      • converts tokens to the correct Java types, and  
      • returns an array of four tires in the order FL, FR, RL, RR.  
   b. Handle malformed numbers with `try/catch` and return `null` if the entire line is unusable.  
4. Open `Main.java`, uncomment the demo block, and run. You should see a printed summary for lap 1 that matches the first data row in `stint_01.csv`.

Starter code (excerpt)  
```java
// src/model/Tire.java
package model;

public class Tire {
    // TODO: Add fields (e.g., private double wear;)
    
    // TODO: Implement constructor with validation
    
    // TODO: Add getters
    
    @Override
    public String toString() {
        // TODO: Friendly string for debugging
        return "";
    }
}

// src/io/TelemetryReader.java
package io;

import model.Tire;
import java.util.*;

public class TelemetryReader {
    // Delimiter is semicolon so Excel doesn't get angry ;)
    private static final String DELIM = ";";

    public Tire[] parseLine(String csv) {
        // TODO: Split, validate length, convert, build Tire objects
        return null;
    }
}
```

💡 Hints:  
<details>
<summary>Click for hint</summary>

• Java’s `Double.parseDouble()` throws `NumberFormatException`—catch it!  
• Consider creating an `enum Position {FL, FR, RL, RR}` inside `Tire` for clarity.  
• Validation example: wear must be between 0 and 100 %, temperature between 20 °C and 140 °C.

```java
if (wear < 0 || wear > 100) {
    throw new IllegalArgumentException("Wear out of range: " + wear);
}
```
</details>

✅ Success Criteria:  
- [ ] Constructor rejects invalid data via exceptions  
- [ ] `TelemetryReader.parseLine()` returns a non-null `Tire[]` for valid input  
- [ ] Demo block prints four sensible `Tire` objects matching `stint_01.csv` row 1  
- [ ] No uncaught exceptions on bad data rows (they are skipped gracefully)

---

### Exercise 4: Meet the Car & Crew 🔄  
⏱️ Estimated Time: 45-60 minutes  

🎯 Goal: Integrate `Car` and `RaceManager` so your simulation can process an entire stint file and decide when to pit based on simple rules.

📝 Instructions:  
1. Complete `src/model/Car.java`  
   a. Store four `Tire` references, current fuel (double), and current lap (int).  
   b. Provide `updateTires(Tire[] newData)` and `boolean needsPitStop()` methods.  
      • Rule: pit if ANY tire’s wear ≥ 70 % OR temperature ≥ 115 °C.  
2. Implement `src/control/RaceManager.java`  
   a. Give it a `TelemetryReader`, a `Car`, and a reference to the CSV file to stream.  
   b. In `startRace()`, read the file line by line, update the car, print a one-line dashboard, and break when `needsPitStop()` returns true.  
   c. After the loop, print either “PIT NOW!” or “Stint completed safely.”  
3. In `Main.java` wire everything together and let the race begin.  

💡 Hints:  
<details>
<summary>Click for hint</summary>

• Use `java.nio.file.Files.lines(Path)` inside a try-with-resources block for a sleek file loop.  
• Keep the dashboard minimal for now—lap, four wear % values, pit decision.  

```java
System.out.printf("Lap %2d | Wear: %.1f/%.1f/%.1f/%.1f | %s%n",
        car.getLap(),
        car.getTire(Position.FL).getWear(),
        ...
        car.needsPitStop() ? "PIT" : "OK");
```
</details>

✅ Success Criteria:  
- [ ] `Car` correctly updates tires and lap count  
- [ ] `needsPitStop()` implements the given rule  
- [ ] `RaceManager` streams the entire file without loading it all in memory  
- [ ] Console output clearly shows when the pit decision is triggered  
- [ ] Exceptions from file I/O are caught and reported

---

### Exercise 5: Stint Saver & Replay 🚀  
⏱️ Estimated Time: 60-90 minutes  

🎯 Goal: Persist race decisions to a new file and replay them, practising both reading and writing files plus richer error handling.

📝 Instructions:  
Part A – Logging  
1. Add `src/io/DecisionLogger.java`. Its job: append one CSV line per lap in the format  
   `lap;wearFL;wearFR;wearRL;wearRR;pitDecision`  
2. Enhance `RaceManager` so it receives a `DecisionLogger` instance and calls `logLap()` inside the loop. Flush the writer every 5 laps.  

Part B – Replay  
3. Create `src/control/StintReplayer.java` that reads a decision log and reconstructs the lap-by-lap state, printing ONE summary line:  
   “Replayed 23 laps – pitted on lap 17 – no I/O errors.”  
4. Allow the user to run either “race” or “replay” mode via a command-line arg in `Main.java`.  

💡 Hints:  
<details>
<summary>Click for hint</summary>

File opening pattern:

```java
try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(filename),
        StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
    // write
} catch (IOException e) {
    System.err.println("Could not write log: " + e.getMessage());
}
```

Validate every numeric token with `Double.parseDouble`, but on replay you can downgrade bad lines to warnings and continue.
</details>

✅ Success Criteria:  
- [ ] Decision log created with one line per processed lap  
- [ ] File flushed at least every 5 laps  
- [ ] Replay mode summarises laps and pit lap accurately  
- [ ] All I/O exceptions handled without program crash  
- [ ] Code is commented and uses meaningful variable names

---

### Exercise 6: Choose-Your-Own Feature 🌟  
⏱️ Estimated Time: 60-90 minutes  

🎯 Goal: Add a creative extension that demonstrates ownership of the project and solid class design.

📝 Instructions: Pick ONE option and implement it. Whichever path you choose, include full Javadoc for every new public class and method.

Option A: Weather Wizard  
• Introduce a `WeatherSystem` that randomly changes track temperature every lap.  
• Tire temperature should drift toward track temp by 10 % each lap.  
• Update pit rules or add a wet-tire compound for rain!

Option B: Strategy Interface Showdown  
• Implement `Strategy` as an interface with two concrete classes: `ConservativeStrategy` and `AggressiveStrategy`.  
• Let the user pick strategy at runtime; print who wins a simulated 50-lap race (fewest pit stops vs. fastest average lap).  

Option C: GUI Dashboard (Swing)  
• Replace console output with a minimal Swing window showing lap number, tire wear bars, and a blinking “PIT” icon.  
• Must refresh at least twice per second without blocking the file-reading thread (use SwingUtilities.invokeLater).

💡 Hints:  
<details>
<summary>Click for hint</summary>

• Keep new classes separate from existing ones—use interfaces or observers to avoid tight coupling.  
• For Swing, never update components from a non-EDT thread.  

```java
/**
 * Updates the on-screen tire bars.
 * @param car current state of the car
 */
public void refresh(Car car) {
    // repaint
}
```
</details>

✅ Success Criteria:  
- [ ] Feature fully implemented and integrated with existing code  
- [ ] Clear, complete Javadoc for all new public APIs  
- [ ] No regression: original console race still works if GUI/feature disabled  
- [ ] Code passes provided unit tests (`mvn test` or `gradle test`)  
- [ ] Creativity shines—something novel or polished in your implementation

---

## 🎉 Submission Checklist

Before submitting, ensure you have:  
- [ ] Completed Exercises 1-6  
- [ ] No TODO comments remain in committed code  
- [ ] `README.md` explains how to run race, replay, and your creative feature  
- [ ] All new classes include Javadoc header comments  
- [ ] Program exits gracefully on bad filenames or corrupt data  
- [ ] Decision log file stored in `logs/` and ignored by Git (.gitignore)  
- [ ] UML diagram updated if your creative feature added classes  
- [ ] Unit tests pass (`mvn test` / `gradle test`)  
- [ ] Code formatted with the course style guide

---

## 🤔 Reflection Questions

1. How did separating I/O, domain logic, and presentation help (or hinder) your development speed?  
2. Which validation checks in your code prevent the most serious in-race bugs, and why?  
3. Describe a real motorsport or manufacturing scenario where your software architecture could be reused. What changes would be necessary?

---

## 📚 Additional Resources

• Java I/O Tutorial (Oracle): https://docs.oracle.com/javase/tutorial/essential/io/  
• Clean Code Chapter 3 “Functions” (for short, readable methods)  
• Basics of UML Class Diagrams (Visual Paradigm short guide)  
• Video: “How F1 Teams Use Data Analytics” (YouTube, 8 min)