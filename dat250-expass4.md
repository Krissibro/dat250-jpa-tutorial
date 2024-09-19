## Expass 4

All tests pass!
I decided to change the java code to kotlin, because kotlin is awesome :)

Me and Brian Jensvoll-Jørgensen live together and met alot of the same problems, so we worked together to solve them.

### Technical problems

I had some issues getting kotlin to work at first, but after some finicking, I got it to work nicely.

I had some problems with sets and their use in the test.
I used lists originally, but the program was not able to compare the lists to the sets in the tests.
I figured this out later and changed all the necessary lists to sets.

When I changed all the necessary lists to sets, I met another problem.
Their hashing functions caused infinite loops when referencing each other. 
I fixed this by overriding the hash and equals functions such that it used the generated ids instead.

I also had to add custom .toString() methods to avoid further infinite recursions.



