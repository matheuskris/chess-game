package com.matheuskris.chessgame;

import java.awt.event.*;

public class MyMouseListener implements MouseListener, MouseMotionListener {
  @Override
  public void mousePressed(MouseEvent e){
    Piece pieceClicked = App.getPiece(e.getX(), e.getY());
    if(pieceClicked.isWhite == App.isWhiteTurn){
      App.selectedPiece = pieceClicked;

      for(int y = 0; y < 8; y++){
        for (int x = 0; x < 8; x++){
           if(pieceClicked.canMove(x, y)){
            Integer[] list = new Integer[2];
            list[0] = x;
            list[1] = y;
            App.possiblePositions.add(list);
           }
        }
      }
    }
  }
  @Override
  public void mouseReleased(MouseEvent e){
    if(App.selectedPiece != null){
      App.selectedPiece.move(e.getX() / 64, e.getY() / 64);
      App.possiblePositions.clear();
      App.frame.repaint();
      App.selectedPiece = null;
    }
  }
  @Override
  public void mouseExited(MouseEvent e){}
  @Override
  public void mouseEntered(MouseEvent e){}
  @Override
  public void mouseClicked(MouseEvent e){}
  @Override
  public void mouseDragged(MouseEvent e) {
    if(App.selectedPiece != null){
      App.selectedPiece.x = e.getX() - 32;
      App.selectedPiece.y = e.getY() - 32 ;
      App.frame.repaint();
    }
  }
  @Override
  public void mouseMoved(MouseEvent e) {}
}
