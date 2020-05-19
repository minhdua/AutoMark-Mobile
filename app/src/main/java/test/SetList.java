package test;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SetList {
    private List<Character> options;
    public void setOptionsString(String string){
        StringTokenizer st = new StringTokenizer(string, ",");
        options = new ArrayList<>();
        while(true) {
            try {
                String token = st.nextToken();
                int lastIndex = token.length() - 1;
                options.add(token.charAt(lastIndex));
            } catch (Exception e) {
                break;
            }
        }
    }

    public List<Character> getOptions() {
        return options;
    }
    public String getOptionsString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            stringBuilder.append(options.get(i)).append(",");
        }
        return stringBuilder.toString();
    }
    public static void  main(String args[]){
        SetList setList = new SetList();
        setList.setOptionsString("A,B,C,D,,");
        System.out.println(setList.getOptions().size());
        for(int i = 0 ;i<setList.getOptions().size();i++){
            System.out.println(setList.getOptions().get(i));
        }
        System.out.println(setList.getOptionsString());
    }
}
