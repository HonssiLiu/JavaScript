package thread;

public class TestThread {

	public static void main(String[] args) {
		Hero gareen = new Hero();
		gareen.name = "盖伦";
		gareen.hp = 6160;
		gareen.damage = 1;

		Hero teemo = new Hero();
		teemo.name = "提莫";
		teemo.hp = 3000;
		teemo.damage = 1;

		Hero bh = new Hero();
		bh.name = "赏金猎人";
		bh.hp = 5000;
		bh.damage = 1;

		Hero leesin = new Hero();
		leesin.name = "盲僧";
		leesin.hp = 4505;
		leesin.damage = 1;

		Thread t1 = new Thread() {
			public void run() {
				while (true) {
					gareen.skill();
				}
			}
		};

		Thread t2 = new Thread() {
			public void run() {
				while (true) {
					gareen.gatherEnergy();
				}
			}
		};

		t1.start();
		t2.start();
	}

}
