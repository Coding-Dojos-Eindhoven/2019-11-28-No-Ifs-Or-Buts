No Ifs Or Buts
==============

This repository contains materials for the coding dojo of November 28, 2019.

Battleships
-----------

We're going to implement the board game 'Battleships'. It's a two-player game.
Each player has a 10x10 grid on which they place their ships:

- A carrier, which is 5 cells long
- A battleship: 4 cells
- A cruiser: 3 cells
- A submarine: 3 cells
- A destroyer: 2 cells

The winner of the game is the player who first finds all their opponent's ships.

First both players set up their boards: they place their ships in such a way
that the other player cannot see where. Ships can be oriented horizontally and
vertically, not diagonally. It's fine if they lie next to each other, but they
cannot overlap of course.

The players take turns guessing. Each guess consists of a column (A-J) and a row
(1-10). The response to a guess is either "hit" or "miss", which is marked using
a red or white peg, respectively. When all cells of one of your ships are hit,
you announce that the ship has sunk and which ship it was.

That was only a brief explanation; for a more complete coverage of the game
please head over [here][game_rules]. And here's a [video][game_video] for the
more visually oriented.

Challenge
---------

Your challenge is to implement the game *without using conditional logic*. So
you **cannot** use:

- `if` statements
- `case`/`switch` statements
- ternary operator (`<cond> ? <when-true> : <when-false>`)
- loops with conditions (e.g. `for`, `while`, etc.)
- `try`/`catch`

Whenever you want to use a construct and you're not sure if you're allowed to,
you're probably not. The goal is to learn about different ways of thinking, ways
that don't require conditions. We're taking it here to the extreme, because then
you'll probably learn more. In "real life" we do not recommend doing this,
unless you want to annoy your colleagues or your future-self of course.

You can use any programming language / editor / IDE of your choice. As always,
please work in pairs and use [TDD][three_laws_of_tdd] (yes,
[really][giving_up_on_tdd]).


[game_rules]: https://www.thesprucecrafts.com/the-basic-rules-of-battleship-411069
[game_video]: https://invidio.us/watch?v=4gHJlYLomrs
[three_laws_of_tdd]: http://www.butunclebob.com/ArticleS.UncleBob.TheThreeRulesOfTdd
[giving_up_on_tdd]: https://blog.cleancoder.com/uncle-bob/2016/03/19/GivingUpOnTDD.html
