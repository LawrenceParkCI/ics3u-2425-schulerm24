package culminating;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import javax.sound.sampled.*;
import hsa_new.Console;
public class BlockDodger implements ActionListener, KeyListener {
   private int baseScoreRate = 1;
   private double scoreMultiplier = 1.0;
   private javax.swing.Timer timer;
   private int playerX = 250;
   private int playerY = 540;
   private int playerWidth = 40;
   private int playerHeight = 40;
   private List<Rectangle> blocks;
   private List<Rectangle> coins;
   private List<Rectangle> targetingBlocks;
   private List<Rectangle> powerUps;
   private List<Rectangle> lasers;
   private Random random;
   private boolean inGame = false;
   private int score = 0;
   private int revscore = 0;
   private int highScore = 0;
   private int powerUpType = 0;
   private long powerUpEndTime = 0;
   private Clip theme;
   private Clip intro;
   private Clip death;
   private boolean isShieldActive = false;
   private List<Rectangle> bombs;
   private List<Rectangle> hazardousAreas;
   private long hazardousAreaEndTime = 0;
   private Clip bombdrop;
   private Clip bombexplode;
   private Clip powerupsong2;
   private Clip smallblocksong;
   private Clip slomosong;
   private Clip coinnoise;
   private Clip scorepowersong;
   private int totalCoinsCollected = 0;
   private boolean showCoinMessage = false;
   private long coinMessageEndTime = 0;
   private int baseresize = 1;
   private int mouseX;
   Console console;
  
   public BlockDodger() {
       console = new Console(600, 550); // Adjusted window size
       timer = new javax.swing.Timer(baseScoreRate, console);
       blocks = new ArrayList<>();
       targetingBlocks = new ArrayList<>();
       powerUps = new ArrayList<>();
       lasers = new ArrayList<>();
       random = new Random();
       initAudioClips();
       bombs = new ArrayList<>();
       hazardousAreas = new ArrayList<>();
       coins = new ArrayList<>();
       mouseX = 0;  // Initialize the mouseX variable
       addMouseMotionTracking();  // Start tracking mouse movement
       console.addKeyListener(this); // Make sure KeyListener is added
       console.setFocusable(true);   // Ensure console is focusable
       console.requestFocusInWindow(); // Request focus
   }
   private void initAudioClips() {
       try {
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
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   private void addMouseMotionTracking() {
       console.addMouseMotionListener(new MouseMotionAdapter() {
           @Override
           public void mouseMoved(MouseEvent e) {
               mouseX = e.getX();
               // Move player based on mouse position
               playerX = mouseX - playerWidth / 2;
              
           }
       });
   }
   private int loadHighScore() {
       try (BufferedReader reader = new BufferedReader(new FileReader("highscore.txt"))) {
           return Integer.parseInt(reader.readLine());
       } catch (IOException | NumberFormatException e) {
           e.printStackTrace();
           return 0;
       }
   }
  
  
   private void saveHighScore(int highScore) {
       try (FileWriter writer = new FileWriter("highscore.txt")) {
           writer.write(String.valueOf(highScore));
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   public void saveGame() {
       saveHighScore(highScore);
   }
   public void loadGame() {
       highScore = loadHighScore();
     
   }
public void paintComponent(Graphics g) {
		
		if (inGame) {
			g.setColor(Color.YELLOW);
			for (Rectangle coin : coins) {
				g.fillOval(baseresize * coin.x, baseresize * coin.y, baseresize * coin.width, baseresize * coin.height);
			} // Display total coins collected
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, baseresize * 20));
			if (showCoinMessage) {
				g.setColor(Color.GREEN);
				g.drawString("Score: " + score + " +500", baseresize * 10, baseresize * 20); // Adjust the position as needed
			}
			g.setColor(Color.BLUE);
			g.fillRect(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight);
			g.setColor(Color.BLACK);
			for (Rectangle bomb : bombs) {
				g.fillOval(baseresize * bomb.x, baseresize * bomb.y, baseresize * bomb.width, baseresize * bomb.height);
			} // Draw hazardous areas
			g.setColor(Color.YELLOW);
			for (Rectangle hazardousArea : hazardousAreas) {
				g.fillRect(baseresize * hazardousArea.x, baseresize * hazardousArea.y, baseresize * hazardousArea.width, baseresize * hazardousArea.height);
			}
			g.setColor(Color.RED);
			for (Rectangle block : blocks) {
				g.fillRect(baseresize * block.x, baseresize * block.y, baseresize * block.width, baseresize * block.height);
			}
			g.setColor(Color.MAGENTA);
			for (Rectangle targetingblock : targetingBlocks) {
				g.fillRect(baseresize * targetingblock.x, baseresize * targetingblock.y, baseresize * targetingblock.width, baseresize * targetingblock.height);
			}
			g.setColor(Color.GREEN);
			for (Rectangle powerUp : powerUps) {
				g.fillRoundRect(baseresize * powerUp.x, baseresize * powerUp.y, baseresize * powerUp.width, baseresize * powerUp.height, baseresize * 25, baseresize * 25);
			}
			g.setColor(Color.CYAN);
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
					powerUpName = "Smaller Size"; //small
					break;
				case 2:
					powerUpName = "Score Multiplier"; //score mult
					break;
				case 3:
					powerUpName = "Slow Motion"; //slomo
					break;
				case 4:
					powerUpName = "Invincibility"; //invincibility
					break;
				case 5:
					powerUpName = "Laser";
					break;
				}
				g.drawString("Power-Up: " + powerUpName, baseresize * 10, baseresize * 50);
			}
		} else {
			showTitleScreen(g);
		}
	}
  
   @Override
   public void actionPerformed(ActionEvent e) {
		if (inGame) {
			score += 1 + baseScoreRate * scoreMultiplier;
			if (random.nextInt(1000) == 1) {
				coins.add(new Rectangle(random.nextInt(baseresize * 550), baseresize * 0, baseresize * 20, baseresize * 20));
			}
			List<Rectangle> coinsToRemove = new ArrayList<>();
			for (Rectangle coin : coins) {
				coin.y += 3; // Adjust coin falling speed if needed
				if (coin.y > 600) {
					coinsToRemove.add(coin);
				} else if (coin.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
					coinnoise.loop(Clip.LOOP_CONTINUOUSLY);
					score += 500; // Add points for collecting a coin
					totalCoinsCollected += 1; // Increment total coins collected
					coinsToRemove.add(coin);
					showCoinMessage = true;
					coinMessageEndTime = System.currentTimeMillis() + 2000;
				}
			}
			coins.removeAll(coinsToRemove);
			if (System.currentTimeMillis() > coinMessageEndTime) {
				showCoinMessage = false;
				coinnoise.stop();
			}
			playerX = Math.max(0, Math.min(baseresize * playerX, baseresize * 600 - baseresize * playerWidth));
			int rate = score / 2500;
			int blockrate = 1 + rate;
			if (score >= 12500) {
				if (random.nextInt(300) <= score/15000) {
					bombs.add(new Rectangle(random.nextInt(baseresize * 530), baseresize * 0, baseresize * 30, baseresize * 30));
					bombdrop.loop(Clip.LOOP_CONTINUOUSLY);
				}
				List<Rectangle> bombRemove = new ArrayList<>();
				for (Rectangle bomb : bombs) {
					bomb.y += 5;
					if (bomb.y == baseresize * 580) {
						explodeBomb(bomb);
						bombdrop.stop();
						bombRemove.add(bomb);
					}
					if (!isShieldActive
							&& bomb.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
						explodeBomb(bomb);
						bombRemove.add(bomb);
						bombdrop.stop();
						powerupsong2.stop();
						smallblocksong.stop();
						intro.stop();
						scorepowersong.stop();
						timer.stop();
						death.loop(Clip.LOOP_CONTINUOUSLY);
						inGame = false;
						if (score > highScore) {
							highScore = score;
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
					hazardousAreas.clear();
				}
				List<Rectangle> hazardousAreaRemove = new ArrayList<>();
				for (Rectangle hazardousArea : hazardousAreas) {
					if (!isShieldActive
							&& hazardousArea.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
						timer.stop();
						bombdrop.stop();
						smallblocksong.stop();
						intro.stop();
						scorepowersong.stop();
						powerupsong2.stop();
						slomosong.stop();
						death.loop(Clip.LOOP_CONTINUOUSLY);
						inGame = false;
						if (score > highScore) {
							highScore = score;
						}
					}
				}
				hazardousAreas.removeAll(hazardousAreaRemove);
			}
			if (score >= 5000) {
				// Add targeting blocks
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
						targetingBlock.y += baseresize * 2;
					}
					if (!isShieldActive
							&& targetingBlock.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
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
						if (score > highScore) {
							highScore = score;
						}
					}
				}
			}
			// Add normal blocks
			if (score >= 100) {
			if (random.nextInt(100) <= score / 2500) {
				blocks.add(new Rectangle(random.nextInt(baseresize * 510), baseresize * 0, baseresize * 50, baseresize * 50));
			}
			}
			// Add power-ups
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
					if (score > highScore) {
						highScore = score;
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
			console.repaint();
		}
	}
	private void explodeBomb(Rectangle bomb) {
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
           lasersfx.open(AudioSystem.getAudioInputStream(new File("src/culminating/Laser FX - Cosmic Debrislasersfx2.wav")));
       } catch (Exception e1) {
           e1.printStackTrace();
       }
       int code = e.getKeyCode();
       if (inGame) {
           if (code == KeyEvent.VK_SPACE) {
               lasers.add(new Rectangle(baseresize * (playerX + playerWidth / 2 - 5), baseresize * (playerY - 10), baseresize * 10, baseresize * playerY));
               if (lasersfx != null) {
                   lasersfx.start();
               }
               console.println("Laser fired!"); // Debugging message
           }
       } else {
           if (console.isKeyDown (KeyEvent.VK_ENTER)) {
               inGame = true;
           }
           if (code == KeyEvent.VK_R) {
               baseresize = 1;
               console = new Console(1120, 800); // Re-create console
               addMouseMotionTracking(); // Re-add mouse motion tracking
               console.addKeyListener(this); // Re-add key listener
               console.setFocusable(true);   // Ensure console is focusable
               console.requestFocusInWindow(); // Request focus
           }
           if (code == KeyEvent.VK_T) {
               baseresize = 1;
               console = new Console(560, 600); // Re-create console
               addMouseMotionTracking(); // Re-add mouse motion tracking
               console.addKeyListener(this); // Re-add key listener
               console.setFocusable(true);   // Ensure console is focusable
               console.requestFocusInWindow(); // Request focus
           }
       }
   }
   @Override
   public void keyTyped(KeyEvent e) {}
   @Override
   public void keyReleased(KeyEvent e) {}
  
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		death.stop();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 600, 550);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, baseresize * 50));
		g.drawString("Block Dodger", baseresize * 120, baseresize * 200);
		g.setFont(new Font("Arial", Font.BOLD, baseresize * 30));
		if (highScore > 0) {
			g.drawString("High Score: " + highScore, baseresize * 160, baseresize * 250);
			g.drawString("Your Score: " + score, baseresize * 160, baseresize * 300);
		}
		g.drawString("Press Enter to Play", baseresize * 150, baseresize * 400);
	}
   void startGame() {
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
		console.repaint();
	}
   public static void main(String[] args) {
       BlockDodger game = new BlockDodger();
       game.console.println("Block Dodger game started!");
       game.console.addKeyListener(game);
       game.console.setFocusable(true);   // Ensure console is focusable
       game.console.requestFocusInWindow(); // Request focus
      
       game.startGame();
   }
}

