import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Winner extends JPanel {

    JButton button;
    private int player;

    public Winner(int player, Window currentWindow) {
        this.player = player;

        // Button setup
        this.setLayout(null);
        button = new JButton(new ImageIcon(getClass().getResource("/resources/button_menu.png")));
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorder(null);
        button.setBackground(Color.BLACK);
        button.setBounds(352, 526, 320, 128);
        button.addActionListener(e -> {
            currentWindow.setPanel(new Menu(currentWindow));
            this.setVisible(false);
        });
        this.add(button);
    }

    // Load proper background image depending on which player won
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        try {
            Image background = ImageIO.read(getClass().getResource("/resources/winner_background_p1.png"));;
            if(player == 2) {
                background = ImageIO.read(getClass().getResource("/resources/winner_background_p2.png"));
            }
            g2d.drawImage(background, 0, 0, this);
        } catch (IOException e) {
            System.out.println("Could not load background!");
        }
    }

}
