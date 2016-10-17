## Week #6
For grading the sprints I will use the rubrics to grade and give feedback about sprint management and code evolution quality. Then I will grade and give feedback to the assignment. These grades will construct the final grade for the sprint using a 20% (sprint management), 40% (code quality) and 40% (assignment) weight system.

### Sprint Management

Sprint Management | Grade 
|---|---:|
Definition | 6
Splitting | 10
Responsibility | 10
Estimation | 6
Prioritization | 10
Reflection | 8

Code Evolution Quality | Grade 
|---|---:|
Code change quality | 7
Formatting | 8
Naming | 10
Comments | 10
Building | 10
Testing | 4
Tooling | 10
Branching | 10
Code review | 10

### Assignment 3

#### Exercise 1 (28/30)
DP1:

1. 5/5
1. 5/5
1. 5/5

DP2:

1. 5/5
1. 5/5
1. 3/5

#### Exercise 2 (30/30)
1. 22/22
1. 8/8

#### Exercise 3 (15/30)
1. 13/22
1. 2/8

### General Feedback
Code Evolution:
- Testing is still heavily under the required level. I expected much more test improvement because it was your 20-time too. It's good that you realized that statics are hard to test (lots of staticness is a red flag in your code)
- There's still commented code present, for formatting, make sure that this is gone, as well as unused imports, and everything like that.
- Code quality: Focus on fixing the inheritance. GameScreen and SplitGameScreen are basically the smae thing: a gameScreen. So why copy the code rather than making a common parent class (abstract class for instance)


Sprint management:
- Splitting is done much better now
- Definition has gone down: many very big tasks without a clear definition of what needs to be done (what tasks must be completed and delivered for 'Design Patterns Singleton'?).
You might consider this redundant since it's obvious if you know the assignment,
but this type of redundancy in communication is pretty important in (project) management.

Assignment:
- Iterator is not implemented correctly.
The iterators themselves are okay, but the way that BallGraph uses them is incorrect.
The BallGraph uses the iterator pattern if it can provide any caller with an Iterator.
The caller can then decide on his own when or how to iterate (and the iterator makes sure that the iteration will work for the BallGraph internal representation).
Right now you are making BallGraph use its own iterator and provide an arraylist, and then give that.

- 20 Time exercise missed the opportunity for a great improvement in testing.
- Also missing small documents (UMLs) for the changed areas in the code.
