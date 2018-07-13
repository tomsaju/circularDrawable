package android.tom.circulardrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements CircularProfileImage.ICircularProfileImageListener{

    private CircularProfileImage profileImage;
    private HorizontalScrollView scrollParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollParent = (HorizontalScrollView) findViewById(R.id.scrollParent);





    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout container = (LinearLayout) findViewById(R.id.container_pro);
        ArrayList<String> namelist = new ArrayList<>();
        namelist.add("allen");
        namelist.add("woody");
        namelist.add("hazard");
        namelist.add("pete");
        namelist.add("charles");
        container.removeAllViews();
        for (int i = 0; i <namelist.size() ; i++) {
             profileImage = new CircularProfileImage(this,getResources().getDrawable(R.drawable.pete),namelist.get(i),true,namelist.get(i)+" id ");
            profileImage.setProfileImageListener(this);
            container.addView(profileImage);
        }



    }

    public static Bitmap getCircularBitmapWithWhiteBorder(Bitmap bitmap,
                                                          int borderWidth) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }

        final int width = bitmap.getWidth() + borderWidth;
        final int height = bitmap.getHeight() + borderWidth;

        Bitmap canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        Canvas canvas = new Canvas(canvasBitmap);
        float radius = width > height ? ((float) height) / 2f : ((float) width) / 2f;
        canvas.drawCircle(width / 2, height / 2, radius, paint);
        paint.setShader(null);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(borderWidth);
        canvas.drawCircle(width / 2, height / 2, radius - borderWidth / 2, paint);
        return canvasBitmap;
    }

    @Override
    public void onProfileClosed(String name) {
        Toast.makeText(this, "closed "+name, Toast.LENGTH_SHORT).show();
    }
}
