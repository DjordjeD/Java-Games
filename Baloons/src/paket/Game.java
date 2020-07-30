package paket;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class Game extends Frame {

	private Scene sc;
	private Label timelabel = new Label("Time left:");
	private Timer timer;

	public Game() throws HeadlessException {
		super("Baloni");
		setSize(700, 700);
		// setPreferredSize(new Dimension(700, 700));
		Panel center = new Panel(new GridLayout());
		sc = new Scene(this);
		center.add(sc);
		Panel east = new Panel(new GridLayout());

		// setBackground(Color.PINK);
		add(center, BorderLayout.CENTER);
		setVisible(true);
		if (timer != null) {
			timer.interrupt();
		}
		timer = new Timer(timelabel);
		timer.start();
		timer.go();

		// meni

		MenuBar bar = new MenuBar();
		Menu menu = new Menu("Mode");
		setMenuBar(bar);
		bar.add(menu);

		MenuItem play = new MenuItem("Play", new MenuShortcut('P'));
		menu.add(play);
		play.addActionListener(e -> {
			if (sc != null) {

				sc.newGame();

			}
			// dispose();
		});

		east.add(timelabel);
		add(east, BorderLayout.EAST);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				if (timer != null)
					timer.interrupt();
				dispose();
				sc.end_game();
				// prekini nit u tabli
			}
		});
	}

	public static void main(String[] args) {
		new Game();
	}

	public Scene getSc() {
		return sc;
	}

	public void setSc(Scene sc) {
		this.sc = sc;
	}

	public Label getTimelabel() {
		return timelabel;
	}

	public void setTimelabel(Label timelabel) {
		this.timelabel = timelabel;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

}
