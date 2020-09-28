# SpaceTravel coding challenge
This project is my solution to a small coding challenge. 
Problem domain is traveling between star systems in space.

The first five exercises specifies routes between stars and want the distance.
All other exercises have different problems.

## Project structure
The project contains two folders, test and source.
Test bundles all exercises of the challenge. 
The implementation of the world can be found inside src. 

Like many other Graph solution implementations there are two classes, StarSystem for knots 
and SpaceHighway for edges. Each contains functions for interaction between both. 
Because we have directed graphs, we can differ between ramps and exits of SpaceHighways.

The Universe class contains all logic for creating graphs, search for routes and distances between them.
Each Unit test is calling a universe method.

StarRoute saves SpaceHighways between a start and end StarSystem. 
It also defines the allowed depth of the routes. Default is 10, 
the number of highways defined in the coding challenge. It can be changed via constructor.