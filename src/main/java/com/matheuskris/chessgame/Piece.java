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

    Boolean itCanMove = false;
    for (Integer[] i : App.possiblePositions) {
      if(i[0] == newXp && i[1] == newYp){
        itCanMove = true;
      }
    }

    if(!itCanMove){
      x = xp * 64;
      y = yp * 64;
      return;
    }

    if(pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) {
      pieceInTheFutureLocation.kill();
    }

    System.out.println(xp + "," + yp + "," + newXp + "," + newYp);

    this.xp = newXp;
    this.yp = newYp;
    x = xp * 64;
    y = yp * 64;
    String turn = App.isWhiteTurn ? "black" : "white";
    App.ChangeTurn(turn);
  }

  public void kill() {
    if(this.name == PieceType.KING){
      App.restartGame();
    }
    listOfPieces.remove(this);
  }

  // public Boolean canMove(int possibleXp, int possibleYp){
  //   Boolean canIt = false;

  //   switch(name){
  //     case PAWN:
  //       Piece pieceInTheFutureLocation = App.getPiece( possibleXp*64 , possibleYp*64);
  //       if(isWhite){
  //         if(yp == 6){
  //           if((possibleYp == 5 || possibleYp == 4) && possibleXp == xp && pieceInTheFutureLocation == null){
  //             canIt = true;
  //           }
  //         } else {
  //           if(possibleYp == yp - 1 && possibleXp == xp && pieceInTheFutureLocation == null){
  //             canIt = true;
  //           }
  //         }
  //         if(possibleYp == yp - 1 && (possibleXp == xp + 1 || possibleXp == xp - 1) && pieceInTheFutureLocation != null){
  //           canIt = true;
  //         }
  //       } else {
  //         if(yp == 1){
  //           if((possibleYp == 2 || possibleYp == 3) && possibleXp == xp){
  //             canIt = true;
  //           }
  //         } else {
  //           if(possibleYp == yp + 1 && possibleXp == xp){
  //             canIt = true;
  //           }
  //         }
  //         if(possibleYp == yp + 1 && (possibleXp == xp + 1 || possibleXp == xp - 1) && pieceInTheFutureLocation != null){
  //           canIt = true;
  //         }
  //       }
  //       break;
  //     case BISHOP:
  //       if(possibleXp-xp == possibleYp-yp || possibleXp-xp == -possibleYp+yp){
  //         canIt = true;
  //       }
  //       break;
  //     case KING:
  //       if(possibleXp - xp <= 1 && possibleYp - yp <= 1 && possibleXp - xp >= -1 && possibleYp - yp >= -1){
  //         canIt = true;
  //       }
  //       break;
  //     case KNIGHT:
  //       int moveInX = Math.abs(possibleXp - xp);
  //       int moveInY = Math.abs(possibleYp - yp);

  //       if((moveInX == 1 && moveInY == 2) || (moveInX == 2 && moveInY == 1)){
  //         canIt = true;
  //       }
  //       break;
  //     case QUEEN:
  //       if(possibleXp - xp == possibleYp - yp || possibleXp - xp == - possibleYp + yp){
  //         canIt = true;
  //       }
  //       if(possibleXp == xp || possibleYp == yp){
  //         canIt = true;
  //       }
  //       break;
  //     case ROOK:
  //       if(possibleXp == xp || possibleYp == yp){
  //         canIt = true;
  //       }
  //   }
    
  //   return canIt;
  // }

  public void findPossiblePositions () {
    int possibleXp = xp;
    int possibleYp = yp;

    Piece pieceInTheFutureLocation;

    switch(name){
      case PAWN:
        if(isWhite){
          if(yp == 6){
            possibleYp = yp - 1;
            pieceInTheFutureLocation = App.getPiece( possibleXp * 64 , possibleYp * 64);
            if(pieceInTheFutureLocation == null){
              addToPossibleList(possibleXp, possibleYp);
              possibleYp --;
              pieceInTheFutureLocation = App.getPiece( possibleXp * 64 , possibleYp * 64);
              if(pieceInTheFutureLocation == null){
                addToPossibleList(possibleXp, possibleYp);
              }
            }

          } else {
            possibleYp = yp - 1;
            pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
            if(pieceInTheFutureLocation == null){
              addToPossibleList(possibleXp, possibleYp);
            };
          }
          possibleXp = xp - 1;
          possibleYp = yp - 1;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
          if(pieceInTheFutureLocation != null){
            addToPossibleList(possibleXp, possibleYp);
          };
          possibleXp = xp + 1;
          possibleYp = yp - 1;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
          if(pieceInTheFutureLocation != null){
            addToPossibleList(possibleXp, possibleYp);
          };
        } else {
          if(yp == 1){
            possibleYp = yp + 1;
            pieceInTheFutureLocation = App.getPiece( possibleXp * 64 , possibleYp * 64);
            if(pieceInTheFutureLocation == null){
              addToPossibleList(possibleXp, possibleYp);
              possibleYp ++;
              pieceInTheFutureLocation = App.getPiece( possibleXp * 64 , possibleYp * 64);
              if(pieceInTheFutureLocation == null){
                addToPossibleList(possibleXp, possibleYp);
              }
            }

          } else {
            possibleYp = yp + 1;
            pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
            if(pieceInTheFutureLocation == null){
              addToPossibleList(possibleXp, possibleYp);
            };
          }
          possibleXp = xp + 1;
          possibleYp = yp + 1;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
          if(pieceInTheFutureLocation != null){
            addToPossibleList(possibleXp, possibleYp);
          };
          possibleXp = xp - 1;
          possibleYp = yp + 1;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
          if(pieceInTheFutureLocation != null){
            addToPossibleList(possibleXp, possibleYp);
          };
        }
        
        break;
      case BISHOP:

        while(possibleXp < 8 && possibleYp < 8){
          possibleXp ++;
          possibleYp ++;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if(pieceInTheFutureLocation != null){
            possibleXp = 8;
          }
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
        }

        possibleXp = xp;
        possibleYp = yp;

        while(possibleXp >= 0 && possibleYp < 8){
          possibleXp --;
          possibleYp ++;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if(pieceInTheFutureLocation != null){
            possibleXp = -2;
          }
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
        }

        possibleXp = xp;
        possibleYp = yp;

        while(possibleXp < 8 && possibleYp >= 0){
          possibleXp ++;
          possibleYp --;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if(pieceInTheFutureLocation != null){
            possibleXp = 8;
          }
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
        }

        possibleXp = xp;
        possibleYp = yp;

        while(possibleXp >= 0 && possibleYp >= 0){
          possibleXp --;
          possibleYp --;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if(pieceInTheFutureLocation != null){
            possibleXp = -1;
          }
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
        }

        break;
      case KNIGHT:
        // 1 caso!!
        possibleXp += 2;
        possibleYp --;
        pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
        if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
        }
        // 2 caso!!
        possibleYp +=2;
        pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
        if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
        }
        // 3 caso!!
        possibleYp ++;
        possibleXp --;
        pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
        if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){  
          addToPossibleList(possibleXp, possibleYp);
        }
        // 4 caso!!
        possibleXp -= 2;
        pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
        if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
        }
        // quinto caso!!
        possibleXp --;
        possibleYp --;
        pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
        if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
        }
        // 6 caso!!
        possibleYp -= 2;
        pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
        if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
        }
        // 7 caso!!
        possibleYp --;
        possibleXp ++; 
        pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
        if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
        }
        // 8 caso!!
        possibleXp += 2; 
        pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
        if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
        }
        break;
      case QUEEN:
        while(possibleXp < 8 && possibleYp < 8){
          possibleXp ++;
          possibleYp ++;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if(pieceInTheFutureLocation != null){
            possibleXp = 8;
          }
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
        }

        possibleXp = xp;
        possibleYp = yp;

        while(possibleXp >= 0 && possibleYp < 8){
          possibleXp --;
          possibleYp ++;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if(pieceInTheFutureLocation != null){
            possibleXp = -2;
          }
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
        }

        possibleXp = xp;
        possibleYp = yp;

        while(possibleXp < 8 && possibleYp >= 0){
          possibleXp ++;
          possibleYp --;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if(pieceInTheFutureLocation != null){
            possibleXp = 8;
          }
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
        }

        possibleXp = xp;
        possibleYp = yp;

        while(possibleXp >= 0 && possibleYp >= 0){
          possibleXp --;
          possibleYp --;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if(pieceInTheFutureLocation != null){
            possibleXp = -1;
          }
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
        }
        possibleXp = xp;
        possibleYp = yp;
        // #1
        while(possibleYp < 8){
          possibleYp ++;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
          if(pieceInTheFutureLocation != null){
            possibleYp = 8;
          }
        }

        possibleXp = xp;
        possibleYp = yp;
        // #2
        while(possibleYp >= 0){
          possibleYp --;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
          if(pieceInTheFutureLocation != null){
            possibleYp = -1;
          }
        }

        possibleXp = xp;
        possibleYp = yp;
        // #3
        while(possibleXp < 8){
          possibleXp ++;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
          if(pieceInTheFutureLocation != null){
            possibleXp = 8;
          }
        }

        possibleXp = xp;
        possibleYp = yp;
        // #4
        while(possibleXp >= 0){
          possibleXp --;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
          if(pieceInTheFutureLocation != null){
            possibleXp = -1;
          }
        }
        break;
      case ROOK:
        // #1
        while(possibleYp < 8){
          possibleYp ++;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
          if(pieceInTheFutureLocation != null){
            possibleYp = 8;
          }
        }

        possibleXp = xp;
        possibleYp = yp;
        // #2
        while(possibleYp >= 0){
          possibleYp --;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
          if(pieceInTheFutureLocation != null){
            possibleYp = -1;
          }
        }

        possibleXp = xp;
        possibleYp = yp;
        // #3
        while(possibleXp < 8){
          possibleXp ++;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
          if(pieceInTheFutureLocation != null){
            possibleXp = 8;
          }
        }

        possibleXp = xp;
        possibleYp = yp;
        // #4
        while(possibleXp >= 0){
          possibleXp --;
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp *64);
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
          if(pieceInTheFutureLocation != null){
            possibleXp = -1;
          }
        }

        break;
      case KING:
        Integer[] Xpositions = {-1, +1, +1, 0, 0, -1, -1, 0};
        Integer[] Ypositions = {-1, 0, 0, +1, +1, 0, 0, -1};
        for(int i = 0; i<8; i++){
          possibleXp += Xpositions[i];
          possibleYp += Ypositions[i];
          pieceInTheFutureLocation = App.getPiece(possibleXp * 64 , possibleYp * 64);
          if((pieceInTheFutureLocation != null && pieceInTheFutureLocation.isWhite != isWhite) || pieceInTheFutureLocation == null){
            addToPossibleList(possibleXp, possibleYp);
          }
        }
        
        // int moveInX = Math.abs(possibleXp - xp);
        // int moveInY = Math.abs(possibleYp - yp);
        break;
    }
  }

  private void addToPossibleList (int possX,int possY) {
    Integer[] list = new Integer[2];
    list[0] = possX;
    list[1] = possY;
    App.possiblePositions.add(list);
  }
}
