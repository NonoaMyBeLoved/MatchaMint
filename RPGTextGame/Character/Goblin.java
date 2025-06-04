package Character;
import Main.*;

public class Goblin extends Monster 
{
	public Goblin()
	{
		this.hp = 100;
		this.power = 7;
		this.defense = 5;
		this.money = 40;
		this.experience = 30;
	}
	public int attack()
	{
		return power * 2;
	}
	public void attacked(int sum)
	{
		hp -= sum;
	}
}
