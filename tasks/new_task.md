# Pit-Lane Strategist 🏎️💨  
*A one–week mini-sprint for first-year CS students*

---

## 📋 Overview
Formula 1 races are often won or lost in the pit-lane. Choosing the right moment to *“box, box, box!”* and fitting the optimal tyre compound can turn an average car into a race-winner.  

In this project you will build the first playable prototype of a simplified tyre-management game. You will

• read structured CSV files and turn each row into well-initialised Java objects (Exercises 1 & 3)  
• design a clean domain model and apply OO design patterns (Exercises 2, 4 & 5)  
• add a creative extension—dynamic weather (Exercise 6)

Each exercise builds directly on the previous one, so **complete them in order**.

> 💡 *Fun fact* – The fastest recorded F1 pit-stop is 1.82 s (Red Bull Racing, Brazil 2019).

---

## 🎯 Learning Objectives
The six exercises collectively cover all weekly learning goals:

1. **Using Data from Files to Instantiate Objects** – analyse (`Ex 1`), parse (`Ex 3`) and validate CSV data.  
2. **Designing Classes** – draft a UML model (`Ex 2`) and refactor it with the Strategy & (bonus) Observer patterns (`Ex 5–6`).  
3. **Programming Creatively** – extend the core simulation with weather effects and optional visualisations (`Ex 6`) while reflecting on UX choices.

---

## 📚 Prerequisites
• Basic Java (variables, loops, methods, packages, exceptions)  
• IDE of choice (IntelliJ IDEA, VS Code, Eclipse…)  
• JDK 17+ installed

---

## 🚀 Project Bootstrap
1. Create a new project **PitLaneStrategist**.  
2. Add starter packages:  
   ```
   strategist.data     // file loading helpers
   strategist.model    // core domain classes
   strategist.strategy // lap-time algorithms
   strategist.game     // main loop & UI
   ```
3. Download sample data: `data/compounds.csv`, `data/tracks.csv` (top-level folder, *outside* `src/`).  
4. Skim both files to see their structure.  
5. Read the high-level “Project Specification” PDF (no coding yet).

The stage is set—time to dive into the exercises 👇

---

# 🏁 Exercises

### Exercise 1 – Decode the Data File 🧐 (Conceptual Understanding)  
*Estimated 30–45 min*

Analyse the two CSV files and decide how they should map to Java objects while considering bad data scenarios.  
[Details unchanged – see original brief]

---

### Exercise 2 – Sketch the Pit-Lane Model ✏️ (Design & Analysis)  
*Estimated 30–45 min*

Draft a UML diagram containing the core classes and pick an initial design pattern you suspect will help later.  
Hint: Your diagram will be your blueprint for coding in **Exercises 3-6**.  
[Details unchanged]

---

### Exercise 3 – Parse & Populate 🛠️ (Building Foundations)  
*Estimated 45–60 min*

Turn the CSV rows explored in **Ex 1** into concrete `TireCompound` and `Track` objects.  
Note the scaffold already imports `strategist.model.*`, reinforcing your design from **Ex 2**.  
[Details unchanged – now explicitly references Ex 1 & Ex 2]

---

### Exercise 4 – Lap-Time Calculator ⏱️🧮 (Integration & Enhancement)  
*Estimated 45–60 min*

Combine the domain objects from **Ex 3** with new classes `TireSet` and `Car`.  
The wear logic stays inside `TireSet` to keep responsibilities crisp (a design reminder from **Ex 2**).  
[Details unchanged]

---

### Exercise 5 – Strategy Pattern Pit-Boss 🎯🔧 (Advanced Implementation)  
*Estimated 60–90 min*

Refactor lap-time calculation using the Strategy pattern and introduce `PitStopManager`.  
Re-use or adjust your UML from **Ex 2** to reflect the new pattern.  
[Details unchanged, now cross-references Ex 2]

---

### Exercise 6 – Weather Wizard 🌦️🎨 (Creative Extension)  
*Estimated 60–90 min*

Add a dynamic weather system that affects tyre wear and lap times.  
Optional challenges include implementing the Observer pattern—ideal if you marked Observer as useful back in **Ex 2**.  
[Details unchanged]

---

## 🔄 Smooth Transitions & Hints Map
• Ex 1 → Ex 2: Use the fields you identified to flesh out class responsibilities.  
• Ex 2 → Ex 3: Implement exactly the classes and constructors you sketched; this will minimise re-work.  
• Ex 3 → Ex 4: The lists returned by `CsvLoader` feed directly into the simulation loop.  
• Ex 4 → Ex 5: Replace the lap-time formula in `Car` with a pluggable strategy—start by copy-pasting then refactor.  
• Ex 5 → Ex 6: Weather factors are simply extra multipliers inside your **existing** strategy classes—extend, don’t rewrite.

---

## ✅ Final Checklist
Confirm each item **before** tagging your release `v0.1-milestone`.

- [ ] 1. `docs/` contains written answers for Exercises 1 & 2 plus the UML diagram (PNG, PDF or PlantUML).  
- [ ] 2. `CsvLoader` correctly parses both CSV files; JUnit test asserts 3 compounds, ≥1 track.  
- [ ] 3. All domain classes (`TireCompound`, `Track`, `TireSet`, `Car`) have sensible `toString()` and no public fields.  
- [ ] 4. Simulation loop in `strategist.game.Main` prints a lap-by-lap table and halts with **“PIT NOW!”** when appropriate.  
- [ ] 5. Strategy pattern is implemented (`LapTimeStrategy`, `LinearDegradationStrategy`, `ExponentialDegradationStrategy`).  
- [ ] 6. `PitStopManager` swaps tyre sets based on heuristic; unit test ensures ≤ 1 stop over 50 laps.  
- [ ] 7. Weather system modifies lap times & wear; run with identical `--seed` twice to verify reproducibility.  
- [ ] 8. All custom exceptions include helpful messages and are either handled or declared.  
- [ ] 9. Code passes `mvn test` or your IDE’s test runner with **zero** failures.  
- [ ] 10. Reflection questions answered in `docs/reflection.md` (≈ ½ page).  
- [ ] 11. `README.md` updated with build instructions (`javac`, Maven or Gradle) and run example.  
- [ ] 12. Commit history shows incremental progress (≥ 6 commits, one per exercise).

---

## 📚 Additional Resources
1. Oracle Java Tutorials – *Reading and Writing Files*: https://docs.oracle.com/javase/tutorial/essential/io/file.html  
2. “UML Cheatsheet” by Lucidchart (free PDF): https://www.lucidchart.com/pages/uml-cheat-sheet  
3. Refactoring Guru – *Strategy & Observer Patterns* (interactive examples in Java): https://refactoring.guru/design-patterns

---

## 🤝 Collaboration Guidelines
• **Individual work first**: Complete your own design and code.  
• **Discussion allowed**: You may discuss concepts with classmates, but do **not** share code or written answers.  
• **External code**: If you copy <15 lines from an online source, add a comment with the URL and explanation. More than 15 lines requires explicit instructor approval.  
• **Git etiquette**: Commit early & often; don’t force-push to the shared classroom repo.  
• **Help requests**: Use the course forum; include error messages and a *minimal* code snippet (≤ 30 lines).  
• **Academic honesty**: The university plagiarism policy applies—violations result in a zero for the assignment.

---

Good luck, Strategists—may your tyres stay grippy and your pit-stops lightning-quick! 🏁