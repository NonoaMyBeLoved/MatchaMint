package Character;

public class Orc extends Monster
{
	public Orc()
	{
		this.hp = 300;
		this.power = 25;
		this.defense = 18;
		this.money = 80;
		this.experience = 80;
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
