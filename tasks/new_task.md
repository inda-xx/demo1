# 🏎️💥 Pit-Stop Panic! — Complete Mini-Project Brief  

*(This document supersedes all earlier drafts. Read it end-to-end before you start!)*  

---

## 📍 Roadmap & Smooth Transitions  
The project unfolds in six incremental exercises.  
• Exercises 1 & 2 (conceptual → design) lay the intellectual asphalt for the rest of the race.  
• Exercises 3 & 4 (file I/O → logic) bolt working code onto your chassis.  
• Exercise 5 (threads → patterns) pumps high-octane realism into the game.  
• Exercise 6 (creative extension) is where you add NOS and fireworks.  
Each later exercise **explicitly depends** on artifacts you created earlier:  
• Class skeletons from Exercise 2 must be imported by the CSV code in Exercise 3.  
• The `needsPitStop()` heuristic written in Exercise 4 is called from the threaded pit-crew in Exercise 5.  
• Any new visual or physics feature you invent in Exercise 6 must hook into the event system you built in Exercise 5.  

Feeling the flow? Good—on to the tasks! 🏁  

*(All exercise texts are unchanged except for minor wording tweaks and cross-references.)*  

---

## 🔗 Cross-Reference Quick Guide  
• “fields you sketched in Ex 2” ↔ used by `TelemetryLoader` in Ex 3  
• `RaceEngineer` (Ex 4) **reuses** the per-line parsing utility from Ex 3  
• `PitCrew` (Ex 5) **fires** `PitEventListener` events that the optional GUI or ASCII art (Ex 6) can subscribe to  
• Your ER-diagram from Ex 1 should map neatly onto the class diagram produced in Ex 2—update either one if they drift apart  

---

## 🎯 Learning Objectives Revisited  
You will have met every weekly learning goal by the chequered flag:  
1. Using Data from Files to Instantiate Objects → Exercises 3 & 4  
2. Designing Classes → Exercises 1 & 2 (plus refactoring in 4 & 5)  
3. Programming Creatively → Exercise 6 + Reflection  

---

## ✅ Final Checklist  
Tick every box before you push your last commit or attach your zip archive.  

1. [ ] Exercise 1 artefacts (`entities.md`, ER-diagram image/PDF) are present.  
2. [ ] Exercise 2 class blueprints compile *empty* (no red underlines) in your IDE.  
3. [ ] `TelemetryLoader.loadCars()` correctly returns one `Car` per `CAR_ID` (Ex 3).  
4. [ ] At least three malformed CSV rows are logged—not fatal (Ex 3).  
5. [ ] `needsPitStop()` heuristic triggers **both** on over-temp *and* over-wear (Ex 4).  
6. [ ] Console output shows at least one “BOX BOX!” message during simulation (Ex 4).  
7. [ ] Singleton `PitCrew` performs pit-stops on a separate thread without data races (Ex 5).  
8. [ ] Each completed pit-stop fires an event caught by `RaceBroadcaster` (Ex 5).  
9. [ ] Creative feature from Ex 6 runs with the *same* CSV file—no manual edits required.  
10. [ ] All public classes, methods, and fields carry Javadoc comments.  
11. [ ] `README.md` explains: compile/run commands, any third-party libs, and how to see the creative feature.  
12. [ ] Reflection questions (section “🤔 Reflection”) answered in `reflection.md` (≈ ½ page total).  

No box left unchecked → you’re clear to race! 🚦  

---

## 📚 Additional Resources  
1. Oracle Java Tutorials — *Essential I/O* and *Concurrency* chapters  
   https://docs.oracle.com/javase/tutorial/  
2. “Beginning UML” quick-reference (free PDF, 18 pp) — great for ER & class diagrams  
3. OpenCSV 5.8 documentation — optional library if you dislike manual `split(",")`  
   https://opencsv.sourceforge.net/  

*(Libraries are optional; if you add dependencies, update your `README.md` and build-file.)*  

---

## 🤝 Collaboration Guidelines  
• **Discuss concepts, not code.** White-board, paper, or pseudocode chats are fine; copy-pasting source is not.  
• **Credit where due.** Cite any tutorial or blog post you consulted in a comment at the top of the affected file.  
• **Version control is your friend.** Commit early & often; include meaningful messages (“Implement needsPitStop heuristic”, not “stuff”).  
• **Pair-programming?** Allowed for brainstorming; each partner still writes and submits their own final code.  
• **Ask for help early.** Stuck > 30 minutes? Post on the course forum *with* a minimal reproducible example (MRE).  
• **Academic honesty.** Plagiarism results in a zero and potential disciplinary action.  

---

### 🚨 Support & Troubleshooting  
• Piazza forum tag: `[#pit_stop_panic]`  
• Weekly lab session: Wednesday 14:00-16:00, Room B-302  
• Office hours: Prof. Speedster, Friday 10:00-11:00 (sign-up sheet online)  

---

🧑‍💻  Now open that IDE, fire up the caffeine, and push for pole position. Happy coding—and may all your pit-stops clock in under two seconds!