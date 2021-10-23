// Jonathan Yin
// A16283428
// joyin@ucsd.edu

/* This program is used to solve mazes via Dijkstra's algorithm and using cost minimization. 
 * It is used with the Maze class to find the path of minimal cost for Maze objects.
*/

import java.util.ArrayList;

public abstract class MazeSolver {
	/*
	 * This class contains one method used for solving mazes, using Dijkstra's algorithm.
	 */
	
	/**
	 * Solves and returns the finishing square of a maze, should a solution exist.
	 * @param maze The maze to solve
	 * @param pq The PriorityQueue used for tracking squares
	 * @return the finishing Square 
	 */
	public static Square solve(Maze maze, PriorityQueue<Integer,Square> pq) {
		// add start square to the heap
		pq.add(maze.start.getCost(), maze.start);
		
		// while pq is not empty
		while (!pq.isEmpty()) {
			Entry<Integer, Square> current = pq.poll();
			Square currentSquare = current.value;
			// mark current square as visited
			currentSquare.visit();
			
			// if the current square is the finishing square
			if (currentSquare.equals(maze.finish)) {
				return currentSquare;
			}
			else {
				ArrayList<Square> neighbors = new ArrayList<Square>();
				
				// North neighbor
				if (currentSquare.getRow() - 1 >= 0 &&
						!maze.contents[currentSquare.getRow() - 1]
								[currentSquare.getCol()].getIsWall() && 
								!maze.contents[currentSquare.getRow() - 1]
										[currentSquare.getCol()].isVisited()) {
					// if this neighbor is within bounds, is not a wall, and has not been visited
					neighbors.add(maze.contents[currentSquare.getRow() - 1]
								[currentSquare.getCol()]);
					
				}
				
				// South neighbor
				if (currentSquare.getRow() + 1 < maze.rows &&
						!maze.contents[currentSquare.getRow() + 1]
								[currentSquare.getCol()].getIsWall() && 
								!maze.contents[currentSquare.getRow() + 1]
										[currentSquare.getCol()].isVisited()) {
					// if this neighbor is within bounds, is not a wall, and has not been visited
					neighbors.add(maze.contents[currentSquare.getRow() + 1]
								[currentSquare.getCol()]);
					
				}
				
				// East neighbor
				if (currentSquare.getCol() + 1 < maze.cols &&
						!maze.contents[currentSquare.getRow()]
								[currentSquare.getCol() + 1].getIsWall() && 
								!maze.contents[currentSquare.getRow()]
										[currentSquare.getCol() + 1].isVisited()) {
					// if this neighbor is within bounds, is not a wall, and has not been visited
					neighbors.add(maze.contents[currentSquare.getRow()]
								[currentSquare.getCol() + 1]);
					
				}
				
				// West neighbor
				if (currentSquare.getCol() - 1 >= 0 &&
						!maze.contents[currentSquare.getRow()]
								[currentSquare.getCol() - 1].getIsWall() && 
								!maze.contents[currentSquare.getRow()]
										[currentSquare.getCol() - 1].isVisited()) {
					// if this neighbor is within bounds, is not a wall, and has not been visited
					neighbors.add(maze.contents[currentSquare.getRow()]
								[currentSquare.getCol() - 1]);
					
				}
				
				// for each neighbor of current square in the order of NSEW
				for (Square neighbor : neighbors) {
					// set current cost to the current key + neighbor's cost
					int currentCost = current.key + neighbor.getCost();
					
					// if the current cost is less than neighbor's running cost
					if (currentCost < neighbor.getRunningCost()) {
						
						// set the previous of the neighbor to the current square
						neighbor.setPrevious(currentSquare);
						// set the neighbor's running cost to currentCost
						neighbor.setRunningCost(currentCost);
						
						// add currentCost as key and neighbor as value to pq
						pq.add(currentCost, neighbor);
					}
				}
			}
		}
		// if no path is found, return null
		return null;
	}
}
	
