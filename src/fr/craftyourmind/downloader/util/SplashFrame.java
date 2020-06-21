package fr.craftyourmind.downloader.util;

import java.awt.Image;
import javax.swing.*;

public class SplashFrame extends JFrame {
    public SplashFrame(String title, Image image) {
        setTitle(title);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(image.getWidth(this), image.getHeight(this));
        setLocationRelativeTo(null);
        setContentPane(new SplashPanel(image));
    }

    public SplashFrame getContentPane() {
        return (SplashFrame)super.getContentPane();
    }
}
