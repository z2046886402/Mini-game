import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Frame extends JFrame implements KeyListener {

    //数据
    int[][] data = {
            {1,2,3,4},
            {5,6,7,8},
            {9,10,11,12},
            {13,14,15,0}
    };
    int row;int column;//0号空位置坐标

    //窗口加载
    public Frame(){
        this.addKeyListener(this);
        initFrame();//初始化窗口
        initDate();//初始化数据
        paintView();//绘制界面
        setVisible(true);//显示窗口
    }
    //初始化窗口
    public void initFrame(){
        setSize(514,595);//窗口大小
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//关闭方式
        setTitle("Mini-game");//窗口标题
        setAlwaysOnTop(true);//置顶
        setLocationRelativeTo(null);//居中
        setLayout(null);//取消窗口默认布局
    }
    //数据初始化
    public void initDate(){
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int randomX = r.nextInt(4);
                int randomY = r.nextInt(4);
                int temp = data[i][j];
                data[i][j]=data[randomX][randomY];
                data[randomX][randomY] = temp;
            }
        }

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if(data[i][j]==0){
                    row = i;column = j;
                }
            }
        }
    }
    //绘制界面
    public void paintView(){
        super.getContentPane().removeAll();
        if(victory()){
            JLabel label = new JLabel(new ImageIcon("image\\win.png"));
            label.setBounds(180,230,150,147);
            getContentPane().add(label);
        }
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                JLabel imageLable= new JLabel(new ImageIcon("image\\"+data[i][j]+".png"));
                imageLable.setBounds(50+100*j,90+100*i,100,100);
                getContentPane().add(imageLable);
            }
        }
        JLabel backgroudLable = new JLabel(new ImageIcon("image\\background.png"));
        backgroudLable.setBounds(26,48,450,484);
        getContentPane().add(backgroudLable);
        super.getContentPane().repaint();
    }
    //胜利判断
    public boolean victory(){
        int[][] win = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,0}
        };
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                if(data[i][j]!=win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    //移动方块
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();//接收键盘输入
        Move(keyCode);
        paintView();//重绘窗口
    }
    private void Move(int keyCode) {
        if(keyCode == 37){
            if (column == 3) {
                return;
            }
            int temp = data[row][column];
            data[row][column] = data[row][column+1];
            data[row][column+1] = temp;
            column++;
        }else if(keyCode == 38){
            if (row == 3) {
                return;
            }
            int temp = data[row][column];
            data[row][column] = data[row+1][column];
            data[row+1][column] = temp;
            row++;
        } else if (keyCode == 39) {
            if (column == 0) {
                return;
            }
            int temp = data[row][column];
            data[row][column] = data[row][column-1];
            data[row][column-1] = temp;
            column--;
        } else if (keyCode == 40) {
            if (row == 0) {
                return;
            }
            int temp = data[row][column];
            data[row][column] = data[row-1][column];
            data[row-1][column] = temp;
            row--;
        }else if(keyCode == 90){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {

    }
}
