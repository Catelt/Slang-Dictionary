import java.io.*;
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

    /**
     * Add slang in dictionary
     * @param slang
     * @param means
     */
    public void addSlang(String slang,ArrayList<String> means){
        this.data.put(slang,means);
    }

    /**
     * replace means of slang
     * @param slang
     * @param means
     */
    public void replaceSlang(String slang,ArrayList<String> means){
        this.data.replace(slang,means);
    }

    /**
     * add means of slang
     * @param slang
     * @param means
     */
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

    /**
     * remove on slangs
     * @param slang
     */
    public void removeSlang(String slang){
        this.data.remove(slang);
    }

    /**
     * return size of dictionary
     * @return
     */
    public int countSlang() {
        return this.data.size();
    }


    /**
     * read one line when we read file
     * @param str
     * @param means
     * @return
     */
    public static String readLine(String str, ArrayList<String> means){
        int index = str.indexOf('`');
        String slang = str.substring(0,index);
        str = str.substring(index + 1);
        while(str.indexOf('|') != -1 ){
            String mean = str.substring(0,str.indexOf('|'));
            str = str.substring(str.indexOf('|')+1);
            means.add(mean);
        }
        means.add(str);
        return slang;
    }

    /**
     * return to arrayList with one string
     * @param str
     * @return
     */
    public static ArrayList<String> lineMeans(String str){
        ArrayList<String> means = new ArrayList<String>();
        while(str.indexOf('|') != -1 ){
            String mean = str.substring(0,str.indexOf('|'));
            str = str.substring(str.indexOf('|')+1);
            means.add(mean);
        }
        means.add(str);
        return means;
    }

    /**
     * load file data
     * @param nameFile
     */
    public void load(String nameFile){
        this.data.clear();
        try{
            BufferedReader br = new BufferedReader(new FileReader(nameFile));
            while(true){
                String str = br.readLine();
                if(str == null || str.equals("")){
                    break;
                }
                ArrayList<String> means = new ArrayList<String>();
                String slang = Dictionary.readLine(str,means);
                if(this.data.containsKey(slang)){
                    for(int i = 0 ; i < means.size();i++){
                        if(!this.data.get(slang).contains(means.get(i))){
                            this.data.get(slang).add(means.get(i));
                        }
                    }
                }
                else{
                    this.data.put(slang,means);
                }
            }
            br.close();
        }
        catch(IOException e){
            System.out.println("Error: " + e);
        }
    }

    public static boolean  checkExistFle(String nameFile){
        File tempFile = new File(nameFile);
        return tempFile.exists();
    }

    /**
     * deserializaion data
     * @param nameFile
     */
    public void loadOnject(String nameFile){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameFile));
            this.data = (HashMap<String, ArrayList<String>>) ois.readObject();
            ois.close();
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Error: " + e);
        }
    }

    /**
     * save file data
     * @param nameFile
     */
    public void save(String nameFile){
        try{
            FileWriter fw = new FileWriter(nameFile);

            for (String slang : this.data.keySet()) {
                String line = "";
                line += slang + "`";
                int i = 0;
                for(; i < data.get(slang).size() - 1;i++){
                    line += data.get(slang).get(i) + "| ";
                }
                line += data.get(slang).get(i) + "\n";
                fw.write(line);
            }
            fw.close();

        }
        catch (IOException e){
            System.out.println("Error: " + e);
        }
    }

    /**
     * serialization data
     * @param nameFile
     */
    public void saveObject(String nameFile){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nameFile));
            oos.writeObject(this.data);
            oos.close();
        }
        catch (IOException e){
            System.out.println("Error: " + e);
        }
    }

    /**
     * check existence one slang
     * @param slang
     * @return
     */
    public int checkExistence(String slang){
        if(this.data.containsKey(slang)){
            return 1;
        }
        return 0;
    }

    /**
     * logic game check slang and definition
     * @param slang
     * @param mean
     * @return
     */
    public int checkValue(String slang,String mean){
        ArrayList<String> means = this.data.get(slang);
        if(means.contains(mean)){
            return 1;
        }
        return 0;
    }

    /**
     * to String definition
     * @param means
     * @return
     */
    public static String meanstoString(ArrayList<String> means){
        String result = "";
        int i = 0;
        for(; i < means.size() - 1;i++){
            result += "- " + means.get(i) + "\n";
        }
        result += "- " + means.get(i);
        return result;
    }

    /**
     * to String definiton another
     * @param means
     * @return
     */
    public static String meanstoString2(ArrayList<String> means){
        String result = "";
        int i = 0;
        for(; i < means.size() - 1;i++){
            result += means.get(i) + "| ";
        }
        result += means.get(i);
        return result;
    }

    /**
     * find Slang with slang word
     * @param slang
     * @return
     */
    public ArrayList<String> findSlang(String slang){
        return this.data.get(slang);
    }

    /**
     * find slang with definition
     * @param mean
     * @return
     */
    public ArrayList<String> findDefinition(String mean){
        ArrayList<String> slangs = new ArrayList<String>();
        for(String slang: this.data.keySet()){
            for(int i = 0; i < data.get(slang).size();i++){
                if(data.get(slang).get(i).contains(mean)){
                    slangs.add(slang);
                    break;
                }
            }
        }
        return slangs;
    }

    /**
     * to String array slang
     * @param slangs
     * @return
     */
    public String slangstoString(ArrayList<String> slangs){
        String result = "";
        for(int i = 0; i < slangs.size();i++){
            result += slangs.get(i) + ": ";
            int j = 0;
            for(; j < data.get(slangs.get(i)).size() - 1;j++){
                result += data.get(slangs.get(i)).get(j) + " | ";
            }
            result += data.get(slangs.get(i)).get(j) + "\n";
        }
        return result;
    }

    /**
     * save history find
     * @param slang
     * @param nameFile
     */
    public static void saveHistory(String slang,String nameFile){
        try{
            BufferedWriter br = new BufferedWriter(new FileWriter(nameFile,true));
            br.append(slang + "\n");
            br.close();
        }
        catch (IOException e){
        }
    }

    /**
     * load  history find
     * @param nameFile
     * @return
     */
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


    /**
     * random one slang
     * @return
     */
    public String randomSlang(){
        Random generator = new Random();
        //Random
        List<String> keyList = new ArrayList<>(this.data.keySet());

        int size = keyList.size();
        int index = new Random().nextInt(size);

        String slang = keyList.get(index);
        ArrayList<String> means = this.data.get(slang);
        return slang;
    }

    /**
     * to String list slangs
     * @return
     */
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
}
