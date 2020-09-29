# SpaceTravel coding challenge
This project is my solution to a small coding challenge. 
Problem domain is traveling between star systems in space.

The first five exercises specifies routes between stars and want the distance.
All other exercises have different problems.

## Project structure
The project contains two folders, test and source.
Test bundles all exercises of the challenge. 
The implementation of the world can be found inside main.  

Each implemented class should only contains functions that are related to one element of the problem domain.
So there are, like in many other graph solution implementations, two classes. StarSystem for nodes 
and SpaceHighway for edges. Each contains functions for creating Objects and
adding relations between nodes and edges. 
Because we have directed graphs, we can differ between ramps and exits of SpaceHighways.

The Universe class contains all general logic that is needed for creating nodes and edges, 
search for routes and distances between them.
Each Unit test is calling a universe method.

StarRoute saves SpaceHighways between a start and end StarSystem. 
It also defines the allowed depth of the routes. Default is 10, 
the number of highways defined in the coding challenge. It can be changed via constructor.
Like in any other project class, the idea of this class is to have a single place for the logic around 
a single problem object.