package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Font;

public class ThongKeDoanhThuGUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtTrangDoanhThu;

	/**
	 * Create the panel.
	 */
	public ThongKeDoanhThuGUI() {
		setLayout(null);
		
		txtTrangDoanhThu = new JTextField();
		txtTrangDoanhThu.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtTrangDoanhThu.setText("Trang Doanh Thu");
		txtTrangDoanhThu.setBounds(31, 10, 206, 28);
		add(txtTrangDoanhThu);
		txtTrangDoanhThu.setColumns(10);

	}

}
