package main.java.entity;

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
            int check_x = (int)Math.floor(x/24);
            int end_x = (int)Math.floor((x+vx)/24);
            int start_y = (int)Math.floor(y/24);
            int end_y = (int)Math.ceil((y+h)/24)-1;
            boolean hit = false;
            while (check_x>end_x && !hit){
                check_x-=1;
                for (int i = start_y;i<=end_y;i++){
                    hit = hit || map.getBlock(check_x,i)!=0;
                }
            }
            if(hit){
                vx=0;
                x=(check_x+1)*24;
            }
            else{
                x+=vx;
            }
        }
        else {
            int check_x = (int)Math.ceil((x+w)/24)-1;
            int end_x = (int)Math.floor((x+w+vx)/24);
            int start_y = (int)Math.floor(y/24);
            int end_y = (int)Math.ceil((y+h)/24)-1;
            boolean hit = false;
            while (check_x<end_x && !hit){
                check_x+=1;
                for (int i = start_y;i<=end_y;i++){
                    hit = hit || map.getBlock(check_x,i)!=0;
                }
            }
            if(hit){
                vx=0;
                x=check_x*24-w;
            }
            else{
                x+=vx;
            }
        }
        if(vy<0){
            int check_y = (int)Math.floor(y/24);
            int end_y = (int)Math.floor((y+vy)/24);
            int start_x = (int)Math.floor(x/24);
            int end_x = (int)Math.ceil((x+w)/24)-1;
            boolean hit = false;
            while (check_y>end_y && !hit){
                check_y-=1;
                for (int i = start_x;i<=end_x;i++){
                    hit = hit || map.getBlock(i,check_y)!=0;
                }
            }
            if(hit){
                on_ground=true;
                vy=0;
                y=(check_y+1)*24;
            }
            else{
                y+=vy;
            }
        }
        else {
            int check_y = (int)Math.ceil((y+h)/24)-1;
            int end_y = (int)Math.floor((y+h+vy)/24);
            int start_x = (int)Math.floor(x/24);
            int end_x = (int)Math.ceil((x+w)/24)-1;
            boolean hit = false;
            while (check_y<end_y && !hit){
                check_y+=1;
                for (int i = start_x;i<=end_x;i++){
                    hit = hit || map.getBlock(i,check_y)!=0;
                }
            }
            if(hit){
                vy=0;
                y=check_y*24-h;
            }
            else{
                y+=vy;
            }
        }
        vy-=0.3;
        vx*=0.7;
    }

    public boolean isOnGround() {
        return on_ground;
    }
}
