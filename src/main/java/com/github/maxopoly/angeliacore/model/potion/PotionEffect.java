package com.github.maxopoly.angeliacore.model.potion;

public class PotionEffect {

	private PotionType type;
	private int strength;
	private int duration; // in seconds

	public PotionEffect(PotionType type, int strength, int duration) {
		this.type = type;
		this.strength = strength;
		this.duration = duration;
	}

	/**
	 * @return Type of the effect
	 */
	public PotionType getType() {
		return type;
	}

	/**
	 * @return Level of the effect
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @return Duration in seconds
	 */
	public int getDuration() {
		return duration;
	}

	// the following makes managing of potion effects a lot easier, because we usually only want one instance of a
	// specific type

	@Override
	public boolean equals(Object o) {
		return o instanceof PotionEffect && ((PotionEffect) o).type == type;
	}

	@Override
	public int hashCode() {
		return type.hashCode();
	}

}
