package Main;

public class Runner {

	static TileMap tilemap;
	
	public static void main(String[] args){
		tilemap = new TileMap(32);
		tilemap.makeBeginning(); 
		tilemap.loadMap4(5);
		tilemap.makeEnd();
		
		//tilemap.writeMap();
		
	}
	
}
