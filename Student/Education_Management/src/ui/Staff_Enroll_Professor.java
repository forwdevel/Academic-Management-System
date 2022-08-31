package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import daovo.EtcDao;
import daovo.ProfessorDao;
import javax.swing.UIManager;

//
//	complete
//

public class Staff_Enroll_Professor extends JFrame {

	Color c1 = new Color(95,113,97); 	// Dark Green
	Color c2 = new Color(109,139,116);	// Ash Green
	Color c3 = new Color(239,234,216);	// light meal
	Color c4 = new Color(208,201,192);	// dark meal

	private JPanel contentPane;
	private JTextField pro_enroll;
	private JTextField pro_name;
	private JTextField college;
	private JTextField major;

	public Staff_Enroll_Professor(String id_login) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 980, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(c2);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel fixed_1 = new JLabel("교수 등록");
		fixed_1.setForeground(c3);
		fixed_1.setHorizontalAlignment(SwingConstants.CENTER);
		fixed_1.setFont(new Font("휴먼엑스포", Font.PLAIN, 36));
		fixed_1.setBounds(393, 76, 167, 49);
		contentPane.add(fixed_1);

		JLabel fixed_2 = new JLabel("임용년도");
		fixed_2.setForeground(c3);
		fixed_2.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_2.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
		fixed_2.setBounds(300, 208, 98, 23);
		contentPane.add(fixed_2);

		JLabel fixed_3 = new JLabel("교수명");
		fixed_3.setForeground(c3);
		fixed_3.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_3.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
		fixed_3.setBounds(341, 257, 57, 23);
		contentPane.add(fixed_3);

		JLabel fixed_4 = new JLabel("단과");
		fixed_4.setForeground(c3);
		fixed_4.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_4.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
		fixed_4.setBounds(341, 308, 57, 23);
		contentPane.add(fixed_4);

		JLabel fixed_5 = new JLabel("전공");
		fixed_5.setForeground(c3);
		fixed_5.setHorizontalAlignment(SwingConstants.RIGHT);
		fixed_5.setFont(new Font("휴먼엑스포", Font.PLAIN, 18));
		fixed_5.setBounds(341, 358, 57, 23);
		contentPane.add(fixed_5);

		pro_enroll = new JTextField();
		pro_enroll.setHorizontalAlignment(SwingConstants.CENTER);
		pro_enroll.setToolTipText("");
		pro_enroll.setBounds(410, 208, 179, 27);
		pro_enroll.setForeground(c4);
		pro_enroll.setBackground(c2);
		pro_enroll.setBorder(null);
		pro_enroll.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		pro_enroll.setColumns(10);
		pro_enroll.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					// Enter is pressed
					int id = 0;
					int enroll = 0;
					if (pro_enroll.getText().length() == 0) {
						new Alert("임용년도를 입력해주세요.");
					} else if (pro_name.getText().length() == 0) {
						new Alert("교수명을 입력해주세요.");
					} else if (college.getText().length() == 0) {
						new Alert("단과를 입력해주세요.");
					} else if (major.getText().length() == 0) {
						new Alert("전공을 입력해주세요.");
					} else {
						// not null

						// id check
						// 1. check format
						try {
							id = Integer.parseInt(pro_enroll.getText());
						} catch (NumberFormatException e1) {
							System.out.println(pro_enroll.getText());
							new Alert("임용년도는 숫자만 입력 가능합니다.");
							return;
						}
						// 2. check length
						if (pro_enroll.getText().length() != 4) {
							new Alert("임용년도는 4자리입니다.");
							return;
						}

						// major & college check
						if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
							college.setText("");
							major.setText("");
							return;
						} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
							major.setText("");
							return;
						}

						boolean b = new ProfessorDao().enroll_pro(pro_name.getText(), college.getText(),
								major.getText(), enroll);

						if (b) {
							setVisible(false);
							new Alert("등록되었습니다.");
							new Staff_Main(id_login);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		contentPane.add(pro_enroll);

		pro_name = new JTextField();
		pro_name.setHorizontalAlignment(SwingConstants.CENTER);
		pro_name.setColumns(10);
		pro_name.setBounds(410, 257, 179, 27);
		pro_name.setForeground(c4);
		pro_name.setBackground(c2);
		pro_name.setBorder(null);
		pro_name.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		pro_name.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					// Enter is pressed
					int id = 0;
					int enroll = 0;
					if (pro_enroll.getText().length() == 0) {
						new Alert("임용년도를 입력해주세요.");
					} else if (pro_name.getText().length() == 0) {
						new Alert("교수명을 입력해주세요.");
					} else if (college.getText().length() == 0) {
						new Alert("단과를 입력해주세요.");
					} else if (major.getText().length() == 0) {
						new Alert("전공을 입력해주세요.");
					} else {
						// not null

						// id check
						// 1. check format
						try {
							id = Integer.parseInt(pro_enroll.getText());
						} catch (NumberFormatException e1) {
							System.out.println(pro_enroll.getText());
							new Alert("임용년도는 숫자만 입력가능합니다.");
							return;
						}
						// 2. check length
						if (pro_enroll.getText().length() != 4) {
							new Alert("임용년도는 4자리만 가능합니다.");
							return;
						}

						// major & college check
						if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
							college.setText("");
							major.setText("");
							return;
						} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
							major.setText("");
							return;
						}

						boolean b = new ProfessorDao().enroll_pro(pro_name.getText(), college.getText(),
								major.getText(), enroll);

						if (b) {
							setVisible(false);
							new Staff_Main(id_login);
							new Alert("등록하였습니다.");
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		contentPane.add(pro_name);

		college = new JTextField();
		college.setHorizontalAlignment(SwingConstants.CENTER);
		college.setColumns(10);
		college.setBounds(410, 308, 179, 27);
		college.setForeground(c4);
		college.setBackground(c2);
		college.setBorder(null);
		college.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		college.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					// Enter is pressed
					int id = 0;
					int enroll = 0;
					if (pro_enroll.getText().length() == 0) {
						new Alert("임용년도를 입력해주세요.");
					} else if (pro_name.getText().length() == 0) {
						new Alert("교수명을 입력해주세요.");
					} else if (college.getText().length() == 0) {
						new Alert("단과를 입력해주세요.");
					} else if (major.getText().length() == 0) {
						new Alert("전공을 입력해주세요.");
					} else {
						// not null

						// id check
						// 1. check format
						try {
							id = Integer.parseInt(pro_enroll.getText());
						} catch (NumberFormatException e1) {
							System.out.println(pro_enroll.getText());
							new Alert("임용년도는 숫자만 입력가능합니다.");
							return;
						}
						// 2. check length
						if (pro_enroll.getText().length() != 4) {
							new Alert("임용년도는 4자리입니다.");
							return;
						}

						// major & college check
						if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
							college.setText("");
							major.setText("");
							return;
						} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
							major.setText("");
							return;
						}

						boolean b = new ProfessorDao().enroll_pro(pro_name.getText(), college.getText(),
								major.getText(), enroll);

						if (b) {
							setVisible(false);
							new Alert("등록하였습니다.");
							new Staff_Main(id_login);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		contentPane.add(college);

		major = new JTextField();
		major.setHorizontalAlignment(SwingConstants.CENTER);
		major.setColumns(10);
		major.setBounds(410, 358, 179, 27);
		major.setForeground(c4);
		major.setBackground(c2);
		major.setBorder(null);
		major.setFont(new Font("휴먼엑스포", Font.BOLD, 18));
		major.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==10) {
					// Enter is pressed
					int id = 0;
					int enroll = 0;
					if (pro_enroll.getText().length() == 0) {
						new Alert("임용년도를 입력해주세요.");
					} else if (pro_name.getText().length() == 0) {
						new Alert("교수명을 입력해주세요.");
					} else if (college.getText().length() == 0) {
						new Alert("단과를 입력해주세요.");
					} else if (major.getText().length() == 0) {
						new Alert("전공을 입력해주세요.");
					} else {
						// not null

						// id check
						// 1. check format
						try {
							id = Integer.parseInt(pro_enroll.getText());
						} catch (NumberFormatException e1) {
							System.out.println(pro_enroll.getText());
							new Alert("임용년도는 숫자만 입력가능합니다.");
							return;
						}
						// 2. check length
						if (pro_enroll.getText().length() != 4) {
							new Alert("임용년도는 4자리입니다.");
							return;
						}

						// major & college check
						if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
							college.setText("");
							major.setText("");
							return;
						} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
							major.setText("");
							return;
						}

						boolean b = new ProfessorDao().enroll_pro(pro_name.getText(), college.getText(),
								major.getText(), enroll);

						if (b) {
							setVisible(false);
							new Alert("등록하였습니다.");
							new Staff_Main(id_login);
						}
					}
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		contentPane.add(major);

		JButton enter_btn = new JButton("등록");
		enter_btn.setBackground(c3);
		enter_btn.setForeground(c1);
		enter_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		enter_btn.setBorder(null);
		enter_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = 0;
				int enroll = 0;
				if (pro_enroll.getText().length() == 0) {
					new Alert("�엫�슜�뀈�룄瑜� �엯�젰�빐二쇱꽭�슂.");
				} else if (pro_name.getText().length() == 0) {
					new Alert("援먯닔紐낆쓣 �엯�젰�빐二쇱꽭�슂.");
				} else if (college.getText().length() == 0) {
					new Alert("�떒怨쇰�� �엯�젰�빐二쇱꽭�슂.");
				} else if (major.getText().length() == 0) {
					new Alert("�쟾怨듭쓣 �엯�젰�빐二쇱꽭�슂.");
				} else {
					// not null

					// id check
					// 1. check format
					try {
						id = Integer.parseInt(pro_enroll.getText());
					} catch (NumberFormatException e1) {
						System.out.println(pro_enroll.getText());
						new Alert("�엫�슜�뀈�룄�뒗 �닽�옄留� �엯�젰 媛��뒫�빀�땲�떎.");
						return;
					}
					// 2. check length
					if (pro_enroll.getText().length() != 4) {
						new Alert("�엫�슜�뀈�룄�뒗 4�옄由ъ엯�땲�떎.");
						return;
					}

					// major & college check
					if (new EtcDao().inquiry(college.getText(), major.getText()) == -1) {
						college.setText("");
						major.setText("");
						return;
					} else if (new EtcDao().inquiry(college.getText(), major.getText()) == -2) {
						major.setText("");
						return;
					}

					boolean b = new ProfessorDao().enroll_pro(pro_name.getText(), college.getText(),
							major.getText(), enroll);

					if (b) {
						setVisible(false);
						new Alert("�벑濡앺븯���뒿�땲�떎.");
						new Staff_Main(id_login);
					}
				}
			}
		});
		enter_btn.setBounds(353, 440, 98, 37);
		contentPane.add(enter_btn);

		JButton back_btn = new JButton("취소");
		back_btn.setBackground(c1);
		back_btn.setForeground(c3);
		back_btn.setFont(new Font("휴먼엑스포", Font.PLAIN, 14));
		back_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Staff_Main(id_login);
			}
		});
		back_btn.setBounds(495, 440, 94, 37);
		back_btn.setBackground(c1);
		back_btn.setBorder(null);
		back_btn.setForeground(c3);
		contentPane.add(back_btn);
		
		setVisible(true);
	}
}
