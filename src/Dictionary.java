import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

/**
 * PACKAGE_NAME
 * Created by hovan
 * Date 15/12/2021 - 9:41 SA
 * Description:
 **/
public class Dictionary {
    private HashMap<String,ArrayList<String>> data = new HashMap<String,ArrayList<String>>();

    public void addSlang(String slang,String mean){
        ArrayList<String> means = new ArrayList<>();
        means.add(mean);
        this.data.put(slang,means);
    }

    public void removeSlang(String slang){
        this.data.remove(slang);
    }

    public void slangView(){
        Set<String> setOfSlang = this.data.keySet();
        System.out.println(Arrays.toString(setOfSlang.toArray()));
    }

    public int countSlang() {
        return this.data.size();
    }

    public static String readLine(String str, ArrayList<String> means){
        int index = str.indexOf('`');
        String slang = str.substring(0,index);
        str = str.substring(index + 1);
        while(str.indexOf('|') != -1 ){
            String mean = str.substring(0,str.indexOf('|'));
            str = str.substring(str.indexOf('|')+2);
            means.add(mean);
        }
        means.add(str);
        return slang;
    }

    public static HashMap<String,ArrayList<String>> load(String nameFile){
        HashMap<String,ArrayList<String>> data_temp = new HashMap<String,ArrayList<String>>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(nameFile));
            while(true){
                String str = br.readLine();
                if(str == null || str.equals("")){
                    break;
                }
                ArrayList<String> means = new ArrayList<String>();
                String slang = Dictionary.readLine(str,means);
                data_temp.put(slang,means);
            }
        }
        catch(IOException e){
            return null;
        }
        return data_temp;
    }

    @Override
    public String toString(){
        System.out.println(data.size());
    }

    public static void main(String args[]){

//        ArrayList<String> means = new ArrayList<String>();
//        String slang = Dictionary.readLine(">.<`Frustrated| angry| upset| in pain",means);
//        System.out.println(slang);
//        System.out.print(means.size());
        HashMap<String,ArrayList<String>> data = Dictionary.load("slang.txt");
        System.out.println(data.size());
//        for (String i : data.keySet()) {
//            System.out.println("key: " + i + " value: " + data.get(i));
//        }

    }
}
