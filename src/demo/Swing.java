
package demo;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Swing extends JPanel { ;
    int[][] map ; 
    int robotx =1 ,roboty = 1 ;
    
    Color[] color;         
    int rows = 5;         
    int columns = 5;     
    int border = 100;      
    int sleepTime =10000;
    int speedSleep = 10;   
    int blockSize = 100;   

    int width = -1;  
    int height = -1; 
    int w , h ;
    int totalWidth;   
    int totalHeight; 
    int left;        
    int top; 
    
    final static int backgroundCode = 0;
    final static int wallCode = 1;
    final static int emptyCode = 0;  
    
    private BufferedImage imgx , imgy;
    public Game game ;
    public Swing() {
         try {
            imgx = ImageIO.read(new File("img/Caro_X.png"));
            imgy = ImageIO.read(new File("img/Caro_O.png"));
        } catch (IOException ex) {
            
        }
         resizeIcon();
        checkSize();
        
       map = new int[rows][columns];
        color = new Color[] {
            new Color(200,0,0),
            new Color(128,128,255),
            new Color(128,128,255),
            Color.WHITE,
            new Color(200,200,200)
        };
      
        setPreferredSize(new Dimension(blockSize*columns, blockSize*rows));
        
        
        
        this.addMouseListener(new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
             System.out.println( (int)(((e.getY()-top)/h))+" sdfghjk"+ (int)(((e.getX()-left)/w)));
//             if(map[(int)(e.getY()/h)][(int)(e.getX()/w)]==0){
//                map[(int)(e.getY()/h)][(int)(e.getX()/w)]=1 ;
//             }else  if(map[(int)(e.getY()/h)][(int)(e.getX()/w)]==1){
//                map[(int)(e.getY()/h)][(int)(e.getX()/w)]=0 ;
//             }
             if(game!=null){
                 int x  = (int)(((e.getY()-top)/h));
                  int y  = (int)(((e.getX()-left)/w));
                  if(x<= columns && x >=0 && y>=0&& y<=rows){
                    game.danh(x,y);
                  }else{
                  System.out.println(" danh ngoai pham vi");
                  }
                
             }else{
                 System.out.println("Game null : chọn 1 trong 2 chế độ ");
                 System.out.println(((e.getY()-top)/h)+" "+(int)(((e.getX()-left)/w)));
                 
                     }
          repaint();
        }

        public void mouseReleased(MouseEvent e) {
//         System.out.println(" " + e.getX() +" THA CHUOT");
//          repaint();
        }
      });

      this.addMouseMotionListener(new MouseMotionAdapter() {
        public void mouseDragged(MouseEvent e) {
//       System.out.println(" " + e.getX()+" KEO LE CHUOC");
//          repaint();
//              if(map[(int)(e.getY()/h)][(int)(e.getX()/w)]==1){
//                map[(int)(e.getY()/h)][(int)(e.getX()/w)]=3 ;
//             }else  if(map[(int)(e.getY()/h)][(int)(e.getX()/w)]==3){
//                map[(int)(e.getY()/h)][(int)(e.getX()/w)]=1 ;
//             }
//          repaint();
        }
      });
        
         
                repaint();
    }
  

   public  void checkSize() {
            // Called before drawing the maze, to set parameters used for drawing.
        if (getWidth() != width || getHeight() != height){
            width  = getWidth();
            height = getHeight();
             w = (width - 2*border) / columns;
              h = (height - 2*border) / rows;
            left = (width - w*columns) / 2;
            top = (height - h*rows) / 2;
            totalWidth = w*columns;
            totalHeight = h*rows; 
        }
    }
    synchronized protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        checkSize();
        redrawMaze(g);
        repaint();
    }
 public void resizeIcon(){
     resize(imgx, blockSize, blockSize);
     resize(imgy, blockSize, blockSize);
 }
    public void redrawMaze(Graphics g) {
            // draws the entire maze
        Graphics2D g2 = (Graphics2D)g;
          g2.setPaint(Color.LIGHT_GRAY);
      for (int i = 0; i < totalWidth; i += w) {
        Shape line = new Line2D.Float(left+i,top+0, left+i, top+totalHeight);
        g2.draw(line);
      }
        Shape line = new Line2D.Float(left+totalWidth,top, left+totalWidth, top+totalHeight);
        g2.draw(line);
       for (int i = 0; i < totalHeight; i += h) {
           line = new Line2D.Float(left+0, top+i, left+totalWidth, top+i);
         g2.draw(line);
         }
        line = new Line2D.Float(left,top+totalHeight, left+totalWidth, top+totalHeight);
        g2.draw(line);
        if (map!=null) {
            int w = totalWidth / columns;  // width of each cell
            int h = totalHeight / rows;    // height of each cell
            for (int j=0; j<columns; j++)
                for (int i=0; i<rows; i++) {
                    if (map[i][j] == 1){
                     g2.drawImage(imgx,(j * w) + left, (i * h) + top, w, h, null);
                }   
                       if (map[i][j] == 2){
                     g2.drawImage(imgy,(j * w) + left, (i * h) + top, w, h, null);
                } 
        }}   
    }
    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
    
  
    public void update(){
      this.repaint(); 
    }   
      public static BufferedImage resize(BufferedImage img, int newW, int newH) { 
    Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
    BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

    Graphics2D g2d = dimg.createGraphics();
    g2d.drawImage(tmp, 0, 0, null);
    g2d.dispose();

    return dimg;
   } 
      public void setGame(Game g){
      this.game = g;}
//    public static  void main(String [] agr ){
//      JFrame window = new JFrame("Maze Solver");
//        Swing swing = new Swing();
//        window.setContentPane(swing);
//        swing.repaint();
//        window.pack();
//        window.setLocation(120, 80);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setVisible(true);
//        
//  TicTacToeCatTia b = new TicTacToeCatTia();
//    swing.setMap(b.getBoard());
//    swing.update();
//    
//    Random rand = new Random();
//
//    b.displayBoard();
//
//    System.out.println("chọn người đi trước (1)máy  (2)bạn ");
//    int choice = b.scan.nextInt();
//    if (choice == 1) {
//        Node p = new Node(rand.nextInt(5), rand.nextInt(5));
//        b.placeAMove(p, 1);
//        b.displayBoard();
//    }
////
//    
//    while (!b.isGameOver()) {
//    	 System.out.println("nhập toa độ x y (dòng cột) ");
//        Node userMove = new Node(b.scan.nextInt(), b.scan.nextInt());
//
//        b.placeAMove(userMove, 2); 
//        b.displayBoard();
//        if (b.isGameOver()) break;
//        
//        b.alphaBetaMinimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
//        for (DiemCuaNode pas : b.rootsChildrenScore) 
//            System.out.println("tọa độ: " + pas.point + "điểm: " + pas.score);
//          
//        swing.setMap(b.getBoard());
//           swing.update();
//        
//        b.placeAMove(b.returnBestMove(), 1);
//        b.displayBoard();
//    }
//    if (b.hasXWon()) {
//        System.out.println("Qua tệ bạn đã thua!");
//    } else if (b.hasOWon()) {
//        System.out.println("Bạn đã thắng");
//    } else {
//        System.out.println("Hòa!");
//    }
//     }
  
    
}