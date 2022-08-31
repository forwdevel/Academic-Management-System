package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import daovo.StudentDao;

//
//	�뀒�씠釉� 媛� �닔�젙
//

public class Staff_Inquiry_Student extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton remove_btn;
	private JButton back_btn;

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal

	public Staff_Inquiry_Student(String id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c2);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fixed_1 = new JLabel("학생조회");
		fixed_1.setFont(new Font("휴먼엑스포", Font.BOLD, 24));
		fixed_1.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_1.setBounds(353, 32, 227, 33);
		fixed_1.setForeground(c3);
		contentPane.add(fixed_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(156, 85, 655, 386);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(c3);
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new StudentDao().allStudent(),
			new String[] {
				"\uD559\uBC88", "\uD559\uC0DD\uBA85", "\uB2E8\uACFC", "\uC804\uACF5", "\uD559\uB144", "\uC785\uD559\uB144\uB3C4"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(1).setPreferredWidth(55);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(15);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		
		back_btn = new JButton("뒤로");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Staff_Main(id);
			}
		});
		back_btn.setBounds(421, 503, 97, 23);
		back_btn.setBackground(c1);
		back_btn.setForeground(c3);
		back_btn.setBorder(null);
		back_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 16));
		contentPane.add(back_btn);
		
		setVisible(true);
	}
}
