import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Game extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ArrayList<Globo> globos;
	private Dimension d;
	private AudioClip clip;
	private int globosCount;
	private JLabel score;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Game() {
		d = Toolkit.getDefaultToolkit().getScreenSize();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setBounds(0, 0, (int) d.getWidth(), (int) d.getHeight());
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		initGame();
	}

	private void initGame() {
		Globo globo;
		globosCount=30; 
		score = new JLabel("Score: "+globosCount);
		score.setBounds((int) d.getWidth()-180,120,60,16);
		contentPane.add(score);
		globos = new ArrayList<Globo>();
		for (int i = 0; i < 30; i++) {
			globo = new Globo("globo" + i, d);
			globo.addActionListener(this);
			contentPane.add(globo);
			globos.add(globo);
		}

		loadSound();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Globo globoPulsado = (Globo) e.getSource();
		clip.play();
		globos.remove(globoPulsado);
		contentPane.remove(globoPulsado);
		globosCount--;
		score.setText("Score: "+globosCount);
	}

	private void loadSound() {
		try {
			clip = Applet.newAudioClip(new File("sound/GunShot.wav").toURI().toURL());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
