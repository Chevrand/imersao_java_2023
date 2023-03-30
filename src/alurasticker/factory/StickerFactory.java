package alurasticker.factory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class StickerFactory {
	
	public void createSticker(InputStream image, String stickerName, String subtitle) throws IOException {
		// leitura da imagem
		// InputStream localImage = new FileInputStream(new File("assets/movie.jpg"));
		BufferedImage originalImage = ImageIO.read(image);
		
		// cria nova imagem com transparência e com novo tamanho (em memória)
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		int textBoxHeight = (height / 100) * 20;
		int newHeight = height + textBoxHeight;
		BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);
		
		// copia a imagem original para a nova imagem (em memória)
		Graphics2D graphics = (Graphics2D) newImage.getGraphics();
		graphics.drawImage(originalImage,0, 0, null);
		
		// configura texto a ser escrito
		int textSize = (newHeight / 100) * 6;
		Font font = new Font("impact", Font.BOLD, textSize);
		graphics.setColor(Color.YELLOW);		
		graphics.setFont(font);

		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle2D rectangle = fontMetrics.getStringBounds(subtitle, graphics);
		int textWidth = (int) rectangle.getWidth();
		int textHeight = (int) rectangle.getHeight();
		int textXPosition = (width - textWidth) / 2;
		int textYPosition = (textBoxHeight - textHeight) / 2;

		// escrever um texto personalizado na imagem (em memória)
		graphics.drawString(subtitle, textXPosition, newHeight - textYPosition);
		
		FontRenderContext fontRenderContext = graphics.getFontRenderContext();
		TextLayout textLayout = new TextLayout(subtitle, font, fontRenderContext);
		Shape outline = textLayout.getOutline(null);
		AffineTransform transform = graphics.getTransform();
		transform.translate(textXPosition, (double) newHeight - textYPosition);
		graphics.setTransform(transform);
		BasicStroke outlineStroke = new BasicStroke(width * 0.004f);
		graphics.setStroke(outlineStroke);
		graphics.setColor(Color.BLACK);
		graphics.draw(outline);
		graphics.setClip(outline);
		
		// escrever a nova imagem em um arquivo local
		ImageIO.write(newImage, "png", new File(String.format("stickers/%s.png", stickerName.replace(":", ""))));
	}
}
