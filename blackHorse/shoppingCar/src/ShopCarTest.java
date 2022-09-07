import java.util.Scanner;

public class ShopCarTest {

    public static void main(String[] args) {

        Good[] shopCar = new Good[100];

        while(true){
            System.out.println("请您选择如下命令进行操作");
            System.out.println("添加商品到购物车：add");
            System.out.println("查询商品到购物车：query");
            System.out.println("修改商品购买数量： update");
            System.out.println("结算购买商品的金额：pay");
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您的命令：");
            String command = sc.next();
            switch(command){
                case "add":
                    addGoods(shopCar, sc);
                    break;

                case "query":

                    queryGoods(shopCar);
                    break;

                case "update":
                    updateGoods(shopCar);

                    break;

                case "pay":
                    payGoods(shopCar);
                    break;

                default:
                    System.out.println(" 没有该功能！");
            }
        }


    }

    public static void payGoods(Good[] shopCar) {

    }

    public static void updateGoods(Good[] shopCar) {

    }

    public static void queryGoods(Good[] shopCar) {
        System.out.println("==================查询购物车信息如下================================");
        System.out.println("编号 \t\t名称\t\t\t价格\t\t\t购买数量");

        for(int i = 0; i < shopCar.length; i++){
            Good g = shopCar[i];// 拿到商品的地址
            if(g != null){
                System.out.println(g.id + "\t\t" + g.name+"\t\t\t" + g.price +"\t\t\t" + g.buyNumber);
            }else{
                break;
            }
        }
    }

    public static void addGoods(Good[] shopCar, Scanner sc) {
        // 完成商品的录入,录入用户商品购买的信息
        System.out.println("请您输入购买商品的编号：");
        int id = sc.nextInt();
        System.out.println("请你输入购买商品的名称：");
        String name = sc.next();
        System.out.println("请您输入购买商品的数量：");
        int buyNumber = sc.nextInt();
        System.out.println("请您输入购买商品的价格：");
        double price = sc.nextDouble();

        //把这些购买商品的信息封装成一个商品对象
        Good g = new Good();
        g.id = id;
        g.name = name;
        g.buyNumber = buyNumber;
        g.price = price;

        //把这个商品对象添加到购物车数组中去
        for (int i = 0; i< shopCar.length; i++){
            if(shopCar[i] == null){
                shopCar[i] = g;
                break;
            }
        }
        System.out.println("您的商品"+g.name+"已经添加到购物车了。");
    }
}
