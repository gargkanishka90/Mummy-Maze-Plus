package Maze;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.*;

public class Board extends JPanel implements ActionListener {
	
	private Timer timer;
	private Timer timer2;
	private Map m;
	private Player p;
	private Villain v, v1, v2;
	private Robot r;
	private int mapHeight = 32;
	private int mapWidth = 32;
	//fields for if user wins
	private String message = "";
	private Font font = new Font("Serif", Font.BOLD, 48);
	private boolean win = false;
	
	public Board(){
		m = new Map();
		p = new Player();
		v = new Villain();
		v1= new Villain("Green");
		v2= new Villain("Red");
		addKeyListener(new actionListener());
		setFocusable(true);
		
		timer = new Timer(25, this);
		timer.start();
		
		 
		 
		
		/* try {
			 
		        while (true) {
		        	VM(v);
		            Thread.sleep(2 * 1000);
		        }
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }*/
		
	}//end of Board
	      
	//action for winning
	public void actionPerformed(ActionEvent e){
		if(m.getMap(p.getTileX(), p.getTileY() ).equals("f") ){
			win = true;
			message = "Escape Complete!";
		}
		repaint();
	}//end of actionPerformed
	
	public void paint(Graphics g){
		super.paint(g);
		if(!win){
		for(int i = 0; i< mapHeight; i++)
			for(int j = 0; j< mapWidth; j++){
				if(m.getMap(i,j).equals("f"))
					g.drawImage(m.getFinish(), i * 32, j * 32, null);
				if(m.getMap(i,j).equals("g") )
					g.drawImage(m.getGrass(), i * 32, j * 32, null);
				if(m.getMap(i,j).equals("w") )
					g.drawImage(m.getWall(), i * 32, j * 32, null);
				//draws player
				g.drawImage(p.getPlayer(),p.getTileX() * 32, p.getTileY() * 32, null);
				//draws villain
				g.drawImage(v.getVillain(),v.getVillainX() * 32, v.getVillainY() * 32, null);
				//draw 2nd villain
				g.drawImage(v1.getVillain(),v1.getVillainX() * 32, v1.getVillainY() * 32, null);
				//draw 3rd villain
				g.drawImage(v2.getVillain(),v2.getVillainX() * 32, v2.getVillainY() * 32, null);
			}
		}//end of if !win
		if(win){
			g.setColor(Color.BLACK);
			g.setFont(font);
			g.drawString(message, 85, 250);
			g.drawImage(p.getPlayerFinished(),p.getTileX() * 32, p.getTileY() * 32, null);
		}//end of if win
		
	}//end of paint method
	
	public void VM(Villain v){
		//try {
			 
	      //  while (!win) {
			
		int ranNum = (int)(Math.random() *4) +1;
		System.out.println(ranNum);
			if(ranNum == 1){
				if(!m.getMap(v.getVillainX(), v.getVillainY() - 1).equals("w") )
					v.villainMove(0, -1);
			}
			if(ranNum == 2){
				if(!m.getMap(v.getVillainX(), v.getVillainY() + 1).equals("w") )
					v.villainMove(0, 1);
			}
			if(ranNum == 3){
				if(!m.getMap(v.getVillainX() - 1, v.getVillainY() ).equals("w") )
					v.villainMove(-1, 0);
			}
			if(ranNum == 4){
				if(!m.getMap(v.getVillainX() + 1, v.getVillainY() ).equals("w") )
					v.villainMove(1, 0);
			}
		//	Thread.sleep(2 * 1000);
	        }
	 //   }catch (InterruptedException e) {
	   //     e.printStackTrace();
	  //  }
	//}
	
	public class actionListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			int keyCode = e.getKeyCode();
			//if you press up, move up
			if(keyCode == KeyEvent.VK_UP){
				//if you touch a "w" or wall, the move does not occur
				if(!m.getMap(p.getTileX(), p.getTileY() - 1).equals("w") )
					p.move(0, -1);	
			}
			//if you press down, move down
			if(keyCode == KeyEvent.VK_DOWN){
				if(!m.getMap(p.getTileX(), p.getTileY() + 1).equals("w"))
					p.move(0, 1);
			}
			//if you press left
			if(keyCode == KeyEvent.VK_LEFT){
				if(!m.getMap(p.getTileX() - 1, p.getTileY() ).equals("w"))
					p.move(-1, 0);
			}
			//if you press right
			if(keyCode == KeyEvent.VK_RIGHT){
				if(!m.getMap(p.getTileX() + 1, p.getTileY() ).equals("w") )
					p.move(1, 0);
			}
		}//end of keyCode
	
		public void keyReleased(KeyEvent e){
			
		}//end of keyReleased
		
		public void keyTyped(KeyEvent e){
			VM(v);
			VM(v1);
			VM(v2);
			
		}
	}//end of class actionListener
	
}//end of class Board
