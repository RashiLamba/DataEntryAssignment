package com.example.dataentryassignment.utils;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SaveBitMap {

    public static Uri saveBitMapReturnUri(Bitmap bitmap) throws Exception{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_H", Locale.US).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bytes);

        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = File.createTempFile(imageFileName, ".jpg", storageDir);

        FileOutputStream fo = new FileOutputStream(file);
        fo.write(bytes.toByteArray());
        fo.close();


        return Uri.fromFile(file);

    }
}
