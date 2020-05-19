package com.vothanhhien.automarkmobile.activities.TuyChon;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.shapes.PathShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;


import com.nightonke.jellytogglebutton.JellyToggleButton;
import com.nightonke.jellytogglebutton.State;
import com.vothanhhien.automarkmobile.R;
import com.vothanhhien.automarkmobile.activities.DapAn.DanhSachDapAn;
import com.vothanhhien.automarkmobile.activities.PhieuTraLoi.PhieuTraLoi;
import com.vothanhhien.automarkmobile.constants.SC;
import com.vothanhhien.automarkmobile.models.BaiThi;
import com.vothanhhien.automarkmobile.models.CauTraLoi;
import com.vothanhhien.automarkmobile.models.KhungTraLoi;
import com.vothanhhien.automarkmobile.models.LuaChon;
import com.vothanhhien.automarkmobile.models.Quadrilateral;
import com.vothanhhien.automarkmobile.sqlite.DapAnDatabase;
import com.vothanhhien.automarkmobile.sqlite.KhungTraLoiDatabase;
import com.vothanhhien.automarkmobile.sqlite.PhieuTraLoiDatabase;
import com.vothanhhien.automarkmobile.utils.FileUtils;
import com.vothanhhien.automarkmobile.utils.ImageDetectionProperties;
import com.vothanhhien.automarkmobile.utils.UtilityFunctions;
import com.vothanhhien.automarkmobile.utils.Utils;
import com.vothanhhien.automarkmobile.view.ScanCanvasView;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.view.View.GONE;
import static org.opencv.imgproc.Imgproc.threshold;

public class ChamDiemActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {
    //    // required for Imread etc: https://stackoverflow.com/questions/35090838/no-implementation-found-for-long-org-opencv-core-mat-n-mat-error-using-opencv
    private static final String mOpenCvLibrary = "opencv_java3";
    static {
        System.loadLibrary(mOpenCvLibrary);
    }
    public static int block;
    private Bitmap tempBitmap,resultBitMap;
    private boolean isCapturing = false,turnOnAuto = false;
    private CameraBridgeViewBase mOpenCvCameraView;
    private CountDownTimer autoCaptureTimer, showHoverTimer;
    private FrameLayout acceptLayout;
    private FrameLayout cameraPreviewLayout;
    private int saveRows, saveCols;
    private int secondsLeft;
    private JellyToggleButton startAutoCapture,check_btn;
    private LinearLayout captureHintLayout;
    private long sheet;
    private Mat outMat,originMat;
    private Mat saveOutMat,saveOriginMat;
    private Point[] savePoints;
    private Resources res;
    private SC configController;
    private ScanCanvasView scanCanvasView;
    private static final String TAG = "MainActivity";
    private TextView captureHintText;
    private TextView timeElapsedText;
    private View cropAcceptBtn;
    private View cropRejectBtn;
    private ViewGroup containerScan;
    public boolean acceptLayoutShowing = false, acceptResult=false, canCheckMarker=false;;
    private List<KhungTraLoi> khungs;
    private ImageView showResult;
    private int mode;
    private BaiThi baithi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_camera);
        res = getResources();
        init();

        cropAcceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View v) {
                v.setEnabled(false);
                v.setClickable(false);
                Log.d("custom"+TAG, "Image Accepted.");
                FileUtils.checkMakeDirs(SC.CURR_DIR);
                FileUtils.checkMakeDirs(SC.CURR_ORIG_DIR);
                FileUtils.checkMakeDirs(SC.CURR_ERROR_DIR);
                FileUtils.checkMakeDirs(SC.INPUT_CROP_DIR);
                SC.IMAGE_CTR = new File(SC.CURR_DIR).listFiles(SC.jpgFilter).length + 1;
                final String IMAGE_NAME = SC.IMAGE_PREFIX + SC.IMAGE_CTR+".jpg";

                Toast.makeText(ChamDiemActivity.this, "Saving to: " + IMAGE_NAME, Toast.LENGTH_SHORT).show();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tempBitmap = Utils.matToBitmapRotate(saveOriginMat);
                        FileUtils.saveBitmap(tempBitmap, SC.CURR_ORIG_DIR, IMAGE_NAME);

                        Mat warpOrigin = Utils.four_point_transform_scaled(saveOriginMat, saveCols, saveRows, savePoints);
                        Utils.resize_util_inplace(warpOrigin, SC.uniform_width, SC.uniform_height);
                        Mat imageResult = warpOrigin.clone();
                        Mat matDraw = warpOrigin.clone();
                        tempBitmap = Utils.matToBitmapRotate(warpOrigin);
                        FileUtils.saveBitmap(tempBitmap,SC.INPUT_CROP_DIR,IMAGE_NAME);

                        // vẽ tất cả tâm của các lựa chọn để tiện bề fix vị trí

                        if (checkBtn(R.id.check_btn)){
                            Utils.drawAllChoice(matDraw, khungs);
                            tempBitmap = Utils.matToBitmapRotate(matDraw);
                            FileUtils.saveBitmap(tempBitmap,SC.CURR_ERROR_DIR,IMAGE_NAME);
                        }
                        // stranform hình ảnh xử lý
                        Mat warpOut = Utils.four_point_transform_scaled(saveOutMat, saveCols, saveRows, savePoints);
                        Utils.resize_util_inplace(warpOut, SC.uniform_width, SC.uniform_height);


                        // Chuyển sang ảnh nhị phân để xử lý
                        Mat image_gray = new Mat();
                        threshold(warpOut,image_gray,SC.BINARY_THRESH,255,Imgproc.THRESH_BINARY);
                        FileUtils.saveMat(image_gray,SC.CURR_DIR,"image_gray.jpg");
                        PhieuTraLoi phieuTraLoi = new PhieuTraLoi();
                        List<LuaChon> options, luaChons = new ArrayList<>();
                        //  Lấy Mã đề
                        KhungTraLoi khung0 = khungs.get(0);
                        CauTraLoi code = new CauTraLoi(khung0.getX(),khung0.getY(),khung0.getWidth(),khung0.getHeight(),khung0.getRows(),khung0.getCols());
                        phieuTraLoi.setMaDe(code.getCode(warpOut));
                        luaChons.addAll(code.getmLuaChon());

                        // Lấy Số Báo Danh
                        KhungTraLoi khung1 = khungs.get(1);
                        CauTraLoi soBaoDanh = new CauTraLoi(khung1.getX(),khung1.getY(),khung1.getWidth(),khung1.getHeight(),khung1.getRows(),khung1.getCols());
                        phieuTraLoi.setSBD(soBaoDanh.getCode(warpOut));
                        luaChons.addAll(soBaoDanh.getmLuaChon());

                        // Lấy các câu trả lời
                        List<LuaChon> tuyChons = new ArrayList<>();
                        String luaChonTS = "";
                        int questionCount = baithi.getSoCau();
                        for(int i = 2; i< khungs.size(); i++){
                            KhungTraLoi khungi = khungs.get(i);
                            CauTraLoi luaChonThiSinh = new CauTraLoi(khungi.getX(),khungi.getY(),khungi.getWidth(),khungi.getHeight(),khungi.getRows(),khungi.getCols());
                            luaChonThiSinh.setOrder(i-2);
                            luaChonTS += luaChonThiSinh.getAnswers(warpOut);
                            tuyChons.addAll(luaChonThiSinh.getmLuaChon());
                        }

                        phieuTraLoi.setDS_CauTraLoi(luaChonTS.substring(0,questionCount));
                        Bitmap anhSauNhanDang;
                        if (mode == 0) {// chế độ nhập đáp án đúng
                            luaChons.addAll(tuyChons);
                            imageResult = Utils.drawAnswers(imageResult, luaChons);
                            anhSauNhanDang = Utils.matToBitmapRotate(imageResult);

                            // lấy kết quả
                            String data = phieuTraLoi.getMaDe() +"_"+phieuTraLoi.getDS_CauTraLoi();
                            Intent result = new Intent();
                            result.setData(Uri.parse(data));
                            setResult(DanhSachDapAn.CAMERA_INPUT_CODE, result);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    onBackPressed();
                                    finish();
                                }
                            });

                        } else{

                            //  lấy đáp đáp án đúng
                            DapAnDatabase dapAnDatabase = new DapAnDatabase(ChamDiemActivity.this);
                            int idDapAnDung = dapAnDatabase.LayIdMaDe(Integer.parseInt(phieuTraLoi.getMaDe()),Integer.parseInt(baithi.getId()));
                            if (idDapAnDung>0){
                                com.vothanhhien.automarkmobile.activities.DapAn.DapAn dapAnDung = dapAnDatabase.layDapAn(idDapAnDung);

                                // đánh giá kết quả
                                List<LuaChon> dapAnsDung = UtilityFunctions.convertString2ChoicesAnswer(dapAnDung.getDapAn(),khungs);
                                Pair<Integer,List<LuaChon>> ketQua = UtilityFunctions.evaluate(tuyChons,dapAnsDung);
                                phieuTraLoi.setDiem(ketQua.first/(float)questionCount);
                                phieuTraLoi.setSoCauDung(ketQua.first);
                                luaChons.addAll(ketQua.second);
                            }else{
                                phieuTraLoi.setDiem(0);
                                phieuTraLoi.setSoCauDung(0);
                                luaChons.addAll(tuyChons);
                            }


                            PhieuTraLoiDatabase phieuTraLoiDatabase = new PhieuTraLoiDatabase(ChamDiemActivity.this);
                            phieuTraLoi.setID((phieuTraLoiDatabase.MaxID()+1)+"");
                            phieuTraLoi.setMaBaiThi(baithi.getId());
                            phieuTraLoi.setSoCauCuaBaiThi(questionCount);

                            // crop phần ảnh có chứa thông tin sinh viên

                            Mat imageName = imageResult.clone();
                            Bitmap nameBitMat = Utils.matToBitmapRotate(imageName);
                            Point[] points = new Point[]{
                                    new Point(240,120),
                                    new Point(480,120),
                                new Point(480,440),
                                new Point(240,440)
                            };

                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            Bitmap cropBitmap = Utils.cropBitmap(nameBitMat,points);
                            cropBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                            byte[] byteArray = stream.toByteArray();
                            phieuTraLoi.setName_image(byteArray);


                            // biểu diễn ảnh sau khi đánh giá

                            Utils.drawAnswers(imageResult,luaChons);

                            Mat textRotate = new Mat(imageResult.size(),imageResult.type(),new Scalar(0));
                            Imgproc.putText(textRotate, "Ma De: "+phieuTraLoi.getMaDe(),
                                    new Point(200, 250),
                                    Core.FONT_HERSHEY_SIMPLEX, 1, new Scalar(255,-255,-255), 3);
                            Imgproc.putText(textRotate, "MSSV: "+phieuTraLoi.getSBD(),
                                    new Point(200, 300),
                                    Core.FONT_HERSHEY_SIMPLEX, 1, new Scalar(255,-255,-255), 3);
                            Imgproc.putText(textRotate, Math.round(phieuTraLoi.getDiem()*100)+"%",
                                    new Point(200, 400),
                                    Core.FONT_HERSHEY_SIMPLEX, 4, new Scalar(255,-255,-255), 3);

                            Utils.rotate(textRotate,90);
                            Core.add(textRotate,imageResult,imageResult);

                            Bitmap imageBitMap = Utils.matToBitmapRotate(imageResult);
                            ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                            imageBitMap.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                            byte[] byteArray1 = stream1.toByteArray();
                            phieuTraLoi.setPTL_image(byteArray1);

                            phieuTraLoiDatabase.ThemPhieuTL(phieuTraLoi);
                        }
                        resultBitMap = Utils.matToBitmapRotate(imageResult);
                        acceptResult=true;
                    }
                }).start();
                cancelAutoCapture();
                v.setEnabled(true);
                v.setClickable(true);
            }
        });

        cropRejectBtn = findViewById(R.id.crop_reject_btn);
        cropRejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAutoCapture();
            }
        });
        for( int id : new int []{R.id.xray_btn,R.id.clahe_btn,R.id.gamma_btn,R.id.contour_btn} ){
            JellyToggleButton btn = findViewById(id);
            btn.setChecked(false);

            btn.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
                @Override
                public void onStateChange(float process, State state, JellyToggleButton jtb) {
                    cancelAutoCapture();
                }
            });
        }
        for( int id : new int []{R.id.hover_btn,R.id.canny_btn,R.id.morph_btn} ){
            JellyToggleButton btn = findViewById(id);
            btn.setChecked(true);
            btn.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
                @Override
                public void onStateChange(float process, State state, JellyToggleButton jtb) {
                    cancelAutoCapture();
                }
            });
        }

        startAutoCapture.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
            @Override
            public void onStateChange(float process, State state, JellyToggleButton jtb) {
                switch (state){
                    case LEFT:
                        turnOnAuto = false;
                        cancelAutoCapture();
                        break;
                    case RIGHT:
                        turnOnAuto = true;
                        break;
                }
            }
        });
        JellyToggleButton flash = findViewById(R.id.flash_btn);
        flash.setOnStateChangeListener(new JellyToggleButton.OnStateChangeListener() {
            @Override
            public void onStateChange(float process, State state, JellyToggleButton jtb) {
                switch (state){
                    case LEFT:
                        ((JavaCameraView)mOpenCvCameraView).turnOffTheFlash();
                        break;
                    case RIGHT:
                        ((JavaCameraView)mOpenCvCameraView).turnOnTheFlash();
                        break;
                }
            }
        });
        onCameraGranted();
    }

    private void init() {

        startAutoCapture = findViewById(R.id.start_btn);
        configController = new SC(ChamDiemActivity.this);
        containerScan = findViewById(R.id.container_scan);
        cameraPreviewLayout = findViewById(R.id.camera_preview);
        mOpenCvCameraView = findViewById(R.id.java_camera_view);
        mOpenCvCameraView.setMaxFrameSize(3000, 3000);
        captureHintLayout = findViewById(R.id.capture_hint_layout);
        timeElapsedText = findViewById(R.id.time_elapsed_text);
        captureHintText = findViewById(R.id.capture_hint_text);
        acceptLayout = findViewById(R.id.crop_layout);
        cropAcceptBtn = findViewById(R.id.crop_accept_btn);
        showResult = findViewById(R.id.showResult);
        Bundle bundle = getIntent().getExtras();
        mode = bundle.getInt("mode");
        baithi = (BaiThi) getIntent().getSerializableExtra("BaiThi");
        if (baithi == null) {
            Toast.makeText(this, "Không tải được bài thi! Tạm ngưng việc chấm!", Toast.LENGTH_SHORT).show();
            onBackPressed();
            finish();
            return;
        }
        KhungTraLoiDatabase khungTraLoiDatabase = new KhungTraLoiDatabase(ChamDiemActivity.this);
        khungs = khungTraLoiDatabase.TatCaKhungTL(baithi.getLoaiDe());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Chấm "+baithi.getTen());
    }
    // Sự kiện khi người dùng nhấn nút mũi tên quay lại
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }
    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG, "onPause activity.");
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        mOpenCvCameraView.enableView();
    }

    private void onCameraGranted() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ScanCanvasView scanCanvasView = new ScanCanvasView(ChamDiemActivity.this);
                        cameraPreviewLayout.addView(scanCanvasView);
                        ChamDiemActivity.this.scanCanvasView = scanCanvasView;
                        mOpenCvCameraView.setCvCameraViewListener(ChamDiemActivity.this);

                        mOpenCvCameraView.enableView();
                        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
                    }
                });
            }
//        TODO: Find out why this delay is there
        }, 500);
    }
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        outMat = inputFrame.rgba();
        originMat = outMat.clone();
        Imgproc.cvtColor(outMat, outMat, Imgproc.COLOR_BGR2GRAY, 4);
        configController.updateConfig();
        SC.CLAHE_ON = checkBtn(R.id.clahe_btn);
        SC.GAMMA_ON = checkBtn(R.id.gamma_btn);
        SC.ERODE_ON = checkBtn(R.id.hover_btn);
        Mat processedMat = Utils.preProcessMat(outMat);
        //scanCanvasView.unsetHoverBitmap();
        if(acceptResult) {
            acceptResult = false;
           scanCanvasView.setHoverBitmap(resultBitMap);
           tryShowHover();
        }
        if (!checkBtn(R.id.xray_btn)) {
            try {
                Quadrilateral page = Utils.findPage(processedMat);
                if (null != page) {
                    Size originalPreviewSize = processedMat.size();
                    int originalPreviewArea = processedMat.rows() * processedMat.cols();
                    double contourArea = Math.abs(Imgproc.contourArea(page.contour));
                    guidedDrawRect(processedMat, page.points, contourArea, originalPreviewSize, originalPreviewArea);
                } else {
                    cancelAutoCapture();
                }
            } catch (Exception e) {
                cancelAutoCapture();
            }
        }
        else{
            cancelAutoCapture();
            scanCanvasView.clear();

            if(checkBtn(R.id.morph_btn))
                Utils.morph(processedMat);

            Utils.thresh(processedMat);

            if(checkBtn(R.id.canny_btn))
                Utils.canny(processedMat);
            if(checkBtn(R.id.contour_btn))
                Utils.drawContours(processedMat);
            // TODO : templateMatching output here?!
        }

        tempBitmap = Utils.matToBitmapRotate(processedMat);
        scanCanvasView.setCameraBitmap(tempBitmap);
        invalidateCanvas();
        processedMat.release();

        return outMat;
    }
    private Boolean checkBtn(Integer k){
        return ((JellyToggleButton)findViewById(k)).isChecked();
    }

    private void guidedDrawRect(Mat processedMat, Point[] points, double contourArea, Size stdSize, int previewArea) {
        float previewWidth = (float) stdSize.height;
        float previewHeight = (float) stdSize.width;
        double resultHeight = Math.max(points[1].x - points[0].x, points[2].x - points[3].x);
        double resultWidth = Math.max(points[3].y - points[0].y, points[2].y - points[1].y);
        List<Point> pointsList = Arrays.asList(points);
        ImageDetectionProperties imgDetectionPropsObj
                = new ImageDetectionProperties(previewWidth, previewHeight, resultWidth, resultHeight,
                previewArea, contourArea, points[0], points[1], points[2], points[3]);
        if(imgDetectionPropsObj.checkOK(pointsList)){
                if (acceptLayoutShowing) {
                    saveOriginMat = originMat.clone();
                    saveOutMat = outMat.clone();
                    saveCols = processedMat.cols();
                    saveRows = processedMat.rows();
                    savePoints = points.clone();
                }
                tryAutoCapture();

            Path path = new Path();
            path.moveTo(previewWidth - (float) points[0].y, (float) points[0].x);
            path.lineTo(previewWidth - (float) points[1].y, (float) points[1].x);
            path.lineTo(previewWidth - (float) points[2].y, (float) points[2].x);
            path.lineTo(previewWidth - (float) points[3].y, (float) points[3].x);
            path.close();

            PathShape newBox = new PathShape(path, previewWidth, previewHeight-50);
            Paint paint = new Paint();
            Paint border = new Paint();

            border.setStrokeWidth(7);
            setPaintAndBorder(paint, border);
            scanCanvasView.clear();
            scanCanvasView.addShape(newBox, paint, border);
        }else{
            scanCanvasView.clear();
        }
    }
    private void setPaintAndBorder(Paint paint, Paint border) {
        int paintColor = Color.argb(30, 38, 216, 76);
        int borderColor = Color.rgb(38, 216, 76);
        paint.setColor(paintColor);
        border.setColor(borderColor);
    }

    private void doAutoCapture() {
        if (isCapturing) return;
        try {
            Log.d(TAG,"autoCapture action.");
            isCapturing = true;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
                TransitionManager.beginDelayedTransition(containerScan);
            startAutoCapture.setChecked(false);
            cropAcceptBtn.performClick();
        } catch (Exception e) {
            e.printStackTrace();
        }
        isCapturing = false;
    }
    private synchronized void tryShowHover() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showHoverTimer = new CountDownTimer(5 * 1000, 1000) {
                        public void onTick(long millisUntilFinished) {
                        }

                        public void onFinish() {
                            scanCanvasView.unsetHoverBitmap();

                        }
                    };
                    showHoverTimer.start();
                }
            });
        }
    private  void tryAutoCapture() {
        if(!acceptLayoutShowing && turnOnAuto) {
            acceptLayoutShowing = true;
            secondsLeft = 0;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    captureHintLayout.setVisibility(View.VISIBLE);
                    autoCaptureTimer = new CountDownTimer(SC.AUTOCAP_TIMER * 1000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            secondsLeft = Math.round((float) millisUntilFinished / 1000.0f);
                            captureHintText.setText(res.getString(R.string.timer_text,secondsLeft));
                        }

                        public void onFinish() {
                            secondsLeft = 0;
                            acceptLayoutShowing = false;
                            captureHintLayout.setVisibility(GONE);
                            captureHintText.setText( res.getString(R.string.timer_text,0));
                            doAutoCapture();
                            scanCanvasView.unsetCameraBitmap();
                        }
                    };
                    autoCaptureTimer.start();
                }
            });
        }
    }
    public void cancelAutoCapture() {
        if (acceptLayoutShowing) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    acceptLayoutShowing = false;
                    autoCaptureTimer.cancel();
                    secondsLeft = 0;
                    captureHintLayout.setVisibility(View.GONE);

                }
            });
        }
    }

    public void invalidateCanvas() {
        scanCanvasView.postInvalidate();
    }

    public void onCameraViewStarted(int width, int height) {
        Log.d(TAG, "onCameraViewStarted ");
    }
    public void onCameraViewStopped() {
        Log.d(TAG, "onCameraViewStopped ");
    }

    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }
    public void exitApp(){
        System.gc();
        finish();
    }
}