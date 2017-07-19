/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Huynh Thanh
 */
public class Game extends JFrame{
     Swing swing;
       TicTacToeCatTia b;

    public Game(){
     b = new TicTacToeCatTia();
     swing = new Swing();
     swing.setGame(this);
   
        this.setContentPane(swing);
        swing.repaint();
        this.pack();
        this.setLocation(120, 80);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
     swing.setMap(b.getBoard());
      swing.update();
      
      
      
         Random rand = new Random();

    b.displayBoard();

    System.out.println("chọn người đi trước (1)máy  (2)bạn ");
    int choice = b.scan.nextInt();
    if (choice == 1) {
        Node p = new Node(rand.nextInt(5), rand.nextInt(5));
        b.placeAMove(p, 1);
        b.displayBoard();
    }
 
  
    }
    public void danh( int x , int y){
       if (!b.isGameOver()) {
        Node userMove = new Node(x, y);
        b.placeAMove(userMove, 2); 
        b.displayBoard();
        if(!b.isGameOver()){
        b.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
        for (Diem pas : b.rootsChildrenScore) 
            System.out.println("tọa độ: " + pas.point + "điểm: " + pas.score);
        swing.setMap(b.getBoard());
           swing.update();
        b.placeAMove(b.returnBestMove(), 1);
        b.displayBoard();
        }
        }
        if(b.isGameOver()){
    if (b.hasXWon()) {
        System.out.println("Qua tệ bạn đã thua!");
    } else if (b.hasOWon()) {
        System.out.println("Bạn đã thắng");
    } else {
        System.out.println("Hòa!");
    }
     }
    }
    
      public static  void main(String [] agr ){
          Game g = new Game();
       
        

    
 
//  
 
     }
  
    

}
