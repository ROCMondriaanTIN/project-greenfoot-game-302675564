import greenfoot.*;

/**
 *
 * @author R. Springer
 */
public class Hero extends Mover {

    private final double gravity;
    private final double acc;
    private final double drag;
    
    private int width;

    private boolean isOnGround;

    private int walkStatus;

    private int status = 0;

    private String direction = "right";
    
    public int lives = 1;


    public Hero() {
        super();
        gravity = 9.8;
        acc = 0.6;
        drag = 0.8;
        
        
        setImage("p2_stand.png");
    }

    @Override
    public void act() {
        handleInput();
        
        velocityX *= drag;
        velocityY += acc;
        if (velocityY > gravity) {
            velocityY = gravity;
        }
        applyVelocity();

        for (Actor enemy : getIntersectingObjects(Enemy.class)) {
            if (enemy != null) {
                dood();
                break;
            }
        }
    }
    
    public void dood() {
        
        lives--;
        if (lives > 0) {
            setLocation(200, 700);
        } else {
            getWorld().removeObject(this);
        }
    }
    private double posToNeg(double x) {

        return (x - (x * 2));

    }
        public void handleInput() {
        //gekregen van Gijs de Lange en zelf iets veranderd.
        width = getImage().getWidth() / 2;
        Tile tile = (Tile) getOneObjectAtOffset(0, getImage().getHeight() / 2 + 1, Tile.class);
        if (tile == null) {
            tile = (Tile) getOneObjectAtOffset(this.width - 3, getImage().getHeight() / 2 + 1, Tile.class);
        }
        if (tile == null) {
            tile = (Tile) getOneObjectAtOffset((int) posToNeg(this.width) + 3, getImage().getHeight() / 2 + 1, Tile.class);
        }
        if (tile != null && tile.isSolid) {
            isOnGround = true;
        } else {
            isOnGround = false;
        }
        if (Greenfoot.isKeyDown("space")) {
            if (isOnGround) {
                velocityY = -12;
                animationJump(getWidth(), getHeight(), 2);
            }
        }
        if (Greenfoot.isKeyDown("a")) {
            velocityX = -10;
            direction = "left";
            animationWalk(getWidth(), getHeight(), 2);
        } else if (Greenfoot.isKeyDown("d")) {
            velocityX = 10;
            direction = "right";
            animationWalk(getWidth(), getHeight(), 2);
        } else {
            animationStand(getWidth(), getHeight(), 2);
        }
    }
    public void animationWalk(int width, int heigth, int player) {
        if (status == 2) {
            if (walkStatus >= 11) {
                walkStatus = 1;
            }
            if (isOnGround) {
                setImage("p" + player + "_walk"
                        + walkStatus + ".png");
            } else {
                setImage("p" + player + "_jump.png");
            }
            mirror();
            walkStatus++;
            status = 0;
        } else {
            status++;
        }
        getImage().scale(width, heigth);
    }
    public void animationJump(int width, int heigth, int player) {
        setImage("p" + player + "_jump.png");
        mirror();
        getImage().scale(width, heigth);
    }
    public void animationStand(int width, int heigth, int player) {
        if (isOnGround) {
            setImage("p" + player + "_walk1.png");
            getImage().scale(width, heigth);
            walkStatus = 1;
        } else {
            setImage("p" + player + "_jump.png");
        }
        mirror();
        getImage().scale(width, heigth);
    }
    public void mirror() {
        if (direction.equals("left")) {

            getImage().mirrorHorizontally();

        }
    }public int getWidth() {
        return getImage().getWidth();
    }
    public int getHeight() {
        return getImage().getHeight();
    }
}

