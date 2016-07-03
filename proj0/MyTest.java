/**
 * Created by zhewang711 on 6/29/16.
 */



public class MyTest {

    public static void test1(){
        System.out.println("test1:");
        Planet sun = new Planet(1.0e12, 2.0e11, -1, -1, 2.0e30, "");
        Planet saturn = new Planet(2.3e12, 9.5e11, -1, -1, 6.0e26, "");
        System.out.println("F1X, F1Y: " + sun.calcForceExertedByX(saturn) + ", " + sun.calcForceExertedByY(saturn));
        System.out.println("F2X, F2Y: " + saturn.calcForceExertedByX(sun) + ", " + saturn.calcForceExertedByY(sun));
    }

    public static void test2(){
        System.out.println("test1:");
        Planet Samh = new Planet(1, 0, -1, -1, 10, "");
        Planet AEgir = new Planet(3, 3, -1, -1, 5, "");
        Planet Rocinante = new Planet(5, -3, -1, -1, 50, "");


        System.out.println("Net force on Samh: " +
                "x direction: " + String.valueOf(Samh.calcForceExertedByX(AEgir) + Samh.calcForceExertedByX(Rocinante)) +
                " y direction: " + String.valueOf(Samh.calcForceExertedByY(AEgir) + Samh.calcForceExertedByY(Rocinante))
        );

    }

    public static void test3(){
        System.out.println("test3, testing calcForceExertedBy");
        Planet Samh = new Planet(1, 0, -1, -1, 10, "");
        Planet Rocinante = new Planet(5, -3, -1, -1, 50, "");
        System.out.println("expected 1.334E-9, give " + String.valueOf(Samh.calcForceExertedBy(Rocinante)));

    }

    public static void main(String [] args){
        test1();
        test2();
        test3();


    }
}
