package eg.edu.alexu.csd.oop.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;


import static eg.edu.alexu.csd.oop.game.Main.logger;

public class View extends JComponent {
	private static View view;
	private static JFrame window;
	private JButton btn_start;

	private View(){

	}
	  
	void beginning() {
		window =new JFrame("Circus of plates");
		View game = new View();
		window.add(game);
		window.setDefaultCloseOperation(window.EXIT_ON_CLOSE);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
		window.getContentPane().setLayout(null);
		logger.info("The Game is opened!");
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(725,420);
        
    }
	 protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 Image image1 = new ImageIcon("res//circus2.jpg").getImage();
		 Image image2 = new ImageIcon("res//StartGame.png").getImage();
		 Image image4 = new ImageIcon("res//HowToPlay.png").getImage();
		 Image image3 = new ImageIcon("res//QuitGame.png").getImage();

		 int bitisX = getSize().width;
		 int bitisY = getSize().height;

		 g.drawImage(image1, 0, 0, bitisX, bitisY ,null);
		 g.drawImage(image2,bitisX/2-75 , 60, 150, 35,null);
		 g.drawImage(image4,bitisX/2-75 , 105, 150, 35,null);
		 g.drawImage(image3,bitisX/2-75 , 150,150, 35 ,null);

		  Image newImage2 = image2.getScaledInstance(160 , 50 , Image.SCALE_SMOOTH);
		  btn_start = new JButton(new ImageIcon(newImage2));
		  btn_start.setBounds(bitisX/2-75 , 60, 150, 30);
		   window.getContentPane().add(btn_start);

			btn_start.addActionListener(e -> {
				logger.info("New Game!");
				String[] levels = {"Easy", "Medium", "Hard"};
				JComboBox lev=new JComboBox(levels);
				int input;
				input=JOptionPane.showConfirmDialog(btn_start, lev,"Select Level",JOptionPane.DEFAULT_OPTION);
				if(input==JOptionPane.OK_OPTION) {

					String str=(String)lev.getSelectedItem();
					Main main = new Main() ;
					if (str != null) {
						switch (str) {
							case "Easy":
								logger.info("The level selected is easy!");
								main.level = new Easy();
								break;
							case "Medium":
								logger.info("The level selected is medium!");
								main.level = new Medium();
								break;
							case "Hard":
								logger.info("The level selected is hard!");
								main.level = new Hard();
								break;
						}
					}
					new Controller(main.level.get_level());
					window.dispose();
				}
			});
		 Image newImage4 = image4.getScaledInstance(160 , 50 , Image.SCALE_SMOOTH);
		 JButton instructions = new JButton(new ImageIcon(newImage4));
		 instructions.setBounds(bitisX/2-75 , 105, 150, 30);
		 window.getContentPane().add(instructions);
		 instructions.addActionListener(e ->{
			 logger.info("How to play button is selected!");
			 JOptionPane.showMessageDialog(null,"-the Player Control keys: left and right arrows \n-the player should choose the difficulty of the game \n-the game ends when the time ends","HOW TO PLAY!",JOptionPane.PLAIN_MESSAGE);
		 });
		 Image newImage3 = image3.getScaledInstance(160 , 50 , Image.SCALE_SMOOTH);
		 JButton btn_exit = new JButton(new ImageIcon(newImage3));
		 btn_exit.setBounds(bitisX/2-75 , 150, 150, 30 );
		 window.getContentPane().add(btn_exit);
		 btn_exit.addActionListener(e ->{
					 logger.info("the Game is ended!");
					 window.dispose();
				 });
	    }
	static View singleton(){
		if(view==null){
			return view = new View();
		}
		return view;
	}
}
