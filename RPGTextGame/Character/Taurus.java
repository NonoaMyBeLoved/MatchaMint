package Character;

public class Taurus extends Monster 
{
	public Taurus()
	{
		this.hp = 800;
		this.power = 60;
		this.defense = 30;
		this.money = 100;
		this.experience = 120;
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
