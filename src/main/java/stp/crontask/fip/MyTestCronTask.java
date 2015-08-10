package stp.crontask.fip;

/**
 * Created by XIANGYANG on 2015-8-10.
 */
public class MyTestCronTask {
    public void process(){
        int count=0;
        System.out.println("任务被成功调起，SUCCESS！");
        while (true) {
            if (count < 5) {
                try {
                    Thread.sleep(10000);
                    System.out.println("正在运行第"+count+"次");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                break;
            }
            count++;
        }
    }
}
