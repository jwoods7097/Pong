import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel {

    JButton b1;
    JButton b2;
    public static int indicator = 0;

    public Menu(Window currentWindow) {
        this.setLayout(null);

        // 1 Player Button Setup
        b1 = new JButton(new ImageIcon("images/button1.png"));
        b1.setMargin(new Insets(0, 0, 0, 0));
        b1.setBorder(null);
        b1.setBackground(Color.BLACK);
        b1.setBounds(352, 267, 320, 128);
        b1.addActionListener(e -> {
            currentWindow.setPanel(Window.Panels.GAME);
            currentWindow.invalidate();
            currentWindow.validate();
            this.setVisible(false);
            indicator = 1;
        });
        this.add(b1);

        // 2 Player Button Setup
        b2 = new JButton(new ImageIcon("images/button2.png"));
        b2.setMargin(new Insets(0, 0, 0, 0));
        b2.setBorder(null);
        b2.setBackground(Color.BLACK);
        b2.setBounds(352, 481, 320, 128);
        b2.addActionListener(e -> {
            currentWindow.setPanel(Window.Panels.GAME);
            currentWindow.invalidate();
            currentWindow.validate();
            this.setVisible(false);
        });
        this.add(b2);
    }

    // Draws the background
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {
            Image background = ImageIO.read(new File("images/menu_background.png"));
            g2d.drawImage(background, 0, 0, this);
        } catch (IOException e) {
            System.out.println("Could not load background!");
        }
    }

}
