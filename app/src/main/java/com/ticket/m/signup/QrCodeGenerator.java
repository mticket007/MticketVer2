package com.ticket.m.signup;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

import static android.widget.Toast.LENGTH_LONG;

public class QrCodeGenerator extends BasicActivity {
    private ImageView Qrcodeid;
    public final static int QrcodeWidth=500;
    private Bitmap bitmap;
    private String QrcodeValue;
    private TextView errorTv;
    String theValue;
    private FirebaseAuth auth;
    private StorageReference storageReference;

    //size of the Bitmap
    int size;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_generator);
        //instantiating the com.ticket.m.mticketapplicationintegration.QrDBHelper class
        //instantiating QrDBHelper class
        QrDBHelper qrDBHelper=new QrDBHelper(this);
        Bundle bundle=getIntent().getExtras();
        Qrcodeid=findViewById(R.id.Qrcodeid);
        auth=FirebaseAuth.getInstance();

        if (bundle.getInt("buttonValue")==1)
        {

            //checking if database is available
            SQLiteDatabase dbR = qrDBHelper.getReadableDatabase();
            Cursor  c=dbR.rawQuery("SELECT * FROM QrCode",null);
            if(c.moveToFirst()) {
                //reading blob data from sqlite db
                try {
                    String[] column = {"Qrcode"};
                    SQLiteDatabase dbReading = qrDBHelper.getReadableDatabase();
                    Cursor dbCursor = dbReading.rawQuery("select Qrcode from QrCode where Id=1",null);
                    dbCursor.moveToFirst();
                    int i = dbCursor.getColumnIndex("Qrcode");
                    byte[] byteArray = dbCursor.getBlob(dbCursor.getColumnIndex("Qrcode"));
                    Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                            /*ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(byteArray);
                            Bitmap bm=BitmapFactory.decodeStream(byteArrayInputStream);*/
                    Qrcodeid.setImageBitmap(bm);

                } catch (Exception e) {
                    System.out.println("error is " + e);
                }
            }


        }
        if(bundle.getInt("buttonValue")==2)
        {
            String type_of_ticket=getIntent().getStringExtra("type_of_ticket");
            String type_of_journey=getIntent().getStringExtra("type_of_journey");
            String sourceStation=getIntent().getStringExtra("source");
            sourceStation=sourceStation.replace(" ","_");
            String destinationStation=getIntent().getStringExtra("destination");
            destinationStation=destinationStation.replace(" ","_");
            float price=bundle.getFloat("price");
            String bookingTime,expiry_time;
            bookingTime=getIntent().getStringExtra("bookingTime");
            expiry_time=getIntent().getStringExtra("expiry_time");

            //errorTv instantiating
            errorTv=findViewById(R.id.errorTv);
            QrcodeValue="{\"sourceStation\":"+sourceStation+",\"destinationStation\":"+destinationStation+",\"fare\":"+price+",\"bookingTime\":"+bookingTime+",\"expiry_time\":"+expiry_time+",\"type_of_ticket\":"+type_of_ticket+",\"type_of_journey\":" + type_of_journey + "}";
            try{

                //error is in this part while converting from string to the json
                JSONObject jsonObject=new JSONObject(QrcodeValue);
                theValue=jsonObject.toString();

            }
            catch(JSONException e)
            {

                e.printStackTrace();

            }
            try {
                //passing the value to the TextToImageEncode() method to generate the qr code
                bitmap = TextToImageEncode(theValue);
            } catch (Exception e) {
                System.out.println("error is " + e);
            }

            Qrcodeid.setImageBitmap(bitmap);
           SQLiteDatabase db = qrDBHelper.getWritableDatabase();
           db.delete("QrCode","Id=?",new String[]{"1"});
            //getting an image file from the Qrcodeid image view and converting it to the drawable image adding it to the db
            Bitmap bt = ((BitmapDrawable) Qrcodeid.getDrawable()).getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bt.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] img = byteArrayOutputStream.toByteArray();
            ContentValues values = new ContentValues();
            values.put("Id",1);
            values.put("Qrcode", img);
            db.insert("QrCode", null, values);
            String userId=auth.getCurrentUser().getUid();
            storageReference=FirebaseStorage.getInstance().getReference(userId).child(auth.getCurrentUser().getEmail());
            UploadTask uploadTask=storageReference.putBytes(img);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    if (taskSnapshot.getTask().isSuccessful()) {
                        Toast.makeText(QrCodeGenerator.this, "Successful", Toast.LENGTH_LONG);

                    }
                    else
                    {
                        Toast.makeText(QrCodeGenerator.this,"Unsuccessful",Toast.LENGTH_LONG);
                    }
                }
            });




            // current date of the generation of the QRCODE and Random number

        }




        }

       /* catch (WriterException e)
        {
            e.printStackTrace();
        }*/

    //method for generating the Qrcode
    Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try
        {
            bitMatrix=new MultiFormatWriter().encode(Value, BarcodeFormat.DATA_MATRIX.QR_CODE, QrcodeWidth,QrcodeWidth,null);
        }
        catch (IllegalArgumentException e)
        {
            return null;
        }
        int bitMatrixWidth=bitMatrix.getWidth();
        int bitMatrixHeight=bitMatrix.getHeight();
        int[] pixels=new int[bitMatrixWidth*bitMatrixHeight];
        for(int y=0;y<bitMatrixHeight;y++)
        {
            int offset=y*bitMatrixWidth;
            for(int x=0;x<bitMatrixWidth;x++)
            {
                pixels[x+offset]=bitMatrix.get(x,y)?getResources().getColor(R.color.blackColor):getResources().getColor(R.color.whiteColor);
            }
        }
        Bitmap  bitmap=Bitmap.createBitmap(bitMatrixWidth,bitMatrixHeight, Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels,0,500,0,0,bitMatrixWidth,bitMatrixHeight);
        return bitmap;
    }


}

