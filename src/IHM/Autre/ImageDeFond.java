package IHM.Autre;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageDeFond extends JPanel { // Classe qui fonctionne comme un JPanel, mais avec en argument une image
	
	Image fond;
	
	public ImageDeFond(String nomImage){
		
		ImageIcon ImageAfficheFilm = new ImageIcon(nomImage);
		fond =  scaleImage(ImageAfficheFilm.getImage(), 580); //taille en pixels

	}
	
	public ImageDeFond(String nomImage, int taille){ //Pour régler la taille de l'image quand on l'instancie
		
		ImageIcon ImageAfficheFilm = new ImageIcon(nomImage);
		fond =  scaleImage(ImageAfficheFilm.getImage(), taille); //taille en pixels

	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, null);
    }
	
	public static Image scaleImage(Image source, int width, int height) {
	    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g = (Graphics2D) img.getGraphics();
	    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g.drawImage(source, 0, 0, width, height, null);
	    g.dispose();
	    return img;
	}
	
	public static Image scaleImage(Image source, int size) {
	    int width = source.getWidth(null);
	    int height = source.getHeight(null);
	    double f = 0;
	    if (width < height) {//portrait
	        f = (double) height / (double) width;
	        width = (int) (size / f);
	        height = size;
	    } else {//paysage
	        f = (double) width / (double) height;
	        width = size;
	        height = (int) (size / f);
	    }
	    return scaleImage(source, width, height);
	}
}
