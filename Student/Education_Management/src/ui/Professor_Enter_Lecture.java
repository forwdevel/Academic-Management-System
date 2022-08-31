package ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import daovo.ProfessorDao;
import daovo.ProfessorVo;
import javax.swing.JTextField;
import java.awt.Font;

//
//	complete
//

public class Professor_Enter_Lecture extends JFrame {

	private JPanel contentPane;

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal
	
	private JTextField name;
	private JTextField com;
	private JTextField credit;
	private JTextField room;
	private JTextField limit;
	private JTextField current;
	
	public Professor_Enter_Lecture(ProfessorVo vo) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c2);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton enter_btn = new JButton("등록");
		enter_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name.getText().length() == 0) {
					new Alert("과목명을 입력해주세요.");
					return;
				} else if (com.getText().length() == 0) {
					new Alert("이수구분을 입력해주세요.");
					return;
				} else if (credit.getText().length() == 0) {
					new Alert("학점을 입력해주세요.");
					return;
				} else if (room.getText().length() == 0) {
					new Alert("강의실을 입력해주세요.");
					return;
				}
				
				new ProfessorDao().enterLecture(vo, name.getText(), com.getText(), credit.getText(), room.getText(), limit.getText(), "0");
				setVisible(false);
			}
		});
		enter_btn.setBounds(346, 491, 97, 35);
		enter_btn.setBorder(null);
		enter_btn.setBackground(c3);
		enter_btn.setForeground(c1);
		enter_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		contentPane.add(enter_btn);
		
		JButton back_btn = new JButton("취소");
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Professor_Main(vo);
			}
		});
		back_btn.setBounds(512, 491, 97, 35);
		back_btn.setBorder(null);
		back_btn.setBackground(c4);
		back_btn.setForeground(c1);
		back_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		contentPane.add(back_btn);
		
		JLabel fixed_head = new JLabel("과목등록");
		fixed_head.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_head.setBounds(236, 22, 498, 63);
		fixed_head.setForeground(c3);
		fixed_head.setFont(new Font("휴먼엑스포",Font.BOLD, 36));
		contentPane.add(fixed_head);
		
		name = new JTextField();
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		name.setBounds(417, 152, 196, 35);
		name.setColumns(10);
		name.setBackground(c2);
		name.setBorder(null);
		name.setForeground(c4);
		contentPane.add(name);
		
		com = new JTextField();
		com.setHorizontalAlignment(SwingConstants.CENTER);
		com.setColumns(10);
		com.setBounds(417, 209, 196, 35);
		com.setBackground(c2);
		com.setBorder(null);
		com.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		com.setForeground(c4);
		contentPane.add(com);
		
		credit = new JTextField();
		credit.setHorizontalAlignment(SwingConstants.CENTER);
		credit.setColumns(10);
		credit.setBounds(417, 266, 196, 35);
		credit.setBackground(c2);
		credit.setBorder(null);
		credit.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		credit.setForeground(c4);
		contentPane.add(credit);
		
		room = new JTextField();
		room.setHorizontalAlignment(SwingConstants.CENTER);
		room.setColumns(10);
		room.setBounds(417, 322, 196, 35);
		room.setBackground(c2);
		room.setBorder(null);
		room.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		room.setForeground(c4);
		contentPane.add(room);
		
		limit = new JTextField();
		limit.setHorizontalAlignment(SwingConstants.CENTER);
		limit.setColumns(10);
		limit.setBounds(417, 379, 196, 35);
		limit.setBackground(c2);
		limit.setBorder(null);
		limit.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		limit.setForeground(c4);
		contentPane.add(limit);
		
		JLabel fixed_name = new JLabel("과목명");
		fixed_name.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_name.setBounds(249, 152, 156, 35);
		fixed_name.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		fixed_name.setForeground(c3);
		contentPane.add(fixed_name);
		
		JLabel fixed_com = new JLabel("이수구분");
		fixed_com.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_com.setBounds(249, 209, 156, 35);
		fixed_com.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		fixed_com.setForeground(c3);
		contentPane.add(fixed_com);
		
		JLabel fixed_credit = new JLabel("학점");
		fixed_credit.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_credit.setBounds(249, 266, 156, 35);
		fixed_credit.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		fixed_credit.setForeground(c3);
		contentPane.add(fixed_credit);
		
		JLabel fixed_room = new JLabel("강의실");
		fixed_room.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_room.setBounds(249, 322, 156, 35);
		fixed_room.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		fixed_room.setForeground(c3);
		contentPane.add(fixed_room);
		
		JLabel fixed_limit = new JLabel("인원제한");
		fixed_limit.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_limit.setBounds(249, 379, 156, 35);
		fixed_limit.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		fixed_limit.setForeground(c3);
		contentPane.add(fixed_limit);
		
		JLabel fixed_notice = new JLabel("공란 시, 인원제한없이 수강신청을 받습니다.");
		fixed_notice.setBounds(416, 413, 474, 15);
		fixed_notice.setForeground(Color.white);
		fixed_notice.setFont(new Font("휴먼엑스포", Font.PLAIN, 10));
		contentPane.add(fixed_notice);
		
		setVisible(true);
	}
}
