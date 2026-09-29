package week2;
import java.util.Scanner;
public class Week2 {

    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    
        System.out.println("Please enter number of stops");
        
        int stopNum = input.nextInt();
        
        System.out.println("Please enter bus seating capacity");
        int capacity = input.nextInt();
        input.nextLine();
        
        String[] stopNames = new String[stopNum];
        int[] passangersWaiting = new int[stopNum];
        int[] passangersAlighting = new int[stopNum];
        
        for(int i = 0; i < stopNames.length; i++){
            System.out.println("Please enter name of the stop:");
            stopNames[i] = input.nextLine();
            
            System.out.println("Please number of passangers which are waiting:");
            passangersWaiting[i] = input.nextInt();
            
            System.out.println("Please number of passangers which are alighting:");
            passangersAlighting[i] = input.nextInt();
            input.nextLine();
        }
        
        int[] currentOccupancy = occupancyCalc(stopNames, passangersWaiting, passangersAlighting, capacity);
        
        displayTable(stopNames, passangersWaiting, passangersAlighting, currentOccupancy);
        
        displayStatistics(stopNames, passangersWaiting, passangersAlighting, currentOccupancy, capacity);
        
        
        
        
    }
    
    public static int[] occupancyCalc(String[] stops, int[] waiting, int[] alighting, int capacity){
        int[] current = new int[stops.length];
        int busCount = 0;
        
        for(int i = 0; i < stops.length; i++){
            busCount = busCount + waiting[i] - alighting[i];
                if(busCount < 0){
                    System.out.println("Data error at " + stops[i] + ": cannot have more passengers alighting than currently on the bus. "
                            + "occupancy set to 0");
                    busCount = 0;
                }
                current[i] = busCount;
                System.out.println("passengers on the bus after " + stops[i] + ": " + busCount);
                
                if(busCount > capacity){
                    System.out.println("Warning: bus is over capacity at " + stops[i]);
                }
        }
        return current;
    }
    
    public static void displayTable(String[] stops, int[] waiting, int[] alighting, int[]current){
        System.out.println();
        System.out.println("--------ROUTE TABLE--------");
        System.out.println("SN\tB\tA\tCO"); 
        
        for(int i = 0; i < stops.length; i++){
            System.out.println(stops[i] + "\t" + waiting[i] + "\t" + alighting[i] + "\t" + current[i]);
        }
    }
    
    public static void displayStatistics(String[] stops, int[] waiting, int[] alighting, int[]current, int capacity){
        int busiestIndex = 0;
        int maxBoarding = waiting[0];
        double totalCurrent = 0;
        int overCapacityCount = 0;
        
        for(int i = 0; i < stops.length; i++){
            if(waiting[i] > maxBoarding){
                maxBoarding = waiting[i];
                busiestIndex = i;
            }
            totalCurrent += current[i];
            
            if(current[i] > capacity){
                overCapacityCount++;
            }
        }
        
        double avgOccupancy = totalCurrent / stops.length;
        
        System.out.println();
        System.out.println("---STATISTICS---");
        System.out.println("busiest stop: " + stops[busiestIndex] + " with " + maxBoarding + " passengers boarding.");
        System.out.println("average occupancy across all stops: " + avgOccupancy);
        System.out.println("number of stops exceeding capacity: " + overCapacityCount);
        
        int finalPassengers = current[stops.length - 1];
        if(finalPassengers == 0){
            System.out.println("final check: bus is empty at the last stop" );
        } else {
            System.out.println("final check: bus is not empty at the last stop " + finalPassengers + " passengers left");
        }
    }
    
}
