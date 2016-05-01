import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.*;

public class main {

	private JFrame frame;
    private String pass;
    private JLabel label1;
	/**
	 * Launch the application.
	 */
	public static void main( String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					main window = new main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	

	/**
	 * Create the application.
	 */
	public main() {
		pass="hesoyam";
		init x=new init();
		x.create_database(pass);
		SalesHistory sh=new SalesHistory();
		sh.init(pass);
		Upload y=new Upload();
		y.upload(pass);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Supermarket Automation Software");
		frame.setBounds(100, 100, 487, 324);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JButton btnManager = new JButton("Manager");
		btnManager.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				login x=new login(0,pass);
				x.log();
				
			}
			
			
		});
		btnManager.setBounds(69, 66, 117, 25);
		frame.getContentPane().add(btnManager);
		frame.getContentPane().setBackground(Color.WHITE);
		
		JButton btnemployee = new JButton("Employee");
		btnemployee.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				login y=new login(1,pass);
				y.log();
				
			}
			
			
		});
		btnemployee.setBounds(69, 117, 117, 25);
		frame.getContentPane().add(btnemployee);
		
		JButton btnSalesClerk = new JButton("Sales clerk");
		 btnSalesClerk.addActionListener(new ActionListener(){

			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				login z=new login(2,pass);
				z.log();
				
			}
			
			
		});
		btnSalesClerk.setBounds(69, 170, 117, 25);
		frame.getContentPane().add(btnSalesClerk);
	    BufferedImage img=null;
		try {
            img = ImageIO.read(new File("login.png"));
        } catch (IOException e) {
        	e.printStackTrace();
        }
        BufferedImage scaled=resize(
	            img,275, 215);
        label1=new JLabel(new ImageIcon(scaled));
        label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setVerticalAlignment(JLabel.CENTER);
		label1.setBounds(180, 50, 275, 215);
		frame.getContentPane().add(label1);
	
	}
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
}