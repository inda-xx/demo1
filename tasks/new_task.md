High-Level Weekly Programming Task  
Theme: Simulating Formula-1 Tire Degradation & Pit-Stop Decisions  

Context  
In real Formula-1 races, engineers continuously monitor how quickly each tire compound wears out under varying track conditions. They decide the exact lap on which to pit for fresh tires. Your mission this week is to build a small Java program that reads real-looking tire data from files, models degradation over laps, and recommends when to pit. While coding you will practice:  
• Using Data from Files to Instantiate Objects  
• Designing Classes  
• Programming Creatively  

You have six tightly-linked exercises. The first two are mostly “think & plan”; the remaining four are increasingly hands-on. Work through them in order—the artifacts you create in Exercise 2 feed directly into your code for Exercises 3-6.

-------------------------------------------------
Exercise 1 📄🏎️ “Reading the Track” – Inspecting Tire Data (theoretical)  
Goal: Understand how raw data will enter your program.

1. Download the provided sample file tires.csv (a few lines shown below).  
   compound,startingGrip(%),degradationPerLap(%),maxLaps  
   SOFT,100,3.1,25  
   MEDIUM,100,2.4,35  
   HARD,100,1.7,50  

2. In ≤300 words describe:  
   • The file format & delimiter.  
   • Which columns map to which future object fields.  
   • Potential data-quality issues (negative grip? wrong delimiter?).

3. Sketch pseudocode for safely opening, reading and closing the file while handling IOException and NumberFormatException.

Deliverable: A short PDF answering the above points.

-------------------------------------------------
Exercise 2 🛠️📐 “Building the Garage” – Designing Your Class Model (theoretical → design)  
Goal: Apply good class-design practice before writing code.

1. Identify at least four classes you will need (e.g., Tire, Car, RaceTrack, StrategyAdvisor).  
2. For each class list:  
   • Responsibilities (1–2 sentences)  
   • Key fields  
   • Public methods (with short purpose notes)  
3. Draw a simple UML diagram (hand-drawn & scanned is fine).  
4. Pinpoint one design pattern (Factory? Strategy? Observer?) you might use and briefly justify.

Deliverable: UML diagram + design notes (PDF or image).

-------------------------------------------------
Exercise 3 💻🔍 “Mounting the Tires” – From File to Objects  
Goal: Read the CSV and turn each line into a Tire object.

Skeleton (src/tyre/TireFactory.java):

public class TireFactory {
    // Don’t change the signature; tests rely on it
    public static List<Tire> loadTires(String csvPath) throws IOException {
        List<Tire> tires = new ArrayList<>();
        // TODO parse the file line by line
        // TODO create Tire objects and add to list
        return tires;
    }
}

Tasks  
1. Complete loadTires:  
   • Use java.nio.file.Files or java.util.Scanner – you choose.  
   • Validate that startingGrip == 100 and degradation is positive.  
   • Throw your own InvalidTireDataException on bad input.  
2. Write minimal JUnit tests that load the sample file and assert size==3.

Hints  
• Remember to close your stream in a finally-block or use try-with-resources.  
• Keep Tire immutable once constructed—that simplifies later logic.

Deliverables: TireFactory.java + Tire.java + tests.

-------------------------------------------------
Exercise 4 🧮⚙️ “Running Practice Laps” – Basic Degradation Simulation  
Goal: Simulate how grip decreases each lap and print a lap-by-lap report.

Add to src/sim/RaceSimulator.java:

public class RaceSimulator {
    public static void main(String[] args) {
        // TODO 1: Ask user for compound name and number of laps via console
        // TODO 2: Retrieve matching Tire object from factory
        // TODO 3: Loop laps, update tire state, print remaining grip
    }
}

Rules for this stage  
• Grip after each lap = gripPrevious – degradationPerLap  
• When grip ≤ 30 % the tire is considered “critical”.

Tasks  
1. Implement the loop and print “Box now!” when critical.  
2. Allow user to repeat simulations without restarting the program.

Deliverables: RaceSimulator.java (console demo).

-------------------------------------------------
Exercise 5 🌡️🚦 “Race Day Strategy” – Multi-Factor Pit-Stop Advisor (challenging)  
Goal: Make decisions dynamically using extra track variables.

Extend your design:  
• Add class TrackCondition with fields: temperature(°C), surfaceWearFactor (double between 0–1).  
• Modify Tire so degradationPerLap is multiplied by surfaceWearFactor and an additional +0.05 % for every degree above 30 °C.

StrategyAdvisor API (you create):

public int recommendPitLap(Tire tire, TrackCondition cond, int stintLength) {
    // returns lap number at which to pit, or -1 if tire survives the stint
}

Tasks  
1. Implement TrackCondition (immutable).  
2. Implement StrategyAdvisor.recommendPitLap and write at least two unit tests.  
3. Experiment: change TrackCondition values, show how advice changes.

Deliverables: TrackCondition.java, StrategyAdvisor.java, tests, plus a short README of findings.

-------------------------------------------------
Exercise 6 🏁🤖 “Creative Engineering” – Choose Your Own Extension (creative + integration)  
Pick ONE of the following (or propose your own to your TA):

A. Visual Dashboard  
   • Use JavaFX or Swing to draw grip-over-time graphs and flash a red icon when “Box now!”.  
   • Load data for all three tire compounds at once and let the user switch views.  

B. Real-Time Radio Engineer  
   • Simulate a 50-lap race where StrategyAdvisor listens to live grip data every lap (randomly perturbed).  
   • Print playful radio messages: “Push now!” / “Tires are gone, copy?”  

C. Machine-Learning-ish Twist (CS1-friendly)  
   • Record simulation data to a new CSV (lap, grip, temperature, compound).  
   • In a second run, compute simple averages to “learn” better pit-stop thresholds.

Whatever you choose, document:  
• What new classes (if any) you created.  
• How you handled file I/O or graphics.  
• One thing you would improve given more time.

Deliverables: Source code + short demo video or screenshots + reflection.md (≈½ page).

-------------------------------------------------
Submission Checklist  
[ ] PDF answers for Exercises 1 & 2  
[ ] Well-documented Java source files for Exercises 3-6  
[ ] Unit tests compiled & passing  
[ ] README or reflection file  
[ ] Optional: video/screenshots for Exercise 6

-------------------------------------------------
Grading (100 pts total)  
• Data parsing & exception handling – 15  
• Class design quality + UML – 15  
• Correct degradation simulation – 20  
• StrategyAdvisor accuracy & tests – 20  
• Creativity & polish in Exercise 6 – 20  
• Code style & documentation – 10

-------------------------------------------------
Good Luck & Have Fun—see you in the paddock! 🏆

────────────────────────────────────────────────────────
Exercise 1 📄🏎️  “Reading the Track” – Inspecting Tire Data  
Focus: Using Data from Files to Instantiate Objects  
Length target: ≈350 words
────────────────────────────────────────────────────────
Scenario  
Before any line of Java code is written, an engineer must know exactly what kind of data the program will consume and how reliable that data is. Your first task is to play the role of that engineer.

Resources  
Download tires.csv from the course page. A truncated preview is shown:  
compound,startingGrip(%),degradationPerLap(%),maxLaps  
SOFT,100,3.1,25  
MEDIUM,100,2.4,35  
HARD,100,1.7,50  

Tasks  
1. File-Format Audit  
   • Identify the delimiter and the purpose of the header row.  
   • Explain why a comma-separated text file is appropriate (or not) for this domain.  
2. Field Mapping  
   • For each column, state which attribute of a future Tire object it will initialise.  
   • Comment on appropriate Java types (int, double, enum, …) and on any necessary unit conversions.  
3. Data-Quality Risk Analysis  
   • List at least three realistic data anomalies (e.g., negative grip, missing cells, wrong delimiter).  
   • For each anomaly propose how the program should respond: skip line, halt with custom exception, log warning, etc.  
4. Robust File-Reading Pseudocode  
   • Sketch—in structured English, not Java—the algorithm you would use to open the file, read all lines, validate each value, and close resources.  
   • Explicitly include where IOException and NumberFormatException might be caught or re-thrown.

Deliverable  
A single-page PDF (≈300–400 words) containing your answers to 1–4. Keep explanations concise but technically precise.

Learning Pay-off  
Completing this exercise means you can articulate the contract between external data and internal objects, and you understand why defensive programming matters before a single constructor is invoked.

────────────────────────────────────────────────────────
Exercise 2 🛠️📐  “Building the Garage” – Designing Your Class Model  
Focus: Designing Classes & Programming Creatively  
Length target: ≈350 words
────────────────────────────────────────────────────────
Scenario  
You have the raw materials (CSV rows); now design the “garage” that will turn those rows into a working race-simulation system. Good class design up-front saves hours of debugging later.

Tasks  
1. Identify Core Classes  
   List at least four classes you believe the project needs (example names: Tire, Car, TrackCondition, StrategyAdvisor, RaceSimulator).  
2. Responsibility Cards  
   For each class write:  
   • Responsibility: one or two sentences that capture its single, clear purpose.  
   • Key Fields: bullet list of the most important private attributes and their intended types.  
   • Public Interface: bullet list of methods, each with a short intent statement (no signatures yet).  
3. Structural Overview (Mini-UML)  
   Draw a simple class diagram showing:  
   • Classes and their relationships (association, aggregation, dependency).  
   • Visibility of important methods/fields is optional but welcome.  
   Hand-drawn is fine—scan or photograph clearly.  
4. Design Pattern Reflection  
   Pick one OO design pattern (Factory, Strategy, Observer, etc.) that could improve your design. In ≤100 words explain:  
   • Where in your model it fits.  
   • What problem it solves in this context.  
5. Stretch-Thought (optional but rewarded)  
   Suggest one future extension (e.g., adding weather changes) and briefly note how your design could accommodate it with minimal refactor.

Deliverable  
One PDF or image bundle containing:  
• A bulleted design document (≈300–400 words).  
• The mini-UML diagram.

Learning Pay-off  
By the end you will have practised the art of defining class responsibilities, recognising when a design pattern adds value, and thinking ahead to scalability—all before writing a single line of Java.

────────────────────────────────────────────────────────


────────────────────────────────────────────────────────
Exercise 3 💻🔧  “Mounting the Tires” – From File to Objects  
Focus: Using Data from Files to Instantiate Objects  
Length target (code + comments): ≈100 – 120 non-blank lines  
────────────────────────────────────────────────────────
Scenario  
Your design is ready; time to bring the CSV to life. You will implement a small “factory” that parses tires.csv and returns fully-formed Tire objects that the rest of the application can trust. Robust I/O, validation and test-driven development are the key themes.

Starter Package (already in the Git repo)  
src/tyre/  
 • Tire.java        // empty blueprint  
 • TireFactory.java    // contains the method stub below  
test/tyre/  
 • TireFactoryTest.java // JUnit 5 scaffold (empty)  

Skeleton (do NOT change the signature; autograder depends on it)

public class TireFactory {

    /**
     * Reads a CSV file and returns an immutable list of Tire objects.
     * @param csvPath absolute or relative path to tires.csv
     * @return List<Tire> containing one object per valid CSV line
     * @throws IOException                if the file cannot be opened
     * @throws InvalidTireDataException   if a line is malformed or fails validation
     */
    public static List<Tire> loadTires(String csvPath)
            throws IOException, InvalidTireDataException {

        List<Tire> tires = new ArrayList<>();
        // TODO: your code here
        return tires;
    }
}

Tasks  
1. Implement Tire.java  
   • Make each field private and final (recommended: compound, startingGrip, degradationPerLap, maxLaps).  
   • Provide only getters—no setters—to keep the object immutable.  
   • Supply a readable toString() for debugging.

2. Complete TireFactory.loadTires  
   • Use try-with-resources and java.nio.file.Files.lines or a Scanner.  
   • Skip the header row automatically.  
   • Validation rules (throw InvalidTireDataException on failure):  
     – startingGrip must equal 100 (exactly).  
     – degradationPerLap and maxLaps must be strictly positive.  
     – compound must be a non-empty word (tip: treat it as an enum later).  
   • On the first invalid row, propagate the custom exception and abort loading—this makes bad data visible early.

3. Write JUnit 5 tests in TireFactoryTest.java  
   • happyPath(): load the provided tires.csv and assert list size == 3.  
   • badData(): point to bad_tires.csv (you create) with at least one illegal value; assert that InvalidTireDataException is thrown.

4. Commit small & often. Push when tests are green.

Hints  
• Look back at the pseudocode you wrote in Exercise 1—turn it into Java almost line-for-line.  
• InvalidTireDataException is not provided; create it as a lightweight checked exception extending Exception.  
• Immutability keeps later multithreaded experiments safe.

Deliverables  
• Tire.java, TireFactory.java, InvalidTireDataException.java  
• test/tyre/TireFactoryTest.java (all tests passing)  
• bad_tires.csv used in the negative test (place under test/resources).  

Learning Pay-off  
You will have practised end-to-end file input, defensive validation, exception handling and unit testing—exactly the tool-belt needed for reliable data-driven applications.

────────────────────────────────────────────────────────
Exercise 4 🏎️📉  “Running Practice Laps” – Basic Degradation Simulation  
Focus: Designing Classes & Programming Creatively  
Length target (code + comments): ≈150 – 180 non-blank lines  
────────────────────────────────────────────────────────
Scenario  
With real Tire objects in hand, you can now simulate a stint on track. This exercise is your first integration test of multiple classes working together: console I/O, simple math, loops and safe state updates. Keep it interactive and fun—pretend you are the race engineer!

New Starter File  
src/sim/RaceSimulator.java  (empty main class)

Rules (Stage 1)  
• Remaining grip after each lap = previousGrip − degradationPerLap  
• If remainingGrip ≤ 30 % the tire is in the “critical” zone; pitting is advised.  
• A tire cannot run more than maxLaps by design—stop the loop automatically.

Tasks  
1. User Dialogue  
   • In main(), greet the user and list the available compounds by name (query the list from TireFactory, don’t hard-code).  
   • Prompt for:  
     a. compound (string)  
     b. number of laps the user wants to attempt  
   • Validate the inputs (compound exists, laps > 0). Loop until valid.

2. Simulation Loop  
   • Fetch the selected Tire object (clone it or hold separate mutable state so the original stays untouched).  
   • For lap = 1 … userLaps (or until critical / maxLaps reached):  
       – Compute new grip  
       – Print formatted line: “Lap #3 – Grip: 78.5 %”  
       – If critical, print “Box now!” and break.  

3. Replay Feature  
   • After a run, ask “Try another stint? (y/n)”.  
   • Cleanly exit on ‘n’, else start over without restarting the JVM.  
   • Make sure resources (Scanner) are closed exactly once.

4. (Optional ✨) Colourful Console  
   • Add ANSI colours: green (>60 %), yellow (60 %–30 %), red (≤30 %).  
   • Works on most UNIX terminals and modern Windows terminals—tests will ignore colours if absent.

Hints  
• Separate concerns: put all console I/O in RaceSimulator; keep Tire math inside Tire (e.g., add a method double gripAfterLaps(int laps)).  
• Round percentages to one decimal place with String.format("%.1f", value).  
• Think about how the loop would change later when TrackCondition comes into play (Exercise 5).

Deliverables  
• RaceSimulator.java (well-commented)  
• Any helper classes you felt necessary (must be in src/, documented).  
• Short GIF or screenshot of a sample run (embed in README or commit to docs/).

Learning Pay-off  
This task merges:  
1. File-fed object creation (Exercise 3)  
2. Cohesive class design (Exercise 2)  
3. Creative UX decisions (colour codes, replay loop)  

By completing it you will see how a handful of thoughtfully designed classes collaborate to produce a realistic, user-driven simulation—exactly the bridge to the more sophisticated strategy engine you will build in Exercises 5 & 6.

────────────────────────────────────────────────────────
Time-Management Advice  
Allocate roughly:  
• 4–5 h for Exercise 3 (coding + tests)  
• 4–5 h for Exercise 4 (coding + polishing)  
• Keep 1–2 h buffer for debugging and Git workflow.

Good luck—see you on the pit wall! 🏁

────────────────────────────────────────────────────────
Exercise 5 🌡️🚦  “Race-Day Strategy” – Multi-Factor Pit-Stop Advisor  
Focus: 1) Using Data from Files to Instantiate Objects  
        2) Designing Classes (Strategy pattern)  
        3) Unit-Test-First Mind-set  
Length target (code + comments): ≈180 – 220 non-blank lines  
────────────────────────────────────────────────────────
Scenario  
Your simulator can already wear a tyre down in a vacuum; real races are rarely that kind.  
Engineers watch the asphalt, the sun and even loose gravel to refine pit-stop timing.  
In this exercise you will:

• Read track-condition data from a small CSV.  
• Model those conditions as immutable value objects.  
• Implement a StrategyAdvisor that recommends the *earliest* lap at which the driver should pit, given a planned stint length.  

Starter Files (added to the repo)  
data/track_conditions.csv         // 3 lines shown below  
   lap,temperature,wearFactor  
   1,29,0.92  
   2,31,0.97  
   …(up to 10 lines)  

src/track/  
   TrackCondition.java             // empty shell  
src/strategy/  
   StrategyAdvisor.java            // interface with 1 method stub  
test/strategy/  
   StrategyAdvisorTest.java        // empty JUnit scaffold  

----------------------------------------------------------------
Rules & Formulas  
1. Base degradation this lap = tire.getDegradationPerLap().  
2. Environmental multiplier = currentWearFactor (read from file).  
3. Temperature surcharge  = max(0, temperature – 30 °C) × 0.05.  
4. Effective degradation   = base × multiplier + surcharge.  
   (Round to 3 decimals for internal math; display 1 decimal later.)  
5. A tyre must pit as soon as remaining grip ≤ 30 % *or* if it would exceed tire.getMaxLaps().  
6. If the tyre would comfortably survive the requested stint length, return -1.

----------------------------------------------------------------
Tasks  
1. TrackCondition.java  
   • private final int lap;  
   • private final double temperature;  
   • private final double wearFactor;  
   • Provide getters, toString(), equals()/hashCode().  
   • Add static TrackCondition fromCsvRow(String line) that parses and validates a CSV line (“lap must be ≥1, temperature 0-60, wearFactor 0-1”). Throw InvalidTrackDataException (create).  

2. TrackConditionLoader  
   Create a tiny utility in the same package:  
      static List<TrackCondition> load(String path) throws IOException, InvalidTrackDataException  
   (very similar to TireFactory; reuse patterns you learned).  

3. StrategyAdvisor (interface)  
   public int recommendPitLap(Tire tire, List<TrackCondition> cond, int stintLength);  

   • Implement it in DefaultStrategyAdvisor.java.  
   • If cond.size() < stintLength, assume the last known condition repeats.  
   • Never mutate the passed-in Tire.  

4. Unit Tests  
   • happyPathStintSurvives(): expect -1.  
   • pitsEarlyDueToHeat(): craft a tiny cond-list with 38 °C to force early pit on e.g. lap 4; assert returned lap == 4.  
   • badTrackData(): feed a line with wearFactor > 1; expect InvalidTrackDataException.  

5. Wire-up Demo (mini-main)  
   Write StrategyDemo.java that runs:  
   • Default tire = “MEDIUM”.  
   • stintLength = 10 laps.  
   • Prints advisor’s answer plus a mini table (lap, grip%).  
   Keep it <40 lines; this is just for your own visual sanity check.

----------------------------------------------------------------
Hints  
• You are essentially applying the Strategy pattern twice:  
  – StrategyAdvisor encapsulates “how to decide”.  
  – Later you could swap in new advisor classes (e.g., FuelAwareAdvisor).  
• Re-use try-with-resources; you have written it once in TireFactory!  
• Keep calculations in double; gripping accuracy is more important than integer speed.

----------------------------------------------------------------
Deliverables  
• TrackCondition.java, TrackConditionLoader.java, InvalidTrackDataException.java  
• strategy/DefaultStrategyAdvisor.java & StrategyAdvisor.java  
• test/strategy/StrategyAdvisorTest.java (≥3 green tests)  
• demo/StrategyDemo.java (non-graded, but include in repo)  

Learning Pay-off  
You combine file-driven object creation *and* algorithmic decision making, held together by a clean OO design. After this task you can point to a self-made, data-informed “brains of the operation” component—exactly what real-world back-end services do.

Estimated Time: 5 – 6 h (coding + tests + debug).  
Tip: budget 45 min just for the maths & unit-test design before touching the keyboard.

────────────────────────────────────────────────────────
Exercise 6 🏁🤖  “Grand-Prix Weekend” – Integrative & Creative Extension  
Focus: Programming Creatively (+ a pinch of new CS1 material)  
Length target (total new code): ≈200 – 250 non-blank lines  
────────────────────────────────────────────────────────
Choose ONE of the three tracks below (or pitch your own to the TA and get approval).  
Each option *must* re-use the classes you have built so far; the fun is in extending, not rewriting.

Common Requirements  
• Place new code under a distinct package (gui/, radio/, ml/ etc.).  
• Keep previous unit tests GREEN.  
• Add a reflection.md (≈½ page) answering:  
  1. What new classes did you add and why?  
  2. One obstacle you hit and how you solved it.  
  3. Given another week, what would you improve?  

----------------------------------------------------------------
Option A. Visual Dashboard (JavaFX or Swing)  
Task-Snippets  
1. Load *all* three tyre compounds at program start.  
2. A tabbed UI (or buttons) lets the user pick SOFT / MEDIUM / HARD.  
3. Graph grip% vs lap (use a LineChart in JavaFX or a JFreeChart in Swing).  
4. When StrategyAdvisor says “pit”, flash a red icon and disable the “Next Lap” button.  

Very Light CS-Beyond-101: you will compile one 3rd-party library (JFreeChart) *or* learn minimal JavaFX setup.

----------------------------------------------------------------
Option B. Real-Time Radio Engineer (Console-only)  
Task-Snippets  
1. Simulate an entire 50-lap race.  
2. Each lap:  
   • Randomly perturb temperature ±3 °C and wearFactor ±0.05 (keep in valid range).  
   • Feed updated TrackCondition to StrategyAdvisor.  
   • Print playful radio:  
     “Lap 12: 72.4 % grip — ‘You’re doing great, push now!’”  
3. When advisor recommends pitting, print “Box, box, box!” and stop.  

Very Light CS-Beyond-101: introduces java.util.Random and simple state machine (RUNNING, PIT, FINISHED).

----------------------------------------------------------------
Option C. “Machine-Learning-ish” Twist (CSV → Average Learner)  
Task-Snippets  
Stage 1 – Data Gathering  
  • During any simulation (Exercise 4 or 5), append a row to stint_log.csv:  
    lap,compound,temperature,wearFactor,gripRemaining  

Stage 2 – Offline Learning  
  • Create Learner.java with:  
       double averageCriticalGrip(String compound)  
    Returns the average grip% value *when the tyre actually hit critical (≤30 %)* across the log.  

Stage 3 – Smarter Advisor  
  • Write LearningStrategyAdvisor that extends DefaultStrategyAdvisor:  
    – Uses averageCriticalGrip(compound) as a dynamic threshold instead of hard-coded 30 %.  

Very Light CS-Beyond-101: introduces grouping & averaging over a CSV (you may use Java Streams but stick to features already covered in class if you prefer).

----------------------------------------------------------------
Deliverables (all options)  
• Source code for new classes & packages  
• Updated README or reflection.md  
• At least one screenshot or short GIF (commit under docs/ or embed in MD)  

Grading (Exercise 6 – 20 pts)  
• Functional completeness – 10  
• Clean integration with earlier work – 4  
• Creativity / polish – 4  
• Reflection quality – 2  

Estimated Time: 6 – 8 h (including reading docs for chosen library/feature).

────────────────────────────────────────────────────────
Overall Week-End Checklist  
[ ] Exercise 5 code & tests committed, all green  
[ ] Exercise 6 chosen track implemented  
[ ] reflection.md added  
[ ] README updated with build/run instructions  
[ ] Optional media (GIF / screenshot) committed under docs/  

You have now moved from reading a flat CSV to running a (tiny but realistic) data-driven decision engine—complete with strategy, environmental modelling and optional UI/analytics.  
Congratulations, rookie engineer, the chequered flag is yours! 🏆