package Character;

public class Monster extends Character 
{
	public int power;
	public int defense;
	public int money;
	public int experience;
	
	public int attack()
	{
		return 1;
	}
	public void attacked(int sum)
	{
		hp -= sum;
	}
}
