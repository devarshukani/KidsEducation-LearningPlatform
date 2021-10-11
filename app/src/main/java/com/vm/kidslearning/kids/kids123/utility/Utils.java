package com.vm.kidslearning.kids.kids123.utility;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;

/**
 * Created by VISHAL on 7/24/2019.
 */

public class Utils {

    private String TAG = Utils.class.getSimpleName();
    private Context _context;

    // constructor
    public Utils(Context context) {
        this._context = context;
    }

    /*
     * getting screen width
     */
    @SuppressWarnings("deprecation")
    public int getScreenWidth() {
        int columnWidth;
        WindowManager wm = (WindowManager) _context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) {
            // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        columnWidth = point.x;
        return columnWidth;
    }
    public void setAsWallpaper(Bitmap bitmap) {
        try {
            WallpaperManager wm = WallpaperManager.getInstance(_context);

            wm.setBitmap(bitmap);
            Toast.makeText(_context,
                    "Wallpaper Set Successfully",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(_context,
                    "Wallpaper Not Set",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
