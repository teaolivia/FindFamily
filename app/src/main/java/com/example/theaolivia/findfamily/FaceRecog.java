package com.example.theaolivia.findfamily;

/**
 * Created by theaolivia on 9/6/15.
 */



import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import android.media.FaceDetector;
import android.view.View;
import android.graphics.Bitmap;
import android.graphics.PointF;

import java.lang.String;
import android.os.Environment;
import android.graphics.Color;
import android.util.Log;
/* import yang belum kepake
*
* import java.lang.Object;
* import android.hardware.Camera.Face;
* import android.hardware.Camera.FaceDetectionListener;
* import android.graphics.Paint;
* import android.text;
* import android.util;
*
* */

public class FaceRecog {

    public class FaceView extends View{
        /*
         * Here is sample source code for face detection.
         * This sample code enables a custom View that shows
         * a saved image from an SD Card and draws transparent
         * red circles on the detected faces.
         *
         * */
        private static final int MAX_FACES = 10;
        private static final String IMAGE_FN = "image.jpg";
        private Bitmap background_image;
        private FaceDetector.Face[] faces;
        private int face_count;

        // prealokasi untuk method onDraw()
        private PointF tmp_point = new PointF();
        private Paint tmp_paint = new Paint();

        // ctor
        public FaceView(Context context){
            super(context);
            // Load gambar dari SD Card
            updateImage(Environment.getExternalStorageDirectory() + "/" + IMAGE_FN);
        }

        public void updateImage (String image_fn){
            // Set konfigurasi internal ke RGB_565
            BitmapFactory.Options bitmap_options = new BitmapFactory.Options();
            bitmap_options.inPreferredConfig = Bitmap.Config.RGB_565;

            background_image = BitmapFactory.decodeFile(image_fn, bitmap_options);
            FaceDetector face_detector = new FaceDetector( background_image.getWidth(), background_image.getHeight(),
                    MAX_FACES);

            faces = new FaceDetector.Face[MAX_FACES];
            // The bitmap must be in 565 format
            face_count = face_detector.findFaces(background_image, faces);
            Log.d("Face_Detection", "Face Count: " + String.valueOf(face_count));
        }

        public void onDraw(Canvas canvas){
            canvas.drawBitmap(background_image, 0, 0, null);
            for (int i = 0; i < face_count; i++) {
                FaceDetector.Face face = faces[i];
                tmp_paint.setColor(Color.RED);
                tmp_paint.setAlpha(100);
                face.getMidPoint(tmp_point);
                canvas.drawCircle(tmp_point.x, tmp_point.y, face.eyesDistance(),
                        tmp_paint);

            }
        }

        // public void
    }
}
