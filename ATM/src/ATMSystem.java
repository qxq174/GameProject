import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ATMSystem {
    //1.定义一个账户类

    //2.定义一个集合容器，负责以后存储的全部账户内容，进行相关的业务操作

    ArrayList<Account> accounts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    //3.展示系统的首页
     while (true) {
        System.out.println("=================ATM系统=====================");
        System.out.println("1.账户登录");
        System.out.println("2.账户开户");

        System.out.println("请您选择操作：");
        int command = sc.nextInt();

        switch (command) {
            case 1:
                //用户登录操作

                break;

            case 2:
                //用户账户开户
                register(accounts, sc);

                break;

            default:
                System.out.println("您输入的操作命令不存在！");
        }
    }

    private static void register(ArrayList<Account> accounts, Scanner sc) {
        System.out.println("=====================系统开户操作=======================");

        //1.创建一个账户对象，用于后期封装对象账户信息。
        Account account = new Account();

        //2.录入当前这个账户的信息，并注入到账户对象中去
        System.out.println("请输入您的账户用户名：");
        String userName = sc.next();
        account.setUserName(userName);

        while (true) {
            System.out.println("请输入您的账户密码：");
            String password = sc.next();
            System.out.println("请再次输入您的账户密码：");
            String okpassWord = sc.next();
            if (password.equals(okpassWord)) {
                account.setPassWord(password);
                break;//密码已经录入成功
            } else {
                System.out.println("您输入的2次密码不一致，请重新输入！");
            }
        }

        System.out.println("请输入账户每次取现额度：");
        double quotaMoney = sc.nextDouble();
        account.setQuotaMoney(quotaMoney);

        //为账户随机一个8位并且与其它账户不重复的号码。（独立成方法）
        String cardId = getRandomCardId(accounts);
        account.setCardId(cardId);


        //3.把账户对象添加到账户集合中去
        accounts.add(account);
        System.out.println("恭喜"+account.getUserName()+"先生/女士，开户成功，您的卡号为："+account.getCardId());



    }

    /**
     * 为账户生成8位并且不重复的号码
     * @param accounts 传过来的arraylist
     * @return
     */
    private static String getRandomCardId(ArrayList<Account> accounts) {
        //1.先生成8位数字
        String cardId = "";
        Random r = new Random();
        for(int i = 0; i < 8; i++){
            cardId += r.nextInt(10);//每次随机生成一位数，一共循环8次
        }
        //2.判断这个8位的卡号是否与其它卡号重复了
        Account acc= getAccountByCardId(cardId, accounts);
        if(acc == null){//说明cardID并没有重复
            return cardId;
        }

    }


    /**
     * 根据卡号查询出一个账户对象来
     * @param cardId 卡号
     * @param accounts arraylist集合
     * @return 返回账户对象或者null
     */
    private static Account getAccountByCardId(String cardId,ArrayList<Account> accounts){
        for(int i = 0; i < accounts.size(); i++){
            Account acc = new Account();
            if(acc.getCardId().equals(cardId)){
                return acc;
            }
        }
        return null; //如果没有重复就返回nul，空
    }

}