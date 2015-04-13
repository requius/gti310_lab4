package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Zigzag {

	private final int[] ORDRE_X = new int[]{
			0,0,1,2,1,0,0,1,2,3,4,3,2,1,0,0,1,2,3,4,5,6,5,4,3,2,1,0,0,1,2,3,4,5,6,7,7,6,5,4,3,2,1,2,3,4,5,6,7,7,6,5,4,3,4,5,6,7,7,6,5,6,7,7
		};
		
	private final int[] ORDRE_Y = new int[]{
			0,1,0,0,1,2,3,2,1,0,0,1,2,3,4,5,4,3,2,1,0,0,1,2,3,4,5,6,7,6,5,4,3,2,1,0,1,2,3,4,5,6,7,7,6,5,4,3,2,3,4,5,6,7,7,6,5,4,5,6,7,7,6,7
		};
		
	
	public int[] zigzager(int[][] imageEntree){
		int[] listeZigzag = new int[64];
		
		for (int i = 0; i < listeZigzag.length; i++) {
			listeZigzag[i] = imageEntree[ORDRE_X[i]][ORDRE_Y[i]];		
		}
		
		return listeZigzag;
	}
	
	public void inverseZigzager(){
		
		int taille = ORDRE_X.length;
		int compteur = taille;
		
		int[] ordre_inverse_x = new int[taille];
		int[] ordre_inverse_y = new int[taille];
				
		for (int i = 0; i < taille; i++) {
			ordre_inverse_x[i] = ORDRE_X[--compteur];
			ordre_inverse_y[i] = ORDRE_Y[compteur];
		}
	
	}
	
	public void DCPM(int[] imageZigzag){
/*
		if (compteur != 0){
			dc[compteur] = imageZigzag[0]-dc[compteur-1];
			compteur++;
		} else {
			dc[compteur++] = imageZigzag[0];
		}
*/
	}
	
	public void IDCPM(){
		
	}
	

	
	public String[] RLC(int[] listeZigzag){
		String[] coefAC = new String[64];
		int runLength = 0;
		int compteur = 0;
		
		for (int i = 1; i < listeZigzag.length; i++) {
			if (listeZigzag[i] != 0) {
				coefAC[compteur++] = runLength + "," + listeZigzag[i];
				runLength = 0;
			} else {
				runLength++;
			}
		}
		if (listeZigzag[listeZigzag.length-1] == 0)
			coefAC[compteur++] = "EOB";
	
		return coefAC;
	}
	
	public void IRLC(){
		
	}
	
}