import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
/**
 * PACKAGE_NAME
 * Created by hovan
 * Date 15/12/2021 - 9:41 SA
 * Description:
 **/
public class Dictionary {
    private Map<String,String> data = new TreeMap<String,String>();

    public String addSlang(String slang,String mean){
        return this.data.put(slang,mean);
    }

    public String removeSlang(String slang){
        return this.data.remove(slang);
    }

    public String lookUp(String slang){
        String mean = this.data.get(slang);
        return mean;
    }

    public void slangView(){
        Set<String> setOfSlang = this.data.keySet();
        System.out.println(Arrays.toString(setOfSlang.toArray()));
    }

    public int countSlang() {
        return this.data.size();
    }
}
