/*
 	Menu vote par l'equipe
*/

import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class VueMenu{

	static Image image;

	public static void main(String [] args)
	{
		String chemin = "images/sandwich.jpg";
		image = Toolkit.getDefaultToolkit().getImage(chemin);
		Frame frame = new Frame();
		frame.add(new CustomPaintComponent());
		int w= 450;
		int h = 350;
		frame.setSize(w,h);
		frame.setVisible(true);
	}

	static class CustomPaintComponent extends Component
	{
		public void paint(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		int x= 0;
		int y= 0;
		g2d.drawImage(image,x,y,10,100,this);
		}
	}


}
