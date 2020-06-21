package fr.craftyourmind.downloader.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

class SplashPanel extends JPanel implements ActionListener {
    private int dep = 0;

    private Timer declencheur;

    private Image image;

    private Color gray = new Color(207, 207, 207);

    public SplashPanel(Image image) {
        this.image = image;
        this.declencheur = new Timer(300, this);
        this.declencheur.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, getWidth(), getHeight(), this);
        g.setColor(this.gray);
        int i = 1;
        while (i <= this.dep) {
            g.drawRect(392 + i * 3, 62, 1, 1);
            i++;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.declencheur && this.dep < 3) {
            repaint();
            this.dep++;
        } else if (e.getSource() == this.declencheur) {
            repaint();
            this.dep = 0;
        }
    }
}
