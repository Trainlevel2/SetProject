import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Set
{
	private final int RED    = 0;
	private final int GREEN  = 1;
	private final int PURPLE = 2;
	private final int SOLID = 0;
	private final int STRIPED = 1;
	private final int CLEAR = 2;
	private final int DIAMOND = 0;
	private final int OVAL = 1;
	private final int SQUIGGLE = 2;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
		Card [] table = new Card[15];
		final BufferedImage [] card = new BufferedImage[15];
		for(int i = 0; i<12; i++){
			table[i] = new Card(random(),random(),random(),random()+1);
//			card [i] = new JButton(new ImageIcon(table[i].getImage()));
//			card[i].setBorder(BorderFactory.createEmptyBorder());
//			card[i].setContentAreaFilled(false);
//			card[i].addActionListener(new ActionListener(){
//				public void actionPerformed(ActionEvent e){
//					System.out.println("Clicked on button");
//				}
//			});
			card [i] = table[i].getImage();
		}
//		final BufferedImage test = new Card(0,0,0,3).getImage();
		
		JFrame frame = new JFrame("SET BOARD"){
			public void paint(Graphics G){
				for(int i = 0; i<12; i++)
					G.drawImage(card[i], 50+140*(i%4), 50+180*(i/4), this);
//				G.drawImage(test, 50, 50, this);
			}
		};
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(new Color(154,165,127));
		frame.setVisible(true);
		frame.pack();
		frame.setSize(new Dimension(700,600));
	}
	public static int random(){
		return (int)(Math.random()*3);
	}

}
