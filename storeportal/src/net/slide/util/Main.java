package net.slide.util;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;

public class Main extends Panel {
  private BufferedImage images[];

  private int imageIndex = 0;

  public Main() throws Exception {
    String filename = "F://contrast.jpg";
    FileInputStream inputStream = new FileInputStream(filename);
    String extensionName = filename.substring(filename.lastIndexOf('.') + 1);
    Iterator readers = ImageIO.getImageReadersBySuffix(extensionName);
    ImageReader imageReader = (ImageReader) readers.next();
    ImageInputStream imageInputStream = ImageIO.createImageInputStream(inputStream);
    imageReader.setInput(imageInputStream, false);
    int num = imageReader.getNumImages(true);
    images = new BufferedImage[num];
    for (int i = 0; i < num; ++i) {
      images[i] = imageReader.read(i);
    }
    inputStream.close();
  }

  public void paint(Graphics g) {
    if (images == null)
      return;
    g.drawImage(images[imageIndex], 0, 0, null);
    imageIndex = (imageIndex + 1) % images.length;
  }

  static public void main(String args[]) throws Exception {
    JFrame frame = new JFrame();
    Panel panel = new Main();
    frame.add(panel);
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

   