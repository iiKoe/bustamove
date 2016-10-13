## Week #4
For grading the sprints I will use the rubrics to grade and give feedback about sprint management and code evolution quality. Then I will grade and give feedback to the assignment. These grades will construct the final grade for the sprint using a 20% (sprint management), 40% (code quality) and 40% (assignment) weight system.

### Sprint Management

Sprint Management | Grade 
|---|---:|
Definition |  7
Splitting |  4
Responsibility |  10
Estimation |  8
Prioritization |  10
Reflection |  10

Code Evolution Quality | Grade 
|---|---:|
Code change quality | 7
Formatting | 8
Naming | 10
Comments | 9
Building | 10
Testing | 2
Tooling | 10
Branching | 10
Code review | 10

### Assignments

#### Assignment 1 (15/30)
DP1:

1. 3/5
1. 4/5
1. 0/5

DP2:

1. 3/5
1. 5/5
1. 0/5

#### Assignment 2 (24/30)
1. 16/22
1. 8/8

#### Assignment 3 (17/30)
1. 14/22
1. 3/8

### General Feedback
Code Evolution:
- The design patterns were not really implemented correctly. The observer pattern is actually a Command pattern and the Strategy pattern sort of works like a strategy, but also is a factory for itself, which it shouldn't be. I can give additional explanation on this in real-life, since writing it out takes too long.
- remove commented code in releases.
- Use all javadoc tags (@param etc) everywhere
- I hope testing gets fixed this week
- Make minimal size (atomic) tests. This means that while many asserts are good to cover lots of branches/edge cases, you want to split up the different cases as much as possible in different test cases, so that when a test fails, you know exactly what functionality is failing. Tip for upcoming sprint (since itis your 20-time): Make tests that test the functionality, not the implementation. This means that it tests whether some method provokes the correct state changes/output, but how it did so.
- Checkstyle: make it stricter. javadoc checks allow all kidns of missing things, no point in doing so, they should just be there. Variable naming (MethodTypeParameterName, ClassTypeParameterName, InterFaceTypeParameterName, etc) should all be the google default (or sun_checks default), this is global java convention and not open for discussion (under_scores vs camelCase). Follow (and check against) the language's convention as much as possible.
- PMD: make sure that it actually checks something. It doesn't seem like it is configured, just activated (it generates 0 errors, which is good, but only if it actually can trigger any errors). No deduction, just a notification.
- Code review is done well, but more peopel would be even better.

Sprint management:
- Make sure to split the work evenly. Still getting a few members ahead by nearly double the amount of hours.

Assignment:
- Quality of requirements was not too great, would have been better to have a feedback cycle.
- Design patterns did some decoupling, but their implementation tangled the code in other ways. Revise the lectures and look at the class diagrams and comprae it to your own.

Other:
Comments regarding Contributing:
- When new code gets written, make sure that as many people look at it as possible, especially when code is new, this is when you should do in-depth code review (as opposed to editing existing code), because you might have had the completely wrong direction with the implementation, whereas editing existing code only requires a sanity check (because it should have been well-structured in the first place, you are only checking if you are not breaking the functionality and design)

Apologies for the late feedback.
