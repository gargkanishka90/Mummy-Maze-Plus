package Maze;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
public class Map {
	
	
	private String[] map = new String[32];
	
	private Image grass, wall, finish;
	
	public Map(){
		//defines grass and wall and finish images
		ImageIcon img = new ImageIcon("grass.png");
		grass = img.getImage();
		img = new ImageIcon("wall.png");
		wall = img.getImage();
		img = new ImageIcon("finish.png");
		finish = img.getImage();
		//methods below to open, read and close file
		openFile();
		//readFile();
		//closeFile();
	}
	//get methods
	public Image getGrass(){return grass;}
	public Image getWall(){return wall;}
	public Image getFinish(){return finish;}
	public String getMap(int x, int y){
		StringBuffer index = new StringBuffer();
		index.append(map[y].substring(x, x+1 ));
		return index.toString();	
	}//end of getMap
	
	public void openFile(){
		try{
			File file = new File("map.txt");
			Scanner scan = new Scanner(file);
			while(scan.hasNext() ){
				for(int i = 0; i < map.length; i++)
					map[i] = scan.next();
			}
			scan.close();
		}catch (Exception e){System.out.print("Error Loading map");}
	}//end of openFile
	

}//end of class Map
