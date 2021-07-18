package RecycleGo;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;

public class AnimatedBag {
    
  
    BufferedImage bag = null;
    AnimationPane ap;
    JFrame frame;
    Buttons b;

    public AnimatedBag() { 
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }
                
                try {
                    bag = ImageIO.read(new File("C:/Users/vaish/eclipse-workspace/RecycleGo/src/RecycleGoImagesAndSounds/plasticBag.png"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
               
                frame = new JFrame("RecycleGo");
                frame.setLayout(new BorderLayout());
                ap = new AnimationPane(bag);
                frame.add(ap);
                //frame.add(new AnimationPane(apple));
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setSize(800, 600);
                //panel.setVisible(true);
                frame.setVisible(true);
                b = new Buttons();
                b.recycle.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (b.recycle.isEnabled()) {
                            frame.setVisible(false);
                            frame.dispose();
                            b.frame.setVisible(false);
                            b.frame.dispose();
                            Buttons.points++;
                            AnimatedApple apple = new AnimatedApple();   
                            
                        }
                    }
                });
                
                b.compost.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (b.compost.isEnabled()) {
                            frame.setVisible(false);
                            frame.dispose();
                            b.frame.setVisible(false);
                            b.frame.dispose();
                            GameOver go = new GameOver(); 
                            Buttons.points = 0;
                            
                        }
                    }
                });
                
                b.trash.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (b.trash.isEnabled()) {
                            frame.setVisible(false);
                            frame.dispose();
                            b.frame.setVisible(false);
                            b.frame.dispose();
                            GameOver go = new GameOver(); 
                            Buttons.points = 0;
                            
                        }
                    }
                });
                
                Buttons.back.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (Buttons.back.isEnabled()) {
                            b.frame.setVisible(false);
                            b.frame.dispose();
                            frame.setVisible(false);
                            frame.dispose();
                            HomeScreen hs = new HomeScreen();
                            Buttons.points = 0;
                            
                        }
                    }
                });
                
                
                
            }

        });
    }
    
    

    public class AnimationPane extends JPanel {

        private BufferedImage apple;
        private int xPos = 0;
        private int direction = 1;

        public AnimationPane(BufferedImage image) {
            // apple = ImageIO.read(new File("C:/Users/vaish/eclipse-workspace/RecycleGo/src/RecycleGoImagesAndSounds/appleCore.png"));
            apple = image;   
            Timer timer = new Timer(0, new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e) {
                        xPos += direction;
                        if (xPos + apple.getWidth() > getWidth()) {
                            xPos = getWidth() - apple.getWidth();
                            direction *= -1;
                            //Buttons.points--;
                        } else if (xPos < 0) {
                            xPos = 0;
                            direction *= -1;
                            //Buttons.points--;
                        }
                        repaint();
                    }

                });
                timer.setRepeats(true);
                timer.setCoalesce(true);
                timer.start();
        }
        
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int y = getHeight() - apple.getHeight();
            g.drawImage(apple, xPos, y, this);

        }

    }   

}