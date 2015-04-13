package application;

public class Conversion {

	public int[][][] conversionRGBaYCbCr(int[][][] imageRGB, int hauteur, int largeur){
		int[][][] imageYCbCr = new int[3][hauteur][largeur];
		
		for (int i = 0; i < imageRGB[0].length; i++) {
			for (int j = 0; j < imageRGB[0][0].length; j++) {

				int R = imageRGB[0][i][j];
				int G = imageRGB[1][i][j];
				int B = imageRGB[2][i][j];
				
				imageYCbCr[0][i][j] = (int)((0.299 * R) 		+ (0.587 * G)		+ (0.114 * B));
				imageYCbCr[1][i][j] = (int)((-0.168736 * R) 	+ (-0.331264 * G)	+ (0.5 * B)) + 128;
				imageYCbCr[2][i][j] = (int)((0.5 * R) 			+ (-0.418688 * G) 	+ (-0.081312 * B)) + 128;
			}  
		}
	
		System.out.println("Conversion RGB a YCbCr!");
		return imageYCbCr;
	}
	
	public int[][][] conversionYCbCraRGB(int[][][] imageYCbCr, int hauteur, int largeur){
		int[][][] imageRGB = new int[3][hauteur][largeur];
		
		for (int i = 0; i < imageYCbCr[0].length; i++) {
			for (int j = 0; j < imageYCbCr[0][0].length; j++) {
				int Y = imageYCbCr[0][i][j];
				int Cb = imageYCbCr[1][i][j];
				int Cr = imageYCbCr[2][i][j];
				
				imageRGB[0][i][j] = (int)((1 * Y) 	+ (1.402 * Cr));
				imageRGB[1][i][j] = (int)((1 * Y) 	+ (-0.344136 * Cb)	+ (-0.714136 * Cr)) - 128;
				imageRGB[2][i][j] = (int)((1 * Y) 	+ (1.772 * Cb)) 	 - 128;
			}  
		}
		
		return imageRGB;
	}
	
}
