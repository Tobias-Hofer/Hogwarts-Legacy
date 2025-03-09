package a12036902;

/**
 * Defines the various magic levels.
 * wizards have a magic level.
 * in order to be able to use specific spells a minimum magic level is necessary.
 * Note: the compiler generated default constructor may not be sufficient for your implementation
 */
public enum MagicLevel {
	NOOB(50,"*"),
	ADEPT(100,"**"),
	STUDENT(200,"***"),
	EXPERT(500,"****"),
	MASTER(1000,"*****");
	
	private final String asterik;
	private final int mp;
	
	MagicLevel(int mp, String asterik) {
		this.mp = mp;
		this.asterik = asterik;
	}
	
	public int toMana() {
		return mp;
	}
	
	@Override
	public String toString() {
		return asterik;
	}
	
}