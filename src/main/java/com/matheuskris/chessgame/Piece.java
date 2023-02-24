package com.matheuskris.chessgame;

import java.util.LinkedList;

enum PieceType {
  PAWN,
  ROOK,
  BISHOP,
  KNIGHT,
  QUEEN,
  KING,
}

public class Piece {
  
  int xp; 
  int yp; 
  int x; // *64 - drawning location
  int y; // *64 - drawning location

  boolean isWhite;
  LinkedList<Piece> listOfPieces;
  PieceType name;

  public Piece(int xp, int yp, boolean isWhite, PieceType name, LinkedList<Piece> ps){
    this.xp = xp;
    this.yp = yp;

    this.x = xp*64;
    this.y = yp*64;

    this.isWhite = isWhite;
    this.listOfPieces = ps;
    this.name = name;
    ps.add(this);
  }

  public void move(int newXp, int newYp) {
    Piece pieceInTheFutureLocation = App.getPiece(newXp*64, newYp*64);
    
    if(pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite == isWhite){
      x = xp * 64;
      y = yp * 64;
      return;
    }

    if(pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) {
      pieceInTheFutureLocation.kill();
    }

    this.xp = newXp;
    this.yp = newYp;
    x = xp * 64;
    y = yp * 64;
    App.isWhiteTurn = !App.isWhiteTurn;
    String turn = App.isWhiteTurn ? "Brancas" : "Pretas";
    System.out.println("agora é turno das peças " + turn);
  }

  public void kill() {
    listOfPieces.remove(this);
  }

  public Boolean canMove(int possibleXp, int possibleYp){
    Boolean canIt = false;

    switch(name){
      case PAWN:
        if(isWhite){
          if(yp == 6){
            if((possibleYp == 5 || possibleYp == 4) && possibleXp == xp){
              canIt = true;
            }
          } else {
            if(possibleYp == yp -1 && possibleXp == xp){
              canIt = true;
            }
          }
        } else {
          if(yp == 1){
            if((possibleYp == 2 || possibleYp == 3) && possibleXp == xp){
              canIt = true;
            }
          } else {
            if(possibleYp == yp + 1 && possibleXp == xp){
              canIt = true;
            }
          }
        }
        break;
      case BISHOP:
        if(possibleXp-xp == possibleYp-yp || possibleXp-xp == -possibleYp+yp){
          canIt = true;
        }
        break;
      case KING:
        if(possibleXp - xp <= 1 && possibleYp - yp <= 1 && possibleXp - xp >= -1 && possibleYp - yp >= -1){
          canIt = true;
        }
        break;
      case KNIGHT:
        int moveInX = Math.abs(possibleXp - xp);
        int moveInY = Math.abs(possibleYp - yp);

        if((moveInX == 1 && moveInY == 2) || (moveInX == 2 && moveInY == 1)){
          canIt = true;
        }
        break;
      case QUEEN:
        if(possibleXp - xp == possibleYp - yp || possibleXp - xp == - possibleYp + yp){
          canIt = true;
        }
        if(possibleXp == xp || possibleYp == yp){
          canIt = true;
        }
        break;
      case ROOK:
        if(possibleXp == xp || possibleYp == yp){
          canIt = true;
        }
    }
    
    return canIt;
  }
}
