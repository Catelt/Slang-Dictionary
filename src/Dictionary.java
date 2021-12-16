import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * PACKAGE_NAME
 * Created by hovan
 * Date 15/12/2021 - 9:41 SA
 * Description:
 **/
public class Dictionary {
    private HashMap<String,ArrayList<String>> data = new HashMap<String,ArrayList<String>>();

    public HashMap<String,ArrayList<String>> getData(){
        return this.data;
    }


    public void addSlang(String slang,ArrayList<String> means){
        this.data.put(slang,means);
    }

    public void replaceSlang(String slang,ArrayList<String> means){
        this.data.replace(slang,means);
    }

    public void duplicateSlang(String slang,ArrayList<String> means){
        ArrayList<String> check = this.data.get(slang);
        int size = check.size();
        for(int i = 0 ; i < means.size();i++){
            int flag = 1;
            for(int j = 0 ;j < size;j++){
                if(means.get(i).indexOf(check.get(j)) != -1){
                    flag = 0;
                    break;
                }
            }
            if(flag == 1){
                check.add(means.get(i));
            }
        }
    }


    public void removeSlang(String slang){
        this.data.remove(slang);
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

    public static ArrayList<String> lineMeans(String str){
        ArrayList<String> means = new ArrayList<String>();
        while(str.indexOf('|') != -1 ){
            String mean = str.substring(0,str.indexOf('|'));
            str = str.substring(str.indexOf('|')+1);
            if(mean.charAt(0) == ' '){
                mean = mean.replace(" ","");
            }
            means.add(mean);
        }
        if(str.charAt(0) == ' '){
            str = str.replace(" ","");
        }
        means.add(str);
        return means;
    }

    public void load(String nameFile){
        try{
            BufferedReader br = new BufferedReader(new FileReader(nameFile));
            while(true){
                String str = br.readLine();
                if(str == null || str.equals("")){
                    break;
                }
                ArrayList<String> means = new ArrayList<String>();
                String slang = Dictionary.readLine(str,means);
                this.data.put(slang,means);
            }
            br.close();
        }
        catch(IOException e){
        }
    }

    public int checkExistence(String slang){
        if(this.data.containsKey(slang)){
            return 1;
        }
        return 0;
    }

    public int checkValue(String slang,String mean){
        ArrayList<String> means = this.data.get(slang);
        if(means.contains(mean)){
            return 1;
        }
        return 0;
    }

    public static String meanstoString(ArrayList<String> means){
        String result = "";
        int i = 0;
        for(; i < means.size() - 1;i++){
            result += "- " + means.get(i) + "\n";
        }
        result += "- " + means.get(i);
        return result;
    }

    public static String meanstoString2(ArrayList<String> means){
        String result = "";
        int i = 0;
        for(; i < means.size() - 1;i++){
            result += means.get(i) + "| ";
        }
        result += means.get(i);
        return result;
    }

    public ArrayList<String> findSlang(String slang){
        return this.data.get(slang);
    }

    public static void saveHistory(String slang,String nameFile){
        try{
            FileWriter fw = new FileWriter(nameFile);
            fw.write(slang + "\n");
            fw.close();
        }
        catch (IOException e){
        }
    }

    public static ArrayList<String> loadHistory(String nameFile){
        ArrayList<String> result = new ArrayList<String>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(nameFile));
            while(true){
                String str = br.readLine();
                if(str == null || str.equals("")){
                    break;
                }
                result.add(str);
            }
            br.close();
        } catch (IOException e){

        }
        return result;
    }

    public String randomSlang(){
        Random generator = new Random();
        //Random
        Set<String> keySet = this.data.keySet();
        List<String> keyList = new ArrayList<>(keySet);

        int size = keyList.size();
        int index = new Random().nextInt(size);

        String slang = keyList.get(index);
        ArrayList<String> means = this.data.get(slang);
        return slang;
    }


    @Override
    public String toString(){
        String result = "";
        for (String slang : this.data.keySet()) {
            result += slang + ": ";
            int i = 0;
            for(; i < data.get(slang).size() - 1;i++){
                result += data.get(slang).get(i) + " | ";
            }
            result += data.get(slang).get(i) + "\n";
        }
        return result;
    }

    public static void main(String args[]){
        Dictionary data = new Dictionary();
        data.load("slang.txt");
        System.out.println(data.randomSlang());
    }
}
