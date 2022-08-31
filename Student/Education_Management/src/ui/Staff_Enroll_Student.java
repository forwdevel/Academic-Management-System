package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import daovo.EtcDao;
import daovo.StudentDao;

//
//	complete
//

public class Staff_Enroll_Student extends JFrame {

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal

	private JPanel contentPane;
	private JTextField stu_name;
	private JTextField college;
	private JTextField major;
	private JTextField enroll;

	public Staff_Enroll_Student(String id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c2);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel fixed_1 = new JLabel("학생 등록");
		fixed_1.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_1.setFont(new Font("휴먼엑스포", Font.BOLD, 36));
		fixed_1.setForeground(c3);
		fixed_1.setBounds(393, 66, 167, 49);
		contentPane.add(fixed_1);

		JLabel fixed_3 = new JLabel("학생명");
		fixed_3.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_3.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		fixed_3.setForeground(c3);
		fixed_3.setBounds(341, 257, 57, 23);
		contentPane.add(fixed_3);
		
		JLabel fixed_3_1 = new JLabel("입학년도");
		fixed_3_1.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_3_1.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		fixed_3_1.setBounds(299, 209, 99, 23);
		fixed_3_1.setForeground(c3);
		contentPane.add(fixed_3_1);

		JLabel fixed_5 = new JLabel("단과");
		fixed_5.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_5.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		fixed_5.setForeground(c3);
		fixed_5.setBounds(341, 308, 57, 23);
		contentPane.add(fixed_5);

		JLabel fixed_6 = new JLabel("전공");
		fixed_6.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_6.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		fixed_6.setForeground(c3);
		fixed_6.setBounds(341, 358, 57, 23);
		contentPane.add(fixed_6);

		stu_name = new JTextField();
		stu_name.setHorizontalAlignment(SwingConstants.CENTER);
		stu_name.setColumns(10);
		stu_name.setBounds(410, 257, 179, 27);
		stu_name.setBorder(null);
		stu_name.setBackground(c2);
		stu_name.setForeground(c4);
		stu_name.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		stu_name.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// enter is pressed
					if (enroll.getText().length() == 0) {
						new Alert("입학년도를 입력해주세요.");
						return;
					} else if (stu_name.getText().length() == 0) {
						new Alert("학생명을 입력해주세요.");
						return;
					} else if (college.getText().length() == 0) {
						new Alert("단과를 입력해주세요.");
						return;
					} else if (major.getText().length() == 0) {
						new Alert("전공을 입력해주세요.");
						return;
					} else {
						// major & college check
						if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
							// not exist college
							college.setText("");
							major.setText("");
							return;
						} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
							major.setText("");
							return;
						}

						boolean b = new StudentDao().enroll_stu(Integer.parseInt(enroll.getText()), stu_name.getText(), college.getText(),
								major.getText());

						if (b) {
							setVisible(false);
							new Alert("등록하였습니다.");
							new Staff_Main(id);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		contentPane.add(stu_name);

		college = new JTextField();
		college.setHorizontalAlignment(SwingConstants.CENTER);
		college.setBorder(null);
		college.setBackground(c2);
		college.setForeground(c4);
		college.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		college.setColumns(10);
		college.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// enter is pressed
					if (enroll.getText().length() == 0) {
						new Alert("입학년도를 입력해주세요.");
						return;
					} else if (stu_name.getText().length() == 0) {
						new Alert("학생명을 입력해주세요.");
						return;
					} else if (college.getText().length() == 0) {
						new Alert("단과를 입력해주세요.");
						return;
					} else if (major.getText().length() == 0) {
						new Alert("전공을 입력해주세요.");
						return;
					} else {
						// major & college check
						if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
							// not exist college
							college.setText("");
							major.setText("");
							return;
						} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
							major.setText("");
							return;
						}

						boolean b = new StudentDao().enroll_stu(Integer.parseInt(enroll.getText()), stu_name.getText(), college.getText(),
								major.getText());

						if (b) {
							setVisible(false);
							new Alert("등록하였습니다.");
							new Staff_Main(id);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		college.setBounds(410, 308, 179, 27);
		
		contentPane.add(college);

		major = new JTextField();
		major.setHorizontalAlignment(SwingConstants.CENTER);
		major.setColumns(10);
		major.setBounds(410, 358, 179, 27);
		major.setBorder(null);
		major.setBackground(c2);
		major.setForeground(c4);
		major.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		major.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// enter is pressed
					if (enroll.getText().length() == 0) {
						new Alert("입학년도를 입력해주세요.");
						return;
					} else if (stu_name.getText().length() == 0) {
						new Alert("학생명을 입력해주세요.");
						return;
					} else if (college.getText().length() == 0) {
						new Alert("단과를 입력해주세요.");
						return;
					} else if (major.getText().length() == 0) {
						new Alert("전공을 입력해주세요.");
						return;
					} else {
						// major & college check
						if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
							// not exist college
							college.setText("");
							major.setText("");
							return;
						} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
							major.setText("");
							return;
						}

						boolean b = new StudentDao().enroll_stu(Integer.parseInt(enroll.getText()), stu_name.getText(), college.getText(),
								major.getText());

						if (b) {
							setVisible(false);
							new Alert("등록하였습니다.");
							new Staff_Main(id);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		contentPane.add(major);

		JButton enter_btn = new JButton("등록");
		enter_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (enroll.getText().length() == 0) {
					new Alert("입학년도를 입력해주세요.");
					return;
				} else if (stu_name.getText().length() == 0) {
					new Alert("학생명을 입력해주세요.");
					return;
				} else if (college.getText().length() == 0) {
					new Alert("단과를 입력해주세요.");
					return;
				} else if (major.getText().length() == 0) {
					new Alert("전공을 입력해주세요.");
					return;
				} else {
					// major & college check
					if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
						// not exist college
						college.setText("");
						major.setText("");
						return;
					} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
						major.setText("");
						return;
					}

					boolean b = new StudentDao().enroll_stu(Integer.parseInt(enroll.getText()), stu_name.getText(), college.getText(),
							major.getText());

					if (b) {
						setVisible(false);
						new Staff_Main(id);
						new Alert("등록하였습니다.");
					}
				}
			}
		});
		enter_btn.setBounds(353, 440, 98, 37);
		enter_btn.setBorder(null);
		enter_btn.setBackground(c3);
		enter_btn.setForeground(c1);
		enter_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		contentPane.add(enter_btn);

		JButton cancel_btn = new JButton("취소");
		cancel_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Staff_Main(id);
			}
		});
		cancel_btn.setBounds(495, 440, 94, 37);
		cancel_btn.setBackground(c1);
		cancel_btn.setBorder(null);
		cancel_btn.setForeground(c3);
		cancel_btn.setFont(new Font("휴먼엑스포", Font.BOLD, 14));
		contentPane.add(cancel_btn);
		
		enroll = new JTextField();
		enroll.setHorizontalAlignment(SwingConstants.CENTER);
		enroll.setColumns(10);
		enroll.setBounds(410, 209, 179, 27);
		enroll.setBorder(null);
		enroll.setBackground(c2);
		enroll.setForeground(c4);
		enroll.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		enroll.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					// enter is pressed
					if (enroll.getText().length() == 0) {
						new Alert("입학년도를 입력해주세요.");
						return;
					} else if (stu_name.getText().length() == 0) {
						new Alert("학생명을 입력해주세요.");
						return;
					} else if (college.getText().length() == 0) {
						new Alert("단과를 입력해주세요.");
						return;
					} else if (major.getText().length() == 0) {
						new Alert("전공을 입력해주세요.");
						return;
					} else {
						// major & college check
						if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
							// not exist college
							college.setText("");
							major.setText("");
							return;
						} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
							major.setText("");
							return;
						}

						boolean b = new StudentDao().enroll_stu(Integer.parseInt(enroll.getText()), stu_name.getText(), college.getText(),
								major.getText());

						if (b) {
							setVisible(false);
							new Alert("등록하였습니다.");
							new Staff_Main(id);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		contentPane.add(enroll);
		
		setVisible(true);
	}
}
