# Pyramid Solitaire

1. Types of Games
- Relaxed: Remove and card in the pyramid
- Basic: Remove cards only exposed
- Multi: Total 104 cards, lay in three pyramid, remove only the exposed cards

2. How to start the game
```java
// change the game model by changing PyramidSolitaireCreator.GameType.THETYEPYOUWANT
PyramidSolitaireModel model = PyramidSolitaireCreator.create(PyramidSolitaireCreator.GameType.MULTIPYRAMID);
PyramidSolitaireController controller = new PyramidSolitaireTextualController(new InputStreamReader(System.in),System.out);
controller.playGame(model,model.getDeck(),false,7,3);
```
- Click run and start the game

3. How to play the game
- rm1 row col: Remove single card(K)
- rm2 row1 col1 row2 col: Remove two card sum 13
- rmwd drawIndex row col: Remove card with card in draw pile
- dd drawIndex: Discard a card in drawCardPile
- q or Q: Quit the game