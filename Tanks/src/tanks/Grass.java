package tanks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class Grass extends Tile {

	public Grass(Field f, int i, int j) {
		super(f, i, j);
		wallFlag = false;
		// setBackground(Color.GREEN);
		// TODO Auto-generated constructor stub
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if (field.getMode() == false
						&& field.getGame_field().cb.getSelectedCheckbox().getLabel().equals("Wall")) {

					int tempar[] = position(Grass.this);
					Wall tempwall = new Wall(field, tempar[0], tempar[1]);
					int adr_fun = field.getSizeField() * tempar[0] + tempar[1];
					field.remove(adr_fun);
					field.add(tempwall, adr_fun);
					field.revalidate();

				}
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
		setVisible(true);
	}
}
