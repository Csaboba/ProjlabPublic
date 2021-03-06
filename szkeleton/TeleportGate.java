package szkeleton;

import java.util.ArrayList;

/**
 * Class representing a teleport gate
 */
public class TeleportGate extends Place {
    private boolean pairIsPlaced; // whether its pair is placed
    private TeleportGate pair; // its pair
    private boolean isCrazy = false; // happens after it got hit by solar storm
    private TeleportView teleportView;
    /**
     * create a teleport gate with name, unique id, map
     */
    public TeleportGate(String name, int id, Map m){
        super(name, id, m);
        pairIsPlaced = false;
        pair = null;
    }

    //inventory teszteléséhez
    public TeleportGate(){}

    public TeleportGate(String name, int id, Map m, TeleportView tv){
        super(name, id, m);
        pairIsPlaced = false;
        pair = null;
        teleportView = tv;
    }

    /**
     * getter for name
     */
    public String getName(){ return this.name; }

    /**
     * teleportgate is hit by solar storm
      */
    @Override
    public void HitByStorm() {
    	ArrayList<Entity> copy = new ArrayList<Entity>();
    	for(int i =0; i < entity.size(); i++) {
    		copy.add(entity.get(i));
    	}
    	for(int i =0; i < copy.size(); i++) {
    		copy.get(i).Die();
    	}
        isCrazy = true;
    }

    /**
     * do action by settler
     */
    @Override
    public void Action(Settler s){
        if (pairIsPlaced) {
            s.UseTeleport();
        }
        teleportView.updateTeleport(this);
    }

    /**
     * do action by robot (nothing is done here as robots cannot teleport)
     */
    @Override
    public void Action(Robot r){}

    /**
     * do action by ufo
     */
    @Override
    public void Action(Ufo u) {
        if (pairIsPlaced) {
            u.UseTeleport();
        }
        teleportView.updateTeleport(this);
    }

    /**
     * returns the pair of the teleportgate
     */
    public TeleportGate GetPair(){
        return pair;
    }

    /**
     * setter for isCrazy
     */
    public void SetIsCrazy(){isCrazy=true;}

    /**
     * set the pair of the gate
     */
    public void SetPair(TeleportGate tg){
        pair = tg;
    }

    /**
     * notify the gate that its pair was placed
     */
    public void SetPairIsPlaced(){
        pairIsPlaced = true;
    }

    /**
     * getter for pairIsPlaced
     */
    public boolean GetPairIsPlaced() {return pairIsPlaced;}

    /**
     * teleportgate is placed
     */
    @Override
    public void Placed(){
        pair.SetPairIsPlaced();
        teleportView.updateTeleport(this);
    }

    /**
     * make its turn
     */
    @Override
    public void Step(){
    	if(isCrazy) {
    		MoveRandom();
    		teleportView.updateTeleport(this);
    	}
    }

    /**
     * move gate to random neighbor
     * happens when gate goes crazy
     */
    public void MoveRandom(){
        Asteroid a = (Asteroid) neighbors.get(0);
        a.RemoveNeighbor(this);
        Place neighbor = a.GetRandomNeighbor();
        neighbor.AddNeighbor(this);
        
        neighbors.remove(a);
        neighbors.add(neighbor);
    }
    /**
     * add a new neighbor to the place
     */
    @Override
    public void AddNeighbor(Place p){
        neighbors.add(p);
        p.Placed();
        map.getGame().getFrame().disableAsteroidActionButtons();
        teleportView.updateTeleport(this);
    }

    /**
     * remove a neighbor from the place
     */
    @Override
    public void RemoveNeighbor(Place p){
        neighbors.remove(p);
        teleportView.updateTeleport(this);
    }
}
