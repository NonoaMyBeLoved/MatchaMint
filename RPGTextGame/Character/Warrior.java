package Character;
import Main.*;

public class Warrior extends Hero
{
	public Warrior()
	{
		this.power = 20;
		this.defense = 10;
		this.hp = 400;
		this.mp = 60;		
	}
	public int attack()
	{
		return power * 2;
	}
	public int hyungjoon()
	{
		return power * 5;
	}
	public int unstoppable()
	{
		return power * 10;
	}
	public void attacked(int sum)
	{
		hp -= sum;
	}
}
