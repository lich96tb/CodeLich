package relax.music.bigqsys.com.recyclerviewkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list= ArrayList<User>()
        list.add(User("lich",1))
        list.add(User("lich",2))
        list.add(User("lich",3))
        list.add(User("lich",4))
        recyclerview.layoutManager=LinearLayoutManager(this)
        recyclerview.adapter=AdapterUser(list,this)
    }
}
