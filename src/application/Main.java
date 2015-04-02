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
		/*
		DiscretCosTrans dct = new DiscretCosTrans();
		int[][] retour = dct.DCT(test);
		
		for (int[] is : retour) {
			for (int i : is) {
				System.out.println(compteur++ + " - " + i);
				
			}
		}
	*/
		int[][] test2 = new int[][]{
				{1,2,6,7,15,16,28,29},
				{3,5,8,14,17,27,30,43},
				{4,9,13,18,26,31,42,44},
				{10,12,19,25,32,41,45,54},
				{11,20,24,33,40,46,53,55},
				{21,23,34,39,47,52,56,61},
				{22,35,38,48,51,57,60,62},
				{36,37,49,50,58,59,63,64},
		};
		Zigzag zig = new Zigzag();
		int tab2[] = zig.zigzager(test2);
		
		for (int i : tab2) {
			System.out.println(i);
		}
		
	}
}
