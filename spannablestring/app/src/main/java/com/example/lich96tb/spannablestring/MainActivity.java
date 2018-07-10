package com.example.lich96tb.spannablestring;

import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = findViewById(R.id.txt);
        final String text = "do trong lich";
        ForegroundColorSpan frRed = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan frBlue = new ForegroundColorSpan(Color.BLUE);

        SpannableString ss = new SpannableString(text);
        ClickableSpan span1 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Toast.makeText(MainActivity.this, "dsfsdfsd", Toast.LENGTH_SHORT).show();
                // do some thing
            }
        };

        ClickableSpan span2 = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                // do another thing
                Toast.makeText(MainActivity.this, "aldjfksdjffr", Toast.LENGTH_SHORT).show();
            }
        };

        ss.setSpan(span1, 0, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(span2, 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(frBlue, 6, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt.setText(ss);
        txt.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
