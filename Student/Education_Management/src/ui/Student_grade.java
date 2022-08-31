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

import daovo.EtcDao;
import daovo.StudentVo;

@SuppressWarnings("serial")
public class Student_grade extends JFrame {
	private JPanel contentPane;
	private Object[][] object;
	private JTable table;
	
	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal
	
	public Student_grade(StudentVo vo) {

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c3);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		
		JLabel fixed_1 = new JLabel("성적 조회");
		fixed_1.setBounds(333, 46, 296, 40);
		fixed_1.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_1.setFont(new Font("휴먼엑스포", Font.BOLD, 24));
		fixed_1.setForeground(c1);
		contentPane.add(fixed_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 131, 514, 316);
		scrollPane.setBackground(Color.WHITE);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new EtcDao().returnGrade(vo.getId()),
			new String[] {
				"과목코드", "과목명", "성적"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBackground(Color.white);
		scrollPane.setViewportView(table);
		
		JButton back_btn = new JButton("뒤로가기");
		back_btn.setBounds(446, 492, 97, 23);
		// ActionListener
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Student_Main(vo);
			}
		});
		back_btn.setBorder(null);
		back_btn.setBackground(c1);
		back_btn.setForeground(c4);
		contentPane.add(back_btn);
		
		setVisible(true);
	}
}
