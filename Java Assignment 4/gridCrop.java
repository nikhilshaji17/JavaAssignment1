import java.util.Scanner;
import java.util.Arrays;
public class gridCrop {
    public static void main(String[] args) {
        int N,M;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Number of Rows:");
        N = in.nextInt();
        System.out.println("Enter the Number of Columns:");
        M = in.nextInt();

        in.nextLine(); 
        String[] crops = new String[N];

        //Accepting the initial crop pattern 
        for (int i=0;i<N;i++)
        {
            String input_String = null;
            System.out.println("Input your crop pattern in the following manner: (a1a2a3...aM, where M is the number of columns), Row number: "+ (i+1) );
            input_String = in.nextLine();
            crops[i]=input_String;
        }
        
        System.out.println();
        System.out.println("Your Current Plot State:");
        for(int i=0;i<N;i++)
        {
            for(int j=0;j<M;j++){
                System.out.print(crops[i].charAt(j)+" ");
            }
            System.out.println();
        }
        in.close();
        System.out.println("\n Number of replants to be done for Given Conditions to be satisfied:");
        System.out.println(replant(crops));
    }
    public static int replant(String[] crops){
    // Write your code here
    // Return the number of replanted crops
    StringBuilder[] cropsSB = new StringBuilder[crops.length];
        // for (int i = 0; i < crops.length; i++) {
        //     System.out.println(crops[i]);
        // }
        // for (int i = 0; i < crops.length; i++) {
        //     System.out.println(crops[i]);
        // }
        Arrays.setAll(cropsSB, i -> new StringBuilder(crops[i]));
        int output_replants = 0;
        if(cropsSB.length==1){
            if(cropsSB[0].length()==1){
                output_replants = 0;
            }else{
                output_replants = replant_helper_Horizontal_Vector(cropsSB);
            }
        }else{
            if(cropsSB[0].length()==1){
                output_replants = replant_helper_Vertical_Vector(cropsSB);
            }else{
                output_replants = replant_helper(cropsSB,0,0);
            }
        }
        System.out.println("Your Final Plot State:");
        for(int i=0;i<cropsSB.length;i++){
            for(int j=0;j<cropsSB[i].length();j++){
                System.out.print(cropsSB[i].charAt(j)+" ");
            }
            System.out.println();
        }
        return output_replants;
    }
    public static int replant_helper_Horizontal_Vector(StringBuilder[] crops) {
        int horizontal_replacement_count = 0;
        for(int i=crops[0].length()-2;i>=0;i--){
            if(crops[0].charAt(i) == crops[0].charAt(i+1)){
                crops[0].setCharAt(i, ';');
                horizontal_replacement_count++;
            }
        }
        return horizontal_replacement_count;
    }
    public static int replant_helper_Vertical_Vector(StringBuilder[] crops) {
        int vertical_replacement_count = 0;
        boolean check_difference = true;
        for(int i=crops.length-2;i>=0;i--){
            if(check_difference){
                if(crops[i] == crops[i+1]){
                    vertical_replacement_count++;
                    check_difference = false;
                }
            }else{
                check_difference=true;
            }
        }
        return vertical_replacement_count;
    }   
    public static int replant_helper(StringBuilder[] crops,int row,int col){
        if(row>crops.length-1 || col>crops[0].length()-1){
            return 0;
        }
        if(row == 0){
            //plant at (0,M-1) only move down
            if(col==crops[0].length()-1){
                int down = replant_helper(crops, row+1, col);
                if(crops[0].charAt(col) == crops[1].charAt(col)){
                    crops[0].setCharAt(col,';');
                    down++;
                }
                return down;
            }
            //any other plant in row 0 excluding last => move down and right 
            else{
                int right = replant_helper(crops, row, col+1);
                int down = replant_helper(crops, row+1, col);
                if(crops[0].charAt(col+1)==';'){
                    if(crops[1].charAt(col)==';'){
                        right += 0;
                        down += 0;
                    }else{
                        if(crops[0].charAt(col) == crops[1].charAt(col)){
                            crops[0].setCharAt(col, ';');
                            down++;
                        }
                    }
                }else{
                    if(crops[1].charAt(col)==';'){
                        if(crops[0].charAt(col) == crops[0].charAt(col+1)){
                            crops[0].setCharAt(col, ';');
                            right++;
                        }  
                    }else{
                        if(crops[0].charAt(col) == crops[0].charAt(col+1)){
                            crops[0].setCharAt(col, ';');
                            right++;
                        }
                        if(crops[0].charAt(col) == crops[1].charAt(col)){
                            crops[0].setCharAt(col, ';');
                            down++;
                        }
                    }
                }
                return right+down;
            }
        }else if(row == crops.length-1){
            //plant at (N-1,0) =>Only to the right
            if(col==0){
                if(crops[row].charAt(col+1) == ';'){
                    return 0;
                }
                int right = replant_helper(crops, row, col+1);
                if(crops[row].charAt(col) == crops[row].charAt(col+1)){
                    crops[row].setCharAt(col,';');
                    right++;
                }
                return right;
            }
            //plant at (N-1,M-1) => No Move
            else if(col==crops[0].length()-1){
                if(crops[row].charAt(col) == crops[row-1].charAt(col) && crops[row].charAt(col) == crops[row].charAt(col-1)){
                    crops[row].setCharAt(col, ';');
                    return 1;
                }
                return 0;
            }else{
                if(crops[row].charAt(col) == crops[row-1].charAt(col) && crops[row].charAt(col) == crops[row].charAt(col-1)){
                    crops[row].setCharAt(col, ';');
                    return 1;
                }
                int right = replant_helper(crops, row, col+1);
                if(crops[row].charAt(col) == crops[row].charAt(col+1)){
                    crops[row].setCharAt(col,';');
                    right++;
                }
                return right;
            }
        }else{
            if(col+1>crops[row].length()-1){
                if(crops[row].charAt(col) == crops[row-1].charAt(col) && crops[row].charAt(col) == crops[row].charAt(col-1) && crops[row].charAt(col) != crops[row+1].charAt(col)){
                    crops[row].setCharAt(col, ';');
                    return 1;
                }
            }else{
                if(col!=0){
                    if(crops[row].charAt(col) == crops[row-1].charAt(col) && crops[row].charAt(col) == crops[row].charAt(col-1) && crops[row].charAt(col) != crops[row+1].charAt(col) && crops[row].charAt(col) != crops[row].charAt(col+1)){
                        crops[row].setCharAt(col, ';');
                        return 1;
                    }
                }
            }
            if(col==crops[0].length()-1){
                int down = replant_helper(crops, row+1, col);
                if(crops[row].charAt(col) == crops[row+1].charAt(col)){
                    crops[row].setCharAt(col,';');
                    down++;
                }
                return down;
            }else{
                int right = replant_helper(crops, row, col+1);
                int down = replant_helper(crops, row+1, col);
                if(crops[row].charAt(col+1)==';'){
                    if(crops[row+1].charAt(col)==';'){
                        right += 0;
                        down += 0;
                    }else{
                        if(crops[row].charAt(col) == crops[row+1].charAt(col)){
                            crops[row].setCharAt(col, ';');
                            down++;
                        }
                    }
                }else{
                    if(crops[row+1].charAt(col)==';'){
                        if(crops[row].charAt(col) == crops[row].charAt(col+1)){
                            crops[row].setCharAt(col, ';');
                            right++;
                        }  
                    }else{
                        if(crops[row].charAt(col) == crops[row].charAt(col+1)){
                            crops[row].setCharAt(col, ';');
                            right++;
                        }
                        if(crops[row].charAt(col) == crops[row+1].charAt(col)){
                            crops[row].setCharAt(col, ';');
                            down++;
                        }
                    }
                }
                return right+down;
            }
        }
    }
}