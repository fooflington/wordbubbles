WordBubbles Solver
==================

This is a brute-force-ish solver for games such as [Boggle](http://www.amazon.co.uk/Hasbro-Boggle/dp/B016I77JSO), [Ruzzle](http://ruzzle-game.com/) or [WordBubbles](https://itunes.apple.com/us/app/wordbubbles!/id922488002?mt=8) which require the player to traverse a grid of letters to form words without visiting the same letter twice.

Pre-requisites
--------------

 1. You will need a word list of some sort! Any list of words will do and some Linux distributions provide one by default (check somewhere like `/usr/share/dict/words`).
 1. A modern Java

Building
--------

Building ought to be nice and simple:

```
$ javac main.java
```

This should build all the relevant files in the project...

Running
-------

First create a text file with the grid and save it as, for example, `grid.txt`. If you need to include blanks then use a `space` character. You can, optionally, include a line starting with a # which has a list of word lengths to look for. For example:

```
# 6 7
tz p
deak
mssm
 apt
```

Then invoke the solver:

```
$ java main /path/to/wordlist <grid.txt
```

This should output a list of words :-)

### Test run
On my pretty old and crusty machine (1.6GHz Intel Atom) it runs in a couple of seconds:

```
$ time java main /stuff/dicts/twl06 <grid.txt
passed
passed
pampas
pampas
demast
massed
massed
smazes
spasmed
massed
massed
passed
passed

real    0m2.254s
user    0m2.144s
sys     0m0.172s
```
