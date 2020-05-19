package com.vothanhhien.automarkmobile.constants;

import android.os.Environment;

import com.mohammedalaa.seekbar.RangeSeekBarView;
import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.activities.TuyChon.ChamDiemActivity;
import com.vothanhhien.automarkmobile.models.CauTraLoi;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class defines constants
 */

public class SC {

    public static final int MARKER_DOWN = 14 ;
    public static final int MARKER_UP= 30 ;
    public static final double CANNY_THRESH = 0;
    public static final int ROWS_EC = 3;
    public static final int GROUPS_EC = 1;
    public static final boolean HORIZONTAL_EC = false;
    public static final List<Character> OPTIONS_EC = new ArrayList<Character>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
    public static final int ROWS_SID = 6;
    public static final int GROUPS_SID = 1;
    public static final boolean HORIZONTAL_SID = false;
    public static final List<Character> OPTIONS_SID = new ArrayList<Character>(Arrays.asList('0','1','2','3','4','5','6','7','8','9'));
    public static final int ROWS_A = 10;
    public static final int GROUPS_A = 3;
    public static final boolean HORIZONTAL_A = true;
    public static final List<Character> OPTIONS_A = new ArrayList<Character>(Arrays.asList('A','B','C','D'));
    public static final int RATIO = 12;
    public static final int THR_D =  19;
    public static final int NONZERO_THRESH =  70;

    public static final CauTraLoi ID_CAU_TRA_LOI = new CauTraLoi(185,168,245,160,10,6);
    public static final CauTraLoi CODE_CAU_TRA_LOI = new CauTraLoi(185,66,245,80,10,3);
    public static final CauTraLoi BLOCK_CAU_TRA_LOI =  new CauTraLoi(0,0,1200,840,0,0);
    public static final CauTraLoi BLOCK_1_CAU_TRA_LOI = new CauTraLoi(560,576,510,144,20,4);
    public static final CauTraLoi BLOCK_2_CAU_TRA_LOI = new CauTraLoi(570,325,500,144,20,4);
    public static final CauTraLoi BLOCK_3_CAU_TRA_LOI = new CauTraLoi(570,80,500,144,20,4);
    public static final double THRESHOLDB = 200;
    public static final int MARKED_BUTTON_COLOR = R.drawable.button_background_circle_filled_gray;
    public static final int DEFAULT_BUTTON_COLOR = R.drawable.button_background_circle ;
    public static  int ZEROCOUNT_THRESH = 90 ;


    public static int count=0;
    public static final int THR_U = 28;
    public static final int D = 10;
    public static final int TOTAL_BUBBLE_EC = SC.ROWS_EC*SC.GROUPS_EC*SC.OPTIONS_EC.size();
    public static final int TOTAL_BUBBLE_SID = SC.ROWS_SID*SC.GROUPS_SID*SC.OPTIONS_SID.size();
    public static final int TOTAL_BUBBLE_A = SC.ROWS_A*SC.GROUPS_A*SC.OPTIONS_A.size();
    public static int getcount(){
        count  +=1;
        return count;
    }
    private static final int ENLARGE = 40;
    public static final Rect RECT_EC = new Rect(7*ENLARGE,1*ENLARGE,10*ENLARGE,4*ENLARGE);
    public static final Rect RECT_SID = new Rect(260,212,360,216);
//    public static final Rect RECT_SID = new Rect(7*ENLARGE,5*ENLARGE,10*ENLARGE,7*ENLARGE);
    public static final Rect RECT_A = new Rect(17*ENLARGE,1*ENLARGE,11*ENLARGE,17*ENLARGE);

    private static final String TAG = SC.class.getSimpleName();

    public static final String TECHNO_DIR = "AutoMarkMobile/";
    public static final String STORAGE_HOME =  Environment.getExternalStorageDirectory().getAbsolutePath() +"/";
    public static final String STORAGE_TECHNO =  STORAGE_HOME + TECHNO_DIR;
    public static final FileFilter jpgFilter  = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            if(pathname.isFile() && pathname.getName().endsWith(".jpg"))
                return true;
            return false;
        }
    };

    public static String INPUT_DIR = TECHNO_DIR + "JE/";
    public static String INPUT_ORIG_DIR =TECHNO_DIR+"JE/"+ "_Original";
    public static String INPUT_ERROR_DIR =TECHNO_DIR+"JE/"+ "_Error";
    public static String INPUT_CROP_DIR =TECHNO_DIR+"JE/"+ "_Crop";
    public static String IMAGE_PREFIX = "Image_";
    public static String CURR_DIR =  STORAGE_HOME + INPUT_DIR;
    public static String CURR_ORIG_DIR =  STORAGE_HOME + INPUT_ORIG_DIR;
    public static String CURR_ERROR_DIR =  STORAGE_HOME + INPUT_ERROR_DIR;

    public static int IMAGE_CTR = 1;

    public static final int uniform_width_hd = (int) (1000 / 1.75);
    public static final int uniform_height_hd = (int)(1231 / 1.75);
    public static final int uniform_width = (int) (1200);
    public static final int uniform_height = (int)(840);

    public static final int marker_scale_fac = 26;
    public static final double thresholdVar = 0.3;

    public static int TRUNC_THRESH = 150;
    public static int ZERO_THRESH = 155;
    public static int AUTOCAP_TIMER = 4; // will be multiplied by 1000
    public static int HOLD_TIMER = 5; // will be multiplied by 100
    public static int MARKER_SCALE = 100 ; // will be divided by 100
    public static int MATCH_PERCENT = 40; // will be divided by 100
    public static int BINARY_THRESH = 175; // will be divided by 100
    public static int KSIZE_BLUR = 3;
    public static int KSIZE_CLOSE = 10;
    public static int GAMMA_HIGH = 125 ; // will be divided by 100
    public static int CANNY_THRESHOLD_L = 85;
    public static int CANNY_THRESHOLD_U = 185;

    //TODO: put these into interface under configController
    public static boolean CLAHE_ON = true;
    public static boolean GAMMA_ON = true;
    public static boolean ERODE_ON = true;
    public static Mat markerToMatch;
    public static Mat markerEroded;
//
    private final RangeSeekBarView s_AUTOCAP_TIMER;
    private final RangeSeekBarView s_CANNY_THRESHOLD_L;
    private final RangeSeekBarView s_CANNY_THRESHOLD_U;
    private final RangeSeekBarView s_KSIZE_BLUR;
    private final RangeSeekBarView s_KSIZE_CLOSE;
    private final RangeSeekBarView s_TRUNC_THRESH;
    private final RangeSeekBarView s_ZERO_THRESH;
    private final RangeSeekBarView s_GAMMA_HIGH;
    private final RangeSeekBarView s_MARKER_SCALE;
    private final RangeSeekBarView s_MATCH_PERCENT;
    private final RangeSeekBarView s_BINARY_THRESH;

    public SC(ChamDiemActivity s) {
        s_AUTOCAP_TIMER = s.findViewById(R.id.autocap_timer);
        s_CANNY_THRESHOLD_L = s.findViewById(R.id.canny_l);
        s_CANNY_THRESHOLD_U = s.findViewById(R.id.canny_u);
        s_KSIZE_BLUR = s.findViewById(R.id.ksize_blur);
        s_KSIZE_CLOSE = s.findViewById(R.id.ksize_morph);
        s_TRUNC_THRESH = s.findViewById(R.id.trunc_thresh);
        s_ZERO_THRESH = s.findViewById(R.id.zero_thresh);
        s_GAMMA_HIGH = s.findViewById(R.id.gamma);
        s_MARKER_SCALE = s.findViewById(R.id.marker_scale);
        s_MATCH_PERCENT= s.findViewById(R.id.match_percent);
        s_BINARY_THRESH = s.findViewById(R.id.binary_thresh);
    }
    public void updateConfig(){
        AUTOCAP_TIMER = s_AUTOCAP_TIMER.getCurrentValue();
        CANNY_THRESHOLD_L = s_CANNY_THRESHOLD_L.getCurrentValue();
        CANNY_THRESHOLD_U = s_CANNY_THRESHOLD_U.getCurrentValue();
        KSIZE_BLUR = s_KSIZE_BLUR.getCurrentValue();
        KSIZE_CLOSE = s_KSIZE_CLOSE.getCurrentValue();
        TRUNC_THRESH = s_TRUNC_THRESH.getCurrentValue();
        ZERO_THRESH = s_ZERO_THRESH.getCurrentValue();
        GAMMA_HIGH = s_GAMMA_HIGH.getCurrentValue();
        MARKER_SCALE = s_MARKER_SCALE.getCurrentValue();
        ZEROCOUNT_THRESH = s_MATCH_PERCENT.getCurrentValue();
        BINARY_THRESH =  s_BINARY_THRESH.getCurrentValue();
    }
}
