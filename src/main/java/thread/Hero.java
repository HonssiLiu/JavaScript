package thread;

public class Hero {
	
	public String name;
	
	public float hp;

	public int damage;
	
	public int energy = 3;

	public void attackHero(Hero h) {

		h.hp -= damage;
		System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n", name, h.name, h.name, h.hp);

		if (h.isDead())
			System.out.println(h.name + "死了！");
	}

	public boolean isDead() {
		return 0 >= hp ? true : false;
	}
	
	public void skill(){
		if(hasEnergy()){
			System.out.format("%s 发动波波拳，第%d发%n", name,3-energy+1);
			energy--;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void gatherEnergy(){
		if(!hasEnergy()){
			System.out.format("%s 正在充能...\r\n", name);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			energy = 3;
		}
	}
	
	public boolean hasEnergy(){
		return energy>0;
	}
	

}
