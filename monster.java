import java.awt.Font;
import java.util.Random;


public class Monster implements Runnable
{
    public enum MonsterType {INVALID, SKELETON, ORC, BAT, SLIME, GREEN};

    private MonsterType type = MonsterType.INVALID;    // type of monster
    private int         x;                            // x location of monster
    private int         y;                            // y location of monster
    private int         sleepMs;                    // delay between times monster moves
    private int         hp;                            // hit points - damage sustained
    private int         damage;                        // damage monster causes
    private World         world;                        // the world the monster moves about in
    private Stats timer = null;                     // elapsed time for showing damage;

    
    public Monster(World world, String code, int x, int y, int hp, int damage, int sleepMs)
    {
        this.world        = world;
        this.x             = x;
        this.y             = y;
        this.hp            = hp;
        this.damage        = damage;
        this.sleepMs     = sleepMs;

        if      (code.toUpperCase().equals("SK")) type = MonsterType.SKELETON;
        else if (code.toUpperCase().equals("OR")) type = MonsterType.ORC;
        else if (code.toUpperCase().equals("BA")) type = MonsterType.BAT;
        else if (code.toUpperCase().equals("SL")) type = MonsterType.SLIME;
    }

   
    public void incurDamage(int points)
    {
        hp -= points;
        if (timer == null)
            timer = new Stats();
        timer.reset();
    }

 
    public void draw()
    {
        double drawX = (x + 0.5) * Tile.SIZE;
        double drawY = (y + 0.5) * Tile.SIZE;
        switch (type)
        {
            case SKELETON:    StdDraw.picture(drawX, drawY, "skeleton.gif");     break;
            case ORC:        StdDraw.picture(drawX, drawY, "orc.gif");         break;
            case BAT:        StdDraw.picture(drawX, drawY, "bat.gif");         break;
            case SLIME:        StdDraw.picture(drawX, drawY, "slime.gif");     break;
            case GREEN:        StdDraw.picture(drawX,drawY, "green.gif");        break;
            default:                                                         break;
        }

        if ((timer != null) && (timer.elapsedTime() < World.DISPLAY_DAMAGE_SEC))
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setFont(new Font("SansSerif", Font.BOLD, 12));
            StdDraw.text(drawX, drawY, "" + hp);
        }        
    }


    public int getHitPoints()
    {
        return hp;
    }


    public int getDamage()
    {
        return damage;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }


    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public void run() //Random Movement
    {

        Random rand = new Random();
        while (hp > 0)
        {
            try {
                Thread.sleep(this.sleepMs);
            } catch (InterruptedException e) {               
                e.printStackTrace();
            }
            int x = getX();
            int y = getY();
            int randX = rand.nextInt(2);
            int randY = rand.nextInt(2);
            int newX =0;
            int newY=0;
            if (randX == 0)
            newX = x-1;
            else
            newX = x+1;
            if (randY == 0)
            newY = y-1;
            else
            newY = y+1;
            this.world.monsterMove(newX, newY, this);
        }
    }
}

