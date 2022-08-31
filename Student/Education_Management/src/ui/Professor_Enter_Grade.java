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
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import daovo.ProfessorDao;
import daovo.ProfessorVo;

public class Professor_Enter_Grade extends JFrame {

	private JPanel contentPane;

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal
	private JTable table;
	
	public Professor_Enter_Grade(ProfessorVo vo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c2);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel fixed_header = new JLabel("성적입력");
		fixed_header.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_header.setBounds(245, 28, 475, 41);
		fixed_header.setFont(new Font("휴먼엑스포", Font.BOLD, 30));
		fixed_header.setForeground(c3);
		contentPane.add(fixed_header);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(133, 87, 714, 390);
		contentPane.add(scrollPane);
		
		// Object[][] object;
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new ProfessorDao().inquiryLectureForGrade(vo.getName()),
			new String[] {
				"\uD559\uC218\uBC88\uD638", "\uD559\uBC88", "\uD559\uC0DD\uBA85", "\uC131\uC801\uC785\uB825"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		scrollPane.setViewportView(table);
		
//		table.getModel().addTableModelListener(new TableModelListener() {
//			public void tableChanged(TableModelEvent e) {
//				table.edit
//			}
//		});
		
		JButton enter_btn = new JButton("입력");
		enter_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < table.getRowCount(); i++) {
					new ProfessorDao().enterGrade(table.getValueAt(i, 0).toString(), table.getValueAt(i, 1).toString(), table.getValueAt(i, 2).toString(), table.getValueAt(i, 3).toString());
					
				}
				new Professor_Main(vo);
				setVisible(false);
				new Alert("입력하였습니다.");
			}
		}); 
		enter_btn.setBounds(440, 499, 97, 36);
		enter_btn.setForeground(c1);
		enter_btn.setBackground(c3);
		enter_btn.setBorder(null);
		enter_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		contentPane.add(enter_btn);
		
		setVisible(true);
	}
}
