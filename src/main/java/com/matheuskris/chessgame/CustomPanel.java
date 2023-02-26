package com.matheuskris.chessgame;

import java.util.LinkedList;
import java.awt.Image;
import java.awt.Color;

import javax.swing.JPanel;

public class CustomPanel extends JPanel{

  LinkedList<Piece> piecesList;
  Image[] imagesList;

  public CustomPanel ( LinkedList<Piece> ps, Image[] imgs) {

    this.piecesList = ps;
    this.imagesList = imgs;
  }

  public void paint(java.awt.Graphics g) {
    boolean isWhite = true;

    for(int y = 0; y < 8; y++){
      for (int x = 0; x < 8; x++){
        if(isWhite){
          g.setColor(new Color(235,235, 208));
        } else {
          g.setColor(new Color(119, 148, 85));
        }
        g.fillRect(x*64, y*64, 64, 64);
        isWhite = !isWhite;
      }
      isWhite = !isWhite;
    }
    
    if(App.possiblePositions.size() > 0){
      for(Integer[] position: App.possiblePositions){
        if((position[0] + position[1]) % 2 > 0){
          g.setColor(new Color(119, 180, 85));
        } else {
          g.setColor(new Color(225, 255, 208));
        }
        g.fillRect(position[0]*64, position[1]*64, 64, 64);
      } 
    }

    for(Piece p: piecesList){
      int ind=0;
      if(p.name == PieceType.KING){
          ind=0;
      }
      if(p.name == PieceType.QUEEN){
          ind=1;
      }
      if(p.name == PieceType.BISHOP){
          ind=2;
      }
      if(p.name == PieceType.KNIGHT){
          ind=3;
      }
      if(p.name == PieceType.ROOK){
          ind=4;
      }
      if(p.name == PieceType.PAWN){
          ind=5;
      }
      if(!p.isWhite){
          ind+=6;
      }
      g.drawImage(imagesList[ind], p.x, p.y, this);
    }
  };
}
