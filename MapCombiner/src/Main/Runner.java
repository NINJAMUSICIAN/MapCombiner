package Main;

public class Runner {

	static TileMap tilemap;
	
	public static void main(String[] args){
		tilemap = new TileMap(32);
		tilemap.loadMap3();
		//tilemap.writeMap();
		
	}
	
}
