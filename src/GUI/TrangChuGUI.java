package GUI;

import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TrangChuGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public String absolutePath = new File("").getAbsolutePath();

	/**
	 * Create the panel.
	 */
	public TrangChuGUI() {
		JPanel pnlHome = new JPanel();
		pnlHome.setLayout(new GridLayout(1, 0));
		JLabel lblAnhTrangChu = new JLabel();
		ImageIcon icon = new ImageIcon(absolutePath + "/src/images/images/trangchu.jpg");
		Image image = icon.getImage();
		Image scaledImage = image.getScaledInstance(1280, 720, Image.SCALE_DEFAULT);
		icon = new ImageIcon(scaledImage);
		lblAnhTrangChu.setIcon(icon);
		pnlHome.add(lblAnhTrangChu);
		add(pnlHome);
	}

}
