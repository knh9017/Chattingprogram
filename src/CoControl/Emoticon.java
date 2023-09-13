package CoControl;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.io.File;
import java.io.PrintWriter;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.ActionEvent;
import Action.Protocol;

public class Emoticon extends JFrame implements ActionListener {

	private JPanel contentPane;
	public JButton[] Emoticon1;

	
	public ImageIcon[] eicon1;
	

	public File[] k_files;

	
	private PrintWriter pw;

	/**
	 * Create the frame.
	 */
	public Emoticon(PrintWriter pw) {
		
		try {
			this.pw=pw;
			setBounds(100, 100, 500, 200);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			contentPane.add(tabbedPane, BorderLayout.CENTER);
			
			JScrollPane scrollPane = new JScrollPane();
			tabbedPane.addTab("카카오 프렌즈", null, scrollPane, null);
			
			JPanel panel = new JPanel();
			scrollPane.setViewportView(panel);
			panel.setLayout(new GridLayout(2,4));
			
			File kakaofriends_path = new File( "./emoticon/4419645/thum_pack");//현재 폴더
			k_files = kakaofriends_path.listFiles();
			Emoticon1 = new JButton[k_files.length];
			eicon1 = new ImageIcon[k_files.length];
			for(int i =0; i<k_files.length; i++)
			{
				Emoticon1[i] = new JButton();
				eicon1[i]= new ImageIcon(k_files[i].getCanonicalPath());
				Image resize = (eicon1[i].getImage()).getScaledInstance(60, 60, Image.SCALE_SMOOTH);
				eicon1[i] = new ImageIcon(resize);
				
				Emoticon1[i].setIcon(eicon1[i]);
				Emoticon1[i].setName(k_files[i].getCanonicalPath());
				Emoticon1[i].addActionListener(this);
				panel.add(Emoticon1[i]);
			}
			
			
		
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
	}

	public void actionPerformed(ActionEvent e) {
		//TODO Progressing
		JButton a = (JButton)e.getSource();
		String b = a.getName();
		System.out.println(b);

		pw.println(Protocol.EMOSEND+"|"+b);
		pw.flush();
	}
}
