package com.matheuskris.chessgame;

import javax.swing.JFrame;

public class CustomFrame extends JFrame {

  public CustomFrame () {

    this.setSize(700, 800);
    this.addMouseListener(new MyMouseListener());
    this.addMouseMotionListener(new MyMouseListener());
    this.setDefaultCloseOperation(3);
    // this.add(restartButton); 
    this.setVisible(true);
    
  }
}
