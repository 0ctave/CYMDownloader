package fr.craftyourmind.downloader;

import fr.craftyourmind.downloader.util.MessageWithLink;
import fr.craftyourmind.downloader.util.SplashFrame;
import fr.theshark34.openlauncherlib.LaunchException;
import fr.theshark34.openlauncherlib.external.ClasspathConstructor;
import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.supdate.SUpdate;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Main {
    private File CYMHome = new File(GameDirGenerator.createGameDir("craftyourmind"), "");
    private File launcherHome = new File(CYMHome, "launcher");

    private SplashFrame splash;

    public static void main(String[] args) {
        System.setOut(new PrintStream(new OutputStream() {
            @Override public void write(int b) throws IOException {}
        }));
        try {
            Class.forName("javafx.application.Application");
            new Main().start();
        } catch (ClassNotFoundException e) {
            throwException("Votre version de Java est trop ancienne pour jouer sur CraftYourMind, veuillez la mettre à jour :  <a href=\"https://java.com\">https://java.com</a><br>", "Java is not up to date");
        }
    }

    private void start() {
        CYMHome.mkdir();
        try {
            this.splash = new SplashFrame("CraftYourMind", ImageIO.read(getClass().getResourceAsStream("/images/logosplash.png")));
        } catch (IOException ignored) {}
        Image icone = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png"));
        this.splash.setIconImage(icone);
        this.splash.setVisible(true);
        SUpdate up = new SUpdate("https://cdn.craftyourmind.fr/launcher/", this.launcherHome);
        try {
            up.start();
        } catch (Exception e) {
            throwException("Le site CYM est en maintenance pardonnez nous de la gène occasionnée !", "Site internet inaccessible");
        }
        ClasspathConstructor constructor = new ClasspathConstructor();
        constructor.add(new File(this.launcherHome, "launcher.jar"));

        ExternalLaunchProfile profile = new ExternalLaunchProfile("fr.craftyourmind.launcher.Main", constructor.make());
        ExternalLauncher launcher = new ExternalLauncher(profile);
        try {
            launcher.launch();
        } catch (LaunchException e) {
            throwException("Il y a un a eu un problème lors du lancement du lanceur CYM, contactez un membre du staff pour qu'il vous aide !", "Problème lors du lancement du jeu");
        }
        this.splash.setVisible(false);
        System.exit(0);
    }

    private static void throwException(String message, String title) {
        JOptionPane.showMessageDialog(null, new MessageWithLink(message), title, JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
