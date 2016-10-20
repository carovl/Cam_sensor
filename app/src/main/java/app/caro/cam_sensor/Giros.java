package app.caro.cam_sensor;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;


public class Giros extends Activity implements SensorEventListener {

    Bitmap bitmap;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    ImageView iv, ivR, ivL, ivU, ivD;


    @Override
    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.giros);

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("IMAGEN")!= null)
        {
            bitmap = StringToBitMap(bundle.getString("IMAGEN"));
        }

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        iv = (ImageView) findViewById(R.id.img);
        ivU = (ImageView) findViewById(R.id.imgU);
        ivD = (ImageView) findViewById(R.id.imgD);
        ivL = (ImageView) findViewById(R.id.imgL);
        ivR = (ImageView) findViewById(R.id.imgR);


    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
        // TODO Auto-generated method stub
    }

    @Override
    protected void onResume () {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause () {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged (SensorEvent event){
        float x = event.values[0];
        float y = event.values[1];


        if (Math.abs(x) > Math.abs(y)) {
            if (x < 0) {
                ivR.setImageBitmap(bitmap);
                //ivR.setImageResource(R.mipmap.ic_launcher);
                ivL.setImageResource(R.mipmap.blanco);
                ivD.setImageResource(R.mipmap.blanco);
                ivU.setImageResource(R.mipmap.blanco);
                iv.setImageResource(R.mipmap.blanco);


            }

            if (x > 0) {
                ivL.setImageBitmap(bitmap);
                //ivL.setImageResource(R.mipmap.ic_launcher);
                ivR.setImageResource(R.mipmap.blanco);
                ivD.setImageResource(R.mipmap.blanco);
                ivU.setImageResource(R.mipmap.blanco);
                iv.setImageResource(R.mipmap.blanco);

            }

        } else {
            if (y < 0) {
                ivU.setImageBitmap(bitmap);
                //ivU.setImageResource(R.mipmap.ic_launcher);
                ivL.setImageResource(R.mipmap.blanco);
                ivD.setImageResource(R.mipmap.blanco);
                ivR.setImageResource(R.mipmap.blanco);
                iv.setImageResource(R.mipmap.blanco);

            }
            if (y > 0) {
                ivD.setImageBitmap(bitmap);
                //ivD.setImageResource(R.mipmap.ic_launcher);
                ivL.setImageResource(R.mipmap.blanco);
                ivR.setImageResource(R.mipmap.blanco);
                ivU.setImageResource(R.mipmap.blanco);
                iv.setImageResource(R.mipmap.blanco);

            }
        }

        if (x > (-2) && x < (2) && y > (-2) && y < (2)) {
            iv.setImageBitmap(bitmap);
            //iv.setImageResource(R.mipmap.ic_launcher);
            ivL.setImageResource(R.mipmap.blanco);
            ivD.setImageResource(R.mipmap.blanco);
            ivU.setImageResource(R.mipmap.blanco);
            ivR.setImageResource(R.mipmap.blanco);
        }

    }
    //http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

}

