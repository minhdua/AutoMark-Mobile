package com.vothanhhien.automarkmobile.utils;

import androidx.core.util.Pair;

import com.vothanhhien.automarkmobile.models.LuaChon;
import com.vothanhhien.automarkmobile.models.KhungTraLoi;

import org.opencv.core.Scalar;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UtilityFunctions {

    public static List<Character> ConvertStringtoList(String string, int questioncount) {
        StringTokenizer st = new StringTokenizer(string, ",");
        List<Character> yourStringList = new ArrayList<Character>(questioncount);
        for(int i =0 ;i< questioncount; i++) yourStringList.add('A');
        for (int i = 0; i < questioncount; i++) {
            String token = st.nextToken();
            int lastIndex = token.length() - 1;
            yourStringList.set(i, token.charAt(lastIndex));
        }
        return yourStringList;
    }

    public static String ConvertListtoString(List<Character> list, int questioncount) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < questioncount; i++) {
            stringBuilder.append(i+1).append(list.get(i)).append(",");
        }
        return stringBuilder.toString();

    }
    public static List<Character> createDefaultAnswerList(int questionCount){
        List<Character> initialList = new ArrayList<>(questionCount);
        for (int i = 0; i < questionCount; i++) initialList.add('N');
        return initialList;
    }

    public static String convertChoices2StringCode(List<LuaChon> options) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            int j = options.get(i).getjChoice();
            if (j>=0){
                stringBuilder.append(j+"");
            }else{
                stringBuilder.append("-");
            }

        }
        return stringBuilder.toString();
    }
    public static String convertChoices2StringAnswer(List<LuaChon> options) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            int iChoice = options.get(i).getiChoice();
            int jChoice = options.get(i).getjChoice();
            if(jChoice !=-1)
                stringBuilder.append(jChoice+1);
            else
                stringBuilder.append('-');
        }
        return stringBuilder.toString();
    }

    public static Pair<Integer,Integer> findPointCenter(int i,int j, List<KhungTraLoi> khungs){
        // xác định khung
        int soHang = khungs.get(2).getRows();
        int idxKhung = i/soHang;
        KhungTraLoi khung = khungs.get(idxKhung+2); // bo khung ma de va so bao danh

        // xác định kích thước 1 ô
        int dx = khung.getWidth()/khung.getRows();
        int dy = khung.getHeight()/khung.getCols();

        // xác định tọa độ trung tâm
        int Xcenter = khung.getX()+dx*(i%soHang) + dx/2;
        int Ycenter = khung.getY()+dy*(khung.getCols() - j) +dy/2;

        return new Pair<Integer, Integer>(Xcenter,Ycenter);

    }
    public static List<LuaChon> convertString2ChoicesAnswer(String dapAn,List<KhungTraLoi> khungTraLois) {
        List<LuaChon> choices = new ArrayList<>();
        for(int i = 0;i<dapAn.length();i++){
            int j = Integer.parseInt(dapAn.charAt(i)+"");
            Pair <Integer,Integer> pointDraw = findPointCenter(i,j,khungTraLois);
            LuaChon luachon = new LuaChon(i,j,pointDraw.first,pointDraw.second);
            choices.add(luachon);
        }
        return choices;
    }

    public static List<LuaChon> standAnswers(List<LuaChon> answers, int questionCount) {
        List<LuaChon> tuyChons = new ArrayList<>();
        int j = 0; int i = -1;
        while((tuyChons.size()<questionCount) && (j<answers.size())){
            // Nếu thứ tự các tùy chọn tiếp theo bằng với thứ tự tùy chọn
            if(i+1 == (answers.get(j).getiChoice()%questionCount)){
                // thêm lựa chọn của thí sinh vào tùy chọn
                LuaChon luaChon = new LuaChon(i+1,answers.get(j).getjChoice(),answers.get(j).getDrawX(),answers.get(j).getDrawY());
                tuyChons.add(luaChon);
                j++; i++;
            }else if (i + 1 < answers.get(j).getiChoice() )// Nếu thứ tự tùy chọn tiếp theo lớn hơn thứ tự tùy chọn(không có lựa chọn nào)
            {
               // Ta bổ sung thêm tùy chọn và giá giá trị là -1
                LuaChon luaChon = new LuaChon(i+1,-1,answers.get(j).getDrawX(),answers.get(j).getDrawY());
                tuyChons.add(luaChon);
                i++;// đi đến tùy chọn kết tiếp
            }
            else{ // Nếu tùy chọn tiếp theo nhỏ hơn thứ tự tùy chọn (có nhiều hơn 1 lựa chọn)
                // Ta không lưu lại đáp án này đồng thời gán nó với tùy chọn trước đó giá trị -1
                tuyChons.get(i).setjChoice(-1);
                answers.get(j).setColor(new Scalar(255,0,0));
                answers.get(j-1).setColor(new Scalar(255,0,0));
                j++;//xét phần tử tiếp theo.
            }
        }

        // Nếu tùy chọn có độ dài nhỏ hơn quy định thì những phần tử cuối cùng ta gán giá trị là -1.
        if(tuyChons.size() < questionCount){
            i = tuyChons.size();
            while(i<questionCount){
                LuaChon luaChon = new LuaChon(i,-1,0,0);
                tuyChons.add(luaChon);
                i++;
            }
        }

        return tuyChons;
    }
    public static void colorize(List<LuaChon> list, int s, Scalar color){
        for (int i = 0; i<list.size();i++){
            if(list.get(i).getiChoice()==s) list.get(i).setColor(color);
        }
    }
    public static Pair<Integer,List<LuaChon>> evaluate(List<LuaChon> luaChonThiSinh, List<LuaChon> luaChonDung) {
        int correctNumber = 0;
        List<LuaChon> result = new ArrayList<>();
        for (int i = 0; i< luaChonDung.size();i++){
            int dapAnDung = luaChonDung.get(i).getjChoice();
            int luaChonTS =  luaChonThiSinh.get(i).getjChoice();
            if(luaChonTS == dapAnDung ){
                correctNumber ++;
                result.add(luaChonThiSinh.get(i));
            }else{
                LuaChon luaChonSai = luaChonThiSinh.get(i);
                luaChonSai.setColor(new Scalar(255,0,0)); // to mau do
                LuaChon dapAn = luaChonDung.get(i);
                dapAn.setColor(new Scalar(100,100,100));
                result.add(luaChonSai);
                result.add(dapAn);
            }
        }
        return new Pair<Integer, List<LuaChon>>(correctNumber,result);
    }

}
