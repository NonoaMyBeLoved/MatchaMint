package Character;

public class Slime extends Monster 
{
	public Slime()
	{
		this.hp = 30;
		this.power = 1;
		this.defense = 1;
		this.money = 10;
		this.experience = 10;
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
