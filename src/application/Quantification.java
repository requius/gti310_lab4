package application;

public class Quantification {
	
	private int[][] Qy = new int[][]{
			{16,40,40,40,40,40,51,61},
			{40,40,40,40,40,58,60,55},
			{40,40,40,40,40,57,69,56},
			{40,40,40,40,51,87,80,62},
			{40,40,40,56,68,109,103,77},
			{40,40,55,64,81,104,113,92},
			{49,46,78,87,103,121,120,101},
			{72,92,95,98,112,100,103,95}
	};
	
	private int[][] Qc = new int[][]{
			{17,40,40,95,95,95,95,95},
			{40,40,40,95,95,95,95,95},
			{40,40,40,95,95,95,95,95},
			{40,40,95,95,95,95,95,95},
			{95,95,95,95,95,95,95,95},
			{95,95,95,95,95,95,95,95},
			{95,95,95,95,95,95,95,95},
			{95,95,95,95,95,95,95,95},
	};
	
	public int[][] quantifier(int[][] imageEntree, int facteurQ, int composant){
		
		int[][] imageRetour = new int[8][8]; 
		double alpha = 0;
		
		alpha = (1 <= facteurQ && facteurQ <= 50) ? 50/facteurQ : (200 - (2 * facteurQ)) / 100;  
		
		int[][] Q;
		
		Q = (composant == 0) ? Qy : Qc;
		
		for (int u = 0; u < imageRetour.length; u++) {
			for (int v = 0; v < imageRetour.length; v++) {
				
				imageRetour[u][v] = (int) ((facteurQ == 100) 
						? imageEntree[u][v] 
						: Math.round((imageEntree[u][v])/(alpha * Q[u][v])));
					
			}
		}
		
		return imageRetour;
	}
	
	public void dequantifier(){
		
	}
	
}
