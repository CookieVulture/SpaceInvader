package invadem;

public class AppTest2 extends App{
    public static App lastIns;
    public AppTest2(int i){
        super();
        lastIns = this;
        setGameState(i);

    }
}
