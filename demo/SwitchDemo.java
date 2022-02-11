import java.util.Scanner;
/**
* *@Author: Xu1Aan
* @Description: java 12 switch新用法
*/
public class SwitchDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入剪刀、石头、布中任意一个：");
        String player = scanner.nextLine();
        String arr[] = {"剪刀","石头","布" };
        String computer = arr[(int)Math.random() * 3];
        System.out.println("玩家："+player+",电脑："+computer);
        switch (player){
            case "剪刀" -> System.out.println(player.equals(computer) ? "平局" : (computer.equals("石头") ? "电脑胜":"玩家胜"));
            case "石头" -> System.out.println(player.equals(computer) ? "平局" : (computer.equals("布") ? "电脑胜":"玩家胜"));
            case "布" -> System.out.println(player.equals(computer) ? "平局" : (computer.equals("剪刀") ? "电脑胜":"玩家胜"));
            default -> System.out.println("\n输入参数有误！");
        }
    }
}
