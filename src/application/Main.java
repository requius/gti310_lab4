package application;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author Francois Caron
 */
public class Main {

	/*
	 * The entire application assumes that the blocks are 8x8 squares.
	 */
	public static final int BLOCK_SIZE = 8;
	
	/*
	 * The number of dimensions in the color spaces.
	 */
	public static final int COLOR_SPACE_SIZE = 3;
	
	/*
	 * The RGB color space.
	 */
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	/*
	 * The YUV color space.
	 */
	public static final int Y = 0;
	public static final int U = 1;
	public static final int V = 2;
	
	/**
	 * The application's entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Squeeze Light Media Codec !");
		
		int facteurQ = Integer.parseInt(args[0]);
		String fichierAEncoder = args[1]; 
		String fichierCompresse= args[2]; 
		
		PPMReaderWriter ppmRW = new PPMReaderWriter();
		
		int tab[][][];
		tab = ppmRW.readPPMFile(fichierAEncoder);
		int hauteur = tab[0].length;
		int largeur = tab[0][0].length;
		
		int hauteurPar8 = hauteur / 8;
		int largeurPar8 = largeur / 8;
		
		
		int[][] test = new int[][]{
					{200,202,189,188,189,175,175,175},
					{200,203,190,188,189,182,178,175},
					{203,200,200,195,200,187,185,175},
					{200,200,200,200,197,187,187,187},
					{200,205,200,200,195,188,187,175},
					{200,200,200,200,200,190,187,175},
					{205,200,199,200,191,187,187,175},
					{210,200,200,200,188,185,187,186}};
		
		
		
		Conversion conversion = new Conversion();
		DiscretCosTrans dct = new DiscretCosTrans();
		
		int[][][] imageYCbCr = conversion.conversionRGBaYCbCr(tab, hauteur, largeur);

		int[][][][][] imageEnBloc = dct.decoupage(imageYCbCr, hauteurPar8, largeurPar8);
		
		int[][][][][] imageDCT = new int[3][hauteurPar8][largeurPar8][8][8];
		
		for (int i = 0; i < imageEnBloc.length; i++) {
			for (int j = 0; j < imageEnBloc[i].length; j++) {
				for (int k = 0; k < imageEnBloc[i][j].length; k++) {
					imageDCT[i][j][k] = dct.DCT(imageEnBloc[i][j][k]);
				}
			} 
		}
		System.out.println("DCT!");		
		
		Quantification quantification = new Quantification();
		int[][][][][] imageQuantifie = new int[3][hauteurPar8][largeurPar8][8][8];
		for (int i = 0; i < imageDCT.length; i++) {
			for (int j = 0; j < imageDCT[i].length; j++) {
				for (int k = 0; k < imageDCT[i][j].length; k++) {
					imageQuantifie[i][j][k] = quantification.quantifier(imageDCT[i][j][k], facteurQ, i);
				}
			}
		}
		System.out.println("Quantification");
	
		
		Zigzag zigzag = new Zigzag();
		
		int[][][][] listeZigzage = new int[3][hauteurPar8][largeurPar8][hauteurPar8 * largeurPar8]; 
		int[]temp = new int[hauteurPar8 * largeurPar8];
		int c = 0;
		int[][] coefficientDC = new int[3][hauteurPar8 * largeurPar8];
		
		for (int i = 0; i < imageQuantifie.length; i++) {
			Arrays.fill(temp, 0);
			for (int j = 0; j < imageQuantifie[i].length; j++) {
				for (int k = 0; k < imageQuantifie[i][j].length; k++) {
					listeZigzage[i][j][k] = zigzag.zigzager(imageQuantifie[i][j][k]);
					// coefficents de DC
					if (c != 0){
						if (j != 0 && k == 0)
							temp[c] = imageQuantifie[i][j][k][0][0] - imageQuantifie[i][j-1][largeurPar8-1][0][0] ;
						else
							temp[c] = imageQuantifie[i][j][k][0][0] - imageQuantifie[i][j][k-1][0][0] ;
						c++;
					} else {
						
						temp[c++] = imageQuantifie[i][j][k][0][0];
					}
				}
			}
			
			c = 0;
			coefficientDC[i] = temp;

		}
		System.out.println("Zigag!");
		System.out.println("Coefficient DC!");
		
		String[][][][] coefficientAC = new String[3][hauteurPar8][largeurPar8][64];
		
		for (int i = 0; i < listeZigzage.length; i++) {
			for (int j = 0; j < listeZigzage[i].length; j++) {
				for (int k = 0; k < listeZigzage[i][j].length; k++) {
					coefficientAC[i][j][k] = zigzag.RLC(listeZigzage[i][j][k]);
				}
			}
		}
		System.out.println("Coefficient AC!");
		
		
		
		/*
			for (int j = 0; j < coefficientDC[0].length; j++) {
				System.out.println(coefficientDC[0][j]);
			}
		*/
		
		
		/*
		
		for (int i = 0; i < listeZigzage.length; i++) {
			for (int j = 0; j < listeZigzage[i].length; j++) {
				for (int k = 0; k < listeZigzage[i][j].length; k++) {
					zigzag.DCPM(listeZigzage[i][j][k]);
				}
			}
			coefficientDC[i] = zigzag.getDC();
			zigzag.viderListeDC();
			zigzag.viderCompteur();
			break;
		}
	
		
		
			for (int j = 0; j < 5; j++) {
				System.out.println(coefficientDC[0][j]);
			}
				

		
		*/
		/*
		DiscretCosTrans dct = new DiscretCosTrans();
		int[][] retour = dct.DCT(test);
		
		for (int[] is : retour) {
			for (int i : is) {
				System.out.println(compteur++ + " - " + i);
				
			}
		}
	*/
		/*int[][] test2 = new int[][]{
				{1,2,6,7,15,16,28,29},
				{3,5,8,14,17,27,30,43},
				{4,9,13,18,26,31,42,44},
				{10,12,19,25,32,41,45,54},
				{11,20,24,33,40,46,53,55},
				{21,23,34,39,47,52,56,61},
				{22,35,38,48,51,57,60,62},
				{36,37,49,50,58,59,63,64},
		};


		//zig.inverseZigzager();

		int[] test1 = zigzag.zigzager(test2);
		for (int i : test1) {
			System.out.println(i);
		}
		
		/*
		for (String string : list) {
			System.out.println(string);
		}
		*/
		/*
		int tab2[] = zig.zigzager(test2);
		
		
		for (int i : tab2) {
			System.out.println(i);
		}
		*/
	}
}
