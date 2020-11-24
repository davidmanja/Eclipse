import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Globo extends JButton implements Runnable {
	private static final long serialVersionUID = 1L;
	private static final int MAXSPEED = 5;
	private static final int ANCHO = 120;
	private static final int ALTO = 170;

	private Thread hilo;
	private int x, y, speedx, speedy;
	private Dimension d;

	public Globo(String nombre, Dimension d) {
		super();
		this.d = d;
		x = (int) (Math.random() * (d.width - ANCHO));
		y = (int) (Math.random() * (d.height - ALTO));

		do {
			speedx = getSpeed();
			speedy = getSpeed();
		} while (speedx == 0 && speedy == 0);

		// set icon
		int color = (int) (Math.random() * 15) + 1;
		setContentAreaFilled(false);
		setBorderPainted(false);
		setIcon(new ImageIcon("img/globo" + color + ".png"));
		setBounds(x, y, ANCHO, ALTO);

		hilo = new Thread(this);
		hilo.setName(nombre);
		hilo.start();
	}

	private int getSpeed() {
		int speed = (int) (Math.random() * MAXSPEED) + 1;
		int azar = (int) (Math.random() * 3) - 1;

		return azar * speed;
	}

	@Override
	public void run() {
		while (true) {
			x += speedx;
			y += speedy;
			setLocation(x, y);
			if (x < 0 || x > (d.getWidth() - ANCHO)) {
				speedx = -speedx;
			}
			if (y < 0 || y > (d.getHeight() - ALTO)) {
				speedy = -speedy;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {}
		}
	}

}
