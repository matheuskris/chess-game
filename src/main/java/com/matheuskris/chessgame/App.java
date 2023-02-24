package com.matheuskris.chessgame;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Image;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

public class App {
  
  static String IMAGES_PIECES_LOCATION_FILE = "C:\\Users\\Matheus\\Documents\\JAVA DEVELOPMENT\\chessPieces.png";
  public static LinkedList<Piece> piecesList = new LinkedList<>();
  public static Piece selectedPiece = null;
  public static JFrame frame;
  public static Boolean isWhiteTurn = true;
  public static ArrayList<Integer[]> possiblePositions = new ArrayList<>();

  public static void main( String[] args ) throws IOException {

    Image imgs[] = getChessPiecesImages();

    getChessPieces(piecesList);

    frame = new JFrame();
    frame.setBounds(10, 10, 512, 512);
    frame.setUndecorated(true); 

    JPanel mainPanel = new CustomPanel(piecesList, imgs);

    frame.add(mainPanel);
    frame.addMouseListener(new MyMouseListener());
    frame.addMouseMotionListener(new MyMouseListener());
    frame.setDefaultCloseOperation(3);
    frame.setVisible(true);

    System.out.println("The game Started, now is white turn");
  }

  private static Image[] getChessPiecesImages() throws IOException {
    final File CHESS_PIECE_IMAGE = new File(IMAGES_PIECES_LOCATION_FILE);
    final BufferedImage TOTAL_IMAGE = ImageIO.read(CHESS_PIECE_IMAGE);

    Image imgs[] = new Image[12];
    
    int ind=0;
    
    for(int y=0;y<400;y+=200){
      for(int x=0;x<1200;x+=200){
        imgs[ind] = TOTAL_IMAGE
          .getSubimage(x, y, 200, 200)
          .getScaledInstance(64, 64, BufferedImage.SCALE_SMOOTH);

        ind++;
      }   
    }

    return imgs;
  }

  private static void getChessPieces (LinkedList<Piece> pieces) {
    Piece brook = new Piece(0, 0, false, PieceType.ROOK, pieces);
    Piece bkinght = new Piece(1, 0, false, PieceType.KNIGHT, pieces);
    Piece bbishop=new Piece(2, 0, false, PieceType.BISHOP, pieces);
    Piece bqueen=new Piece(3, 0, false, PieceType.QUEEN, pieces);
    Piece bking=new Piece(4, 0, false, PieceType.KING, pieces);
    Piece bbishop2=new Piece(5, 0, false, PieceType.BISHOP, pieces);
    Piece bkight2=new Piece(6, 0, false, PieceType.KNIGHT, pieces);
    Piece brook2=new Piece(7, 0, false, PieceType.ROOK, pieces);
    Piece bpawn1=new Piece(1, 1, false, PieceType.PAWN, pieces);
    Piece bpawn2=new Piece(2, 1, false, PieceType.PAWN, pieces);
    Piece bpawn3=new Piece(3, 1, false, PieceType.PAWN, pieces);
    Piece bpawn4=new Piece(4, 1, false, PieceType.PAWN, pieces);
    Piece bpawn5=new Piece(5, 1, false, PieceType.PAWN, pieces);
    Piece bpawn6=new Piece(6, 1, false, PieceType.PAWN, pieces);
    Piece bpawn7=new Piece(7, 1, false, PieceType.PAWN, pieces);
    Piece bpawn8=new Piece(0, 1, false, PieceType.PAWN, pieces);
    
    Piece wrook=new Piece(0, 7, true, PieceType.ROOK, pieces);
    Piece wkinght=new Piece(1, 7, true, PieceType.KNIGHT, pieces);
    Piece wbishop=new Piece(2, 7, true, PieceType.BISHOP, pieces);
    Piece wqueen=new Piece(3, 7, true, PieceType.QUEEN, pieces);
    Piece wking=new Piece(4, 7, true, PieceType.KING, pieces);
    Piece wbishop2=new Piece(5, 7, true, PieceType.BISHOP, pieces);
    Piece wkight2=new Piece(6, 7, true, PieceType.KNIGHT, pieces);
    Piece wrook2=new Piece(7, 7, true, PieceType.ROOK, pieces);
    Piece wpawn1=new Piece(1, 6, true, PieceType.PAWN, pieces);
    Piece wpawn2=new Piece(2, 6, true, PieceType.PAWN, pieces);
    Piece wpawn3=new Piece(3, 6, true, PieceType.PAWN, pieces);
    Piece wpawn4=new Piece(4, 6, true, PieceType.PAWN, pieces);
    Piece wpawn5=new Piece(5, 6, true, PieceType.PAWN, pieces);
    Piece wpawn6=new Piece(6, 6, true, PieceType.PAWN, pieces);
    Piece wpawn7=new Piece(7, 6, true, PieceType.PAWN, pieces);
    Piece wpawn8=new Piece(0, 6, true, PieceType.PAWN, pieces);
  }

  public static Piece getPiece(int x, int y){
    int drawnX = x/64;
    int drawnY = y/64;

    for(Piece p: piecesList){
      if(p.xp==drawnX && p.yp == drawnY){
        return p;
      }
    }
    return null;
  }
}
