import javax.swing.text.AbstractDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by zhewang711 on 7/2/16.
 */

class Contents{
    public int n;
    public double radius;
    public String [] planetNames;
    public double [] xpos;
    public double [] ypos;
    public double [] xvol;
    public double [] yvol;
    public double [] mass;

    public Contents(String path){
        try{
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String [] tmp;

            this.n = Integer.parseInt(br.readLine());
            this.radius = Double.parseDouble(br.readLine());

            this.xpos = new double[n];
            this.ypos = new double[n];
            this.xvol = new double[n];
            this.yvol = new double[n];
            this.mass = new double[n];
            this.planetNames = new String[n];

            int cnt = 0;
            while (cnt < this.n){
                tmp = br.readLine().split(" +");
                this.xpos[cnt] = Double.parseDouble(tmp[0]);
                this.ypos[cnt] = Double.parseDouble(tmp[1]);
                this.xvol[cnt] = Double.parseDouble(tmp[2]);
                this.yvol[cnt] = Double.parseDouble(tmp[3]);
                this.mass[cnt] = Double.parseDouble(tmp[4]);
                this.planetNames[cnt] = tmp[5];
                cnt += 1;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}

public class NBody {



//    private String path;
//    private Contents content;
//
//
//    private void cache(String path){
//        if (! path.equals(this.path)){
//            this.content = new Contents(path);
//        }
//    }

    public static double readRadius(String path){
        Contents content = new Contents(path);
        return content.radius;
    }

    public static Planet[] readPlanets(String path){
        Contents content = new Contents(path);
        Planet [] planets = new Planet[content.n];
        for (int i = 0; i < content.n; ++i){
            planets[i] = new Planet(content.xpos[i], content.ypos[i], content.xvol[i],
                    content.yvol[i], content.mass[i], content.planetNames[i]);
        }
        return planets;
    }

    public static void main(String [] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Contents contents = new Contents(filename);
        double radius = readRadius(filename);
        Planet [] planets = readPlanets(filename);

        StdDraw.setXscale(-radius, radius);
        StdDraw.setYscale(-radius, radius);
//        StdDraw.picture(0, 0, "images/starfield.jpg");
//        for (Planet p : planets){
//            StdDraw.picture(p.xxPos, p.yyPos, "images/" + p.imgFileName);
//        }

        double t = 0;
        double [] xForces = new double[contents.n];
        double [] yForces = new double[contents.n];

        while (t < T){
            //StdDraw.clear();
            StdDraw.show(20);
            for (int i = 0; i < planets.length; ++i){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; ++i){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets){
                StdDraw.picture(p.xxPos, p.yyPos, "images/" + p.imgFileName);
            }
            StdDraw.show();
            t += dt;
        }



    }

}
