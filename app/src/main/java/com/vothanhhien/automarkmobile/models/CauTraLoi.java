package com.vothanhhien.automarkmobile.models;

import com.vothanhhien.automarkmobile.constants.SC;
import com.vothanhhien.automarkmobile.utils.FileUtils;
import com.vothanhhien.automarkmobile.utils.UtilityFunctions;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CauTraLoi {
    public int x,y,w,h,rows,cols;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int order;
    private List <LuaChon> mLuaChon;
    public CauTraLoi(int x, int y, int w, int h, int rows, int cols) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rows = rows;
        this.cols = cols;
        this.order = 0;
    }
    public List<LuaChon> findAnswers(Mat image){
        List<LuaChon> answers = new ArrayList<>();
        if (rows==0 || cols == 0) return answers;
        int dx = w/rows;
        int dy = h/cols;
        int x0 = 0,y0 = 0;

        Rect rect = new Rect(x, y, w, h);
        Mat sub_image = image.submat(rect);
        int count = SC.getcount();
//        FileUtils.saveMat(sub_image, SC.CURR_DIR,"image_sub"+SC.getcount()+".jpg");

        // get number zero at each cell
        ArrayList<List<Cell>> filled = new ArrayList<>();
        for(int i = 0; i<rows;i++) filled.add(new ArrayList<>());
        List<Cell> cells = new ArrayList<>();
        for (int i=0 ; i<rows;i++){
            for (int j=0;j<cols;j++){
                int _x = x0+dx*i;
                int _y = y0+dy*j;
                Rect cellRect = new Rect(_x,_y,dx,dy);
                Mat cellMat = sub_image.submat(cellRect);
//                FileUtils.saveMat(cellMat, SC.CURR_DIR,count+"cell mat"+i+"-"+j+".jpg");
                Cell cell = countZero(cellMat);
                cell.setX(cell.getX()+_x);
                cell.setY(cell.getY()+_y);
                cells.add(cell);
            }
            filled.get(i).addAll(cells);
            cells.clear();
        }

        Cell cell ;

        for(int i = 0;i<rows;i++){
            for(int j =0;j<cols;j++){
                cell = filled.get(i).get(j);
                if(cell.getZeroCount()>= SC.ZEROCOUNT_THRESH){
                    LuaChon luaChon = new  LuaChon(i + rows*order,j,cell.getX()+x+SC.RATIO/2,cell.getY()+y+SC.RATIO/2);
                    luaChon.setZeroCount(cell.getZeroCount());
                    answers.add(luaChon);
                }
            }
        }

        return answers;
    }

    private Cell countZero(Mat cellMat) {
        int w = cellMat.width()-SC.RATIO;
        int h = cellMat.height()-SC.RATIO;
        int area = (int) Math.pow(SC.RATIO,2);
        int pointBlackMax = 0;
        int i0=0,j0=0;
        for(int i = 0; i<w ; i++){
            for (int j = 0; j<h; j++){
                Rect subCellRect = new Rect(i,j,SC.RATIO,SC.RATIO);
                Mat subCellMat = cellMat.submat(subCellRect);
//                FileUtils.saveMat(subCellMat,SC.CURR_DIR,"cell"+i+"_"+j+".jpg");
                int pointWhite = Core.countNonZero(subCellMat);
                int pointBlack = area-pointWhite;
                if(pointBlack>pointBlackMax){
                    pointBlackMax = pointBlack;
                    i0=i;
                    j0=j;
                }
            }
        }
        return new Cell(i0,j0,pointBlackMax);
    }

    public List<LuaChon> getmLuaChon() {
        return mLuaChon;
    }

    public void setmLuaChon(List<LuaChon> mLuaChon) {
        this.mLuaChon = mLuaChon;
    }

    public String getCode(Mat image){
        Mat image_gray = new Mat();
        Imgproc.threshold(image,image_gray,175,255,Imgproc.THRESH_BINARY);
        List<LuaChon> answers = findAnswers(image_gray);
        answers = compensation(answers);
        answers = sort(answers);
        answers = swap(answers);
        answers = UtilityFunctions.standAnswers(answers,cols);
        String codeAnswer = UtilityFunctions.convertChoices2StringCode(answers);
        this.mLuaChon = answers;
        return codeAnswer;
    }

    private List<LuaChon> swap(List<LuaChon> answers) {
        List<LuaChon> answers_swap = new ArrayList<>();
        for (int i =0 ; i<answers.size();i++){
            LuaChon luaChon = answers.get(i);
            int temp = luaChon.getiChoice();
            luaChon.setiChoice(luaChon.getjChoice());
            luaChon.setjChoice(temp);
            answers_swap.add(luaChon);
        }
        return answers_swap;
    }

    public String getAnswers(Mat image){
        // chuyển sang ảnh nhị phân
        Mat image_gray = new Mat();
        Imgproc.threshold(image,image_gray,SC.BINARY_THRESH,255,Imgproc.THRESH_BINARY);
        List<LuaChon> answers = findAnswers(image_gray);
        answers = compensation(answers);
        answers = UtilityFunctions.standAnswers(answers,rows);
        answers = increcment(answers);
        String codeAnswer = UtilityFunctions.convertChoices2StringCode(answers);
        this.mLuaChon = answers;
        return codeAnswer;
    }

    private List<LuaChon> increcment(List<LuaChon> answers) {
        List<LuaChon> luaChons = new ArrayList<>();
        for (int i=0;i<answers.size();i++){
            LuaChon luaChon =  answers.get(i);
            luaChon.setjChoice(luaChon.getjChoice()+1);
            luaChons.add(luaChon);
        }
        return luaChons;
    }

    private List<LuaChon> sort(List<LuaChon> answers) {
        Collections.sort(answers, new Comparator<LuaChon>() {
            @Override
            public int compare(LuaChon o1, LuaChon o2) {
                if (o1.getjChoice()>o2.getjChoice()) return 1;
                else if  (o1.getjChoice() == o2.getjChoice()) return 0;
                    else return -1;
            }
        });
        return answers;
    }
/*  Đảo ngược thức thự các lưa chọn


 */
   private  List<LuaChon> compensation(List<LuaChon> answers){
       List<LuaChon> answers_compen = new ArrayList<>();
       for (int i =0 ; i<answers.size();i++){
           LuaChon luaChon = answers.get(i);
           luaChon.setjChoice(cols - luaChon.getjChoice()-1);
           answers_compen.add(luaChon);
       }
       return answers_compen;
    }

}
