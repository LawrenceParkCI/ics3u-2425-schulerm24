package culminating;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class BlockDodgerJFrameVersion extends JPanel implements ActionListener, KeyListener {
	private int baseScoreRate = 1; //set score increment
	private double scoreMultiplier = 1.0; //multiply score by +1 per second when powerup is active
	private Timer timer; //timer (score per millisecond)
	private int playerX = 250; // player x coord
	private int playerY = 540; // player y coord
	private int playerWidth = 40; // player width
	private int playerHeight = 40; // player height
	private List<Rectangle> blocks; //spawn red blocks
	private List<Rectangle> coins; //spawn coins
	private List<Rectangle> targetingBlocks; //spawn targeting blocks
	private List<Rectangle> powerUps; //spawn powerups
	private List<Rectangle> lasers; //spawn lasers when shot
	private Random random; // allows for random numbers for spawning in blocks
	private boolean inGame = false; // sets if player is in game or not
	private int score = 0; // starting score
	private int revscore = 0;
	private int highScore1 = 0; // high score
	private int powerUpType = 0; // sets player's power up
	private long powerUpEndTime = 0; // when power up ends
	private Clip theme; 
	private Clip intro;
	private Clip death;
	private boolean isShieldActive = false; // Track if the invincibility is active
	private List<Rectangle> bombs; // spawn bombs
	private List<Rectangle> hazardousAreas; // spawn hazard areas
	private long hazardousAreaEndTime = 0; // when hazard area despawn
	private Clip bombdrop; 
	private Clip bombexplode;
	private Clip powerupsong2;
	private Clip smallblocksong;
	private Clip slomosong;
	private Clip coinnoise;
	private Clip scorepowersong;
	private int totalCoinsCollected = 0; // total coins collected (leave this in for now but might delete later)
	private boolean showCoinMessage = false; // Whether to show the coin message
	private long coinMessageEndTime = 0; // When to stop showing the coin message
	private Clip coin;
	private int baseresize = 1; // player resize


    // Load high score
    public int loadHighScore() { // loads highscore (isn't working but dont delete)
        try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return 0; // Default value if there's an error
        }
    }

    // Save high score
    public void saveHighScore(int highScore) { // isn't working but don't delete
        try (FileWriter writer = new FileWriter("highscore.txt")) {
            writer.write(String.valueOf(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save and load game
    private int highScore;

    public void saveGame() {
        saveHighScore(highScore1);
    }

    public void loadGame() {
        highScore1 = loadHighScore();
    }

    

    // Constructor
    public BlockDodgerJFrameVersion() {
    	
    	    // Add mouse motion listener
    	    addMouseMotionListener(new MouseAdapter() {
    	        @Override
    	        public void mouseMoved(MouseEvent e) {
    	            if (inGame) {
    	                playerX = e.getX() - playerWidth / 2; // set middle of player to mouse x coord
    	                repaint();
    	            }
    	        }
    	    });

    	    timer = new Timer(10, this); // game speed (lower number = faster)
    	    blocks = new ArrayList<>(); // spawn default blocks
    	    targetingBlocks = new ArrayList<>(); // spawn target blocks
    	    powerUps = new ArrayList<>(); // spawn power up blocks
    	    lasers = new ArrayList<>(); // spawn lasers when shot
    	    random = new Random(); // random number generator for block spawning
    	    addKeyListener(this);
    	    setFocusable(true);
    	    setFocusTraversalKeysEnabled(false);

    	    try { // audio clips
    	        coinnoise = AudioSystem.getClip();
    	        coinnoise.open(AudioSystem.getAudioInputStream(new File("src/culminating/Coin Noise_1Coin.wav")));

    	        bombdrop = AudioSystem.getClip();
    	        bombdrop.open(AudioSystem.getAudioInputStream(new File("src/culminating/Audio 2bombdrop.wav")));

    	        scorepowersong = AudioSystem.getClip();
    	        scorepowersong.open(AudioSystem.getAudioInputStream(new File("src/culminating/score multiplierscore multiplier.wav")));

    	        slomosong = AudioSystem.getClip();
    	        slomosong.open(AudioSystem.getAudioInputStream(new File("src/culminating/slowchinamusicslowpowerup2.wav")));

    	        bombexplode = AudioSystem.getClip();
    	        bombexplode.open(AudioSystem.getAudioInputStream(new File("src/culminating/explosionsfx_1.wav")));

    	        powerupsong2 = AudioSystem.getClip();
    	        powerupsong2.open(AudioSystem.getAudioInputStream(new File("src/culminating/Second powerup song_1.wav")));

    	        intro = AudioSystem.getClip();
    	        intro.open(AudioSystem.getAudioInputStream(new File("src/culminating/block dodger soundtrack updatedblockdodgersplaysong.wav")));

    	        smallblocksong = AudioSystem.getClip();
    	        smallblocksong.open(AudioSystem.getAudioInputStream(new File("src/culminating/smallblocksongsmallblocksong.wav")));

    	        theme = AudioSystem.getClip();
    	        theme.open(AudioSystem.getAudioInputStream(new File("src/culminating/block dodger soundtrack updatedblockdodgerintro.wav")));

    	        death = AudioSystem.getClip();
    	        death.open(AudioSystem.getAudioInputStream(new File("src/culminating/Zone FX 2 - Interchangeblock explode1.wav")));
    	    } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e1) {
    	        e1.printStackTrace();
    	    }

    	    bombs = new ArrayList<>();
    	    hazardousAreas = new ArrayList<>();
    	    coins = new ArrayList<>();
    }

    

    @Override
    public void paintComponent(Graphics g) {


		super.paintComponent(g);
		if (inGame) {
			g.setColor(Color.YELLOW); // coins
			for (Rectangle coin : coins) {
				g.fillOval(baseresize * coin.x, baseresize * coin.y, baseresize * coin.width, baseresize * coin.height);
			} // Display total coins collected
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, baseresize * 20));
			if (showCoinMessage) {
				g.setColor(Color.GREEN); // coin pickup message
				g.drawString("Score: " + score + " +500", baseresize * 10, baseresize * 20); // Adjust the position as needed
			}
			g.setColor(Color.BLUE); // player
			g.fillRect(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight);
			g.setColor(Color.BLACK);
			for (Rectangle bomb : bombs) { // bombs
				g.fillOval(baseresize * bomb.x, baseresize * bomb.y, baseresize * bomb.width, baseresize * bomb.height);
			} 
			g.setColor(Color.YELLOW); // hazard area
			for (Rectangle hazardousArea : hazardousAreas) {
				g.fillRect(baseresize * hazardousArea.x, baseresize * hazardousArea.y, baseresize * hazardousArea.width, baseresize * hazardousArea.height);
			}
			g.setColor(Color.RED); // default block
			for (Rectangle block : blocks) {
				g.fillRect(baseresize * block.x, baseresize * block.y, baseresize * block.width, baseresize * block.height);
			}
			g.setColor(Color.MAGENTA); // target blocks
			for (Rectangle targetingblock : targetingBlocks) {
				g.fillRect(baseresize * targetingblock.x, baseresize * targetingblock.y, baseresize * targetingblock.width, baseresize * targetingblock.height);
			}
			g.setColor(Color.GREEN); // power up block
			for (Rectangle powerUp : powerUps) {
				g.fillRoundRect(baseresize * powerUp.x, baseresize * powerUp.y, baseresize * powerUp.width, baseresize * powerUp.height, baseresize * 25, baseresize * 25);
			}
			g.setColor(Color.CYAN); // lasers
			for (Rectangle laser : lasers) {
				g.fillRect(baseresize * laser.x, baseresize * laser.y, baseresize * laser.width, baseresize * 20);
			}
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, baseresize * 20));
			g.drawString("Score: " + score, baseresize * 10, baseresize * 20);
			if (powerUpEndTime > System.currentTimeMillis()) {
				String powerUpName = "";
				switch (powerUpType) {
				case 1:
					powerUpName = "Smaller Size"; // small power up
					break;
				case 2:
					powerUpName = "Score Multiplier"; // score mult power up
					break;
				case 3:
					powerUpName = "Slow Motion"; // slomo power up
					break;
				case 4:
					powerUpName = "Invincibility"; // invincibility power up
					break;
				case 5:
					powerUpName = "Laser"; // laser power up
					break;
				}
				g.drawString("Power-Up: " + powerUpName, baseresize * 10, baseresize * 50);
			}
		} else {
			showTitleScreen(g);
		}
    }

    private void showTitleScreen(Graphics g) {


		intro.stop();
		theme.stop();
		slomosong.stop();
		bombdrop.stop();
		powerupsong2.stop();
		smallblocksong.stop();
		scorepowersong.stop();
		coinnoise.stop();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
		death.stop();
		g.setColor(Color.BLACK); // title screen graphics
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, baseresize * 50));
		g.drawString("Block Dodger", baseresize * 120, baseresize * 200);
		g.setFont(new Font("Arial", Font.BOLD, baseresize * 30));
		if (highScore1 > 0) {
			g.drawString("High Score: " + highScore1, baseresize * 160, baseresize * 250);
			g.drawString("Your Score: " + score, baseresize * 160, baseresize * 300);
		}
		g.drawString("Press Enter to Play", baseresize * 150, baseresize * 400);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


		if (inGame) { // if player is in a game
			score += 1 + baseScoreRate * scoreMultiplier; // score increase rate
			if (random.nextInt(1000) == 1) {
				coins.add(new Rectangle(random.nextInt(baseresize * 550), baseresize * 0, baseresize * 20, baseresize * 20));
			}
			List<Rectangle> coinsToRemove = new ArrayList<>();
			for (Rectangle coin : coins) {
				coin.y += 3; // Adjusts coin falling speed (leave at 3)
				if (coin.y > 600) {
					coinsToRemove.add(coin);
				} else if (coin.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
					coinnoise.setMicrosecondPosition(0); // set clip to beginning
					coinnoise.loop(Clip.LOOP_CONTINUOUSLY);
					score += 500; // Add points for collecting a coin
					totalCoinsCollected += 1; // Increment total coins collected (has no effect on game)
					coinsToRemove.add(coin);
					showCoinMessage = true;
					
					
					coinMessageEndTime = System.currentTimeMillis() + 2000; // display "+500" for 2 seconds
				}
			}
			coins.removeAll(coinsToRemove);
			if (System.currentTimeMillis() > coinMessageEndTime) {
				showCoinMessage = false;
				coinnoise.stop(); // stop the coin noise after 2 seconds
			}
			playerX = Math.max(0, Math.min(baseresize * playerX, baseresize * getWidth() - baseresize * playerWidth));
			int rate = score / 2500;  
			int blockrate = 1 + rate;
			if (score >= 12500) { // allow bombs to spawn if score is greater than 12500
				if (random.nextInt(300) <= score/15000) {
					bombs.add(new Rectangle(random.nextInt(baseresize * 530), baseresize * 0, baseresize * 30, baseresize * 30));
					bombdrop.loop(Clip.LOOP_CONTINUOUSLY);
				}
				List<Rectangle> bombRemove = new ArrayList<>();
				for (Rectangle bomb : bombs) { // spawn bombs
					bomb.y += 5;
					if (bomb.y == baseresize * 580) {
						explodeBomb(bomb);
						bombdrop.stop();
						bombRemove.add(bomb);
					}
					if (!isShieldActive && bomb.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
						explodeBomb(bomb); // explode bomb
						bombRemove.add(bomb); // remove bomb
						bombdrop.stop();
						powerupsong2.stop();
						smallblocksong.stop();
						intro.stop();
						scorepowersong.stop();
						timer.stop();
						death.loop(Clip.LOOP_CONTINUOUSLY);
						inGame = false;
						if (score > highScore1) {
							highScore1 = score;
						}
					}
					List<Rectangle> lasersToRemove = new ArrayList<>();
					for (Rectangle laser : lasers) {
						laser.y -= 10;
						if (laser.y < 0) {
							lasersToRemove.add(laser);
						}
						if (laser.intersects(bomb)) {
							lasersToRemove.add(laser);
							bombRemove.add(bomb);
						}
					}
				}
				bombs.removeAll(bombRemove);
				// Handle hazardous areas
				if (System.currentTimeMillis() > hazardousAreaEndTime) {
					hazardousAreas.clear(); // clear hazard areas
				}
				List<Rectangle> hazardousAreaRemove = new ArrayList<>();
				for (Rectangle hazardousArea : hazardousAreas) {
					if (!isShieldActive && hazardousArea.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
						timer.stop();
						bombdrop.stop();
						smallblocksong.stop();
						intro.stop();
						scorepowersong.stop();
						powerupsong2.stop();
						slomosong.stop();
						death.loop(Clip.LOOP_CONTINUOUSLY);
						inGame = false;
						if (score > highScore1) {
							highScore1 = score;
						}
					}
				}
				hazardousAreas.removeAll(hazardousAreaRemove); // remove all hazard areas
			}
			if (score >= 5000) { // allow target blocks to spawn when score is greater or equal to 5000
				
				if (random.nextInt(200) <= score/5000) {
					targetingBlocks.add(new Rectangle(random.nextInt(baseresize * 540), baseresize * 0, baseresize * 20, baseresize * 20));
				}
				for (Rectangle targetingBlock : targetingBlocks) {
					if (targetingBlock.y <= baseresize * 450) { 
						if (targetingBlock.x < baseresize * playerX) {
							targetingBlock.x += 2;
							// move right towards the player
						} else if (targetingBlock.x > baseresize * playerX) {
							targetingBlock.x -= baseresize * 2;
							// move left towards the player
						}
						targetingBlock.y += baseresize * 2; // move down
					} else {
						targetingBlock.y += baseresize * 2; // move down
					}
					if (!isShieldActive && targetingBlock.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
						theme.stop();
						intro.stop();
						scorepowersong.stop();
						slomosong.stop();
						powerupsong2.stop();
						smallblocksong.stop();
						bombdrop.stop();
						death.loop(Clip.LOOP_CONTINUOUSLY);
						timer.stop();
						inGame = false;
						if (score > highScore1) {
							highScore1 = score;
						}
					}
				}
			}
			
			if (score >= 100) { // allow normal blocks to spawn
			if (random.nextInt(100) <= score / 2500) {
				blocks.add(new Rectangle(random.nextInt(baseresize * 510), baseresize * 0, baseresize * 50, baseresize * 50));
			}
			}
			// Add power-ups if score is a multiple of 1000
			if(score % 1000 == 0) {
			//if (random.nextInt(1000) <= 3 + blockrate) {
				powerUps.add(new Rectangle(random.nextInt(baseresize * 510), baseresize * 0, baseresize * 50, baseresize * 50));
			}
			//}
			List<Rectangle> blockRemove = new ArrayList<>();
			for (Rectangle block : blocks) {
				block.y += (powerUpType == 3 && powerUpEndTime > System.currentTimeMillis()) ? 2 : 5;
				if (block.y > baseresize * 600) {
					blockRemove.add(block);
				}
				if (!isShieldActive && block.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
					theme.stop();
					intro.stop();
					scorepowersong.stop();
					slomosong.stop();
					powerupsong2.stop();
					smallblocksong.stop();
					bombdrop.stop();
					death.loop(Clip.LOOP_CONTINUOUSLY);
					timer.stop();
					inGame = false;
					if (score > highScore1) {
						highScore1 = score;
					}
				}
			}
			List<Rectangle> powerUpRemove = new ArrayList<>();
			for (Rectangle powerUp : powerUps) {
				powerUp.y += baseresize * 5;
				if (powerUp.y > baseresize * 600) {
					powerUpRemove.add(powerUp);
				}
				if (powerUp.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
					score += 500;
					showCoinMessage = true;
					coinMessageEndTime = System.currentTimeMillis() + 2000;
					theme.stop();
					activatePowerUp();
					powerUpRemove.add(powerUp);
				}
			}
			List<Rectangle> lasersToRemove = new ArrayList<>();
			List<Rectangle> targetingBlockRemove = new ArrayList<>();
			List<Rectangle> bombRemove = new ArrayList<>();
			for (Rectangle laser : lasers) {
				laser.y -= baseresize * 10;
				if (laser.y < 0) {
					lasersToRemove.add(laser);
				}
				for (Rectangle block : blocks) {
					if (laser.intersects(block)) {
						lasersToRemove.add(laser);
						blockRemove.add(block);
					}
				}
				for (Rectangle targetingBlock : targetingBlocks) {
					if (laser.intersects(targetingBlock)) {
						lasersToRemove.add(laser);
						targetingBlockRemove.add(targetingBlock);
					}
				}
				for (Rectangle bomb : bombs) {
					if (laser.intersects(bomb)) {
						lasersToRemove.add(laser);
						bombRemove.add(bomb);
					}
				}
			}
			blocks.removeAll(blockRemove);
			powerUps.removeAll(powerUpRemove);
			targetingBlocks.removeAll(targetingBlockRemove);
			lasers.removeAll(lasersToRemove);
			if (powerUpEndTime <= System.currentTimeMillis()) {
				isShieldActive = false;
				playerWidth = baseresize * 40;
				playerHeight = baseresize * 40;
				powerUpType = 0;
				smallblocksong.stop();
				scorepowersong.stop();
				powerupsong2.stop();
				intro.stop();
				slomosong.stop();
				theme.loop(Clip.LOOP_CONTINUOUSLY);
			}
			repaint();
		}
    }

    private void explodeBomb(Rectangle bomb) { // explode bombs
		hazardousAreas.add(new Rectangle((baseresize * bomb.x) - (baseresize * 20), baseresize * bomb.y - (baseresize * 20), baseresize * 70, baseresize * 70));
		hazardousAreaEndTime = System.currentTimeMillis() + 2500; // Hazardous area lasts for 5 seconds
    }

    private void activatePowerUp() {


		powerUpType = random.nextInt(5) + 1;
		powerUpEndTime = System.currentTimeMillis() + 10000;
		if (powerUpType == 1 && powerUpEndTime > System.currentTimeMillis()) { // small
			playerWidth = baseresize * 20;
			playerHeight = baseresize * 20;
			smallblocksong.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			smallblocksong.stop();
			playerWidth = baseresize * 40; // Normal block size
			playerHeight = baseresize * 40;
		}
		if (powerUpType == 2 && powerUpEndTime > System.currentTimeMillis()) { // score multiplier
			scoreMultiplier = 2.5;
			scorepowersong.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			scoreMultiplier = 1.0;
			scorepowersong.stop();
		}
		if (powerUpType == 3 && powerUpEndTime > System.currentTimeMillis()) { // slomo
			slomosong.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			slomosong.stop();
		}
		if (powerUpType == 4 && powerUpEndTime > System.currentTimeMillis()) { // invincible
			isShieldActive = true;
			powerupsong2.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			isShieldActive = false;
			powerupsong2.stop();
		}
		if (powerUpType == 5 && powerUpEndTime > System.currentTimeMillis()) { // laser
			intro.loop(Clip.LOOP_CONTINUOUSLY);
		} else {
			intro.stop();
		}
    }

    @Override
    public void keyPressed(KeyEvent e) {


		Clip lasersfx = null;
		try {
			lasersfx = AudioSystem.getClip();
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // create the clip object
		try {
			lasersfx.open(AudioSystem
					.getAudioInputStream(new File("src/culminating/Laser FX - Cosmic Debrislasersfx2.wav")));
		} catch (LineUnavailableException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} // open the given file for the clip
		int code = e.getKeyCode();
		int speed = (powerUpType == 3 && powerUpEndTime > System.currentTimeMillis()) ? 30 : 15;
		if (inGame) {
			
			if (powerUpType == 5 && powerUpEndTime > System.currentTimeMillis() && code == KeyEvent.VK_SPACE) {
				lasers.add(new Rectangle(baseresize * (playerX + playerWidth / 2 - 5), baseresize * (playerY - 10), baseresize * 10, baseresize * playerY));
				lasersfx.start();
			}
		} else {
			if (code == KeyEvent.VK_ENTER) {
				startGame();
			}
			if (code == KeyEvent.VK_RIGHT) {
			}
			if (code == KeyEvent.VK_R) {
				baseresize = 1;
				JFrame frame = new JFrame("Block Dodger JFrame Version");
				BlockDodgerJFrameVersion game = new BlockDodgerJFrameVersion();
				frame.setSize(1120, 1200);
				
			}
			if (code == KeyEvent.VK_T) {
				baseresize = 1;
				JFrame frame = new JFrame("Block Dodger JFrame Version");
				BlockDodgerJFrameVersion game = new BlockDodgerJFrameVersion();
				frame.setSize(560, 600);
				baseresize = 1;
				
			}
		}
    }

    private void startGame() { // resets everything on game start
		inGame = true;
		death.stop();
		bombdrop.stop();
		playerX = baseresize * 250;
		playerWidth = baseresize * 40;
		playerHeight = baseresize * 40;
		isShieldActive = false;
		blocks.clear();
		targetingBlocks.clear();
		bombs.clear();
		hazardousAreas.clear();
		powerUps.clear();
		lasers.clear();
		score = 0 + revscore;
		powerUpType = 0;
		powerUpEndTime = 0;
		theme.start();
		timer.start();
		repaint();
    }

    public static void main(String[] args) { // game panel size and other stuff
		JFrame frame = new JFrame("Block Dodger");
		BlockDodgerJFrameVersion game = new BlockDodgerJFrameVersion();
		frame.add(game);
		frame.setSize(560, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
