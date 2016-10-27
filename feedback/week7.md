## Week #7
For grading the sprints I will use the rubrics to grade and give feedback about sprint management and code evolution quality. Then I will grade and give feedback to the assignment. These grades will construct the final grade for the sprint using a 20% (sprint management), 40% (code quality) and 40% (assignment) weight system.


### Sprint Management


Sprint Management | Grade 
|---|---:|
Definition | 10
Splitting | 10
Responsibility | 10
Estimation | 8
Prioritization | 10
Reflection | 8


Code Evolution Quality | Grade 
|---|---:|
Code change quality | 7
Formatting | 9
Naming | 10
Comments | 10
Building | 10
Testing | 5
Tooling | 10
Branching | 10
Code review | 10


### Assignments


#### Exercise 1 (30/30)
1. 30/30


#### Exercise 2 (26/30)
1. 18/22
1. 8/8


#### Exercise 3 (30/30)
1. 15/15
1. 15/15


### General Feedback
Code Evolution:
- You should really consider changing the screen classes and splitting them into a controller and a GUI view.
- Did you consider Singleton for DynamicSettings, rather than passing it around?
- Try to tackle all code duplication (found by your IDE or by CPD tool)
- Go through your FindBugs report every now and then as well (every PR? :-))
- Focus on improving tests and code quality (seems like it is mostly the cobertura report in terms of testing). You should be on your way otherwise.
  Make sure to apply proper design in any new features so that you don't introduce unnecessary sloppy code.

Sprint management:
- Reflection seems to miss some mention of the missed testing (high priority, not finished) and why this was
- Estimation can be more exact.
- Code review should not be part of the planning (all your overhead is simply overhead that you account for but do not explicitly mention).
  You're not getting paid or something, no need to be making hours.

Assignment:
- Why did you not use the BombBall class and ColoredBall class instead of the BallType to change the behaviour and animations and such? Seems like way less hassle.
  Right now the ColoredBall/BombBall don't add much so there's plenty room to change behaviour there.
