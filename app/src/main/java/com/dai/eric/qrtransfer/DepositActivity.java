package com.dai.eric.qrtransfer;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DepositActivity extends AppCompatActivity {

    private ImageView qrCodePic;
    private Button generateQRBTN;
    private EditText eTransferURL;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);

        qrCodePic = findViewById(R.id.qrImage);
        generateQRBTN = findViewById(R.id.btnGenerateQR);
        eTransferURL = findViewById(R.id.txtEURL);
    }

    public void generateQR(View view) {

        String eTransferURLString = eTransferURL.getText().toString();

        try {
            bitmap = TextToImageEncode(eTransferURLString);

            qrCodePic.setImageBitmap(bitmap);

        } catch (WriterException e) {
            Log.w("Eric", "Error occur when insert to database: " + e.toString());
        }

    }

    public void finishTransfer(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("History");
        FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String currentUerEmail = mFirebaseAuth.getCurrentUser().getEmail();

        TransferRecord newTransfer = new TransferRecord("out", timeStamp, currentUerEmail);

        try{
            myRef.push().setValue(newTransfer);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private Bitmap TextToImageEncode(String Value) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    500, 500, null
            );

        } catch (IllegalArgumentException Illegalargumentexception) {

            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++) {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++) {

                pixels[offset + x] = bitMatrix.get(x, y) ?
                        getResources().getColor(R.color.QRCodeBlackColor):getResources().getColor(R.color.QRCodeWhiteColor);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, 500, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
