/**
 * Created by zhewang711 on 6/28/16.
 */


public class Planet {
    double xxPos;   // current x position
    double yyPos;   // current y position
    double xxVel;   // current velocity in X direction
    double yyVel;   // current velocity in Y direction
    double mass;    // its mass
    String imgFileName;     // Name of image in `image` directory that depicts the planet

//    private double distance(Planet other){
//        return Math.sqrt((this.xxPos - other.xxPos)*(this.xxPos - other.xxPos) + (this.yyPos - other.yyPos)*(this.yyPos - other.yyPos));
//    }
//
//    private double force(Planet other){
//        double r =  this.distance(other);
//        return (6.67e-11) * this.mass * other.mass / (r*r);
//    }

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;

    }

    public Planet (Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    private double calcForceExertedByXorY(Planet other, boolean X){
        double r = Math.sqrt((this.xxPos - other.xxPos)*(this.xxPos - other.xxPos) + (this.yyPos - other.yyPos)*(this.yyPos - other.yyPos));
        double F = (6.67e-11) * this.mass * other.mass / (r*r);
        if (X){
            double dx = other.xxPos - this.xxPos;
            return F * dx / r;
        }
        else{
            double dy = other.yyPos - this.yyPos;
            return F * dy / r;
        }
    }

    /* compute the X direction force that `other` exert on `this`*/
    public double calcForceExertedByX(Planet other){
        return calcForceExertedByXorY(other, true);
    }

    public double calcForceExertedByY(Planet other){
        return calcForceExertedByXorY(other, false);
    }

    public double calcDistance(Planet other){
        double deltaX = this.xxPos - other.xxPos;
        double deltaY = this.yyPos - other.yyPos;
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    public double calcForceExertedBy(Planet other){
        double r = Math.sqrt((this.xxPos - other.xxPos)*(this.xxPos - other.xxPos) + (this.yyPos - other.yyPos)*(this.yyPos - other.yyPos));
        return (6.67e-11) * this.mass * other.mass / (r*r);
    }

    public double calcNetForceExertedByX(Planet [] allPlanets){
        double res = 0;
        for (Planet p : allPlanets){
            if (! p.equals(this)){
                res += this.calcForceExertedByX(p);
            }
        }
        return res;
    }

    public double calcNetForceExertedByY(Planet [] allPlanets){
        double res = 0;
        for (Planet p : allPlanets){
            if (! p.equals(this)){
                res += this.calcForceExertedByY(p);
            }
        }
        return res;
    }

    public void update(double dt, double fX, double fY){
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += dt * aX;
        this.yyVel += dt * aY;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }



}
