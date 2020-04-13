package com.example.rtmpdumpx264faac.meida;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.example.rtmpdumpx264faac.LivePusher;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 音频
 */
public class AudioChannel {

    private LivePusher livePusher;
    private AudioRecord audioRecord;
    private int channels = 2;
    int channelConfig;
    private final int sampleRateInHz = 44100;
    private boolean isLiving = false;
    private ExecutorService executor;
    int minBufferSize;
    private int inputSamples;

    public AudioChannel(LivePusher livePusher) {
        executor = Executors.newSingleThreadExecutor();
        this.livePusher = livePusher;
        if (channels == 2) {
            channelConfig = AudioFormat.CHANNEL_IN_STEREO;
        } else {
            channelConfig = AudioFormat.CHANNEL_IN_MONO;
        }
        livePusher.native_setAudioEncInfo(sampleRateInHz, channels);
        inputSamples = livePusher.getInputSamples() * 2;
        minBufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, AudioFormat.ENCODING_PCM_16BIT) * 2;
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, channelConfig, AudioFormat.ENCODING_PCM_16BIT, Math.min(minBufferSize, inputSamples))
        ;
    }

    public void startLive() {
        isLiving = true;
        executor.submit(new AudioTask());
    }

    public void setChannels(int channels) {
        this.channels = channels;
    }

    class AudioTask implements Runnable {
        @Override
        public void run() {
            audioRecord.startRecording();
            //pcm原始数据
            byte[] bytes = new byte[inputSamples];
            while (isLiving) {
                int len = audioRecord.read(bytes, 0, bytes.length);
                if (len >= 0)
                    livePusher.native_pushAudio(bytes);
            }
        }
    }
}
