package col.lambton.laserwargame;


import java.io.File;
import java.util.*;





import java.io.*;

public class AGameSession {

	private String gameid;
	public static int ID;

	public static String[] namelist = new String[0];

	public static boolean GotGames = false;

	private int width;
	private int height;

	private int x;
	private int y;

	private int angle = 0;

	private int x2;
	private int y2;

	private int angle2 = 180;
	
	private int speed1 = 3;
	private int speed2 = 3;

	public synchronized int getSpeed2() {
		return speed2;
	}

	public synchronized void setSpeed2(int speed2) {
		this.speed2 = speed2;
	}

	public synchronized int getSpeed1() {
		return speed1;
	}

	public synchronized void setSpeed1(int speed) {
		this.speed1 = speed;
	}

	String endMsg = "You win!";

	private ArrayList<fireInfo> fires = new ArrayList();

	//public static AudioStream laserGun = null;

	public synchronized void addNewFireInfo(fireInfo fi) {
		fires.add(fi);

	}

	public synchronized int getCountFires() {

		return fires.size();

	}

	public synchronized void removeAllZeros() {

		Iterator<fireInfo> iterator = fires.iterator();

		while (iterator.hasNext()) {
			fireInfo fi = iterator.next();

			if (fi.time == 0)
				iterator.remove();

		}

	}

	public synchronized fireInfo getNthFire(int i) {

		return fires.get(i);

	}

	public synchronized String getEndMsg() {
		return endMsg;
	}

	public synchronized void setEndMsg(String endMsg) {
		this.endMsg = endMsg;
	}

	public synchronized int getFule1() {
		return fule1;
	}

	public synchronized void setFule1(int fule1) {
		this.fule1 = fule1;
	}

	public synchronized int getFule2() {
		return fule2;
	}

	public synchronized void setFule2(int fule2) {
		this.fule2 = fule2;
	}

	private int fule1 = 30;
	private int fule2 = 30;

	final static int MaxDiv = 36;

	/*
	public synchronized Image getImgTank() {
		return imgTank;
	}

	public synchronized void setImgTank(Image imgTank) {
		this.imgTank = imgTank;
	}

	public synchronized Image getImgTank2() {
		return imgTank2;
	}

	public synchronized void setImgTank2(Image imgTank2) {
		this.imgTank2 = imgTank2;
	}
   */
	//public BufferedImage[] tanks = new BufferedImage[MaxDiv];
	//public Image imgTank = null;// this.getToolkit().getImage("./resources/tank0.jpg");
	//public Image imgTank2 = null;
	//public static Image imgExp = null;// ImageIO.read(new
										// File("./resources/exploded.png"));

	public synchronized int getX2() {
		return x2;
	}

	public synchronized void setX2(int x2) {
		this.x2 = x2;
	}

	public synchronized int getY2() {
		return y2;
	}

	public synchronized void setY2(int y2) {
		this.y2 = y2;
	}

	public synchronized int getAngle2() {
		return angle2;
	}

	public synchronized void setAngle2(int angle2) {
		this.angle2 = angle2;
	}

	private Constraint c1 = new Constraint(0, 0, 0, 0);
	private Constraint c2 = new Constraint(0, 0, 0, 0);

	public synchronized int getAngle() {
		return angle;
	}

	public synchronized Constraint getC1() {
		return c1;
	}

	public synchronized void setC1(Constraint c1) {
		this.c1 = c1;
	}

	public synchronized Constraint getC2() {
		return c2;
	}

	public synchronized void setC2(Constraint c2) {
		this.c2 = c2;
	}

	public synchronized void setAngle(int angle) {
		this.angle = angle;
	}

	public synchronized int getX() {
		return x;
	}

	public synchronized void setX(int x) {
		this.x = x;
	}

	public synchronized int getY() {
		return y;
	}

	public synchronized void setY(int y) {
		this.y = y;
	}

	public synchronized int getWidth() {
		return width;
	}

	public synchronized void setWidth(int width) {
		this.width = width;
	}

	public synchronized int getHeight() {
		return height;
	}

	public synchronized void setHeight(int height) {
		this.height = height;
	}

	public synchronized String getGameid() {
		return gameid;
	}

	public AGameSession() {

	/*	try {
			for (int i = 0; i < MaxDiv; i++) {

				tanks[i] = ImageIO.read(new File("./resources/tank" + i
						+ ".png"));

			}

			imgExp = ImageIO.read(new File("./resources/exploded.png"));

		} catch (Exception e) {
			e.printStackTrace();
		}
   */
	}

	public synchronized void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public synchronized void getTankImageForMain() {

		int angle = gameClientHandler.game.getAngle();
		int i = angle % 360 / 10;
		if (i < 0)
			i += 36;
		//imgTank = tanks[i];

	}

	public synchronized void getTankImageForSec() {

		int angle = gameClientHandler.game.getAngle2();
		int i = angle % 360 / 10;
		if (i < 0)
			i += 36;
		//imgTank2 = tanks[i];

	}

	public static synchronized boolean isGotGames() {
		return GotGames;
	}

	public static synchronized void setGotGames(boolean gotGames) {
		GotGames = gotGames;
	}

}
