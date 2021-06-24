import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;

public class Task_6 {
    // Ex 1
    public static String hiddenAnagram(String line0, String line1){
        int counter;
        String res = "";
        char let;

        ArrayList<Character> mainLetArr = new ArrayList<>();
        ArrayList<Character> letArr;

        for (int i = 0; i < line1.length(); i++){
            let = line1.toLowerCase().charAt(i);
            if ((int)let > 96 && (int)let < 123)
                mainLetArr.add(let);
        }

        for (int k = 0; k < line0.length(); k++){

            res = "";
            counter = -1;
            letArr = new ArrayList<>(mainLetArr);

            for (int i = k; i < line0.length(); i++){
                let = line0.toLowerCase().charAt(i);
                if ((int)let < 97 || (int)let > 122)
                    counter++;
                else if (letArr.contains(let)){

                    if (counter == -1)

                        counter = i;
                    else if (i - counter != 1){
                        res = "notfound";
                        break;
                    }
                    else
                        counter = i;

                    res += let;//записываем в итог
                    letArr.remove(letArr.indexOf(let));
                }
            }

            if (letArr.size() == 0){
                break;
            }
            else
                res = "notfound";
        }

        return res;
    }

    // Ex 2
    public static String[] collect(String line, int sliceLen){
        if (line.length() < sliceLen){
            return new String[] {};
        }
        else{
            String newLine = "", oldLine = "";
            for (int i = 0; i < line.length(); i++){
                if (i < sliceLen)
                    newLine += line.charAt(i);
                else
                    oldLine += line.charAt(i);
            }

            return append(collect(oldLine, sliceLen), newLine);
        }
    }

    private static <T>T[] append(T[] arr, T el){
        int arrSize = arr.length;
        arr = Arrays.copyOf(arr, arrSize + 1);
        arr[arrSize] = el;
        Arrays.sort(arr);
        return arr;
    }

    // Ex 3
    public static String nicoCipher(String mes, String key){
        int[] keyArr = new int[key.length()];
        String[] arr = key.split("");
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++){
            keyArr[i] = key.indexOf(arr[i]);

        }

        String res = "";
        for (int i = 0; i < mes.length() / keyArr.length + 1; i++){
            for (int k = 0; k < keyArr.length; k++){
                if (i * keyArr.length + keyArr[k] < mes.length()){
                    res += mes.charAt(i * keyArr.length + keyArr[k]);
                }
                else
                    res += " ";
            }
        }

        return res;
    }

    // Ex 4
    public static int[] twoProduct(int[] arr, int num){
        for (int i = 0; i < arr.length; i++){
            for (int k = i+1; k < arr.length; k++){
                if (arr[i] * arr[k] == num)
                    return new int[] {arr[i], arr[k]};
            }
        }

        return new int[] {};
    }

    // Ex 5
    public static int[] isExact(int num){
        return getFact(num, 1, 1);
    }

    private static int[] getFact(int num, int cNum, int counter) {
        if (cNum < num)
            return getFact(num, cNum * (counter + 1), counter + 1);
        else if (cNum == num)
            return new int[]{cNum, counter};
        else
            return new int []{};
    }
////num граница

    // Ex 6
    public static String fractions(String line){
        String[] res = line.split("\\(");
        res[1] = res[1].substring(0, res[1].length() - 1);
        System.out.println(Arrays.toString(res));

        int s = line.split("\\.")[1].length() - res[1].length() - 2;
        System.out.println(s);
        double n = pow(10, res[1].length());
        System.out.println(n);
        double x = Double.parseDouble(res[0]);                  //возвращает число, полученное из строки
        System.out.println(x);
        double nX = Double.parseDouble(res[0] + res[1]) * n;
        System.out.println(nX);
        return reduceFraction( (nX - x)*pow(10, s) , (n-1) * pow(10, s));
    }

    private static String reduceFraction(double num1, double num2){
        for (int k = 2; k <= num1; k++){
            if (num1 % k == 0 && num2 % k == 0){
                num1 /= k;
                num2 /= k;
            }
            System.out.println((int)num1 + "/" + (int)num2);
        }
        return (int)num1 + "/" + (int)num2;
    }


    // Ex 7
    public static String pilish_string(String line){
        String res = "";
        int[] lineLengths = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};

        int counter = 0;
        int wordCounter = 0;
        for (int k = 0; k < line.length(); k++){
            if (counter > 15)
                counter = 0;

            if (wordCounter < lineLengths[counter]){
                res += line.charAt(k);
                wordCounter++;
            }
            else{
                counter++;
                wordCounter = 1;
                res += " " + line.charAt(k);
            }
        }

        while (wordCounter++ < lineLengths[counter])
            res += res.charAt(res.length() - 1);

        return res;
    }

    // Ex 8
    public static String generateNonconsecutive(int n){
        return "0".repeat(n) + " " + getBinCons("0".repeat(n));
    }
    private static String getBinCons(String line){
        int ind;
        String nLine;
        String res = "";
//поиск и сохранение вариантов, куда можно вставить одну единичку
        if (line.charAt(line.length() - 1) == '1')
            return "";

        for (int k = 0; k < line.length()-1; k++){
            ind = line.length() - k - 1;

            if (line.charAt(ind-1) == '0'){
                nLine = line.substring(0, ind) + "1" + line.substring(ind+1, line.length());
                res += nLine + " ";

                res += getBinCons(nLine);
            }
            else
                return res;
        }
//вызов для каждого найденного способа вставить единичку еще одного метода по поиску вставки единички
        if (line.charAt(0) == '0'){
            nLine = "1" + line.substring(1, line.length());
            res += nLine + " ";
            res += getBinCons(nLine);
        }

        return res;
    }

    // Ex 9
    public static String isValid(String line){
        Map<Character, Integer> counter = new HashMap<Character, Integer>();

        for (int k = 0; k < line.length(); k++){
            char sym = line.charAt(k);
            if (counter.containsKey(sym))
                counter.put(sym, counter.get(sym)+1);
            else
                counter.put(sym, 1);
        }

        int valSum = 0;
        int valNum = 0;
        for (char key : counter.keySet()){
            int val = counter.get(key);
            valSum += val;
            valNum ++;
        }
        boolean checkDif = true;
        int srNum = Math.round(valSum / valNum);

        for (char key : counter.keySet()){
            int val = counter.get(key);
            if (val != srNum)
              if (checkDif)
                  checkDif = false;
              else
                    return "No";
        }

        return "Yes";
    }

    // Ex 10
    public static int[][] sumsUp(int[] nums){
        ArrayList<int[]> aList = new ArrayList<int[]>();
        for (int k = 0; k < nums.length; k++){
            for (int i = k+1; i < nums.length; i++){
                if (nums[k] + nums[i] == 8){
                    int[] arr;
                    if (nums[k] < nums[i])
                        arr = new int[] {nums[k], nums[i]};
                    else
                        arr = new int[] {nums[i], nums[k]};

                    aList.add(arr);
                    System.out.println(Arrays.toString(arr));
                }

            }
        }
        return new int[][]{};
    }

       // int[][] res = new int[aList.size()][];

      //  int c = 0;
     //   for (int[] k : aList){
      //      res[c] = k;
      //      c++;
      ////  }
        //return res;


    public static void main (String args[]){
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.My world evolves in a beautiful space called Tesh.","sworn love lived" ));
        System.out.println(Arrays.toString(collect("strengths", 3)));
        System.out.println(nicoCipher("myworldevolvesinhers","tesh"));
        System.out.println(Arrays.toString(twoProduct(new int[] {1, 2, 3, 9, 4, 15, 3, 5}, 45)));
        System.out.println(Arrays.toString(isExact(6)));
        System.out.println(fractions("0.(6)"));
        System.out.println(pilish_string("33314444"));
        System.out.println(generateNonconsecutive(1));
        System.out.println(isValid(("aabbccc") ));
        System.out.println(Arrays.toString(sumsUp(new int [] {1, 6, 5, 4, 8, 2, 3, 7})));
    }













}





































