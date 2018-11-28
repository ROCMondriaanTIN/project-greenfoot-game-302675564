import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Menu extends World
{

    
    public Menu()
    {    
        
        super(600, 400, 1); 
        
           
        
        Greenfoot.start();
    }
    @Override
    public void act()
    {
        if (Greenfoot.isKeyDown("space")) Greenfoot.setWorld(new MyWorld());
    }
    
    public void startknop()
    {
        
        //setImage("Start.png");
    }
}


        
    
