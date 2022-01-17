Monster Card Trading Game Documentation

Developer : Ahmet Satilmis - if19b095 Date: 07/2021 - 01/2021

Project description

The purpose of this project is to make a card game running on an Http/Rest server. No framework was used in the project.
This is a maven project. Communication between client and server is provided by a BufferReader placed on port 8001.
Values sent to BufferReader are in URL format. What is expected from the server is parsing the URL sent by the client,
understand the user request and answer the question appropriately. The data required for the game is stored in the MySQL
database. The server opens a new thread for each new client and prevents possible confusion.

Battle Logic

Step 1: For each tournament, 4 different players are expected to have entered the same lobby. 
Step 2: The card decksowned by the players entering the lobby are reported to the user. 
Step 3: The user selects the card pack they want touse for battle. 
Step 4: Each card pack consists of 5 cards. But the game requires 4 cards. For this reason, the user isasked to remove a card of his choice. 
Step 5: The user enters the war lobby with 4 cards. 
Step 6: After all players haveentered the war lobby with their cards, the battle begins. 
Step 7: Per the tournament, the first and second playerfight. Then the third and fourth players fight. The winners and losers of these wars fight among themselves again. 
Step 8: After the tournament is over, the first player receives 5 points, the second receives 3 points, the third receives -1
points and the fourth -3 points. These points are reflected in the player's elo score in the database. 
Step 9: Players return to the lobby. The data of each finished tournament is cleared from the database at the end of the war.

Check List:

1. Implements a server listening to incoming clients: done
2. Implements multiple threads to serve client requests: done
3. Does not use an HTTP helper framework: none used
4. Uses a Database for storing data: yes
5. Does not allow for SQL injection: none injection
6. Does not use an OR-Mapping Library: none used
7. Implements Unit Tests: only 10 unit test
8. Register and login users, user state management : done.
9. Create and acquire packages: there is 20 packages, that user can buy but creating new package is not implemented.
10. Show and configure decks: done.
11. Edit user data, Profile-Page: user can change username and password
12. Stats and ScoreBoard: Elo list ist implemented and user can see the list.
13. Trading: not implemented. only remove card from desk.
14. Battle-Logic, Play rounds: there is 4 match for every tournament.
15. Draw possible: Not implemented.
16. Clean log of battle : done.
17. Take-over cards after loss of a round : Take over card before the round start.
19. Consider specialities in battle-rounds between cards: Only the strong card win always.
18. ELO calculation : Done.
19. Token-based security : Not implemented.
20. Persistence (DB) : Done.
21. Unit Tests : Done. Not enough but there are small test cases.
22. Integration Tests (curl or alternatively a custom app, working automatically) : I prepared a client app as
    alternative to CURL.

Link to GIT: https://github.com/ahmets1993/MCTG_2022

Project duration: more than 150 hours, because I wrote this code 3 times. I tried spring and glassfisch frameworks to
understand REST API better. And this version. Also, I wrote a complete HTTP server. I learned REST API, HTTP protocol,
MVC architecture and Unit testing. Thank you for your time.



