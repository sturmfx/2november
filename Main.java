package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main
{
    public static Random r = new Random();
    public static JFrame gui = new JFrame();
    public static JButton load = new JButton("LOAD PICTURE");
    public static JButton shuffle = new JButton("SHUFFLE");
    public static JTextField n = new JTextField(10);
    public static JButton reset = new JButton("RESET");


    public static JFrame image = new JFrame();
    public static JLabel picLabel;
    public static BufferedImage myPicture;
    public static BufferedImage myPicture2;
    static String image_path = "";
    final static JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    public static void main(String[] args)
    {
        load.addActionListener(e ->
        {
            int returnValue = fc.showOpenDialog(null);
            image_path = fc.getSelectedFile().getAbsolutePath();
            try
            {
                load_image();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        });

        shuffle.addActionListener(e ->
        {
            shuffle(n.getText());
        });

        reset.addActionListener(e ->
        {
            myPicture = myPicture2;
            picLabel.setIcon(new ImageIcon(myPicture));
        });


        gui.setSize(new Dimension(200, 500));
        gui.setLayout(new FlowLayout());
        gui.add(load);
        gui.add(shuffle);
        gui.add(n);
        gui.add(reset);

        gui.setVisible(true);
        gui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    public static void load_image() throws IOException
    {
        image.dispose();
        myPicture = ImageIO.read(new File(image_path));
        myPicture2 = ImageIO.read(new File(image_path));
        image = new JFrame();
        image.setSize(new Dimension(myPicture.getWidth(), myPicture.getHeight()));
        picLabel = new JLabel(new ImageIcon(myPicture));
        image.add(picLabel);
        image.setVisible(true);
    }

    public static void shuffle(String n)
    {
        int n1 = 10;
        if(n.matches("\\d{2}") || n.matches("\\d"))
        {
            n1 = Integer.parseInt(n);
        }

        Color[] colors = new Color[3];
        colors[0] = Color.RED;
        colors[1] = Color.GREEN;
        colors[2] = Color.BLUE;
        for(int i = 0; i < myPicture.getWidth(); i++)
        {
            for(int j = 0; j < myPicture.getHeight(); j++)
            {
                if(r.nextInt(n1) == 1)
                {
                    myPicture.setRGB(i, j, colors[r.nextInt(2)].getRGB());
                }
            }
        }
        picLabel.setIcon(new ImageIcon(myPicture));
    }


}
