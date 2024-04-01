package GUI;

import java.awt.*;
import javax.swing.*;

public class BaoHanhGUI {
    static JTextField productNameField, warrantyPeriodField,warrantyReasonField,searchField;
    static JButton submitButton;
    static JTable table;

    public static JPanel baoHanhGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Panel for input fields
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel productNameLabel = new JLabel("Tên sản phẩm:");
        inputPanel.add(productNameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        productNameField = new JTextField(20);
        inputPanel.add(productNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel warrantyPeriodLabel = new JLabel("Thời gian bảo hành:");
        inputPanel.add(warrantyPeriodLabel, gbc);

        gbc.gridx = 1;
        warrantyPeriodField = new JTextField(20);
        inputPanel.add(warrantyPeriodField, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel reasonLabel = new JLabel("Lý do bảo hành:");
        inputPanel.add(reasonLabel, gbc);

        gbc.gridx = 1;
        warrantyReasonField = new JTextField(20); // Change from JComboBox to JTextField
        inputPanel.add(warrantyReasonField, gbc);
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        // Panel for table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Danh sách sản phẩm"));
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        // Panel for search input
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        JLabel searchLabel = new JLabel("Tìm kiếm:");
        searchPanel.add(searchLabel);
        searchField = new JTextField(15);
        searchPanel.add(searchField);
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        
        JButton addButton = new JButton("Thêm");
        JButton editButton = new JButton("Sửa");
        JButton deleteButton = new JButton("Xóa");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        return mainPanel;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(baoHanhGUI());
        frame.pack();
        frame.setVisible(true);
    }
}
