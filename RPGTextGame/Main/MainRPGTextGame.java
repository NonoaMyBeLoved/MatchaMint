package Main;
import java.util.Scanner;
import Character.*;
import Map.*;


public class MainRPGTextGame 
{
	static Hero hero = null;
	static String monster_name;
	static boolean warrior = false, wizard =false, archor = false;
	static boolean fail = false;
	static public boolean three = false;
	static public boolean specialWeapon = false;

	public static void main(String[] args) 
	{		
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);		
		System.out.println("********RPG GAME********");
		System.out.println("1. 전사");
		System.out.println("2. 마법사");
		System.out.println("3. 궁수");
		System.out.print("직업을 선택하세요 : ");
		int select = in.nextInt();
		while(true)
		{
			if(select == 1)
			{
				System.out.println("전사를 선택했어요");
				hero = new Warrior();
				warrior = true;
				break;
			}				
			else if(select == 2)
			{
				System.out.println("마법사를 선택했어요");
				hero = new Wizard();
				wizard = true;
				break;
			}
			else if(select == 3)
			{
				System.out.println("궁수를 선택했어요");
				hero = new Archor();
				archor = true;
				break;
			}
			else
			{
				System.out.println("다시 선택하세요.");
			}
		}
		System.out.print("영웅의 이름을 입력하세요 : ");
		String name = in.next();
		hero.name = name;
		System.out.println("영웅의 이름이 입력되었어요.");
		System.out.println("게임에 입장합니다.");
		while(true)
		{
			System.out.println("********************");
			System.out.println("현재" + hero.name + "의 이름 : " + hero.name);
			System.out.println("현재" + hero.name + "의 레벨 : " + hero.level);
			System.out.println("현재" + hero.name + "의 힘 : " + hero.power);
			System.out.println("현재" + hero.name + "의 방여력 : " + hero.defense);
			System.out.println("현재" + hero.name + "의 HP : " + hero.hp);
			System.out.println("현재" + hero.name + "의 MP : " + hero.mp);
			System.out.println("현재" + hero.name + "의 경험치 : " + hero.experience);
			System.out.println("현재" + hero.name + "의 돈 : " + hero.money);
			System.out.println("********************");
			
			System.out.println("1. 사냥터");
			System.out.println("2. 포션 상점");
			System.out.println("3. 무기 상점");
			System.out.println("4. 인벤토리");
			System.out.print("갈 곳을 선택하세요 : ");
			int place = in.nextInt();
			if(place == 1)
			{				
				System.out.println("사냥터에 입장했어요.");
				System.out.println("1. 슬라임");
				System.out.println("2. 고블린");
				System.out.println("3. 오크");
				System.out.println("4. 황소");
				System.out.print("전투할 상대를 입력하세요 : ");
				int mob = in.nextInt();
				if(mob == 1)
				    battle(new Slime(), "슬라임");
				else if(mob == 2)
				    battle(new Goblin(), "고블린");
				else if(mob == 3)
				    battle(new Orc(), "오크");
				else if(mob == 4)
					battle(new Taurus(), "황소");
				else
					System.out.println("잘못된 입력이에요.");
				
				if(fail)
				{
					System.out.println("게임 오버");
					break;
				}
			}
			else if(place == 2)
			{
				potionStore.Store(hero);
			}
			else if(place == 3)
			{
				weaponStore.Store(hero);
			}
			else if(place == 4)
			{
				Hero.inven();
			}
			if(hero.level == 7)
			{
				System.out.println("7레벨 달성! 게임을 종료합니다");
				break;				
			}
		}
	}
	
	public static void battle(Monster monster, String monster_name)
	{
		Scanner in = new Scanner(System.in);
		System.out.println(monster_name + "(을)를 잡자!");
		if(warrior)
		{
			while(monster.hp >= 0 && hero.hp >= 0)
			{
				System.out.println(hero.name + "의 턴");
				System.out.println("1. 베기");
				System.out.println("2. 몸통박치기");
				if(three) System.out.println("3. 저지불가");
				else System.out.println("3. ???");
				System.out.print("공격 선택 (다른 숫자 입력 시 턴 스킵) : ");
				int attack = in.nextInt();						
				switch(attack)
				{
				case 1 :
					System.out.println(hero.name + "의 베기!");
					if(hero.mp >= 3)
					{
						int cut = hero.attack();
						System.out.println(cut + "의 데미지를 입혔다.");
						monster.attacked(cut);
						hero.mp -= 3;
						break;
					}
					else
					{
						System.out.println("마나가 없어 벨 수 없었다!");
						break;
					}
				case 2 :
					if(hero.mp >= 5)
					{
						System.out.println(hero.name + "의 몸통박치기!");
						int body = ((Warrior)hero).hyungjoon();
						System.out.println(body + "의 데미지를 입혔다.");
						monster.attacked(body);
						if(monster_name.equals("고블린"))
						{
							System.out.println("조그만한 고블린에겐 치명적이었다!");
							System.out.println((body/2) + "의 추가피해");
							monster.attacked(body/2);
						}
						hero.mp -= 5;
						break;
					}
					else
					{
						System.out.println("마나가 없어 몸통이 안 움직인다!");
						break;
					}
				case 3 :
					if(hero.mp >= 15 && three)
					{
						System.out.println(hero.name + "의 저지불가!");
						int ult = ((Warrior)hero).unstoppable();
						if(specialWeapon) ult *= 2;
						System.out.println(ult + "의 데미지를 입혔다.");
						monster.attacked(ult);
						hero.mp -= 15;
						break;
					}
					else
					{
						System.out.println("효과가 없었다..");
						break;
					}
				default :
					System.out.println("턴을 건너뜁니다.");
				}
				if(monster.hp <= 0)
				{
					System.out.println(monster_name + "(이)가 죽었습니다.");
					hero.experience += monster.experience;
					hero.money += monster.money;
					switch(monster_name)
					{
					case "슬라임" -> Hero.eyes++;
					case "고블린" -> Hero.knife++;
					case "오크" -> Hero.tooth++;
					case "황소" -> Hero.horn++;
					}
					hero.levelup();
					if(monster_name.equals("고블린"))
						hero.goblinMission();
					break;
				}
				System.out.println(monster_name + "의 공격!");
				System.out.println(monster.attack() + "의 데미지를 받았다.");
				hero.attacked(monster.attack());
				if(hero.hp <= 0)
				{
					System.out.println("죽었습니다!");
					fail = true;
					break;
				}							
			}
		}
		
		else if(wizard)
		{
			while(monster.hp >= 0 && hero.hp >= 0)
			{
				System.out.println(hero.name + "의 턴");
				System.out.println("1. 마법(물리)");
				System.out.println("2. 졸트라크");
				if(three) System.out.println("3. 아제리유제");
				else System.out.println("3. ???");
				System.out.print("공격 선택 (다른 숫자 입력 시 턴 스킵) : ");
				int attack = in.nextInt();						
				switch(attack)
				{
				case 1 :
					if(hero.mp >= 1)
					{
						System.out.println(hero.name + "의 마법(물리)!");
						int ad = hero.attack();
						System.out.println(ad + "의 데미지를 입혔다.");
						monster.attacked(ad);
						hero.mp -= 1;
						break;
					}
					else
					{
						System.out.println("마나가 없어 휘두를 수 없다!");
						break;
					}
				case 2 :
					if(hero.mp >= 10)
					{
						System.out.println(hero.name + "의 졸트라크!");
						int ap = ((Wizard)hero).zoltraak();
						System.out.println(ap + "의 데미지를 입혔다.");
						monster.attacked(ap);
						if(monster_name.equals("오크"))
						{
							System.out.println("묵직한 오크에게 원거리 졸트라크는 상성이 좋았다!");
							System.out.println(ap/2 + "의 추가피해");
							monster.attacked(ap/2);
						}
						hero.mp -= 10;
						break;
					}
					else
					{
						System.out.println("마나가 없어 사용하지 못했다!");
						break;
					}
				case 3:
					if(hero.mp >= 20 && three)
					{
						System.out.println(hero.name + "의 아제리유제!");
						int ap = ((Wizard)hero).auserlese();
						if(specialWeapon) ap *= 2;
						System.out.println(ap + "의 데미지를 입혔다.");
						monster.attacked(ap);
						hero.mp -= 20;
						break;
					}
					else
					{
						System.out.println("효과가 없었다..");
						break;
					}
				default :
					System.out.println("턴을 건너뜁니다.");
				}
				if(monster.hp <= 0)
				{
					System.out.println(monster_name + "(이)가 죽었습니다.");
					hero.experience += monster.experience;
					hero.money += monster.money;
					switch(monster_name)
					{
					case "슬라임" -> Hero.eyes++;
					case "고블린" -> Hero.knife++;
					case "오크" -> Hero.tooth++;
					case "황소" -> Hero.horn++;
					}
					hero.levelup();
					if(monster_name.equals("고블린"))
						hero.goblinMission();
					break;
				}
				System.out.println(monster_name + "의 공격!");
				System.out.println(monster.attack() + "의 데미지를 받았다.");
				hero.attacked(monster.attack());
				if(hero.hp <= 0)
				{
					System.out.println("죽었습니다!");
					fail = true;
					break;
				}
			}
		}
		
		else if(archor)
		{
			while(monster.hp >= 0 && hero.hp >= 0)
			{
				System.out.println(hero.name + "의 턴");
				System.out.println("1. 저격");
				System.out.println("2. 어썰트");
				if(three) System.out.println("3. 페어리 턴");
				else System.out.println("3. ???");
				System.out.print("공격 선택 (다른 숫자 입력 시 턴 스킵) : ");
				int attack = in.nextInt();						
				switch(attack)
				{
				case 1 :
					if(hero.mp >= 1)
					{
						System.out.println(hero.name + "의 저격!");
						int arrow = hero.attack();
						System.out.println(arrow + "의 데미지를 입혔다.");
						monster.attacked(arrow);
						hero.mp -= 1;
						break;
					}
					else
					{
						System.out.println("마나가 없어 쏘지 못했다!");
						break;
					}
				case 2 :
					if(hero.mp >= 10)
					{
						System.out.println(hero.name + "의 어썰트!");
						int ass = ((Archor)hero).assault();
						System.out.println(ass + "의 데미지를 입혔다.");
						monster.attacked(ass);
						if(monster_name.equals("황소"))
						{
							System.out.println("오늘 저녁은 화살로 다진 소고기~");
							System.out.println(ass/2 + "의 추가피해");
							monster.attacked(ass/2);
						}
						hero.mp -= 10;
						break;
					}
					else
					{
						System.out.println("마나가 없어 연기만 나갔다!");
						break;
					}
				case 3:
					if(hero.mp >= 15 && three)
					{
						System.out.println(hero.name + "의 페어리 턴!");
						int fry = ((Archor)hero).fairy();
						if(specialWeapon) fry *= 2;
						System.out.println(fry + "의 데미지를 입혔다.");
						monster.attacked(fry); 
						hero.mp -= 15;
						break;
					}
					else
					{
						System.out.println("효과가 없었다..");
						break;
					}
				default :
					System.out.println("턴을 건너뜁니다.");
				}
				if(monster.hp <= 0)
				{
					System.out.println(monster_name + "(이)가 죽었습니다.");
					hero.experience += monster.experience;
					hero.money += monster.money;
					switch(monster_name)
					{
					case "슬라임" -> Hero.eyes++;
					case "고블린" -> Hero.knife++;
					case "오크" -> Hero.tooth++;
					case "황소" -> Hero.horn++;
					}
					hero.levelup();
					if(monster_name.equals("고블린"))
						hero.goblinMission();
					break;
				}
				System.out.println(monster_name + "의 공격!");
				System.out.println(monster.attack() + "의 데미지를 받았다.");
				hero.attacked(monster.attack());
				if(hero.hp <= 0)
				{
					System.out.println("죽었습니다!");
					fail = true;
					break;
				}
			}
		}
	}
	
	
}
