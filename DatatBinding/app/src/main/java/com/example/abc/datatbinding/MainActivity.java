package com.example.abc.datatbinding;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.abc.datatbinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MyPresenter presenter = new MyPresenter();

//        //cach 1
//        user = new User();
//        user.setName("Ravi Tamada");
//        user.setEmail("ravi@androidhive.info");

        //cach2
        user = new User();
        user.name.set("Ravi Tamada");
        user.email.set("ravi@androidhive.info");

        binding.setAbc(presenter);
        binding.setUser(user);
    }

    public class MyPresenter {

        //cach 1
//        public void onLisClicka(User user) {
//            user.setName("do trong lich");
//            Toast.makeText(MainActivity.this, "dddddd", Toast.LENGTH_SHORT).show();
//        }
//        public boolean onLisClickaa() {
//            user.setEmail("hoang thi hanh");
//            Toast.makeText(MainActivity.this, "dddddd", Toast.LENGTH_SHORT).show();
//            return true;
//        }


        //cach 2
        public void onLisClicka(User user) {
            user.name.set("do trong lich");
            Toast.makeText(MainActivity.this, ""+user.edtSoa.get(), Toast.LENGTH_SHORT).show();
        }
        public boolean onLisClickaa() {
            user.edtSoa.set("do torng lich");
            user.email.set("hoang thi hanh");
            user.image.set("https://hinhanhdephd.com/wp-content/uploads/2015/12/hinh-anh-dep-girl-xinh-hinh-nen-dep-gai-xinh.jpg");
            Toast.makeText(MainActivity.this, "dddddd", Toast.LENGTH_SHORT).show();
            return true;
        }

    }
}
