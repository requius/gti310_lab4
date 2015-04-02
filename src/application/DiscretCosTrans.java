package application;

import java.lang.Math;

public class DiscretCosTrans {
	
	private int[][][][][] imageEnBloc;
	
	public DiscretCosTrans(){
		
	}
	
	public void decoupage(int[][][] imageYUV, int taille){ // taille = 8
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
	}
	
	public int[][][][][] DCT(){
		int Cu;
		int Cv;
		int somme = 0;
		
		for (int couleur = 0; couleur < imageEnBloc.length; couleur++) {
			for (int ligneBloc = 0; ligneBloc < imageEnBloc.length; ligneBloc++) {
				if (ligneBloc == 0) {
					Cu = (int) (1/(Math.sqrt(2)));
				} else {
					Cu = 1;
				}
				
				for (int colonneBloc = 0; colonneBloc < imageEnBloc.length; colonneBloc++) {
					if (colonneBloc == 0) {
						Cv = (int) (1/(Math.sqrt(2)));
					} else {
						Cv = 1;
					}
					
					for (int ligneDansB = 0; ligneDansB < imageEnBloc.length; ligneDansB++) {
						for (int colonneDansB = 0; colonneDansB < imageEnBloc.length; colonneDansB++) {
							somme += (Math.cos((((2 * ligneDansB + 1) * ligneBloc * Math.PI)/16))
									* Math.cos((((2 * colonneDansB +1) * colonneBloc*Math.PI)/16))); 
						}
					}
					((Cu * Cv) / 4) * somme ;
				}
			}
		}
		
		
		return imageEnBloc;
	}
	
	public int[][][][][] IDCT(){
		
		return imageEnBloc;
	}
}
