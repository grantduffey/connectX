default: cpsc2150/connectX/BoardPosition.java cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameScreen.java cpsc2150/connectX/IGameBoard.java
	javac cpsc2150/connectX/BoardPosition.java cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameScreen.java cpsc2150/connectX/IGameBoard.java
run: default
	java cpsc2150/connectX/GameScreen
clean:
	rm cpsc2150/connectX/*.class
test: cpsc2150/connectX/BoardPosition.java cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameScreen.java cpsc2150/connectX/IGameBoard.java cpsc2150/connectX/TestGameBoard.java
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/connectX/BoardPosition.java cpsc2150/connectX/GameBoard.java cpsc2150/connectX/GameScreen.java cpsc2150/connectX/IGameBoard.java cpsc2150/connectX/TestGameBoard.java cpsc2150/connectX/TestGameBoardMem.java
testGB: test
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.connectX.TestGameBoard
testGBMem: test
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.connectX.TestGameBoardMem
