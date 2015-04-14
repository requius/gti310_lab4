package application;

import java.lang.Math;

public class DiscretCosTrans {

	// decouper l'image par 8x8
	public int[][][][][] decoupage(int[][][] imageYCbCr, int hauteur, int largeur) {
		int ligneBloc = 0;
		int colonneBloc = 0;
		int ligneDansBloc = 0;
		int colonneDansBloc = 0;
		int[][][][][] imageEnBloc = new int[3][hauteur][largeur][8][8];
		
		for (int i = 0; i < imageYCbCr.length; i++) {
			for (int j = 0; j < imageYCbCr[i].length; j++) {
				for (int k = 0; k < imageYCbCr[i][j].length; k++) {

					if (ligneDansBloc == 8) {
						ligneBloc++;
						ligneDansBloc = 0;
					}

					if (colonneDansBloc == 8) {
						colonneBloc++;
						colonneDansBloc = 0;
					}

					if (ligneBloc == hauteur) {
						ligneBloc = 0;
					}

					if (colonneBloc == largeur) {
						colonneBloc = 0;
					}
					
					imageEnBloc[i][ligneBloc][colonneBloc][ligneDansBloc][colonneDansBloc] = imageYCbCr[i][j][k];
					
					colonneDansBloc++;
				}
				ligneDansBloc++;
			}
		}

		System.out.println("Decoupage 8 par 8!");
		return imageEnBloc;
	}

	
	public int[][] DCT(int[][] imageEntree) {
		double Cu;
		double Cv;
		double valeur = 0.0;
		double somme = 0;
		int[][] imageRetour = new int[8][8];
		
		for (int u = 0; u < 8; u++) {
			for (int v = 0; v < 8; v++) {
				
				somme = 0.0;
				
				if (u == 0) {
					Cu = (1.0 / (Math.sqrt(2)));
				} else {
					Cu = 1.0;
				}

				if (v == 0) {
					Cv = 1.0 / (Math.sqrt(2));
				} else {
					Cv = 1.0;
				}

				valeur = (Cu * Cv) / 4.0;

				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < 8; j++) {

						somme += (Math
								.cos((((2.0 * i + 1.0) * u * Math.PI) / 16.0))
								* Math.cos((((2.0 * j + 1.0) * v * Math.PI) / 16.0)) * imageEntree[i][j]);
					}
				}

				imageRetour[u][v] = (int) (Math.round(valeur * somme));

			}
		}

		return imageRetour;
	}


	public void IDCT() {

		
	}
}
