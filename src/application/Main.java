package application;

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
		System.out.println(args[0]);
		
		PPMReaderWriter ppmRW = new PPMReaderWriter();
		int tab[][][];
		tab = ppmRW.readPPMFile(args[0]);
		
		int compteur = 0;
		
		int[][] test = new int[][]{{200,202,189,188,189,175,175,175},
					{200,203,190,188,189,182,178,175},
					{203,200,200,195,200,187,185,175},
					{200,200,200,200,197,187,187,187},
					{200,205,200,200,195,188,187,175},
					{200,200,200,200,200,190,187,175},
					{205,200,199,200,191,187,187,175},
					{210,200,200,200,188,185,187,186}};
		
		DiscretCosTrans dct = new DiscretCosTrans();
		int[][] retour = dct.DCT(test);
		
		for (int[] is : retour) {
			for (int i : is) {
				System.out.println(compteur++ + " - " + i);
				
			}
		}

	}
}
