package szkeleton;

import java.util.Random;
import java.util.Scanner;

public class Robot extends Entity{

    private RobotView robotView;

    /**
     * Konstruktor
     */
	public Robot(String name, Game g, Place p) {
    	super(name, g, p);
    }

    public Robot(String name, Game g, Place p, RobotView rv) {
	    super(name, g, p);
	    robotView = rv;
	    rv.updateRobot(this);
	}

    /**
     * Műveletvégrehajtás
     */
    public void Action(){
	    place.Action(this);
    }

    /**
     * Robot halála
     */
    public void Die() {
	    game.RobotDied(this);
        robotView.robotDied(this);
    }

    /**
     * Robotot aszteroidarobbanás éri
     */
    public void BlownUp() {
        // random szomszédra átrepül
        Place destination = place.GetRandomNeighbor();
        place.RemoveEntity(this);
        destination.AcceptEntity(this);
        place = destination;
        robotView.updateRobot(this);
    }

    /**
     * Robot lép
     */
    public void Step() {
        Random rand1 = new Random();
        // random vagy műveletet hajt végre vagy mozog
        int rand_int = rand1.nextInt(2); // a random szám 0, vagy 1 lehet
        if (rand_int == 0)
            Action();
        else
            Move(place.GetRandomNeighbor().GetPlaceID());
    }

    /**
     * Szomszédos aszteroidára áthelyezi az entity-t.
     */
    @Override
    public void Move(int asteroidID) {
        Place neighbour = place.GetNeighbor(asteroidID);
        place.RemoveEntity(this);
        neighbour.AcceptEntity(this);
        place = neighbour;
        robotView.updateRobot(this);
    }
}
