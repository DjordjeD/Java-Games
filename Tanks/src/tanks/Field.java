package tanks;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class Field extends Panel implements Runnable {

	private Tile matrix[][];
	private Game game_field;
	private ArrayList<Tank> tankList;
	private ArrayList<Coin> coinList;
	private ArrayList<Player> playerList;
	private int sizeField;
	boolean startFlag = false;
	private Thread field_thr;
	int points;
	private boolean mode;
	int coins;

	public Field(int size, Game game) {
		// TODO Auto-generated constructor stub

		matrix = new Tile[size][size];
		this.sizeField = size;
		this.game_field = game;
		setVisible(true);
		setLayout(new GridLayout(size, size, 2, 2));
		int numbergrass = (int) ((int) size * size * 0.8);
		int numberwall = size * size - numbergrass;
		mode = true;
		setFocusable(true);
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Random rand = new Random();
				int randint = rand.nextInt(5) + 1;
				if (randint == 1 && numberwall > 0) {
					matrix[i][j] = new Wall(this, i, j);
					numberwall--;
				} else if (numbergrass >= 0) {
					matrix[i][j] = new Grass(this, i, j);
					numbergrass--;
				} else if (numberwall > 0) {
					matrix[i][j] = new Wall(this, i, j);
					numberwall--;
				}
				add(matrix[i][j]);
			}
		}
		requestFocus();
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case KeyEvent.VK_A: {
					System.out.println("A");
					Player p1 = playerList.get(0);
					Tile t = p1.tile;
					Tile t1 = p1.tile.tileMovedBy(0, -1);
					if (t1.coinTile != null) {
						t.coinTile = null;
					}
					p1.move(t1);
					Field.this.repaint();
					t.repaint();
					break;
				}
				case KeyEvent.VK_S: {
					System.out.println("S");
					Player p1 = playerList.get(0);
					Tile t = p1.tile;
					Tile t1 = p1.tile.tileMovedBy(1, 0);
					if (t1.coinTile != null) {
						t.coinTile = null;
					}
					p1.move(t1);
					t.repaint();
					break;
				}
				case KeyEvent.VK_D: {
					System.out.println("D");
					Player p1 = playerList.get(0);
					Tile t = p1.tile;
					Tile t1 = p1.tile.tileMovedBy(0, 1);
					if (t1.coinTile != null) {
						t.coinTile = null;
					}
					p1.move(t1);
					t.repaint();
					break;
				}
				case KeyEvent.VK_W: {
					System.out.println("W");
					Player p1 = playerList.get(0);
					Tile t1 = p1.tile.tileMovedBy(-1, 0);
					Tile t = p1.tile;
					if (t1.coinTile != null) {
						t.coinTile = null;
					}
					p1.move(t1);
					t.repaint();
					break;
				}
				}
			}
		});

	}

	public void start(int coin_number) {

		int tank_number = coin_number / 3;
		tankList = new ArrayList<Tank>();
		coinList = new ArrayList<Coin>();
		playerList = new ArrayList<Player>();
		field_thr = new Thread(this);
		points = 0;
		coins = coin_number;
		while (tank_number > 0) {
			Random rand = new Random();
			int r1 = rand.nextInt(sizeField);
			int r2 = rand.nextInt(sizeField);
			if (!matrix[r1][r2].isWall()) {
				Tank t1 = new Tank(matrix[r1][r2]);
				matrix[r1][r2].setTankTile(t1);
				tankList.add(t1);
				tank_number--;
			}
		}
		int cn = coin_number;
		while (cn > 0) {
			Random rand = new Random();
			int r1 = rand.nextInt(sizeField);
			int r2 = rand.nextInt(sizeField);
			if (!matrix[r1][r2].isWall() && matrix[r1][r2].getCoinTile() == null
					&& matrix[r1][r2].getPlayerTile() == null) {
				Coin t1 = new Coin(matrix[r1][r2]);
				matrix[r1][r2].setCoinTile(t1);
				coinList.add(t1);
				cn--;
			}
		}
		int pn = 1;
		while (pn > 0) {
			Random rand = new Random();
			int r1 = rand.nextInt(sizeField);
			int r2 = rand.nextInt(sizeField);
			if (!matrix[r1][r2].isWall() && matrix[r1][r2].getCoinTile() == null
					&& matrix[r1][r2].getTankTile() == null) {
				Player t1 = new Player(matrix[r1][r2]);
				matrix[r1][r2].setPlayerTile(t1);
				playerList.add(t1);
				pn--;
			}
		}

		field_thr.start();
		game_field.timer.go();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!Thread.interrupted()) {
			try {
				requestFocus();
//				synchronized (this) {
//					if (startFlag == false)
//						field_thr.wait();

				for (Coin coin : coinList) {
					coin.draw();
				}
				for (Tank coin : tankList) {
					coin.draw();
				}
				for (Player coin : playerList) {
					coin.draw();
				}
				Thread.sleep(200);
			}

			catch (InterruptedException e) {
				// TODO: handle exception
				field_thr.interrupt();
			}

		}

	}

	public void newGame() {

		this.removeAll();
		revalidate();
		int numbergrass = (int) ((int) sizeField * sizeField * 0.8);
		int numberwall = sizeField * sizeField - numbergrass;
		for (int i = 0; i < sizeField; i++) {
			for (int j = 0; j < sizeField; j++) {
				Random rand = new Random();
				int randint = rand.nextInt(5) + 1;
				if (randint == 1 && numberwall > 0) {
					matrix[i][j] = new Wall(this, i, j);
					numberwall--;
				} else if (numbergrass >= 0) {
					matrix[i][j] = new Grass(this, i, j);
					numbergrass--;
				} else if (numberwall > 0) {
					matrix[i][j] = new Wall(this, i, j);
					numberwall--;
				}
				add(matrix[i][j]);
			}
		}
		revalidate();
	}

	public void stop() {
		// TODO Auto-generated method stub
		if (tankList != null)
			for (Tank tank : tankList) {
				tank.stopTank();
			}
		if (field_thr != null) {
			field_thr.interrupt();
			game_field.timer.pause();
			game_field.timer.reset();
		}

	}

	public Tile[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Tile[][] matrix) {
		this.matrix = matrix;
	}

	public Game getGame_field() {
		return game_field;
	}

	public void setGame_field(Game game_field) {
		this.game_field = game_field;
	}

	public ArrayList<Tank> getTankList() {
		return tankList;
	}

	public void setTankList(ArrayList<Tank> tankList) {
		this.tankList = tankList;
	}

	public ArrayList<Coin> getCoinList() {
		return coinList;
	}

	public void setCoinList(ArrayList<Coin> coinList) {
		this.coinList = coinList;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public int getSizeField() {
		return sizeField;
	}

	public void setSizeField(int sizeField) {
		this.sizeField = sizeField;
	}

	public boolean isStartFlag() {
		return startFlag;
	}

	public void setStartFlag(boolean startFlag) {
		this.startFlag = startFlag;
	}

	public boolean getMode() {
		return mode;
	}

	public void setMode(boolean mode) {
		this.mode = mode;
	}

}
