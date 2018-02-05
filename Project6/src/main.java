/**
 * Created by shengjiezhai on 2017/3/1.
 */
import java.util.*;
import java.io.*;
import java.lang.String;

public class main {

    public static Set<String> dictionary = new HashSet<>();

    public static String result = "";
    public static String oldstr = "";
    public static int size;

    private static void loadDictionary(String dictionaryFileName)
    {
        File inFile = new File(dictionaryFileName);

        try {
            Scanner scan = new Scanner(inFile);
            String line;
            while (scan.hasNext()) {
                line = scan.next();
                dictionary.add(line.trim());
            }//while
            scan.close();
        } //try block

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }//load dictionary

    private static boolean dict(String word){
        return dictionary.contains(word);
    } //look up a provided word in the dictionary

    //iterative bottom-up version
    public static boolean iterative(){
        boolean[] bool = new boolean[size];
        for (int i = 0; i < size; i++){bool[i] = false;}
        int len = size - 1;
        while(len >= 0){
            for (int num = len; num < size; num++){
                if (num == size - 1){
                    bool[len] = bool[len] || dict(oldstr.substring(len, num + 1));
                }
                if (num < size - 1){
                    bool[len] = bool[len] || (dict(oldstr.substring(len, num + 1)) && bool[num + 1]);
                }
            }
            len--;
        }
        int prev = 0;
        int n = 1;
        do{if(bool[n] == true){
                if (dict(oldstr.substring(prev,n))){
                    result = result + oldstr.substring(prev,n) + " ";
                    prev = n;
                }
            }n++;
        }while(n < size);
        result = result + oldstr.substring(prev,size);
        return bool[0];
    }

    //recursive memorized version
    private static boolean recursive(int num){
        boolean bool = false;
        String prep = "";

        if (num >= size + 1){
            return true;
        }
        int n = num - 1;
        while(n < size){
            prep = prep + oldstr.charAt(n);
            if (dict(prep)){
                result = result + prep + " ";
                bool = recursive(n + 2) || bool;
            }else{
                bool = bool;
            }
            boolean Bool = false;
            if(bool == true){n = size;}
            int len = result.length();
            if(n == size -1){
                for (int ele = len - 2; ele >= 0; ele--){
                    if (result.charAt(ele) == ' '){
                        result = result.substring(0, ele + 1);
                        Bool = true;
                        ele = 0;
                    }
                }
                if (Bool == false){result = "";}

            }
            n++;
        }
        return bool;
    }

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        String [] inputStr = new String [num];
        for(int i = 1; i <= num; i++){
            String input = scan.next();
            inputStr[i-1] = input;
        }
        String filename = "src/diction10k.txt";
        loadDictionary(filename);
        for(int j = 0; j < num; j++){
            int n = j+1;
            System.out.println("phrase number: " + n);
            oldstr = inputStr[j];
            System.out.println(oldstr);
            size = oldstr.length();
            System.out.println("\n");
            System.out.println("iterative attempt:");
            if(iterative()){
                System.out.println("YES, can be split");
                System.out.println(result);
                System.out.println("\n");
            }else{
                System.out.println("NO, cannot be split\n");
            }
            result = "";

            System.out.println("memoized attempt:");
            if(recursive(1) == true){
                System.out.println("YES, can be split");
                System.out.println(result);
                System.out.println("\n");
            }else{
                System.out.println("NO, cannot be split\n");
            }
            oldstr = "";
            result = "";
        }
        scan.close();
    }
}
