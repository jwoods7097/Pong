import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {

    // 4:3 aspect ratio that should fit on most screens, can change if needed
    public static final int WINDOW_WIDTH = 1024;
    public static final int WINDOW_HEIGHT = 768;

    // Sets up the Window for the application
    public Window() {
        setPanel(new Menu(this));
        this.setTitle("Pong");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    // Used for switching between different panels
    public void setPanel(JPanel panel) {
        panel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        this.add(panel);
        this.invalidate();
        this.validate();
    }
}
