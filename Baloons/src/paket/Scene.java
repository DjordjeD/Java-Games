package paket;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

@SuppressWarnings("serial")
public class Scene extends Canvas implements Runnable {

	Game game;
	ArrayList<CircleFigure> figures;
	ArrayList<CircleFigure> temp_list = new ArrayList<>();
	Player active;
	Thread scene_thr = new Thread(this);

	public Scene(Game game) {

		// TODO Auto-generated constructor stub

		this.game = game;
		active = new Player(new Vector(game.getWidth() / 2, game.getHeight() - 100), Color.GREEN, 30, this);
		figures = new ArrayList<CircleFigure>();
		figures.add(active);
		scene_thr.start();
		this.setBackground(Color.LIGHT_GRAY);
		game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					active.movePlayer(10);
					break;
				case KeyEvent.VK_LEFT:
					active.movePlayer(-10);
					break;

				}
			}
		});

	}

	public void newGame() {
		if (scene_thr.isAlive()) {
			end_game();
		}
		active = new Player(new Vector(game.getWidth() / 2, game.getHeight() - 150), Color.GREEN, 30, this);
		figures = new ArrayList<CircleFigure>();
		figures.add(active);
		scene_thr = new Thread(this);
		Timer t = new Timer(game.getTimelabel());
		game.setTimer(t);
		game.getTimer().start();
		game.getTimer().go();
		repaint();
		scene_thr.start();

	}

	public void remove_scene(CircleFigure c) {
		for (Iterator iterator = figures.iterator(); iterator.hasNext();) {
			CircleFigure circleFigure = (CircleFigure) iterator.next();
			if (circleFigure.equals(c))
				iterator.remove();

		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

		// g.setColor(getBackground().DARK_GRAY);
		// g.fillRect(0, 0, this.game.getWidth(), this.game.getHeight());
		if (Thread.interrupted()) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(0, 0, this.game.getWidth(), this.game.getHeight());
		}
		synchronized (this) {
			for (Iterator<CircleFigure> iterator = figures.iterator(); iterator.hasNext();) {
				CircleFigure circleFigure = (CircleFigure) iterator.next();
				circleFigure.draw(this);
			}

		}

	}

	public void add_scene(CircleFigure c) {
		figures.add(c);
	}

	public void end_game() {

		scene_thr.interrupt();
		if (game.getTimer() != null) {
			game.getTimer().reset();
			game.getTimer().interrupt();
		}

		repaint();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			scene_thr.interrupt();
			e1.printStackTrace();
		}
		while (!Thread.interrupted()) {
			try {

				Thread.sleep(60);
				Random randint = new Random();
				int prob = randint.nextInt(100);
				if (prob < 10) {
					int rand = randint.nextInt(game.getWidth());
					figures.add(new Baloon(new Vector(rand, 20), Color.RED, 20, this));
				}
				for (Iterator<CircleFigure> iterator = figures.iterator(); iterator.hasNext();) {
					CircleFigure circleFigure = (CircleFigure) iterator.next();
					circleFigure.moveFigure();
					if (circleFigure instanceof Baloon && circleFigure.intersect(active)) {
						active.crashed();
					}
					if (circleFigure.getCenter().getX() < 0 || circleFigure.getCenter().getX() > this.getWidth()
							|| circleFigure.getCenter().getY() < 0
							|| circleFigure.getCenter().getY() > this.getHeight()) {
						iterator.remove();
					}
				}

				repaint();

			} catch (InterruptedException e) {
				// TODO: handle exception
				scene_thr.interrupt();
			}
		}
	}

}
