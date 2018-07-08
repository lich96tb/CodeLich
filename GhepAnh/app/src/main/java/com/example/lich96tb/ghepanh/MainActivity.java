package com.example.lich96tb.ghepanh;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xiaopo.flying.sticker.DrawableSticker;
import com.xiaopo.flying.sticker.Sticker;
import com.xiaopo.flying.sticker.StickerView;
import com.xiaopo.flying.sticker.TextSticker;

public class MainActivity extends AppCompatActivity {
    private StickerView stickerView, stickerView2;
    private TextSticker sticker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stickerView = (StickerView) findViewById(R.id.sticker_view);
        stickerView2 = (StickerView) findViewById(R.id.sticker_view2);
        loadSticker();
    }

    private void loadSticker() {
        Drawable drawable =
                ContextCompat.getDrawable(this, R.drawable.haizewang_215);
        Drawable drawable1 =
                ContextCompat.getDrawable(this, R.drawable.haizewang_23);
        Drawable drawable3 =
                ContextCompat.getDrawable(this, R.drawable.khung1);

        stickerView.addSticker(new DrawableSticker(drawable));
        stickerView.addSticker(new DrawableSticker(drawable1), Sticker.Position.BOTTOM | Sticker.Position.RIGHT);
        stickerView2.addSticker(new DrawableSticker(drawable3));
        Drawable bubble = ContextCompat.getDrawable(this, R.drawable.bubblea);
        stickerView.addSticker(
                new TextSticker(getApplicationContext())
                        .setDrawable(bubble)
                        .setText(" ")
                        .setMaxTextSize(14)
                        .resizeText()
                , Sticker.Position.TOP);
    }

    public void clickButton(View view) {
        switch (view.getId()) {
            case R.id.btnKhoaHinh:
                stickerView.setLocked(!stickerView.isLocked());
                break;
            case R.id.btnKhoaKhung:
                stickerView2.setLocked(!stickerView.isLocked());
                break;
        }
    }
}
