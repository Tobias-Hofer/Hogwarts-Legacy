package a12036902;

import java.util.HashSet;
import java.util.Set;

/**
 * Magic spells that do some sort of damage to a target
 */
public class AttackingSpell extends Spell {
	/**
	 * Defines, if HP or MP is affected.
	 * <p>
	 * type == true: affects HP
	 * <p>
	 * type == false: affects MP
	 */
	private boolean type;
	/**
	 * Defines, if amount is interpreted as an absolute value or as a percentage.
	 * <p>
	 * percentage == true: deduct value to subtract as 'amount' percentage of basic value
	 * <p>
	 * percentage == false: subtract amount directly 
	 */
	private boolean percentage;
	/**
	 * Has to be non negative.
	 * <p>
	 * if percentage==true, amount must be in the interval [0,100]
	 */
	private int amount;
	
	/**
	 * @param name name
	 * @param manaCost manaCost
	 * @param levelNeeded levelNeeded
	 * @param type defines if health or mana is affected
	 * @param percentage defines if amount is an absolute or a percentage value
	 * @param amount amount
	 */
	public AttackingSpell(String name,	int manaCost, MagicLevel levelNeeded, boolean type, boolean percentage, int amount) {
		super(name, manaCost, levelNeeded);
		if(amount < 0) throw new IllegalArgumentException("Wert darf nicht Negativ sein");
		if(percentage){
			if (amount < 0 || amount > 100) throw new IllegalArgumentException("Wert darf nicht Negativ sein oder über 100 liegen");
		}
		this.type = type;
		this.percentage = percentage;
		this.amount = amount;
	}

	/**
	 * If the target is protected against this spell (isProtected), then protection against
	 * exactly this spell is removed (removeProtection).
	 * Otherwise use one of the functions takeDamage, takeDamagePercent, weakenMagic or
	 * weakenMagicPercent on target according to the flags type and percentage.
	 * @param target target that takes the damage
	*/
	@Override
	public void doEffect(MagicEffectRealization target) {
		if (target.isProtected(this)) {
			Set<AttackingSpell> spellSet = new HashSet<>();
			spellSet.add(this);
			target.removeProtection(spellSet);
		}
		else {
			if (this.type) {
				if (this.percentage) 
					target.takeDamagePercent(this.amount);
				else
					target.takeDamage(this.amount);
			}
			else
				if (this.percentage)
					target.weakenMagicPercent(this.amount);
				else
					target.weakenMagic(this.amount);
		}
	}

	/**
	 * Returns "; -'amount' 'percentage' 'HPorMP'".
	 * where 'percentage' is a '%'-sign if percentage is true,
	 * empty otherwise and HPorMP is HP if type is true, MP otherwise.
	 * E. g. "; -10 MP" or "; -50 % HP"
	 * @return "; -'amount' 'percentage' 'HPorMP'".
	 */
	@Override
	public String additionalOutputString() {
		String output = "; -"+ this.amount;
		if (this.type){
			if(this.percentage){
				return output += " % HP";
			}else return output += " HP";
		}else {
			if (this.percentage) {
				return output += " % MP";
			} else return output += " MP";
		}
	}

}