package main.java.entity;

import main.java.Environment;

import java.util.Arrays;

public abstract class PhysicalEntity extends Entity{
	

    protected double w;
    protected double h;
    protected boolean on_ground;
    protected short[] touched;
    private int last_shot;
    
    public PhysicalEntity(double x, double y, double vx, double vy, double w, double h) {
        super(x, y, vx, vy);
        this.w = w;
        this.h = h;
        this.on_ground = false;
        this.touched = new short[(int)(Math.ceil(w+1)*Math.ceil(h+10))];
    }

    public int getLast_shot() {
		return last_shot;
	}

	public void setLast_shot(int last_shot) {
		this.last_shot = last_shot;
	}

	@Override
    public void tick(Environment environment) {

    	setLast_shot(getLast_shot()-1);
        on_ground=false;
        if(vx<0){
            int scan_x = (int)Math.floor(x);
            int end_x = (int)Math.floor(x+vx);
            int start_y = (int)Math.floor(y);
            int end_y = (int)Math.ceil(y+h)-1;
            boolean hit = false;
            while (scan_x>end_x && !hit){
            	scan_x-=1;
                for (int i = start_y;i<=end_y;i++){
                    hit = hit || Environment.BLOCK_PROPERTIES.get(environment.getBlock(scan_x,i)).solid()==1 || (Environment.BLOCK_PROPERTIES.get(environment.getBlock(scan_x,i)).solid()==2 && !environment.getPlayer().canWalkThroughMagicWalls());
                }
            }
            if(hit){
                vx=0;
                x=scan_x+1;
            }
            else{
                x+=vx;
            }
        }
        else {
            int scan_x = (int)Math.ceil(x+w)-1;
            int end_x = (int)Math.floor(x+w+vx);
            int start_y = (int)Math.floor(y);
            int end_y = (int)Math.ceil(y+h)-1;
            boolean hit = false;
            while (scan_x<end_x && !hit){
                scan_x+=1;
                for (int i = start_y;i<=end_y;i++){
                	hit = hit || Environment.BLOCK_PROPERTIES.get(environment.getBlock(scan_x,i)).solid()==1 || (Environment.BLOCK_PROPERTIES.get(environment.getBlock(scan_x,i)).solid()==2 && !environment.getPlayer().canWalkThroughMagicWalls());
                }
            }
            if(hit){
                vx=0;
                x=scan_x-w;
            }
            else{
                x+=vx;
            }
        }
        if(vy<0){
            int scan_y = (int)Math.floor(y);
            int end_y = (int)Math.floor(y+vy);
            int start_x = (int)Math.floor(x);
            int end_x = (int)Math.ceil(x+w)-1;
            boolean hit = false;
            while (scan_y>end_y && !hit){
                scan_y-=1;
                for (int i = start_x;i<=end_x;i++){
                	hit = hit || Environment.BLOCK_PROPERTIES.get(environment.getBlock(i,scan_y)).solid()==1 || (Environment.BLOCK_PROPERTIES.get(environment.getBlock(i,scan_y)).solid()==2 && !environment.getPlayer().canWalkThroughMagicWalls());
                }
            }
            if(hit){
                on_ground=true;
                vy=0;
                y=scan_y+1;
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
                	hit = hit || Environment.BLOCK_PROPERTIES.get(environment.getBlock(i,check_y)).solid()==1 || (Environment.BLOCK_PROPERTIES.get(environment.getBlock(i,check_y)).solid()==2 && !environment.getPlayer().canWalkThroughMagicWalls());
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
        int bottom_x = (int)Math.floor(x);
        int top_x = (int)Math.ceil(x+w)-1;
        int bottom_y = (int)Math.floor(y);
        int top_y = (int)Math.ceil(y+h)-1;
        Arrays.fill(touched, (short) 0);
        int i=0;
        for(int j=bottom_x;j<=top_x;j++) {
            for (int k = bottom_y; k <= top_y; k++) {
                touched[i]=environment.getBlock(j,k);
                i++;
            }
        }
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

    @Override
    public double getImageSizeX(){
        return w;
    }
    @Override
    public double getImageSizeY(){
        return h;
    }
}
