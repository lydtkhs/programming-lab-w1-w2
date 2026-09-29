
package week1;
import java.util.Scanner;
public class Week1 {

    public static void main(String[] args) {
    
        Scanner input = new Scanner(System.in);
        
        String[] teams = {"Team A","Team B","Team C","Team D"};
        int[][] matches = {{0,1},{0,2},{0,3},{1,2},{1,3},{2,3}};
       
        System.out.println("MATCHES:");
        for (int i = 0; i < 6; i++){
            System.out.println("Match " + (i+1) + ": "+ teams[matches[i][0]] + " - " + teams[matches[i][1]]);
        }
        System.out.println();
        
        int[][] stats = new int[4][5]; //0 win,1 draw,2 loss,3 goal diff,4 pts 
        int scores[][] = new int[6][2]; //its not ai's note mine btw :(
               
        for(int i = 0; i < 6; i++){
            int h = matches[i][0];
            int a = matches[i][1];
            
            System.out.println("Match " + (i + 1) + ": " + teams[h] + " vs " + teams[a]);
            System.out.print(teams[h] + " goals: ");
            scores[i][0] = input.nextInt();
            System.out.print(teams[a] + " goals: ");
            scores[i][1] = input.nextInt();
            System.out.println();
            
            updateMatch(stats, h, a, scores[i][0], scores[i][1]);
        }
        
        printStandings(teams, stats);
        announceChampion(teams, stats);
        
    }
    
    public static void updateMatch(int[][] s, int h, int a,int hg, int ag){
        s[h][3] += (hg - ag); 
        s[a][3] += (ag - hg);
        
        if(hg > ag){
            s[h][0]++;
            s[a][2]++ ; 
            s[h][4]+= 3; 
        } else if (ag > hg){
            s[a][0]++;
            s[h][2]++;
            s[a][4]+= 3; 
        } else {
            s[h][1]++;
            s[a][1]++;
            s[h][4]++;
            s[a][4]++; 
        }
    }
    
    public static void printStandings(String[] teams, int[][] s){
        System.out.println("----STANDING TABLE----");
        System.out.println("Team\tMP\tW\tD\tL\tGD\tPts");
        for (int i = 0; i < 4; i++){
            System.out.println(teams[i] + "\t3\t" + s[i][0] + "\t" + s[i][1]+ "\t" + s[i][2]
                + "\t" + s[i][3]+ "\t" + s[i][4]);
        }
    }
    
    public static void announceChampion(String[] teams, int[][] s){
        int champ = 0;
        for (int i = 0; i < 4; i++){
            if (s[i][4] > s[champ][4] || (s[i][4] == s[champ][4] && s[i][3] > s[champ][3])){
                champ = i;
            }
        }
        System.out.println("\nTournament Champion: " + teams[champ]);
    }
}
