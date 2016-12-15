## Week #8
For grading the sprints I will use the rubrics to grade and give feedback about sprint management and code evolution quality. Then I will grade and give feedback to the assignment. These grades will construct the final grade for the sprint using a 20% (sprint management), 40% (code quality) and 40% (assignment) weight system.


### Sprint Management


Sprint Management | Grade 
|---|---:|
Definition | 10
Splitting | 10
Responsibility | 10
Estimation | 9
Prioritization | 10
Reflection | 10


Code Evolution Quality | Grade 
|---|---:|
Code change quality | 9
Formatting | 9
Naming | 10
Comments | 10
Building | 10
Testing | 6
Tooling | 10
Branching | 10
Code review | 10


### Assignments


#### Exercise 1 (30/30)
1. 30/30


#### Exercise 2 (45/45)
1. /3
1. First flaw
    1. 4/4
    1. 10/10
1. Second flaw
    1. 4/4
    1. 10/10
1. Third flaw
    1. 4/4
    1. 10/10


#### Exercise 3 (15/15)
1. 11/11
1. 4/4


### General Feedback
Code Evolution:
- To finish up your code quality, consider some points below, as well as looking back at previous iterations and comments I made earlier regarding specific parts of the code. Try to apply these tips throughout your project and make sure to use your IDE's analysis (warnings) and static analysis to find code smells.
- The resources set in AbstractYouLoseScreen are a bit funky. Why not specify these one level higher: Every AbstractScreen (not necessarily just a YouLoseScreen) has a form of background.
- As I noted whne you implemented the Observer model, your model is a bit un-OOP.
  You are using a function pointer effectively, but what the common behaviour is, is to have the desired function (method) in the Observing class (the Listener).
  When the Observable notifies its observers/listeners, the observing class will know what action to take on that notification (aka: call that method).
  The goal here is that the observable class does not needto contain the code for the exeuction (the buttons do not need their functionality in the observable subscription).
  This is useful for instance if you are working on an Observable that you do not have the source code to (library, etc), which can be quite common with GUI/view models.
  The class that is observing is something in between the model (the actual other classes that are called) and the view (the GUI) and is called the controller. Most of the time, you will see that each View (screen) has its own Controller. MOdification of appearance (view) and functionality (controller) can then be separated, resulting in a better single-responsibility design.
- A good start would be to create a controller class for each screen that does what I explained above, and to then update your observer model to accomodate for that (could affect a lot of things, which is a bit risky, but it should only be moving around code, not changing it).
- A more advanced tip to reduce the coupling in your classes: look at the imports of a class and try to reduce the list. Long import lists show that the class is dependnet on a lot of things (quite literally so: You need those classes in your project for this class to function).
- Remove all commented code, todo's and other 'development litter' for your final release.
- Look at the length of your clases. BallManager is **still* over 650 lines long. This is immense. Consider introducing some classes that can take over some functionality.
- Reduce cyclcomatic complexity while splitting up classes: Focus on creating classes that reduce the need for branching in your methods. (Alike to the if-case assignment).
- Use your IDE to find warnings regarding unused things. Be critical whether you actually need methods to access certain things, or if they are just there to reach your private fields without that being necessary. (fights the Data Class/ Access to Foreign Data flaws)
- Consider my earlier comments regarding Design Pattern assignments (I noted that yo uare looking to refactor the Iterator model). In general, go through all my earlier feedback and check fi it is still applicable.
- Good job overall. The code is generally pretty clean, although there are some larger actions (noted above) that could improve the structure. Some of these really only involve some bunch of copy pasting to have a better splitting throughout your classes.

Sprint management:


Assignment:
