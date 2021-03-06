package szkeleton;

import java.util.ArrayList;
/**
 * A Vas az egyik nyersanyag a játékban.
 */
public class Iron extends Resource{
    public Iron(String name){
        super(name);
        
    }
    public boolean IsRadioactive(){
        return this.radioactive;
    }
    /**
     * Megkapja a játékos listáját és megkeresi, hogy van-e rajta Vas.
     *Ha a kapott játékosnál talál Vasat azt leszedi a játékos nyersanyagai közül.
     * A függvény visszatér a settler listáján talált elemekkel (-1 Vas)
     */
    public ArrayList<Integer> RemoveFromList(ArrayList<Integer> I, Settler s){
        ArrayList<Integer> newList = new ArrayList<>();
        boolean found = false;
        for (Integer i : I){
            if (found)
                newList.add(i);
            else if (i == 12){
                found = true;
                s.RemoveResource(this);
            }
            else {
                newList.add(i);
                s.RemoveResource(this);
            }
        }
        return newList;
    }
    /**
     *Kapott listához hozzáadja az Szén sorszámát, ami a 12.
     */
    public ArrayList<Integer> AddToList(ArrayList<Integer> I){
        I.add(12);
        return I;
    }
}
