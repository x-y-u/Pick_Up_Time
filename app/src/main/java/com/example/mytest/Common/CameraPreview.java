package com.example.mytest.Common;

import android.content.Context;
import android.hardware.Camera;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;

public class CameraPreview extends SurfaceView implements  SurfaceHolder.Callback,Camera.AutoFocusCallback {
    private Camera mCamera;
    private SurfaceHolder mHolder;
    private Context context;
    private static final String TAG="CameraPreview";
    private static final String TAGDRAW="Draw";
    public CameraPreview(Context context, Camera camera) {
        super(context);
        mCamera=camera;
        this.context = context;
        mHolder=getHolder();
        mHolder.addCallback(this);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            mCamera.setPreviewDisplay(holder);
            mCamera.setDisplayOrientation(90);
            /*
            设置一下preview的大小
             */
            Camera.Parameters parameters=mCamera.getParameters();
            parameters.setPreviewSize(640,480);
            parameters.setPictureSize(640,480);
//            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            Display display = wm.getDefaultDisplay();
//            parameters.setPreviewSize(display.getWidth(),display.getHeight());
//            parameters.setPictureSize(display.getWidth(),display.getHeight());
            parameters.setJpegQuality(100);

            mCamera.setParameters(parameters);
            mCamera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        if(mHolder.getSurface()==null){
            return;
        }
        try {
            mCamera.stopPreview();
        }catch (Exception e){

        }
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.setDisplayOrientation(90);
            Camera.Parameters parameters=mCamera.getParameters();
            parameters.setPreviewSize(320,240);
            parameters.setPictureSize(320,240);
//            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//            Display display = wm.getDefaultDisplay();
//            parameters.setPreviewSize(display.getWidth(),display.getHeight());
//            parameters.setPictureSize(display.getWidth(),display.getHeight());
            parameters.setJpegQuality(100);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        }catch (Exception e){
            Log.d(TAG, "Errorstarting camera preview: " + e.getMessage());
        }
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();//停止预览
        mCamera.release();//释放相机资源
        mCamera = null;
        holder = null;
    }
    @Override
    public void onAutoFocus(boolean success, Camera Camera) {
        if (success) {
            Log.i(TAG, "onAutoFocus success="+success);
        }
    }
}
