package org.illegaller.ratabb.hishoot2i.ui.widget;

import org.illegaller.ratabb.hishoot2i.utils.BitmapUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;

public class BadgeDrawable extends Drawable {


    private Bitmap bitmap;
    private int width;
    private int height;
    private Paint paint;

    public BadgeDrawable(Context context, String badgeText, @ColorInt int color) {
        if (bitmap == null) {
            bitmap = BitmapUtils.bitmapBadge(context, badgeText, color);
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        }
        paint = new Paint();
    }

    @Override public int getIntrinsicWidth() {
        return width;
    }

    @Override public int getIntrinsicHeight() {
        return height;
    }

    @Override public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, getBounds().left, getBounds().top, paint);
    }

    @Override public void setAlpha(int i) {        //no-op
    }

    @Override public void setColorFilter(ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
    }

    @Override public int getOpacity() {
        return 0;
    }
}