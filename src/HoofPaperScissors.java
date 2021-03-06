import java.io.*;
import java.util.Objects;

public class HoofPaperScissors  {
    public static boolean checky(String o, String t){
        if (Objects.equals(o, "H")){
            if (Objects.equals(t, "P")){
                return false;
            }
            return Objects.equals(t, "S");
        }else if (Objects.equals(o, "P")){
            if (Objects.equals(t, "S")){
                return false;
            }
            return Objects.equals(t, "H");

        }else{
            if (Objects.equals(t, "H")){
                return false;
            }
            return Objects.equals(t, "P");

        }
    }
    public static int operate(String original, String app_1, String app_2, int changeIndex){
        int wins = 0;
        char[] original_ = original.toCharArray();
        String current_ = app_1;
        for (int i = 0; i<original_.length; i++){
            if (i == changeIndex){
                current_ = app_2;
            }
            if (checky(current_, String.valueOf(original_[i]))){
                wins+=1;
            }

        }
        return wins;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader b = new BufferedReader(new FileReader("./hps.in"));
        int N = Integer.parseInt(b.readLine());
        String stuff = "";
        for (int i = 0; i<N; i++) {
            stuff += (b.readLine());
        }
        String[] options = {"H", "P", "S"};
        for (int i = 0; i<3; i++) {
            for (int j = 0; j<3; j++) {
                for (int k = 0; k<stuff.length(); k++){
                    System.out.println(operate(stuff, options[i], options[j], k));
                }
            }
        }

    }

}