import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

class TagInfo {
	Point start;
	Point stop;
	String name;
	String text;
}

public class ImageTags extends JPanel implements MouseListener,
		MouseMotionListener {
	Point start = new Point(0, 0);
	Point stop = new Point(0, 0);
	Image image = null;
	String tagName = "";
	String assoText = "";
	List<TagInfo> tags = new ArrayList<TagInfo>();
	List passwordSetTags = new ArrayList();
	List passwordTestTags = new ArrayList();
	JMenuBar bar;
	JMenu menu;
	JMenuItem importImage, save, exit, createpassword, testpassword,
			showPassword,lockFiles,unlockFiles;
	int passwordSetCount = 0;
	String passwordSet = "";
	String passwordTagsDims = "";
	int passwordTestCount = 0;
	String passwordTest = "";
	int mode = 0;
	JLabel enterUserID;
	JButton status;
	JPasswordField userID;
	JButton login;
	int singleClick = 1;
	String imagePath;
	int passwordLength = 0;
	int wrongClicks = 0;
	int wrongAttempts = 0;
	int tagsCount = 0;
	int passwordCount = 0;
	static Statement s;
	static java.sql.Connection conn;
	java.sql.ResultSet rs;
	static JFrame f = new JFrame("File Locker");
	String statusBar = "Welcome";

	public ImageTags() {
		image = Toolkit.getDefaultToolkit().createImage("Freedom.jpg");
		addMouseListener(this);
		addMouseMotionListener(this);
		openConnection();
		enterUserID = new JLabel("Please enter UserID");
		userID = new JPasswordField(20);
		login = new JButton("Log in");
		add(enterUserID);
		add(userID);
		add(login);

		//action to be performed on clicking login button
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				if (!userID.getText().equals("")) {
					String user = userID.getText();
					String queryString = "Select * from users where username = '"
							+ user + "'";
					// System.out.println(queryString);
					
					//check whether the user is already register or not
					try {
						Statement s = conn.createStatement();
						rs = s.executeQuery(queryString);
						if (!rs.first()) {
							JOptionPane
									.showMessageDialog(null,
											"You are a new User Please Set the password");
							statusBar = "Creation of new password";
							mode = 7; // Mode 7 is used to create a new password
							
							enterUserID.setText("Welcome");
							menu.add(importImage);
							menu.add(new JSeparator());

							menu.add(save);
							menu.add(new JSeparator());

							menu.add(createpassword);
							menu.add(new JSeparator());

							menu.add(testpassword);
							menu.add(new JSeparator());

							menu.add(showPassword);
							menu.add(new JSeparator());
						} else {
							menu.add(importImage);
							menu.add(new JSeparator());

							menu.add(save);
							menu.add(new JSeparator());

							menu.add(createpassword);
							menu.add(new JSeparator());

							menu.add(testpassword);
							menu.add(new JSeparator());

							menu.add(showPassword);
							menu.add(new JSeparator());
							JOptionPane.showMessageDialog(null,
									"Old User.. Into Authentication mode.");
							statusBar = "Password Authentication";
							rs = s.executeQuery("select * from users where username = '"
									+ user + "'");
							String savedImage = "";
							String usernameFromDB = "";
							String passwordFromDB = "";
							String tagsDimFromDB = "";
							String assotextFromDB = "";
							String tagsNameFromDB = "";
							String imageURLFromDB = "";
							String passDimsFromDB = "";
							String passTextFromDB = "";
							String passTagsIndexFromDB = "";

							while (rs.next()) {
								savedImage = rs.getString("imageURL");
								System.out.println("username : "
										+ rs.getString("username"));
								usernameFromDB = rs.getString("username");
								System.out.println("password : "
										+ rs.getString("password"));
								passwordFromDB = rs.getString("password");
								System.out.println("tagsDim  : "
										+ rs.getString("tagsDim"));
								tagsDimFromDB = rs.getString("tagsDim");
								System.out.println("assoText : "
										+ rs.getString("assotext"));
								assotextFromDB = rs.getString("assotext");
								System.out.println("tagsName : "
										+ rs.getString("tagsName"));
								tagsNameFromDB = rs.getString("tagsName");
								System.out.println("imageURL : "
										+ rs.getString("imageURL"));
								imageURLFromDB = rs.getString("imageURL");
								System.out.println("passDims : "
										+ rs.getString("passDims"));
								passDimsFromDB = rs.getString("passDims");
								System.out.println("passText : "
										+ rs.getString("passText"));
								passTextFromDB = rs.getString("passText");
								System.out.println("passTagsIndex : "
										+ rs.getString("passTagsIndex"));
								passTagsIndexFromDB = rs
										.getString("passTagsIndex");

							}
							StringTokenizer tagsDimFromDBtokens = new StringTokenizer(
									tagsDimFromDB, ",");
							System.out.println("Number of Tokens "
									+ tagsDimFromDBtokens.countTokens());
							int numberOfTags = tagsDimFromDBtokens
									.countTokens() / 4;
							int tokenCount = 0;
							StringTokenizer tagsNameFromDBtokens = new StringTokenizer(
									tagsNameFromDB, ",");
							StringTokenizer assotextFromDBtokens = new StringTokenizer(
									assotextFromDB, ",");

							while (tokenCount < numberOfTags) {
								TagInfo ti = new TagInfo();
								ti.start = new Point((int) Float
										.parseFloat(tagsDimFromDBtokens
												.nextToken()), (int) Float
										.parseFloat(tagsDimFromDBtokens
												.nextToken()));
								ti.stop = new Point((int) Float
										.parseFloat(tagsDimFromDBtokens
												.nextToken()), (int) Float
										.parseFloat(tagsDimFromDBtokens
												.nextToken()));
								ti.name = tagsNameFromDBtokens.nextToken();
								ti.text = assotextFromDBtokens.nextToken();
								tags.add(ti);

								tokenCount++;
							}

							StringTokenizer passTagsIndexFromDBTokens = new StringTokenizer(
									passTagsIndexFromDB, ",");
							while (passTagsIndexFromDBTokens.hasMoreTokens()) {
								passwordSetTags.add(passTagsIndexFromDBTokens
										.nextToken());
								passwordLength++;
							}
							passwordSet = passwordFromDB;
							System.out.println("tags now");
							for (TagInfo ti : tags) {
								System.out.print(ti.name + ",");
							}
							System.out.println("PasswordSetTags List "
									+ passwordSetTags.size());
							System.out.println("PasswordSetTags");
							for (Object name : passwordSetTags) {
								System.out.print(name.toString() + ",");
							}
							System.out.println();
							System.out.println("PasswordTestTags");
							for (Object name : passwordTestTags) {
								System.out.print(name.toString() + ",");
							}
							image = Toolkit.getDefaultToolkit().createImage(
									savedImage);
							//ImageTags.f.setSize(
							//		new ImageIcon(savedImage).getIconWidth(),
							//		new ImageIcon(savedImage).getIconHeight());
							enterUserID.setText("Authentication");

							mode = 4; // mode 4 is used for authentication, to check the password
							
							passwordTestTags.clear();
							passwordTestCount = 0;
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					repaint();
					userID.hide();
					login.hide();
				}
			}
		});

		bar = new JMenuBar();
		menu = new JMenu("File");
		bar.add(menu);
		importImage = new JMenuItem("Import");
		save = new JMenuItem("Save");
		exit = new JMenuItem("Exit");
		createpassword = new JMenuItem("New/Change Password");
		testpassword = new JMenuItem("Test Password");
		showPassword = new JMenuItem("Show Password");
		lockFiles = new JMenuItem("Lock Files");
		unlockFiles = new JMenuItem("UnLocke Files");

		
		lockFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Add Files");
				Example.LockFiles();
			}
		});
		
		unlockFiles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(null, "Show Files");
				UnLockFiles.UnLockFile();
			}
		});
		
		
		importImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// JOptionPane.showMessageDialog(null, "Import clicked");
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				JFrame fcwindow = new JFrame();
				int returnVal = fc.showSaveDialog(fcwindow);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					imagePath = fc.getSelectedFile().toString();
					// System.out.println(imagePath);
					image = Toolkit.getDefaultToolkit().createImage(imagePath);
					//ImageTags.f.setSize(
					//		new ImageIcon(imagePath).getIconWidth(),
					//		new ImageIcon(imagePath).getIconHeight());
					mode = 1; // Mode 1 is used to select the image from the file System
					
					tags.clear();
					passwordSetTags.clear();
					passwordTestTags.clear();
					statusBar = "Image Imported";
					repaint();
				}

			}
		});

		//action to be performed on clicking save menu. save the tags to the database
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "Tags saved in file");
				String tagsDimensions = "";
				String tagsName = "";
				String tagsAssoText = "";
				for (int i = 0; i < tags.size(); i++) {
					Point start = tags.get(i).start;
					Point stop = tags.get(i).stop;
					tagsDimensions = tagsDimensions + start.getX() + ","
							+ start.getY() + "," + stop.getX() + ","
							+ stop.getY() + ",";
					tagsName = tagsName + tags.get(i).name + ",";
					tagsAssoText = tagsAssoText + tags.get(i).text + ",";
				}
				try {
					s = conn.createStatement();
					imagePath = imagePath.replace("\\", "\\\\");
					// s.execute("");
					s.executeUpdate("insert into users values ( '"
							+ userID.getText() + "' , '" + "" + "' , '"
							+ tagsDimensions + "' , '" + tagsAssoText + "' , '"
							+ tagsName + "' , '" + imagePath + "','" + ""
							+ "','" + "" + "','" + "" + "' )");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// System.out.println("---------------------");
				// System.out.println(tagsDimensions);
				// System.out.println(tagsName);
				// System.out.println(tagsAssoText);
				/*
				 * File file = new File("Freedom.jpg.tagg"); try {
				 * file.createNewFile(); BufferedWriter out = new
				 * BufferedWriter(new FileWriter( "Freedom.jpg.tagg")); for (int
				 * i = 0; i < tags.size(); i++) { Point start =
				 * tags.get(i).start; Point stop = tags.get(i).stop;
				 * out.append((int) start.getX() + "," + (int) start.getY() +
				 * "," + (int) stop.getX() + "," + (int) stop.getY() + "," +
				 * tags.get(i).name + "," + tags.get(i).text + "\r\n"); }
				 * out.close();
				 * 
				 * /* BufferedReader in = new BufferedReader(new FileReader(
				 * "Freedom.jpg.tagg")); String line = ""; while ((line =
				 * in.readLine()) != null) { System.out.println(line); }
				 * in.close();
				 */
				/*
				 * } catch (IOException ex) { // TODO Auto-generated catch block
				 * ex.printStackTrace(); }
				 */
			}
		});

		//action to be performed on clicking exit menu, exit the database
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// JOptionPane.showMessageDialog(null, "exit clicked");
				System.exit(0);
			}
		});

		//action to be performed on clicking createpassword menu
		createpassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				passwordLength = Integer.parseInt(JOptionPane.showInputDialog(
						null, "Length of the Password"));
				// System.out.println(passwordLength);
				// JOptionPane.showMessageDialog(null, "Select four tags");
				enterUserID.setText("Password Creation");
				statusBar = "PassWord Creation";
				mode = 3; // Mode 3 is used to create the password, tagging the image
				
				passwordSetTags.clear();
				passwordSet = "";
				passwordSetCount = 0;
				repaint();
			}
		});
		
		//action to be performed on clicking testpassword menu
		testpassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				System.out.println("tags");
				for (TagInfo ti : tags) {
					System.out.print(ti.name + ",");
				}
				System.out.println("PasswordSetTags");
				for (Object name : passwordSetTags) {
					System.out.print(name.toString() + ",");
				}
				System.out.println("PasswordTestTags");
				for (Object name : passwordTestTags) {
					System.out.print(name.toString() + ",");
				}
				enterUserID.setText("Authentication");
				statusBar = "Authentication";
				mode = 4;  // Mode 4 is used enter the authentication part
				
				passwordTestTags.clear();
				passwordTestCount = 0;
				passwordTest = "";
			}
		});

		//action to be performed on clicking showpassword menu
		
		showPassword.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				System.out.println("tags");
				for (TagInfo ti : tags) {
					System.out.print(ti.name + ",");
				}
				System.out.println("PasswordSetTags");
				for (Object name : passwordSetTags) {
					System.out.print(name.toString() + ",");
				}
				System.out.println("PasswordTestTags");
				for (Object name : passwordTestTags) {
					System.out.print(name.toString() + ",");
				}
				enterUserID.setText("Show Password");
				statusBar = "Showing Password";
				mode = 10; // this mode is used to show the existing password
				
				passwordTestTags.clear();
				passwordTestCount = 0;
				passwordTest = "";
			}
		});


		menu.add(exit);

	}

	//to create connection with database
	void openConnection() {
		try {
			String userName = "root";
			String password = "";
			String url = "jdbc:mysql://localhost/tags";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, userName, password);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Cannot Connect Server or tables already exist");
			System.exit(0);
		}
	}

	//close the connection
	static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	//function that executes function
	public static void main(String args[]) {
		ImageTags it = new ImageTags();
		it.setVisible(true);
		it.setBackground(Color.WHITE);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setSize(800, 600);
		f.setResizable(true);
		Container content = f.getContentPane();
		f.setJMenuBar(it.bar);
		//JScrollPane jsp = new JScrollPane();
		//jsp.setPreferredSize( new Dimension(800, 600) );
	    //jsp.setBounds(20, 20, 800, 600);    // determined by trial-and-error
		//it.add(jsp);
		content.add(it);


	}

	//draws the images on the frame
	protected synchronized void paintComponent(Graphics g) {

		// System.out.println(singleClick);
		
		// Mode 7 is used to create a new password
		if (mode == 7) {
			g.drawImage(image, 0, 0, 800, 600,this);
			g.drawString("Welcome", 100, 100);
		}
		
		// Mode 1 is used to select the image from the file System
		if (mode == 1) {
			super.paintComponent(g);
			// System.out.println("mode 1 not a click");
			g.clearRect(0, 0, 800, 600);
			g.drawImage(image, 0, 0, 800, 600,this);
			g.setColor(Color.GREEN);
			for (int i = 0; i < tags.size(); i++) {
				Point start = tags.get(i).start;
				Point stop = tags.get(i).stop;
				// System.out.println(i + "---" + tags.get(i).name);
				g.drawString(tags.get(i).name, (int) start.getX() + 10,
						(int) start.getY() + 20);
				g.drawRect((int) start.getX(), (int) start.getY(),
						Math.abs((int) stop.getX() - (int) start.getX()),
						Math.abs((int) stop.getY() - (int) start.getY()));
			}
			g.drawRect((int) start.getX(), (int) start.getY(),
					Math.abs((int) stop.getX() - (int) start.getX()),
					Math.abs((int) stop.getY() - (int) start.getY()));
			singleClick = 0;
		}
		
		// Mode 3 is used to create the password, tagging the image
		if (mode == 3) {
			g.clearRect(0, 0, 800, 600);
			g.drawImage(image, 0, 0, 800, 600,this);
			g.setColor(Color.GREEN);
			for (int i = 0; i < tags.size(); i++) {
				Point start = tags.get(i).start;
				Point stop = tags.get(i).stop;
				if (passwordSetTags.contains(i)) {
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.GREEN);
				}
				g.drawString(tags.get(i).name, (int) start.getX() + 10,
						(int) start.getY() + 20);
				g.drawRect((int) start.getX(), (int) start.getY(),
						Math.abs((int) stop.getX() - (int) start.getX()),
						Math.abs((int) stop.getY() - (int) start.getY()));

			}
			repaint();
		}

		// this mode is used to show the existing password
		if (mode == 10) {
			g.clearRect(0, 0, 800, 600);
			g.drawImage(image, 0, 0, 800, 600,this);
			g.setColor(Color.GREEN);
			for (int i = 0; i < tags.size(); i++) {
				Point start = tags.get(i).start;
				Point stop = tags.get(i).stop;
				String iStr = i + "";
				if (passwordSetTags.contains(iStr)) {
					System.out.print("Contains");
					g.setColor(Color.RED);
				} else {
					g.setColor(Color.GREEN);
					System.out.print("Not Contains");
				}
				g.drawString(tags.get(i).name, (int) start.getX() + 10,
						(int) start.getY() + 20);
				g.drawRect((int) start.getX(), (int) start.getY(),
						Math.abs((int) stop.getX() - (int) start.getX()),
						Math.abs((int) stop.getY() - (int) start.getY()));

			}
			repaint();
		}

		// mode 4 is used for authentication, to check the password
		if (mode == 4) {
			// System.out.println("in mode four "+ tags.size());
			g.clearRect(0, 0, 800, 600);
			g.drawImage(image, 0, 0, 800, 600,this);
			g.setColor(Color.GREEN);
			for (int i = 0; i < tags.size(); i++) {
				Point start = tags.get(i).start;
				Point stop = tags.get(i).stop;
				if (passwordTestTags.contains(i)) {
					g.setColor(Color.BLUE);
					g.drawString(tags.get(i).name, (int) start.getX() + 10,
							(int) start.getY() + 20);
					g.drawRect((int) start.getX(), (int) start.getY(),
							Math.abs((int) stop.getX() - (int) start.getX()),
							Math.abs((int) stop.getY() - (int) start.getY()));

				} else {
					g.setColor(Color.GREEN);
				}
			}
			repaint();
		}
		int nowHeight = f.getHeight()-70;
		//System.out.println("height is "+ nowHeight);
		g.drawString(statusBar, 10,nowHeight );
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
	//	System.out.println("mouse clicked " + mode);

		// Mode 3 is used to create the password, tagging the image and password not equal to original length
		if (mode == 3 && passwordSetCount != passwordLength) {
			// System.out.println("new password");
			int clickX = me.getX();
			int clickY = me.getY();
			int distance = 5000;
			passwordCount++;
			int select = -1;
			for (int i = 0; i < tags.size(); i++) {
				Point start = tags.get(i).start;
				Point stop = tags.get(i).stop;

				Rectangle rect = new Rectangle((int) start.getX(),
						(int) start.getY(), (int) (stop.getX() - start.getX()),
						(int) (stop.getY() - start.getY()));
				if (rect.contains(new Point(clickX, clickY))) {
					select = i;
				}
			}
			if (select != -1) {
				// System.out.println(select);
				if (!passwordSetTags.contains(select)) {
					passwordSetTags.add(select);
					passwordTagsDims = passwordTagsDims
							+ tags.get(select).start.getX() + ","
							+ tags.get(select).stop.getX() + ","
							+ tags.get(select).stop.getY() + ",";
					passwordSet = passwordSet + tags.get(select).text;
					passwordSetCount++;
				}
			}
			if (passwordSetCount == passwordLength) {
				JOptionPane.showMessageDialog(null,
						"Your Password set Successful");
				String passwordTagsIndex = "";
				for (Object val : passwordSetTags) {
					passwordTagsIndex += val.toString() + ",";
				}
				try {
					s = conn.createStatement();
					s.executeUpdate("update users set " + "password=" + "'"
							+ passwordSet + "'," + "passDims=" + "'"
							+ passwordTagsDims + "'," + "passText=" + "'"
							+ passwordSet + "'," + "passTagsIndex=" + "'"
							+ passwordTagsIndex + "' " + " where username = '"
							+ userID.getText() + "'");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// System.out.println(passwordSet);

		}
		
		if (mode == 4 && passwordTestCount != passwordLength) {
			System.out.println("test password");
			int clickX = me.getX();
			int clickY = me.getY();
			int distance = 5000;
			int select = -1;
			for (int i = 0; i < tags.size(); i++) {
				Point start = tags.get(i).start;
				Point stop = tags.get(i).stop;
				Rectangle rect = new Rectangle((int) start.getX(),
						(int) start.getY(), (int) (stop.getX() - start.getX()),
						(int) (stop.getY() - start.getY()));
				if (rect.contains(new Point(clickX, clickY))) {
					select = i;
				}
			}
			if (select != -1) {
				// System.out.println(select);
				if (!passwordTestTags.contains(select)) {
					passwordTestTags.add(select);
					String tagPass = JOptionPane.showInputDialog(null,
							"Enter the tag Password");
					passwordTest = passwordTest + tagPass;
					passwordTestCount++;
				}
			} else {
				wrongClicks++;
				if (wrongClicks == 3) {
					JOptionPane
							.showMessageDialog(
									null,
									"will lock the system down for 15 minutes and send an E-Mail to a pre-defined address");
					wrongClicks = 0;
				}
			}
			// System.out.println(passwordSet + " --- " + passwordTest);
			if (passwordTestCount == passwordLength) {
				if (passwordTest.equals(passwordSet)) {
					JOptionPane.showMessageDialog(null,
							"Authenticated password successfull");
					menu.add(new JSeparator());
					menu.add(lockFiles);
					menu.add(new JSeparator());
					menu.add(unlockFiles);
					//System.exit(5000);
				} else {
					JOptionPane.showMessageDialog(null,
							"Passwords Does not Match, Attempt once more");
					wrongAttempts++;
				}
				if (wrongAttempts == 3) {
					JOptionPane
							.showMessageDialog(
									null,
									"will lock the system down for 15 minutes and send an E-Mail to a pre-defined address");
					System.exit(5000);

				}
			}

		}

		if (mode != 1)
			repaint();
	}

	@Override
	public void mouseEntered(MouseEvent me) {
		// TODO Auto-generated method stub
		// System.out.println("mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent me) {
		// TODO Auto-generated method stub
		// System.out.println("mouse exited");
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		start = me.getPoint();
		// System.out.println("mouse pressed");
	}

	@Override
	public void mouseReleased(MouseEvent me) throws NullPointerException {
		// TODO Auto-generated method stub
		// System.out.println(start.getX() + "," + me.getX());
		if (start.getX() == me.getX()) {
			// System.out.println("equal");
		} else {
			// System.out.println("not equal");
		}
		if (mode == 1 && start.getX() != me.getX() && start.getY() != me.getY()) {
			stop = me.getPoint();
			repaint();
			// System.out.println("mouse released");
			tagName = "";
			assoText = "";
			singleClick = 0;

			// System.out.println(stop.getX() + "," + stop.getY());

			try {
				tagName = JOptionPane.showInputDialog("Enter The TagName:",
						null);
				// System.out.println(tagName.length());
				if (tagName.length() != 0) {
					assoText = JOptionPane.showInputDialog(
							"Enter The Associated Text:", null);
					// System.out.println(assoText.length());
					if (assoText.length() != 0) {
						TagInfo tag = new TagInfo();
						tag.start = start;
						tag.stop = stop;
						tag.name = tagName;
						tag.text = assoText;
						tags.add(tag);
						tagsCount++;
						repaint();
					}
				}
				// repaint();

			} catch (NullPointerException ex) {
				// ex.printStackTrace();

			}
		}

	}

	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO Auto-generated method stub
		stop = me.getPoint();
		repaint();
		// System.out.println("mouse drag");
	}

	@Override
	public void mouseMoved(MouseEvent me) {
		// TODO Auto-generated method stub
		// System.out.println("mouse move");
	}
}

//class for browsing the files
class MyFilter extends javax.swing.filechooser.FileFilter {
	public boolean accept(File file) {
		String filename = file.getName();
		return filename.endsWith(".jpg");
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "*.jpg";
	}
}