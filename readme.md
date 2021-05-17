# a09-nonograms

## Introduction

In this assignment, you will use the **model-view-controller** design pattern together with the **JavaFX** UI library to design a complete, functioning GUI implementation of the single-player logic puzzle **nonograms**.

If you've never heard of nonograms before, I highly suggest starting by checking out [the nonograms Wikipedia page](https://en.wikipedia.org/wiki/Nonogram). After you do that, [try solving a few nonogram puzzles](https://www.puzzle-nonograms.com/) yourself to make sure you've got the hang of it.

The nonogram app that you will design will be very similar to the nonograms implementation on the website linked above. You are encouraged to be creative with regard to the visual design of your implementation. Feel free to theme your user interface, move the components around in different locations, add new features, or make other significant changes to the look and feel of your app. However, your implementation must have the following features at a bare minimum for full credit:

1. If the user left-clicks on a cell, that cell becomes *shaded*. But, if they right-click on a cell, that cell becomes *eliminated*---just like the reference implementation. There must be a visual difference between a *shaded* and an *eliminated* cell. A cell cannot be both *shaded* and *eliminated*. Cells must be clearly marked, so the user can tell which cell they are selecting.

2. If the user left-clicks on an already-shaded cell, that cell should return to a blank state. If the user right-clicks on an already-eliminated cell, that cell should return to a blank state.

3. After each move, your app must check to see if the user solved the puzzle. If so, the UI must visually update to let the user know that they completed the puzzle. The puzzle is "solved" if the shaded squares match the clues. Non-shaded squares do not need to be labeled "eliminated" in order to solve the puzzle.

4. The UI must include a clearly visible and labeled "reset" button that will clear all the cells in the board and let the user start over from a blank state.

5. The starter code contains a pre-coded library of 5 puzzles to solve. The UI must provide clearly visible and labeled buttons to go to the next puzzle, to go to the previous puzzle, and to jump to a random puzzle. The "previous" button should not cause an uncaught exception if the user accidentally presses it on the first puzzle. Similarly, the "next" button should not cause an uncaught exception if the user accidentally presses it on the last puzzle.

6. The library of puzzles is a `List`, which means the puzzles are indexed. Your app must clearly display the index of the active puzzle in the library and how many puzzles are in the library in total. The displayed index should start from one, not zero. In other words, the first puzzle in the `List` (at index 0) should be displayed as "puzzle 1 of 5" to the user.

7. Your app must support arbitrary-sized boards with different widths and heights. To demonstrate this functionality, the provided pre-coded library of puzzles includes puzzles of at least two different sizes.

This assignment is unique because it has a manually-graded portion *and* an autograded portion. The autograded portion verifies that your *model* is correct, but does not check your *controller* or *view*. The controller and view will be manually graded by a COMP 301 learning assistant after the assignment due date. The LA will run your application and try playing a few games using your user interface! Your grade for that portion of the assignment will be based off of whether the LA encounters UI bugs. This means you won't get immediate feedback about your controller and view implementations.


## Starter code

Once you're familiar with nonograms, take a look at the starter code defined in the repository. Three packages have been created to help you organize your code according to the model-view-controller pattern. In these packages, a few starter interfaces and classes have been defined to help you structure your app.

**It is not strictly necessary to use all the provided starter interfaces.** Most of them are just there to give your app some structure and help you plan your code. If you would rather come up with your own design, there are a few rules that your code must follow so that your code can be autograded correctly. First, you **must** provide a `ModelImpl` and `CluesImpl` class as specified below. Second, running the `main()` method of the provided `Main` class **must** launch your GUI (this is how the LAs will launch your app for manual grading). Other than these two requirements, you are free to safely ignore the rest of the provided interfaces.


## Running the application

This assignment uses Maven as a build manager, and JavaFX as a GUI library. Since JavaFX is an external library, it has to be included in the build process in order for the application to successfully run. JavaFX has already been added as a Maven dependency to the POM file.

To run the application with Maven in IntelliJ, follow these steps:

1. Click the vertical "Maven" expansion tab which is on the right side of the IntelliJ window.

2. Expand the "Plugins" folder.

3. Expand the "javafx" folder.

4. Double-click "javafx:run" to run the project.

**Important: This is exactly the same process that a learning assistant will use to launch your app when they manually grade it. Make sure your app can be launched using this process in your final submission to Gradescope.**


### Main class

The `Main` class represents the starting point of your application. When you use Maven to launch your app as described above, Maven will try to run the `Main.main()` method to launch your app. You are free to change the contents of the `Main` class as necessary, but keep in mind that the `Main.main()` method will always be the starting point of your application.


### PuzzleLibrary

To make your life easier, the starter repository comes with a class called `PuzzleLibrary`. `PuzzleLibrary` exposes a class factory method, `create()`, which instantiates and returns a singleton `List<Clues>` list of `Clues` objects (see below for details). You can use this to set up a puzzle library in your model. If you want, you can add more puzzles to the puzzle library.


## Model

All code related to the application's model should be placed in the `model` package.


### Clues

Take a look at the `Clues` interface. Each `Clues` instance represents the clues for a single nonograms puzzle. In particular, a `Clues` instance should encapsulate the following concepts:

1. The height of the puzzle (i.e. the number of rows in the puzzle)
2. The width of the puzzle (i.e. the number of columns in the puzzle)
3. An array of integers for each row in the puzzle, representing the clues for that row
4. An array of integers for each column in the puzzle, representing the clues for that column

`Clues` instances are intended to be *immutable*---that is, the fields of a `Clues` instance should not change after instantiation.


#### CluesImpl

You **must** create a class named `CluesImpl` in the `model` package which implements the `Clues` interface. This is required for your submission to be autograded.

The `CluesImpl` class should expose a constructor with the following signature:

```java
public CluesImpl(int[][] rowClues, int[][] colClues) {
  // Constructor code here
}
```

The `rowClues` parameter is a two-dimensional array containing the clues for each row of the puzzle (top-to-bottom), and the `colClues` parameter is a two-dimensional array containing the clues for the columns of the puzzle (left-to-right). Spaces can be represented by zeros in the clue arrays. For example, [this puzzle](https://www.puzzle-nonograms.com/?e=MDo5MDMsMDA3) is represented by the following instance:

```java
int[][] rowClues =
    new int[][] {
      new int[] {0, 2},
      new int[] {1, 2},
      new int[] {0, 3},
      new int[] {0, 3},
      new int[] {1, 1},
    };

int[][] colClues =
    new int[][] {
      new int[] {1, 1},
      new int[] {0, 1},
      new int[] {0, 3},
      new int[] {0, 3},
      new int[] {3, 1},
    };

Clues example = new CluesImpl(rowClues, colClues);
```

### Board

A `Clues` instance represents the clues for a puzzle, but doesn't handle the state to track whether individual cells are "shaded" or "eliminated". The `Board` interface is intended to track the array of states for each cell in a puzzle. In other words, a nonogram puzzle is a combination of a `Clues` object, representing the clues for the puzzle, and a `Board` object, representing the states of the puzzle's cells.

Instances of `Board` are not intended to be immutable. As the user clicks on different cells of the puzzle, the internal fields in the `Board` instance should change to reflect the new state. For example, if the user clicks on a cell to change it to be shaded, a field modification must take place inside th**e `Board` instance to reflect that the cell is now shaded. This can be accomplished via the `toggleCellShaded()` and `toggleCellEliminated()` methods.

You may choose to make a `BoardImpl` class as part of your design, but it is not a requirement for this assignment.


### Model

The `Model` interface represents the model of MVC, and therefore contains all data necessary to display the current state of the application. In particular, a `Model` instance should encapsulate the following concepts:

1. A library of available nonogram puzzles for the user to solve
2. A way to select which puzzle is currently active; for example, maybe an index into the puzzle library
3. The `Clues` and corresponding `Board` information for the currently active puzzle
4. A `List<ModelObserver>` of active model observers, represented by the `ModelObserver` interface (see below)

Notice that the `Model` interface extends both `Board` *and* `Clues`. That's because, per #3, a `Model` should represent the currently active  `Board` and `Clues` for the currently active puzzle.

There are multiple ways to encapsulate a library of available puzzles. One way is to simply encapsulate a `List<Clues>` field to store the list of available puzzle `Clues`, and then re-initialize the encapsulated `Board` field(s) every time the user changes the active puzzle. This approach works but has a disadvantage: every time the user changes the active puzzle, their progress on the old puzzle will be forgotten. If you want your app to retain the user's progress when switching between puzzles, you'll have to encapuslate that information in your library somehow. One option is to create a new class, maybe called `Puzzle`, to encapsulate a `Clues` instance together with the corresponding `Board` fields for that puzzle. Then, you can store a `List` of `Puzzle` instances as your library, and keep track of the `Clues` for each puzzle along with the corresponding `Board` information.


#### ModelImpl

You **must** create a class named `ModelImpl` in the `model` package which implements the `Model` interface. This is required for your submission to be autograded.

The `ModelImpl` class should expose a constructor with the following signature:

```java
public ModelImpl(List<Clues> clues) {
  // Constructor code here
}
```

The `clues` parameter is a list of `Clues` instances representing the library of available puzzles for the user to solve.

In order to implement the `isSolved()` method, you'll have to devise an algorithm to check whether the current state of the `Board` satisfies the corresponding `Clues`. This can be tricky, and will probably take some time to get right.

The `ModelImpl` class should be a "subject" with respect to the observer design pattern. This is because your view class will likely register itself as an active observer of the model, and will re-render itself in response to model changes. See the `ModelObserver` interface below for more information about how to implement this.


### ModelObserver

the `ModelObserver` interface defines a single method, `update()`, and is used together with the `ModelImpl` class to implement the observer design pattern.

`ModelImpl` should therefore notify its active observers whenever *any* `Model` field value is changed. **Hint:** Below is a list of all `Model` interface methods. Exactly 4 of the 18 methods involve manipulating the state of the `Model` instance (not including `addObserver()` and `removeObserver()`); these 4 methods should therefore trigger the observer notify process.

```
Model methods:
 1. int getPuzzleCount();
 2. int getPuzzleIndex();
 3. void setPuzzleIndex(int index);
 4. void addObserver(ModelObserver observer);
 5. void removeObserver(ModelObserver observer);
 6. boolean isSolved();

Model methods inherited from Board:
 7. boolean isShaded(int row, int col);
 8. boolean isEliminated(int row, int col);
 9. boolean isSpace(int row, int col);
10. void toggleCellShaded(int row, int col);
11. void toggleCellEliminated(int row, int col);
12. void clear();

Model methods inherited from Clues:
13. int getWidth();
14. int getHeight();
15. int[] getRowClues(int index);
16. int[] getColClues(int index);
17. int getRowCluesLength();
18. int getColCluesLength();
```

You need not implement the `ModelObserver` interface in order to receive full credit from the autograder; however, you may wish to implement it in your `View` class so that your view can respond to changes in the model.


## Controller

All code related to the application's controller should be placed in the `controller` package.

Remember, the controller package in MVC is intended to act as the "glue" between the model and the view. **You are free to implement your controller as you see fit; the controller is not graded by the autograder.** Instead, the controller will be manually graded when a learning assistant tries to play your game by running the `Main.main()` method!

However, to help you get started, an interface named `Controller` has been provided as a starting point. If you decide to use this interface, here's how you might use it.

First, add a new class called `ControllerImpl` to the `controller` package which implements the `Controller` interface. This class should encapsulate a single field: a `Model` instance.

Add a constructor to the `ControllerImpl` class that looks something like this:

```java
public Controller(Model model) {
  // Constructor code goes here
}
```

The `ControllerImpl` object should "wrap" a `Model` instance. Most of the `Controller` methods will simply be delegated/forwarded directly to the encapsulated `Model` instance. However, a few methods, like `prevPuzzle()`, `nextPuzzle`, and `randPuzzle()`, will require a few lines of code to get right.


## View

All code related to the application's view should be placed in the `view` package.

Remember, the `view` package in MVC is intended to hold all code related to the GUI. **You are free to implement your view as you see fit; the view is not graded by the autograder.** Instead, the view will be manually graded when a learning assistant tries to play your game by running the `Main.main()` method!

Regardless of the structure that you use for your view, you are required to use JavaFX as your GUI library. Therefore, the code in the `view` package will primarily create and manipulate JavaFX objects. To help you get started and figure out a good structure for your app, one class (`AppLauncher`) and one interface (`FXComponent`) are provided.


### AppLauncher

By default, this class is the launching point of your application (although you can change that if you'd like). The `Main` class is set up to forward to `AppLauncher`, which extends `Application` and therefore launches the JavaFX GUI.

To write the view, you'll need to fill in the `start()` method in `AppLauncher` to actually set up and create your UI. Inside the `start()` method, you should create a `Model` object and a `Controller` object. You can use the provided `PuzzleLibrary` class to get a simple puzzle library for setting up the model.


### FXComponent

Although you *could* put all your UI generation code directly in the `start()` method of `AppLauncher`, this might not be a good idea since it would clutter the method with *a lot* of JavaFX code. Instead, a better idea is to encapsulate each section of the UI in a separate class.

In GUI programming, it's common to call each "section" of the UI a "component". To this end, a (suggested) interface, named `FXComponent`, has been provided. You can use `FXComponent` to break up your interface into different smaller components. For instance, one `FXComponent` class, called `PuzzleView`, might display the clues and the game board inside a `GridPanel`. Another `FXComponent` class, called `ControlView`, might display the puzzle controls, including buttons, to move through the puzzle library. Finally, you might make a third `FXComponent` class, called `MessageView`, to show the "success" message when the user successfully finishes the puzzle.

The `FXComponent` interface has just one method, named `render()`. `render()` should be kind of like a factory method for creating a new JavaFX `Parent` object. The idea is that calling `render()` should instantiate and return a new JavaFX `Parent` object, representing the current, up-to-date scene graph for that section of the UI. Each time `render()` is called on a `FXComponent`, the `Parent` should be completely re-built *from scratch*, using the controller to reflect the latest state values of the application. Each `FXComponent` class should therefore encapsulate a reference to the `Controller` for retrieving the current application state.

The view must be re-rerendered every time a value in the model is changed. To accomplish this, register an active `ModelObserver` to observer the model instance. You may choose to do this using an anonymous class or a lambda expression directly in the `start()` method of the `AppLauncher` class, since that is where you have a reference to the model. When a model change occurs, the `render()` methods should be called on each `FXComponent` instance, and the resulting new `Parent` objects should be inserted in the `Scene`, while being careful to clear any old, "stale" UI components.

Finally, the view must react to user actions, such as clicking on certain user interface elements. Do this by registering observers on the relevant JavaFX UI component events. Sometimes, an application state change is necessary in response to a user action. For example, if the user clicks the "next puzzle" button, the model must be instructed to go to the next puzzle. Make use of the controller's methods to do this. By utilizing the controller, you will enforce separation of concerns between your model and view, and enforce that the controller is an intermediary between the two.