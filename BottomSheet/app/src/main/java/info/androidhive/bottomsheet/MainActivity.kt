package info.androidhive.bottomsheet

import android.os.Bundle
import android.support.design.widget.BottomSheetDialog
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import butterknife.ButterKnife
import butterknife.OnClick
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_bottom_sheet_dialog.*

class MainActivity : AppCompatActivity() {
    var dialog: BottomSheetDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        val view = layoutInflater.inflate(R.layout.fragment_bottom_sheet_dialog, null)
        dialog = BottomSheetDialog(this)
        dialog!!.setContentView(view)


        dialog!!.layoutClose!!.setOnClickListener { dialog!!.dismiss() }
        btn_bottom_sheet_dialog.setOnClickListener {
            dialog!!.show()
        }
        btn_bottom_sheet_dialog_fragment.setOnClickListener {
            val bottomSheetFragment = BottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag)
        }
    }
}