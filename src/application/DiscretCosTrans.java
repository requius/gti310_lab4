package application;

import java.lang.Math;

public class DiscretCosTrans {
	
	private int[][][][][] imageEnBloc;
	
	public DiscretCosTrans(){
		
	}
	
	// decouper l'image par 8x8
	public void decoupage(int[][][] imageYUV, int taille){ // taille = 8
		int ligneBloc = 0; // 32
		int colonneBloc = 0; // 32
		int ligneDansBloc = 0; // 8
		int colonneDansBloc = 0; // 8
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
	
	public int[][] DCT(int[][] imageEntree){
		double Cu;
		double Cv;
		double valeur = 0.0;
		double somme = 0;
		int[][] imageRetour = new int[8][8]; 
		

					for (int u=0;u<8;u++) {	
						for (int v=0;v<8;v++) {
							somme = 0.0;	
							if (u == 0) {
								Cu =  (1.0/(Math.sqrt(2)));
							} else {
								Cu = 1.0;
							}
				
							if (v == 0) {
								Cv = 1.0/(Math.sqrt(2));
							} else {
								Cv = 1.0;
							}
							
							valeur = (Cu * Cv) / 4.0;
							
							for (int i = 0; i < 8; i++) {
								for (int j = 0; j < 8; j++) {
									
									somme += (Math.cos((((2.0 * i + 1.0) * u * Math.PI)/16.0))
											* Math.cos((((2.0 * j + 1.0) * v * Math.PI)/16.0))
											* imageEntree[i][j]); 
								}
							}
							
							imageRetour[u][v] = (int)(Math.round(valeur * somme));
							
						}
					}
				
				
		return imageRetour;
	}
	
	/*
	public int[][][][][] DCT(int[][] imageEntree){
		int Cu;
		int Cv;
		int valeur = 0;
		int somme = 0;
		int[][][][][] imageRetour = new int[3][32][32][8][8]; 
		
		for (int couleur = 0; couleur < imageEnBloc.length; couleur++) {
			for (int ligneBloc = 0; ligneBloc < imageEnBloc.length; ligneBloc++) {
				for (int colonneBloc = 0; colonneBloc < imageEnBloc.length; colonneBloc++) {
				// ici, nous avons selectionne le bloc de composante 'couleur' situe a lignebloc,colonnebloc
				// On applique maintenant la dct
					
					for (int u=0;u<8;u++) {	
						for (int v=0;v<8;v++) {
							
							if (u == 0) {
								Cu = (int) (1/(Math.sqrt(2)));
							} else {
								Cu = 1;
							}
				
							if (v == 0) {
								Cv = (int) (1/(Math.sqrt(2)));
							} else {
								Cv = 1;
							}
							
							valeur = (Cu * Cv) / 4;
							
							for (int ligneDansB = 0; ligneDansB < imageEnBloc.length; ligneDansB++) {
								for (int colonneDansB = 0; colonneDansB < imageEnBloc.length; colonneDansB++) {
									
									somme += (Math.cos((((2 * ligneDansB + 1) * ligneBloc * Math.PI)/16))
											* Math.cos((((2 * colonneDansB + 1) * colonneBloc * Math.PI)/16))); 
								}
								somme = somme * imageEnBloc[couleur][ligneBloc][colonneBloc][ligneDansB][colonneBloc];
							}
							
							imageRetour[couleur][ligneBloc][colonneBloc][u][v] = valeur * somme;
							
						}
					}
				}
			}
		}
				
		return imageEnBloc;
	}
	*/
	
	public int[][][][][] IDCT(){
		
		return imageEnBloc;
	}
}
