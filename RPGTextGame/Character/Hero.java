package Character;
import Main.*;
import Map.*;

public class Hero extends Character 
{
	public static boolean mission = false;
	int goblin_count = 0;
	public int power;
	public int defense;
	public int money = 100;
	public int experience = 0;
	public int level = 1;
	
	public static int eyes= 0;
	public static int knife = 0;
	public static int tooth = 0;
	public static int horn = 0;
	
	public int attack()
	{
		return power * 10;
	}
	public void attacked(int sum)
	{
		hp -= sum;
	}
	
	public static void inven()
	{
		System.out.println("**********인벤토리**********");
		System.out.printf("슬라임의 눈 : %d개\n", eyes);
		System.out.printf("고블린의 칼 : %d개\n", knife);
		System.out.printf("오크의 이빨 : %d개\n", tooth);
		System.out.printf("황소의 뿔 : %d개\n", horn);
		System.out.println("**************************");
	}
	
	public void levelup()
	{
		if(experience >= level * 40)
		{
			System.out.println("********레벨업!********");
			level += 1;
			power += 5;
			defense += 2;
			money += level * 40;
			mp += level * 20;
			experience = 0;
			
		}
		
	}
	
}
