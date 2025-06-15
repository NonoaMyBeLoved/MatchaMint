package Character;
import Main.*;

public class Wizard extends Hero
{
	public Wizard()
	{
		this.power = 40;
		this.defense = 6;
		this.hp = 200;
		this.mp = 300;		
	}	
	public int attack()
	{
		return power * 2;
	}
	public int zoltraak()
	{
		return power * 7;
	}
	public int auserlese()
	{
		return power * 14;
	}
	public void attacked(int sum)
	{
		hp -= sum;
	}
}
