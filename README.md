# Java Maze Solver

## Objective and basic definition

This project solves a maze of sized `10 x 10` via **backtracking**. The maze is represented by ASCII characters, with the following logic:

- `I` marks the starting square.
- `X` marks the target square.
- `·` marks an explored square.
- `O` marks the shortest found path between `I` and `X`.
- `#` marks a wall.
- Unexplored squares are left empty.

## How does the algorythm work?

We can think of maze solving as a [breadth-first search](https://en.wikipedia.org/wiki/Breadth-first_search) problem. As such, this program executes recursion following these steps:

1. Find all squares neighbouring explored squares (at the beginning, this square is `I`).
2. Prune already explored nodes and walls from the next square search.
3. If the `X` square is reached, stop exploration and retrace the setps followed to reach this square (this is because, when we follow this approach, we will always reach `X` in the least possible amount of steps.
4. Repeat from step 1.

## Changing program behaviour

By modifying `Main.java: 5` you can modify the width and height of the maze. However, be aware that mazes with large sizes might take a long time to solve or might cause recursion errors.

## Future improvements

In the future, I would like to display all paths that reach `X` that are the shortest and of equal length (for example, if there's a loop in the maze and it takes the same to reach `X` via different routes).
