import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A simple example showing how to use {@link FileDrop}
 * 
 * @author Robert Harder, rob@iharder.net
 */
public class Example {

	static List<String> filesToLock = new ArrayList<String>();
	/** Runs a sample program that shows dropped files */
	public static void main(String[] args) {
		javax.swing.JFrame frame = new javax.swing.JFrame(
				"Drag and Drop Files to Lock");
		final javax.swing.JButton ok = new javax.swing.JButton("Lock The Files");
		final javax.swing.JTextArea text = new javax.swing.JTextArea();
		text.setEditable(false);
		frame.getContentPane().add(new javax.swing.JScrollPane(text),
				java.awt.BorderLayout.CENTER);
		frame.getContentPane().add(ok, java.awt.BorderLayout.SOUTH);
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				String files =  text.getText();
				StringTokenizer st =  new StringTokenizer(files,"\n");
				filesToLock.clear();
				while(st.hasMoreTokens()){
					String file = st.nextToken();
					filesToLock.add(file);
					System.out.println(file);
				}
			}
		});
		
		new FileDrop(System.out, text, new FileDrop.Listener() {
			public void filesDropped(java.io.File[] files) {
				for (int i = 0; i < files.length; i++) {
					try {
						text.append(files[i].getCanonicalPath() + "\n");
					} // end try
					catch (java.io.IOException e) {
					}
				} // end for: through each dropped file
			} // end filesDropped
		}); // end FileDrop.Listener

		frame.setBounds(100, 100, 300, 400);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} // end main

}
