package elevatorproject;

import java.util.ArrayList;
/*
This is the driver class implemented for ModeB
*/
public class Driver {
    //method which takes list of instructions fetched from text file
    public static void ModeB(ArrayList<String> list) {
        ElevatorEngine engine = new ElevatorEngine();
        ArrayList<String> temp = new ArrayList<String>();
        engine.startEngine();
        //for loop to get all indexes of list one by one, and get the efficient results
        for (int a = 0; a < list.size(); a++) {
            String[] recs = list.get(a).split(" ");
            engine.pressButton(Integer.parseInt(recs[0]));
            for(int b=1;b<recs.length;b++){
                if(b%2!=0){
                    sleep();
                }
                engine.pressButton(Integer.parseInt(recs[b]));
            }
            sleep();
            sleep();
            ArrayList<Integer> result = engine.list;
            result.add(0);
            //add result to a new list
            for(int x=0;x<result.size()-1;x++){
                if(!result.get(x).equals(result.get(x+1))){
                    temp.add(result.get(x)+"");
                }
            }
            //format the string(result)
            String line = temp.get(0);
            for(int y=1;y<temp.size();y++){
                line = line+"-"+temp.get(y);
            }
            temp.clear();
            temp.add(line);
            engine.list.clear();
            new ElevatorProject().ModeA(temp);  //use this method again, to print the output to console
            temp.clear();
        }
        engine.stopEngine();
            try {
                engine.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
    //method to make thread sleep
    public static void sleep(){
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}