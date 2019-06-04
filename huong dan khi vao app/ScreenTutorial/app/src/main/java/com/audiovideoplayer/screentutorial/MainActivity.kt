package com.audiovideoplayer.screentutorial

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nhaarman.supertooltips.ToolTip
import com.nhaarman.supertooltips.ToolTipView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), ToolTipView.OnToolTipViewClickedListener {
    override fun onToolTipViewClicked(toolTipView: ToolTipView?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val toolTip = ToolTip()
            .withText("A beautiful View")
            .withColor(Color.RED)
            .withShadow()

       var myToolTipView = activity_main_tooltipRelativeLayout.showToolTipForView(toolTip, activity_main_redtv);
        myToolTipView.setOnToolTipViewClickedListener(this);

    }
}
