package Character;
import Main.*;

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
		if(level == 3 && !mission)
		{
			System.out.println("****3레벨 달성! 다음 미션을 완료하면 3번째 기술을 얻습니다.****");
			System.out.println("고블린 3마리 처치");
			mission = true;
			goblin_count = 0;
		}
	}
	public void goblinMission()
	{
		if(mission && !MainRPGTextGame.three)
		{
			goblin_count++;
			System.out.println("현재 잡은 고블린 : " + goblin_count + "마리");
			if(goblin_count >= 3)
			{
				System.out.println("****미션완료! 3번째 기술 해금****");
				MainRPGTextGame.three = true;
			}
		}
	}
}
