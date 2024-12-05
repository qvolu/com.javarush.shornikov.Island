package simulator;

public class lifeCheck extends Exception {
    //Исключение, для объектов, которые не является формой жизни.
    public lifeCheck(String msg) {
        super(msg);
    }
}
