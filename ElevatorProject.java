package elevatorproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/*
This is the main class of this program
*/
public class ElevatorProject {
    //main method to run the program
    public static void main(String[] args) throws IOException {
        
        ArrayList<String> list = new ArrayList<String>();
        //open the text file, read input data from the file and store it in an array list
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = "";
        while((line=br.readLine())!=null){
            list.add(line);
        }
        for(int a=0;a<list.size();a++){
            list.set(a,list.get(a).replaceAll(",", "-"));
            list.set(a,list.get(a).replaceAll(":", "-"));
        }
        //call method to print results for ModeA
        System.out.println("Mode A");
        ModeA(list);
        //call method to print results for ModeB
        System.out.println("Mode B");
        ModeB(list);
    }
    //call method to print results for ModeA
    public static void ModeA(ArrayList<String> list){
        int start = 0, end = 0, sum = 0;
        //traverse and process the list to get the results
        for(int a=0;a<list.size();a++){
            String[] recs = list.get(a).split("-");
            for(int b=0;b<recs.length-1;b++){
                start = Integer.parseInt(recs[b]);
                end = Integer.parseInt(recs[b+1]);
                if(start>end){
                    sum += start-end;
                }
                else if(start<end){
                    sum += end-start;
                }
            }
            list.set(a,list.get(a).replaceAll("-", " "));
            System.out.println(list.get(a)+" ("+sum+")");
            sum = 0;
        }
    }
    ////call method to print results for ModeB
    public static void ModeB(ArrayList<String> list){
        new Driver().ModeB(list);
    }
    
}
