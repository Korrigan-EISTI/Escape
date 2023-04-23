package main.java.entity;

import main.java.Input;
import main.java.Map;

public class PhysicalEntity extends Entity{
    private final double w;
    private final double h;
    private boolean on_ground;
    public PhysicalEntity(double x, double y, double vx, double vy, double w, double h) {
        super(x, y, vx, vy);
        this.w = w;
        this.h = h;
        on_ground=false;
    }

    @Override
    public void tick(Map map) {
        on_ground=false;
        if(vx<0){
            int check_x = (int)Math.floor(x);
            int end_x = (int)Math.floor(x+vx);
            int start_y = (int)Math.floor(y);
            int end_y = (int)Math.ceil(y+h)-1;
            boolean hit = false;
            while (check_x>end_x && !hit){
                check_x-=1;
                for (int i = start_y;i<=end_y;i++){
                    hit = hit || Map.BLOCK_PROPERTIES.get(map.getBlock(check_x,i)).solid();
                }
            }
            if(hit){
                vx=0;
                x=check_x+1;
            }
            else{
                x+=vx;
            }
        }
        else {
            int check_x = (int)Math.ceil(x+w)-1;
            int end_x = (int)Math.floor(x+w+vx);
            int start_y = (int)Math.floor(y);
            int end_y = (int)Math.ceil(y+h)-1;
            boolean hit = false;
            while (check_x<end_x && !hit){
                check_x+=1;
                for (int i = start_y;i<=end_y;i++){
                    hit = hit || Map.BLOCK_PROPERTIES.get(map.getBlock(check_x,i)).solid();
                }
            }
            if(hit){
                vx=0;
                x=check_x-w;
            }
            else{
                x+=vx;
            }
        }
        if(vy<0){
            int check_y = (int)Math.floor(y);
            int end_y = (int)Math.floor(y+vy);
            int start_x = (int)Math.floor(x);
            int end_x = (int)Math.ceil(x+w)-1;
            boolean hit = false;
            while (check_y>end_y && !hit){
                check_y-=1;
                for (int i = start_x;i<=end_x;i++){
                    hit = hit || Map.BLOCK_PROPERTIES.get(map.getBlock(i,check_y)).solid();
                }
            }
            if(hit){
                on_ground=true;
                vy=0;
                y=check_y+1;
            }
            else{
                y+=vy;
            }
        }
        else {
            int check_y = (int)Math.ceil(y+h)-1;
            int end_y = (int)Math.floor(y+h+vy);
            int start_x = (int)Math.floor(x);
            int end_x = (int)Math.ceil(x+w)-1;
            boolean hit = false;
            while (check_y<end_y && !hit){
                check_y+=1;
                for (int i = start_x;i<=end_x;i++){
                    hit = hit || Map.BLOCK_PROPERTIES.get(map.getBlock(i,check_y)).solid();
                }
            }
            if(hit){
                vy=0;
                y=check_y-h;
            }
            else{
                y+=vy;
            }
        }
        vy-=0.02;
        vx*=0.7;
    }

    public boolean isOnGround() {
        return on_ground;
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }
}
