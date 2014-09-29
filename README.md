System of linear equations generator
===========

This project generates arbitrary-sized matrices for testing solutions for system of linear equations.

In order to avoid inconsistent systems, it checks for independence of equations and the overall consistency. 

---

Example of usage:

    Sole s = SoleBuilder.generateRandomConsistentInt(32, -10, 10);
    
Where `32` is the number of variables or just the  matrix size. The next parameters define the range of random numbers. 