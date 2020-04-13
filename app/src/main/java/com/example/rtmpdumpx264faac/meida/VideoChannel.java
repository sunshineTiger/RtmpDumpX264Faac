package com.example.rtmpdumpx264faac.meida;

import android.app.Activity;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;

import com.example.rtmpdumpx264faac.LivePusher;

/**
 * 视频
 */
public class VideoChannel implements Camera.PreviewCallback, CameraHelper.OnChangedSizeListener {

    private static final String TAG = "tuch";
    private CameraHelper cameraHelper;
    private int mBitrate;
    private int mFps;
    private boolean isLiving;
    LivePusher livePusher;

    public VideoChannel(LivePusher livePusher, Activity activity, int width, int height, int bitrate, int fps, int cameraId) {
        mBitrate = bitrate;
        mFps = fps;
        this.livePusher = livePusher;
        cameraHelper = new CameraHelper(activity, cameraId, width, height);
        cameraHelper.setPreviewCallback(this);
        cameraHelper.setOnChangedSizeListener(this);
    }

    //data   nv21
    @Override
    public void onPreviewFrame(byte[] data, Camera camera) {
        //标志位，只有开始的时候才推流
        if (isLiving) {
            //开始推流
            livePusher.native_pushVideo(data);
        }

    }

    @Override
    public void onChanged(int w, int h) {
//
        Log.v(TAG, "w:" + w + " h:" + h + " mFps:" + mFps + " mBitrate:" + mBitrate);
        livePusher.native_setVideoEncInfo(w, h, mFps, mBitrate);
    }

    public void switchCamera() {
        cameraHelper.switchCamera();
    }

    public void setPreviewDisplay(SurfaceHolder surfaceHolder) {
        cameraHelper.setPreviewDisplay(surfaceHolder);
    }

    public void startLive() {
        isLiving = true;
    }

}
