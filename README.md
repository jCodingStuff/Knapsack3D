# Knapsack3D
This is the Project 1 - Part 3 of the Data Science and Knowledge Engineering Bachelor at Maastricht University.  
The code was written in collaboration with Lucas Giovanni Uberti-Bona Marin, Silvia Fallone, Antonio Rodriguez, Sarah Waseem and Pierre Bongrand.

The problem consisted in the following: we were given a set of 3D objects (each with its dimensions and value) and asked to pack them in a certain cargo, in order to maximize profit. In other words, a 3-Dimensional Knapsack Problem.  
The sets of objects can be split in two categories:  
 - Parcels: boxes of types A (1.0x1.0x2.0), B (1.0x1.5x2.0) and C (1.5x1.5x1.5).
 - Pentominoes: of types L, P, and T (each one made of 1.0x1.0x1.0 boxes). 
Since half a unit cannot be represented using half a matrix cell, the dimensions are duplicated.

In order to solve this problems, three algorithms were implemented:  
 - Greedy: selecting the local maximum each iteration hoping that this will lead to the global maximum. It has two variants: deterministic (selecting pieces by their value/volume ratio) and random (selecting pieces randomly).
 - Depth-First Recursive Backtracking: explore all possible scenarios.
 - Divide and Conquer & Dynamic Programming.

From my point of view, it is worth to dive into the third algorithm. In order to do so, I will take a snippet from the presentation:  
"Pursuing the goal of finding a fairly fast algorithm that provides good results for both volume and value, Divide and Conquer techniques were combined with Dynamic Programming.  
It is known that Dynamic Programming uses sub-solutions of the problem, constructed from previously found ones, to give a solution to the problem. Despite that, it is hard to use and implement for multidimensional problems, like this one.  
On the other hand, Divide and Conquer divides the problem into smaller sub-problems that are later combined.  
Then, merging both procedures, some sub-cargos are solved, and later put together to fill the final container. More precisely, Divide and Conquer transforms the problem to 1 dimension which is later solved by Dynamic Programming.

At this situation, one question may arise: How is that done?

First of all, the longest dimension of the cargo is selected (width, height or depth).  
For instance, let’s assume that from now onwards width is the selected one.  
Following that, leaving height and depth (the non selected) constant, width is modified, creating slices from 1 unit up to a limit specified by the user.  
Then, the algorithm tries to solve each slice (fill it completely) using the Backtracking algorithm, and if a certain one is solved, its content and value is saved for later usage.  
As soon as that last step is done, Dynamic programming is used to obtain the combination of solved slices that provides the best value. All of this, given that the sum of the width of the slices must be less or equal that the width of the cargo.

Regarding the strong and weak points of this algorithm:  
 - It is really fast.  
 - It optimizes both volume and value, since it only takes into account fully filled slices.  
 - However, deciding its limit is serious business:  
   - A low limit may provide a short runtime, in exchange of a probably poor profit.  
   - A high limit will give the chance of a better profit, in exchange of a longer runtime."

The application developed in this git reposity allows the user to work with parcels or pentominoes, allowing him or her to customize the dimensions for the cargo and the values for parcels / pentominoes.

Controls:  
 - Drag using the left click to rotate the 3D model.
 - Drag up and down using the right click to use the zoom feature.

Project presentation: https://docs.google.com/presentation/d/1U4Vh8hvta7GG8O-A-Xe1GhkD8qWGQZ6rAC0MlslYCHk/edit?usp=sharing   
Project report: https://docs.google.com/document/d/1B1Tnce2YDTQmLGugcAWCyg0NxO6ezdKuLvTs-reAbTg/edit?usp=sharing
