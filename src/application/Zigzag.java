package application;

public class Zigzag {

	public int[] zigzager(int[][] imageEntree){
		int[] tabZigzag = new int[64];
		int x = 0;
		int y = 0;
		int compteur = 0;
		int pointeurX = 0;
		int pointeurY = 0;
		
		tabZigzag[compteur] = imageEntree[x][y];
		x++;
		
		while (x != 8 && y != 8){
			tabZigzag[compteur++] = imageEntree[x][y];
			
			if (x > y){
				x--;
				y++;
				if (y == 0)	y++;
				
			} else if (x < y){
				x++;
				y++;
				
				if (x == 0) x++;
			}
			
		}
		
		return tabZigzag; 
	}
	
	public void inverseZigzager(){
		
	}
	
}
