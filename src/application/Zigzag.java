package application;

import java.util.ArrayList;
import java.util.Collections;

public class Zigzag {

	private final int[] ORDRE_X = new int[]{
			0,0,1,2,1,0,0,1,2,3,4,3,2,1,0,0,1,2,3,4,5,6,5,4,3,2,1,0,0,1,2,3,4,5,6,7,7,6,5,4,3,2,1,2,3,4,5,6,7,7,6,5,4,3,4,5,6,7,7,6,5,6,7,7
		};
		
	private final int[] ORDRE_Y = new int[]{
			0,1,0,0,1,2,3,2,1,0,0,1,2,3,4,5,4,3,2,1,0,0,1,2,3,4,5,6,7,6,5,4,3,2,1,0,1,2,3,4,5,6,7,7,6,5,4,3,2,3,4,5,6,7,7,6,5,4,5,6,7,7,6,7
		};
		
	private int[] tabZigzag = new int[64];
	private int[] dc = new int[32*32];
	private int compteur = 0;
	
	public int[] zigzager(int[][] imageEntree){
		
		for (int i = 0; i < tabZigzag.length; i++) {
			tabZigzag[i] = imageEntree[ORDRE_X[i]][ORDRE_Y[i]];
			
		}
		
		return tabZigzag; 
	}
	
	public void inverseZigzager(){
		
		int taille = ORDRE_X.length;
		int compteur = taille;
		
		int[] ordre_inverse_x = new int[taille];
		int[] ordre_inverse_y = new int[taille];;
				
		for (int i = 0; i < taille; i++) {
			ordre_inverse_x[i] = ORDRE_X[--compteur];
			ordre_inverse_y[i] = ORDRE_Y[compteur];
		}
	
	}
	
	public void DCPM(int[][] imageEntree){
		dc[compteur] = imageEntree[0][0];
	}
	
	public void IDCPM(){
		
	}
	
	public void RLC(){
		
	}
	
	public void IRLC(){
		
	}
	
}