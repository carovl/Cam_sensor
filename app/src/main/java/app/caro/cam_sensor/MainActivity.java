package app.caro.cam_sensor;


import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn, continuar;
    ImageView img; // donde se va a mostrar la foto
    Intent i;
    Bitmap bmp; // bitmap donde se va a guardar la imagen
    String imagen; //string donde se va a guardar el bitmap


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init(){
        btn= (Button)findViewById(R.id.button1);
        btn.setOnClickListener(this);

        continuar = (Button)findViewById(R.id.button2);
        continuar.setOnClickListener(this);

        img = (ImageView)findViewById(R.id.imagen);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extra = data.getExtras();
        bmp = (Bitmap)extra.get("data"); //obtengo la imagen
        img.setImageBitmap(bmp);
        imagen = BitMapToString(bmp); //la convierto a string para poderla pasar como extra
    }

    @Override
    public  void  onClick(View view){
        int id;
        id = view.getId();
        switch (id){
            case R.id.button1:
                i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,0); //es ,0 porque debe ser una constante
                break;

            case R.id.button2:
                Intent intent = new Intent(this, Giros.class);
                intent.putExtra("IMAGEN",imagen);
                startActivity(intent);

                break;

        }
    }

    //http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }




}