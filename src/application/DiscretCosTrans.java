package application;

public class DiscretCosTrans {
	
	private int[][][][][] imageEnBloc;
	
	public DiscretCosTrans(){
		
	}
	
	public int[][][][][] decoupage(int[][][] imageYUV, int taille){ // taille = 8
		int ligneBloc = 0;
		int colonneBloc = 0;
		int ligneDansBloc = 0;
		int colonneDansBloc = 0;
		imageEnBloc = new int[3][32][32][8][8];
		for (int i = 0; i < imageYUV.length; i++) {
			for (int j = 0; j < imageYUV[i].length; j++) {
				
				for (int k = 0; k < imageYUV[i][j].length; k++) {

					if (ligneDansBloc == taille){
						ligneBloc++;
						ligneDansBloc = 0;
					}
					
					if (colonneDansBloc == taille){
						colonneBloc++;
						colonneDansBloc = 0;
					}
					
					if (ligneBloc == 31) {
						ligneBloc = 0;
					}
					
					if (colonneBloc == 31){
						colonneBloc = 0;
					}

					imageEnBloc[i][ligneBloc][colonneBloc][ligneDansBloc][colonneDansBloc] = imageYUV[i][j][k];
					
					colonneDansBloc++;
				}
				ligneDansBloc++;
			}
		}

		return imageEnBloc;
	}
	
	public int[][][][][] DCT(){
		
		return imageEnBloc;
	}
	
	public int[][][][][] IDCT(){
		
		return imageEnBloc;
	}
}
