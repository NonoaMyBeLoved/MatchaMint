package Character;
import Main.*;

public class Archor extends Hero
{
	public Archor()
	{
		this.power = 30;
		this.defense = 8;
		this.hp = 300;
		this.mp = 40;		
	}	
	public int attack()
	{
		return power * 2;
	}
	public int assault()
	{
		return power * 5;
	}
	public int fairy()
	{
		return power * 10;
	}
	public void attacked(int sum)
	{
		hp -= sum;
	}
}
