package elevatorproject;

import java.util.ArrayList;
import java.util.TreeSet;
/*
This class to run the elevator, threading is done in this class
*/
public class ElevatorEngine extends Thread{
     
    private ElevatorCabin cabin = new ElevatorCabin();
    private volatile boolean stopFlag = false;
    ArrayList<Integer> list = new ArrayList<Integer>();
    //this method is to start the elevator 
    public void startEngine(){
        stopFlag = false;
        this.start();
    }
    //this method is to stop the elevator
    public void stopEngine(){
        stopFlag = true;
    }
    //this method is to run the thread, implementation is done here..
    public void run(){
        while(true){
            if(stopFlag){
                if(cabin.callingFloorList.isEmpty()){                   
                    break;
                }
            }
            Integer next = cabin.gotoNext();
            list.add(next); //adding results to this array list
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //when a new person calls the elevator
    public void pressButton(int floor){
        cabin.add(floor);
    }
}
//Class to represent the elevator
class ElevatorCabin {
     
    public int currentFloor = 0;
    public String direction = "UP";
    public TreeSet<Integer> callingFloorList  = new TreeSet<Integer>();
    //adds oersons to the elevator
    public void add(Integer floor){
        callingFloorList.add(floor);
    }
    //method to move elevator to the next floor
    public Integer gotoNext(){
        Integer nextFloor = currentFloor;
        switch(direction){  //check in whihc direction the elevator is moving, and stop at the nearest floor to pick or drop passengers
        case "UP":
            for (Integer floor : callingFloorList) {
                if(floor > nextFloor){
                    nextFloor = floor;
                    direction = "UP";
                    break;
                }
            }
            if(nextFloor == currentFloor){
                for (Integer floor : callingFloorList.descendingSet()) {
                    if(floor < nextFloor){
                        nextFloor = floor;
                        direction = "DOWN";
                        break;
                    }
                }
            }
            break;
        case "DOWN":
            for (Integer floor : callingFloorList.descendingSet()) {
                if(floor < nextFloor){
                    nextFloor = floor;
                    direction = "DOWN";
                    break;
                }
            }
             
            if(nextFloor == currentFloor){
                for (Integer floor : callingFloorList) {
                    if(floor > nextFloor){
                        nextFloor = floor;
                        direction = "UP";
                        break;
                    }
                }
            }
        }
        callingFloorList.remove(nextFloor);
        currentFloor = nextFloor;
        return nextFloor;
    }
}
