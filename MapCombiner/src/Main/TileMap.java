package Main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TileMap {
	
	// position
	private double x;
	private double y;
	
	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;
	
	private double tween;
	
	// map
	private int[][] map;
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;
	
	// tileset
	private BufferedImage tileset;
	private int numTilesAcross;
	// drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;
	
	public TileMap(int tileSize) {
		this.tileSize = tileSize;
	
		tween = 0.07;
	}
	
	public void loadTiles(String s) {
		
		try {

			tileset = ImageIO.read(
				getClass().getResourceAsStream(s)
			);
			numTilesAcross = tileset.getWidth() / tileSize;
			
			BufferedImage subimage;
			for(int col = 0; col < numTilesAcross; col++) {
				subimage = tileset.getSubimage(
							col * tileSize,
							0,
							tileSize,
							tileSize
						);
				
				subimage = tileset.getSubimage(
							col * tileSize,
							tileSize,
							tileSize,
							tileSize
						);
				
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap(String s) {
		try { 
			for(int j = 1; j < 4; j++){
			//put the files in manually now
			InputStream in = getClass().getResourceAsStream("/text" + j + ".txt");
			BufferedReader br = new BufferedReader(
						new InputStreamReader(in)
					);
			
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			map = new int[numRows][numCols];
			width = numCols * tileSize;
			height = numRows * tileSize;
			
			
			xmax = 0;
			
			ymax = 0;
			
			String delims = "\\s+";
			for(int row = 0; row < numRows; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delims);
				for(int i = 0; i < tokens.length;i++){
					System.out.println(tokens[i]);
				}
				for(int col = 0; col < numCols; col++) {
					map[row][col] = Integer.parseInt(tokens[col]);
					}
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void loadMap2(){
		
		numCols = 0;
		numRows = 0;
		
		try{
			for(int i = 1; i < 4; i++){
				InputStream in = getClass().getResourceAsStream("/text" + i + ".txt");
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				
				numCols = numCols + Integer.parseInt(br.readLine());
				numRows = numRows + Integer.parseInt(br.readLine());
				System.out.println("cols" + numCols);
				System.out.println("rows " + numRows);
				
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int getTileSize() { return tileSize; }
	public double getx() { return x; }
	public double gety() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	
	public void setTween(double d) { tween = d; }
	
	public void setPosition(double x, double y) {
		
		//System.out.println(x);
		//System.out.println(this.x);
		//System.out.println((x - this.x) * tween);
		
		this.x += (x - this.x) * tween;
		this.y += (y - this.y) * tween;
		
		fixBounds();
		
		colOffset = (int)-this.x / tileSize;
		rowOffset = (int)-this.y / tileSize;
		
	}
	
	public void scroll(int p){
		setPosition(x - 640, 0);
	}
	
	private void fixBounds() {
		if(x < xmin) x = xmin;
		if(y < ymin) y = ymin;
		if(x > xmax) x = xmax;
		if(y > ymax) y = ymax;
	}
	
}