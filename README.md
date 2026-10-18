# Java Games

Four small arcade games written in plain Java AWT, with no game engine. Each folder is a separate Eclipse project.

| Game | Folder | What it is |
|---|---|---|
| Balloons | `Baloons/` | Pop the balloons before they escape. Uses a small vector and physics layer. |
| Tanks | `Tanks/` | A tile-based map with walls and grass; drive around and collect coins. |
| Whack-a-Mole | `Whack A Mole/` | Hit the moles, avoid the bombs, and protect your vegetables. See also [WhackAMole](https://github.com/DjordjeD/WhackAMole). |
| Snake | `Zmija/` | Nokia-style snake. See also [SnakeGame](https://github.com/DjordjeD/SnakeGame). |

Each game has its own timer thread driving the loop and a `Scene`/`Field` that draws onto a `Canvas`.

## Run

Import any folder into Eclipse as an existing project and run its `Game` class (`Igra` for Snake). You can also compile from the command line:

```bash
cd Tanks/src && javac tanks/*.java && java tanks.Game
```
