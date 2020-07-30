package tanks;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game extends Frame {

	private Field field = new Field(17, this);
	public boolean mode;
	CheckboxGroup cb;
	Label points = new Label("Points:");
	Label timelabel = new Label("Time left:");
	Timer timer;
	private static int fist_entry = 1;

	public void populate_window() {

		add(field, BorderLayout.CENTER);
		Panel terrain = new Panel();
		Label terrainlabel = new Label("Terrain:");
		terrain.setLayout(new GridLayout());
		terrain.add(terrainlabel, BorderLayout.CENTER);
		if (timer != null) {
			timer.interrupt();
		}
		timer = new Timer(timelabel);
		timer.start();
		terrain.add(timelabel, BorderLayout.NORTH);

		cb = new CheckboxGroup();

		Checkbox grass = new Checkbox("Grass", true, cb);
		Checkbox wall = new Checkbox("Wall", false, cb);

		Panel east = new Panel();
		east.setLayout(new GridLayout(2, 1));
		Panel northeast = new Panel();
		northeast.setLayout(new GridLayout());
		northeast.setBackground(Color.GREEN);
		northeast.add(grass, BorderLayout.CENTER);

		Panel southeast = new Panel();
		southeast.setLayout(new GridLayout());
		southeast.setBackground(Color.GRAY);
		southeast.add(wall, BorderLayout.CENTER);

		east.add(northeast);
		east.add(southeast);

		Panel right = new Panel();
		right.add(terrain, BorderLayout.WEST);
		right.add(east, BorderLayout.EAST);
		right.setLayout(new GridLayout(1, 2));

		Panel south = new Panel();
		Label coins = new Label("Coins:");
		south.add(coins);
		TextField coins_num = new TextField("12");

		Button start = new Button("Start");
		south.add(coins_num);
		south.add(points);
		south.add(start);

		start.addActionListener((ae) -> {
			try {
				int temp = Integer.parseInt(coins_num.getText());
				if (temp < 17 * 17 * 2 / 3)
					field.start(Integer.parseInt(coins_num.getText()));
				start.setEnabled(false);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		MenuBar bar = new MenuBar();
		Menu menu = new Menu("Mode");
		setMenuBar(bar);
		bar.add(menu);
		MenuItem change = new MenuItem("Change the board", new MenuShortcut('C'));
		menu.add(change);
		change.addActionListener(e -> {
			if (field != null) {
				field.setMode(false);
				field.stop();
			}
		});

		MenuItem play = new MenuItem("Play", new MenuShortcut('P'));
		menu.add(play);

		play.addActionListener(e -> {
			if (field != null) {
				field.setMode(true);
				field.newGame();

			}
			start.setEnabled(true);

			// dispose();
		});

		add(right, BorderLayout.EAST);
		add(south, BorderLayout.SOUTH);
	}

	public Game() {
		super("Tanks");
		setVisible(true);
		setSize(1000, 1000);
		populate_window();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (field != null)
					field.stop();
				dispose();
				timer.interrupt();
			}
		});
	}

	public static void main(String[] args) {
		new Game();
	}

}
