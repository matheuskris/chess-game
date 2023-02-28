package com.matheuskris.chessgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JLabel;


public class AsidePanel extends JPanel implements ActionListener {

  JButton restartButton;
  String turnColor;
  public JLabel currentTurnBox;

  public AsidePanel () {
    restartButton = new JButton();
    restartButton.setPreferredSize(new Dimension(130, 40));
    restartButton.addActionListener(this);
    restartButton.setText("Restart Game");

    this.setBackground(Color.GRAY);
    this.setPreferredSize(new Dimension(200, 512));
    this.add(restartButton);

    currentTurnBox = new JLabel("Current Turn: White");
    this.add(currentTurnBox);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == restartButton){
      System.out.println("eita");
      App.restartGame();
    }
  };
}
