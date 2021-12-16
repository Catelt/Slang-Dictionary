import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
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

    public HashMap<String,ArrayList<String>> getData(){
        return this.data;
    }


    public void addSlang(String slang,String mean){
        ArrayList<String> means = new ArrayList<>();
        means.add(mean);
        this.data.put(slang,means);
    }

    public void removeSlang(String slang){
        this.data.remove(slang);
    }

//    public void slangView(){
//        Set<String> setOfSlang = this.data.keySet();
//        System.out.println(Arrays.toString(setOfSlang.toArray()));
//    }

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

    public ArrayList<String> findSlang(String slang){
        return this.data.get(slang);
    }

    public void saveHistory(String slang,String nameFile){
        try{
            FileWriter fw = new FileWriter(nameFile);
            fw.write(slang + "\n");
            fw.close();
        }
        catch (IOException e){
        }
    }

    public ArrayList<String> loadHistory(String nameFile){
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

//        String result = slang + ": ";
//        int i = 0;
//        for(; i < means.size() - 1;i++){
//            result += means.get(i) + ",";
//        }
//        result += means.get(i);
//        return result;
        return slang;
    }

    public void editSlang(String Slang,ArrayList<String> means){
        this.data.replace(Slang,means);
    }


    @Override
    public String toString(){
        String result = "";
        for (String slang : this.data.keySet()) {
            result += slang + ": ";
            int i = 0;
            for(; i < data.get(slang).size() - 1;i++){
                result += data.get(slang).get(i) + ", ";
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
