package ui;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import daovo.EtcDao;
import daovo.StudentVo;

@SuppressWarnings("serial")
public class Student_Main extends JFrame {

	private JPanel contentPane;

	public Student_Main(StudentVo vo) {

		Color c1 = new Color(95,113,97); 	// Dark Green
		Color c2 = new Color(109,139,116);	// Ash Green
		Color c3 = new Color(239,234,216);	// light meal
		Color c4 = new Color(208,201,192);	// dark meal
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c3);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel fixed_1 = new JLabel("학생,");
		fixed_1.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_1.setFont(new Font("휴먼엑스포", Font.BOLD, 32));
		fixed_1.setBounds(179, 22, 110, 69);
		fixed_1.setForeground(c1);
		contentPane.add(fixed_1);
		
		JLabel fixed_6 = new JLabel("학번");
		fixed_6.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		fixed_6.setBounds(26, 108, 56, 43);
		fixed_6.setForeground(c1);
		contentPane.add(fixed_6);
		
		JLabel fixed_7 = new JLabel("학년");
		fixed_7.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		fixed_7.setBounds(26, 146, 56, 43);
		fixed_7.setForeground(c1);
		contentPane.add(fixed_7);
		
		JLabel fixed_8 = new JLabel("단과");
		fixed_8.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		fixed_8.setBounds(26, 187, 56, 43);
		fixed_8.setForeground(c1);
		contentPane.add(fixed_8);
		
		JLabel fixed_9 = new JLabel("전공");
		fixed_9.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		fixed_9.setBounds(26, 226, 56, 43);
		fixed_9.setForeground(c1);
		contentPane.add(fixed_9);
		
		JLabel fixed_10 = new JLabel("입학년도");
		fixed_10.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		fixed_10.setBounds(26, 269, 100, 43);
		fixed_10.setForeground(c1);
		contentPane.add(fixed_10);
		
		JLabel fixed_11 = new JLabel("현재수강학점");
		fixed_11.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		fixed_11.setBounds(26, 312, 154, 43);
		fixed_11.setForeground(c1);
		contentPane.add(fixed_11);
		
		JLabel fixed_2 = new JLabel("최대수강학점");
		fixed_2.setFont(new Font("휴먼엑스포", Font.BOLD, 20));
		fixed_2.setBounds(26, 353, 154, 43);
		fixed_2.setForeground(c1);
		contentPane.add(fixed_2);
		
		JLabel fixed_3 = new JLabel("18");
		fixed_3.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_3.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
		fixed_3.setBounds(178, 353, 111, 43);
		fixed_3.setForeground(c2);
		contentPane.add(fixed_3);
		
		JLabel fixed_4 = new JLabel("일정");
		fixed_4.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_4.setFont(new Font("휴먼엑스포", Font.BOLD, 24));
		fixed_4.setBounds(697, 22, 80, 43);
		fixed_4.setForeground(c1);
		contentPane.add(fixed_4);
		
		JLabel fixed_5 = new JLabel("공지사항");
		fixed_5.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_5.setFont(new Font("휴먼엑스포", Font.BOLD, 24));
		fixed_5.setBounds(684, 288, 110, 43);
		fixed_5.setForeground(c1);
		contentPane.add(fixed_5);
		
		JLabel stu_name = new JLabel(vo.getName());
		stu_name.setFont(new Font("휴먼엑스포", Font.BOLD, 32));
		stu_name.setBounds(26, 22, 176, 69);
		stu_name.setForeground(c2);
		contentPane.add(stu_name);
		
		JButton lecture_register = new JButton("수강신청");
		// ActionListener
		lecture_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = new EtcDao().getEnter();
				if(b) {
					setVisible(false);
					new Student_Enroll_register(vo);
					return;
				}
				new Alert("수강신청기간이아닙니다.");
			}
		});
		lecture_register.setFont(new Font("휴먼엑스포", Font.BOLD, 16));
		lecture_register.setForeground(SystemColor.textHighlightText);
		lecture_register.setBackground(SystemColor.textHighlight);
		lecture_register.setBounds(38, 431, 110, 43);
		lecture_register.setForeground(c3);
		lecture_register.setBackground(c1);
		lecture_register.setBorder(null);
		contentPane.add(lecture_register);
		
		JButton grade_inquiry = new JButton("성적조회");
		// ActionListener
		grade_inquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = new EtcDao().getInquiry();
				if(b) {
					setVisible(false);
					new Student_grade(vo);		
					return;
				}
				new Alert("성적조회기간이 아닙니다.");
			}
			
		});
		grade_inquiry.setForeground(Color.WHITE);
		grade_inquiry.setFont(new Font("휴먼엑스포", Font.BOLD, 16));
		grade_inquiry.setBackground(SystemColor.textHighlight);
		grade_inquiry.setBounds(179, 431, 110, 43);
		grade_inquiry.setForeground(c3);
		grade_inquiry.setBackground(c1);
		grade_inquiry.setBorder(null);
		contentPane.add(grade_inquiry);
		
		JLabel stu_id = new JLabel("" +vo.getId());
		stu_id.setHorizontalAlignment(SwingConstants.RIGHT);
		stu_id.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
		stu_id.setBounds(78, 108, 211, 43);
		stu_id.setForeground(c2);
		contentPane.add(stu_id);
		
		JLabel stu_grade = new JLabel("" + vo.getGrade());
		stu_grade.setHorizontalAlignment(SwingConstants.RIGHT);
		stu_grade.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
		stu_grade.setBounds(78, 146, 211, 43);
		stu_grade.setForeground(c2);
		contentPane.add(stu_grade);
		
		JLabel stu_college = new JLabel(vo.getCollege());
		stu_college.setHorizontalAlignment(SwingConstants.RIGHT);
		stu_college.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		stu_college.setBounds(78, 187, 211, 43);
		stu_college.setForeground(c2);
		contentPane.add(stu_college);
		
		JLabel stu_major = new JLabel(vo.getMajor());
		stu_major.setHorizontalAlignment(SwingConstants.RIGHT);
		stu_major.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		stu_major.setBounds(78, 226, 211, 43);
		stu_major.setForeground(c2);
		contentPane.add(stu_major);
		
		JLabel stu_enroll = new JLabel("" + vo.getEnroll());
		stu_enroll.setHorizontalAlignment(SwingConstants.RIGHT);
		stu_enroll.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
		stu_enroll.setBounds(113, 269, 176, 43);
		stu_enroll.setForeground(c2);
		contentPane.add(stu_enroll);
		
		JLabel credit = new JLabel("" + vo.getCredit());
		credit.setHorizontalAlignment(SwingConstants.RIGHT);
		credit.setFont(new Font("휴먼엑스포", Font.PLAIN, 20));
		credit.setBounds(178, 312, 111, 43);
		credit.setForeground(c2);
		contentPane.add(credit);
		
		JButton change_pw_btn = new JButton("비밀번호변경");
		change_pw_btn.setForeground(Color.WHITE);
		change_pw_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 16));
		change_pw_btn.setBorder(null);
		change_pw_btn.setBackground(c4);
		change_pw_btn.setForeground(c2);
		change_pw_btn.setBounds(38, 484, 251, 26);
		change_pw_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ChangePassword(vo.getId());
			}
		});
		contentPane.add(change_pw_btn);
		
		boolean enter = new EtcDao().getEnter();
		boolean inquiry = new EtcDao().getInquiry();
		
		JLabel enterNoticeLabel = new JLabel("New label");
		if(enter) {
			enterNoticeLabel.setText("수강신청기간입니다.");
		} else {
			enterNoticeLabel.setText("수강신청기간이 아닙니다");
		}
		enterNoticeLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		enterNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		enterNoticeLabel.setBounds(595, 75, 286, 26);
		contentPane.add(enterNoticeLabel);
		
		JLabel inquiryNoticeLabel = new JLabel("New label");
		if(inquiry) {
			inquiryNoticeLabel.setText("수강조회기간입니다.");
		} else {
			inquiryNoticeLabel.setText("수강조회기간이 아닙니다.");
		}
		inquiryNoticeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		inquiryNoticeLabel.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		inquiryNoticeLabel.setBounds(595, 117, 286, 26);
		contentPane.add(inquiryNoticeLabel);
		
		JLabel Notice_1 = new JLabel("수시로 비밀번호를 변경해주세요.");
		Notice_1.setHorizontalAlignment(SwingConstants.CENTER);
		Notice_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		Notice_1.setBounds(595, 343, 286, 26);
		contentPane.add(Notice_1);
		
		JButton logOut_btn = new JButton("로그아웃");
		logOut_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new LogIn();
			}
		});
		logOut_btn.setBounds(855, 525, 97, 26);
		logOut_btn.setBackground(c4);
		logOut_btn.setForeground(c1);
		logOut_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 16));
		logOut_btn.setBorder(null);
		contentPane.add(logOut_btn);
		
		setVisible(true);
	}
}