package culm.will;//package name

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CulmWill extends JPanel implements ActionListener, KeyListener {

    private Graphics dbg;
    Graphics2D guy;
    Image dbImage;
    Timer frame;
    boolean press[] = {false, false, false, false};
    double pos = 0, rand;
    int score = 0, time = 0, bullx, bully;

    public CulmWill() {  
        frame = new Timer(30, this); //sets the delay between frames
        frame.start(); /// starts the timer

        Timer count = new Timer(30, new ActionListener() { // this will run the code inside ever 30ms
            @Override
            public void actionPerformed(ActionEvent e) {
                time++;
                
                /// I commented out condition for bullx = 0, this code will 
                /// determine how long the image of the plane accelerates
                if (bullx == 0 || time > 150) { //if its off screen 
                    time = 0;
                    rand = Math.random() * Math.PI * 2; /// sets double to a random number between 0 and 6.2
                    bullx = (int) (Math.sin(rand) * 400 + 400); //spawn a new one
                    bully = (int) (Math.cos(rand) * 400);
                }
            }
        });

        count.start(); //starts the timer

        addKeyListener(this); //checks if keys are pressed
    }

    public void spawn() {

    }

    public static void main(String[] args) {
        CulmWill panel = new CulmWill(); /// create new CulmWill object panel
        JFrame f = new JFrame("Game"); /// create JFrame object j with titlename "Game"
        f.setResizable(false); /// make the window non resizeable
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /// end program when user closes window
        f.add(panel); /// add the CulmWill panel object to the JFrame f object
//        f.setUndecorated(true); //Takes away the toolBar
        f.setSize(800, 600); /// Set the size to 800 width by 600 height
        f.setVisible(true); /// set the visibility to true
        f.setLocationRelativeTo(null); /// center the window

    }

    public void paint(Graphics g) { //double buffer
        dbImage = createImage(getWidth(), getHeight()); /// set Image dbImage to an image with 794 by 571 
        dbg = dbImage.getGraphics(); /// set the Graphics dbg to graphics of dbImage
        paintComponent(dbg); /// call paint component method with paramater dbg (graphics of dbImage

        g.drawImage(dbImage, 0, 0, this); // call Graphics method on g to drawImage dbImage at top left corner

        g.setFont(new Font("Consolas", Font.PLAIN, 20)); /// set the font for Graphics g to consolas
        g.drawString("SCORE: " + score, 300, 30); /// draw a string that lists the score at 300 300
    }

    public void paintComponent(Graphics g) {
        guy = (Graphics2D) g; /// Graphics2D guy = Graphics g casted as a Graphics2D object
//        guy.drawRect(350, 140, 1, 1); // 

        guy.rotate(pos, 400, 0); /// rotate Graphics2D guy in circle
        
        /// ALL THE CODE BELOW WILL ROTATE
        
        ///-800, -600, 45, 45
        guy.drawImage(new ImageIcon("space.png").getImage(), -550, -950, getWidth()*2+300, getWidth()*2+300, this); /// draw the background image
        guy.drawImage(new ImageIcon("airplane.png").getImage(), bullx - (20 + (time * time) / 14) / 2, bully - (20 + (time * time) / 14) / 2, 20 + (time * time) / 14, 20 + (time * time ) / 14, this);
        guy.rotate(-pos, 400, 0); //all the code below wont rotate
        
        /// ALL THE CODE BELOW WILL NOT ROTATE
        
        
//        guy.drawImage(new ImageIcon("man.jpg").getImage(), 350, 300, 100, 150, this);
        guy.drawImage(new ImageIcon("cockpit.png").getImage(), 000, 200, 800, 400, this);
        
        guy.drawLine(400, 0, (int) (Math.cos(-0.15 + (Math.PI / 2)) * 800) + 400, (int) (Math.sin(-0.15 + (Math.PI / 2)) * 800));
        guy.drawLine(400, 0, (int) (Math.cos(0.15 + (Math.PI / 2)) * 800) + 400, (int) (Math.sin(0.15 + (Math.PI / 2)) * 800));

        //guy.drawRect((int) (Math.cos(pos + (Math.PI / 2)) * 400) + 400, (int) (Math.sin(pos + (Math.PI / 2)) * 400), 30, 30);
        super.paintComponents(g);

        if (press[0] == true) { //checks which key is being pressed
            pos -= .04; //moves left
            if (pos <= 0) {
                pos = Math.PI * 2;
            }
        } else if (press[1] == true) {
            pos += .04; //moves right
            if (pos >= Math.PI * 2) {
                pos = 0;
            }
        }

        if (press[2] == true) { //checks which key is being pressed
            //shoot            
            if (pos <= rand + 0.15 && pos >= rand - 0.15) {
                time = 200;
                bullx = 3000;
                score++;
            }
        } else if (press[3] == true) {
            //power
        }
        if (time > 149 && time < 160 && pos <= rand + 0.15 && pos >= rand - 0.15) {
            System.out.println("GAMEOVER");
            score = 0;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            press[0] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            press[1] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            press[2] = true;
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            press[3] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            press[0] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            press[1] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
            press[2] = false;
        } else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
            press[3] = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        requestFocus();
        setFocusTraversalKeysEnabled(false);
    }
}
