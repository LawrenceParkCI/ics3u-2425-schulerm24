package culminating;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import javax.sound.sampled.*;
import hsa_new.Console;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlockDodger implements ActionListener, KeyListener, MouseListener{
	private int mouseX;
	private int baseScoreRate = 1; //set score increment
	private double scoreMultiplier = 1.0; //multiply score by +1 per second when powerup is active
	private javax.swing.Timer timer; //timer (score per millisecond)
	public PointerInfo cursor = MouseInfo.getPointerInfo();
	private int playerX; //player x coord
	private int playerY = 375; // player y coord
	private int playerWidth = 40; //player width
	private int playerHeight = 40; // player height
	private List<Rectangle> blocks; //spawn red blocks
	private List<Rectangle> coins; //spawn coins
	private List<Rectangle> targetingBlocks; //spawn targeting blocks
	private List<Rectangle> powerUps; //spawn powerups
	private List<Rectangle> lasers; //spawn lasers when shot
	private Random random;
	private boolean inGame = false;
	private int score = 0; //score
	private int revscore = 0;
	private int highScore = 0;//highscore
	private int powerUpType = 0;//powerup
	private long powerUpEndTime = 0;//powerup time limit
	private Clip theme;
	private Clip intro;
	private Clip death;
	private boolean isShieldActive = false; // Track if the shield is active
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
	private boolean showCoinMessage = false; // Whether to show the coin message
	private long coinMessageEndTime = 0; // When to stop showing the coin message
	private Clip coin;
	private int baseresize = 1;
    private Console console;
    
    public void addMouseMotionTracking() {
        console.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                mouseX = e.getX();
                // Move player based on mouse position
                playerX = mouseX - playerWidth / 2;
                renderGame(); // Update the display
            }
        });
    }



    public BlockDodger() {
        console = new Console(); // Adjusted window size

        timer = new javax.swing.Timer(10, this);
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
    }


    private void initAudioClips() {
        try {
            coinnoise = AudioSystem.getClip();
            coinnoise.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/Coin Noise_1Coin.wav")));
            bombdrop = AudioSystem.getClip();
            bombdrop.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/Audio 2bombdrop.wav")));
            scorepowersong = AudioSystem.getClip();
            scorepowersong.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/score multiplierscore multiplier.wav")));
            slomosong = AudioSystem.getClip();
            slomosong.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/slowchinamusicslowpowerup2.wav")));
            bombexplode = AudioSystem.getClip();
            bombexplode.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/explosionsfx_1.wav")));
            powerupsong2 = AudioSystem.getClip();
            powerupsong2.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/Second powerup song_1.wav")));
            intro = AudioSystem.getClip();
            intro.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/block dodger soundtrack updatedblockdodgersplaysong.wav")));
            smallblocksong = AudioSystem.getClip();
            smallblocksong.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/smallblocksongsmallblocksong.wav")));
            theme = AudioSystem.getClip();
            theme.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/block dodger soundtrack updatedblockdodgerintro.wav")));
            death = AudioSystem.getClip();
            death.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/Zone FX 2 - Interchangeblock explode1.wav")));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            score += 1 + baseScoreRate * scoreMultiplier;
            
            playerX = Math.max(0, Math.min(mouseX - playerWidth / 2, console.getWidth() - playerWidth));
            
            if (random.nextInt(1000) == 1) {
                coins.add(new Rectangle(random.nextInt(baseresize * 550), baseresize * 0, baseresize * 20, baseresize * 20));
            }

            List<Rectangle> coinsToRemove = new ArrayList<>();
            for (Rectangle coin : coins) {
                coin.y += 3;
                if (coin.y > 600) {
                    coinsToRemove.add(coin);
                } else if (coin.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
                    coinnoise.loop(Clip.LOOP_CONTINUOUSLY);
                    score += 500;
                    totalCoinsCollected += 1;
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

            playerX = Math.max(0, Math.min(baseresize * playerX, baseresize * console.getWidth() - baseresize * playerWidth));
            int rate = score / 2500;
            int blockrate = 1 + rate;

            if (score >= 12500) {
                if (random.nextInt(300) <= score / 15000) {
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
                    if (!isShieldActive && bomb.intersects(new Rectangle(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight))) {
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
                if (System.currentTimeMillis() > hazardousAreaEndTime) {
                    hazardousAreas.clear();
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
                        if (score > highScore) {
                            highScore = score;
                        }
                    }
                }
                hazardousAreas.removeAll(hazardousAreaRemove);
            }

            if (score >= 5000) {
                if (random.nextInt(200) <= score / 5000) {
                    targetingBlocks.add(new Rectangle(random.nextInt(baseresize * 540), baseresize * 0, baseresize * 20, baseresize * 20));
                }

                for (Rectangle targetingBlock : targetingBlocks) {
                    if (targetingBlock.y <= baseresize * 450) {
                        if (targetingBlock.x < baseresize * playerX) {
                            targetingBlock.x += 2;
                        } else if (targetingBlock.x > baseresize * playerX) {
                            targetingBlock.x -= baseresize * 2;
                        }
                        targetingBlock.y += baseresize * 2;
                        
                        
             
                } else {
                    	
                        targetingBlock.y += baseresize * 2;
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
                        if (score > highScore) {
                            highScore = score;
                        }
                    }
                }
        }
        
    


            if (score >= 100) {
                if (random.nextInt(100) <= score / 2500) {
                    blocks.add(new Rectangle(random.nextInt(baseresize * 510), baseresize * 0, baseresize * 50, baseresize * 50));
                }
            }

            if (score % 1000 == 0) {
                powerUps.add(new Rectangle(random.nextInt(baseresize * 510), baseresize * 0, baseresize * 50, baseresize * 50));
            }

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
            blocks.removeAll(blockRemove);

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

            // Update the console display
            renderGame();

            // Repaint the console
            console.repaint();
            
        }
    }
    

    private void explodeBomb(Rectangle bomb) {
        hazardousAreas.add(new Rectangle((baseresize * bomb.x) - (baseresize * 20), baseresize * bomb.y - (baseresize * 20), baseresize * 70, baseresize * 70));
        hazardousAreaEndTime = System.currentTimeMillis() + 2500;
    }

    private void activatePowerUp() {
        powerUpType = random.nextInt(5) + 1;
        powerUpEndTime = System.currentTimeMillis() + 10000;

        if (powerUpType == 1 && powerUpEndTime > System.currentTimeMillis()) {
            playerWidth = baseresize * 20;
            playerHeight = baseresize * 20;
            smallblocksong.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            smallblocksong.stop();
            playerWidth = baseresize * 40;
            playerHeight = baseresize * 40;
        }

        if (powerUpType == 2 && powerUpEndTime > System.currentTimeMillis()) {
            scoreMultiplier = 2.5;
            scorepowersong.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            scoreMultiplier = 1.0;
            scorepowersong.stop();
        }

        if (powerUpType == 3 && powerUpEndTime > System.currentTimeMillis()) {
            slomosong.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            slomosong.stop();
        }

        if (powerUpType == 4 && powerUpEndTime > System.currentTimeMillis()) {
            isShieldActive = true;
            powerupsong2.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            isShieldActive = false;
            powerupsong2.stop();
        }
        if (powerUpType == 5 && powerUpEndTime > System.currentTimeMillis()) {
            intro.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            intro.stop();
        }
    }

   
    @Override
    public void keyPressed(KeyEvent e) {
    	
    	//console.addKeyListener(this); // Make sure KeyListener is added
       // console.setFocusable(true);
    	Clip lasersfx = null;

        try {
            lasersfx = AudioSystem.getClip();
            lasersfx.open(AudioSystem.getAudioInputStream(new File("src/adventuregame/Laser FX - Cosmic Debrislasersfx2.wav")));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        int code = e.getKeyCode();
        int speed = (powerUpType == 3 && powerUpEndTime > System.currentTimeMillis()) ? 30 : 15;

        if (inGame = true) {
            if (code == KeyEvent.VK_SPACE) {
                lasers.add(new Rectangle(baseresize * (playerX + playerWidth / 2 - 5), baseresize * (playerY - 10), baseresize * 10, baseresize * playerY));
                if (lasersfx != null) {
                    lasersfx.start();
                }
                console.println("Laser fired!"); // Debugging message
            }
        } 	if (inGame = false) {
            if (code == KeyEvent.VK_ENTER) {
                startGame();
                
            }
            
            }
        }
    


    private void renderGame() {
        // Clear the console
        console.clear();

        if (inGame) {
            console.setColor(Color.YELLOW);
            for (Rectangle coin : coins) {
                console.fillOval(baseresize * coin.x, baseresize * coin.y, baseresize * coin.width, baseresize * coin.height);
            }
            console.setColor(Color.BLACK);
            console.setFont(new Font("Arial", Font.BOLD, baseresize * 20));
            if (showCoinMessage) {
                console.setColor(Color.GREEN);
                console.drawString("Score: " + score + " +500", baseresize * 10, baseresize * 20);
            }

            console.setColor(Color.BLUE);
            console.fillRect(baseresize * playerX, baseresize * playerY, baseresize * playerWidth, baseresize * playerHeight);

            console.setColor(Color.BLACK);
            for (Rectangle bomb : bombs) {
                console.fillOval(baseresize * bomb.x, baseresize * bomb.y, baseresize * bomb.width, baseresize * bomb.height);
            }
            console.setColor(Color.YELLOW);
            for (Rectangle hazardousArea : hazardousAreas) {
                console.fillRect(baseresize * hazardousArea.x, baseresize * hazardousArea.y, baseresize * hazardousArea.width, baseresize * hazardousArea.height);
            }

            console.setColor(Color.RED);
            for (Rectangle block : blocks) {
                console.fillRect(baseresize * block.x, baseresize * block.y, baseresize * block.width, baseresize * block.height);
            }

            console.setColor(Color.MAGENTA);
            for (Rectangle targetingblock : targetingBlocks) {
                console.fillRect(baseresize * targetingblock.x, baseresize * targetingblock.y, baseresize * targetingblock.width, baseresize * targetingblock.height);
            }

            console.setColor(Color.GREEN);
            for (Rectangle powerUp : powerUps) {
                console.fillRoundRect(baseresize * powerUp.x, baseresize * powerUp.y, baseresize * powerUp.width, baseresize * powerUp.height, baseresize * 25, baseresize * 25);
            }

            console.setColor(Color.CYAN);
            for (Rectangle laser : lasers) {
                console.fillRect(baseresize * laser.x, baseresize * laser.y, baseresize * laser.width, baseresize * 20);
            }

            console.setColor(Color.BLACK);
            console.setFont(new Font("Arial", Font.BOLD, baseresize * 20));
            console.drawString("Score: " + score, baseresize * 10, baseresize * 20);

            if (powerUpEndTime > System.currentTimeMillis()) {
                String powerUpName = "";
                switch (powerUpType) {
                    case 1:
                        powerUpName = "Smaller Size";
                        break;
                    case 2:
                        powerUpName = "Score Multiplier";
                        break;
                    case 3:
                        powerUpName = "Slow Motion";
                        break;
                    case 4:
                        powerUpName = "Invincibility";
                        break;
                    case 5:
                        powerUpName = "Laser";
                        break;
                }
                console.drawString("Power-Up: " + powerUpName, baseresize * 10, baseresize * 50);
            }
        } else {
            showTitleScreen();
        }
    }


    private void showTitleScreen() {
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
        console.setColor(Color.BLACK);
        console.fillRect(0, 0, console.getWidth(), console.getHeight());

        console.setColor(Color.WHITE);
        console.setFont(new Font("Arial", Font.BOLD, baseresize * 50));
        console.drawString("Block Dodger", baseresize * 120, baseresize * 200);

        console.setFont(new Font("Arial", Font.BOLD, baseresize * 30));
        if (highScore > 0) {
            console.drawString("High Score: " + highScore, baseresize * 160, baseresize * 250);
            console.drawString("Your Score: " + score, baseresize * 160, baseresize * 300);
        }
        console.drawString("Press Enter to Play", baseresize * 150, baseresize * 400);
        console.setFocusable(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    @Override
    public void keyReleased(KeyEvent e) {}
    
    private void startGame() {
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
        renderGame();
    }


    public static void main(String[] args) {
        BlockDodger game = new BlockDodger();
        game.console.println("Block Dodger game started!");

        game.console.addKeyListener(game);
        game.startGame();
    }
}

