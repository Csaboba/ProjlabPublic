package szkeleton;

import java.util.ArrayList;

/**
 * Entitások ősosztálya
 * A Game tárolja a listájukat, és egy aszteroidán tudnak tartózkodni
 */
public abstract class Entity implements Steppable {
    /**
     * Az a hely, ahol az entitás tartózkodik
     */
    protected Place place;

    /**
     * A játék
     */
    protected Game game;

    /**
     * Objektum neve
     */
    String name;

    /**
     * Entitás név szerinti konstruktora a tesztesetekhez.
     */
    public Entity(String n){
        this.name = n;
    }

    //inventory teszteléséhez
    public Entity(){}
    /**
     * Entitás konstruktora.
     */
    public Entity(String n, Game g, Place p) {
    	this.name = n;
    	this.game = g;
    	this.place = p;
    	p.AcceptEntity(this);
    }

    /**
     * Szomszédos aszteroidára áthelyezi az entity-t.
     */
    public abstract void Move(int asteroidID);

    /**
     * Az aszteroidán elvégezhető műveleteket kezeli
     */
    public abstract void Action();

    /**
     * Aszteroidán való fúrást kezeli
     */
    public void Drill(){
        Asteroid a = (Asteroid)place;
        a.ReduceRockLayer();
    }

    /**
     *  Nyersanyagok listájának frissítése
     */
    public ArrayList<Integer> UpdateResourceList(ArrayList<Integer> l){ return l; }

    public abstract void Die();
    public abstract void BlownUp();

    public void SetPlace(Place p) {this.place = p;}
    public void SetGame(Game g) {this.game = g;}
    public Place GetPlace() {return this.place;}
    public Game GetGame() {return  this.game;}
    
    public String getName() {return this.name;}
}
