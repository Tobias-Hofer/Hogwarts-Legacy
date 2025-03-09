package a12036902;

/**
 * HealthPotions increase the consumer's mana
 */
public class HealthPotion extends Potion {
	/**
	 * Must not be negative
	 */
	private int health;

	/**
	 * @param name name
	 * @param usages number of usages still left
	 * @param price price
	 * @param weight weight
	 * @param health change of health on consumer
	 */
	public HealthPotion(String name, int usages, int price, int weight, int health) {
		super(name,usages,price,weight);
		if(health < 0) throw new IllegalArgumentException("Leben darf nicht Negativ sein");
		this.health = health;
	}
	
	/**
	 * returns "; +'health' HP".
	 * e.g. (total result of toString) "[Health Potion; 1 g; 1 Knut; 5 gulps; +10 HP]"
	 * @return "; +'health' HP"
	 */
	@Override
	public String additionalOutputString() {
		return ("; +" + this.health + " HP");
	}

	/**
	 * If usages is greater than 0 reduce usages by 1 (tryUsage method) and
	 * increase HP of target by health (call method heal(health))
	 * @param target target on which to object is to be used on
	 */
	@Override  
	public void useOn(MagicEffectRealization target) {
		if (this.tryUsage())
			target.heal(this.health);
	}
}