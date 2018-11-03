package com.melodymediation.sleepstories.ui;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.melodymediation.sleepstories.data.room.AppDatabase;
import com.melodymediation.sleepstories.data.room.Category;
import com.melodymediation.sleepstories.R;
import com.melodymediation.sleepstories.ui.category.CategoryListFragment;
import com.melodymediation.sleepstories.ui.category.CategoryListViewModel;
import com.melodymediation.sleepstories.ui.home.FragmentHome;
import com.melodymediation.sleepstories.service.evenbust.MessageEvent;
import com.melodymediation.sleepstories.utilities.FragmentUtil;
import com.melodymediation.sleepstories.utilities.InjectorUtils;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    AppDatabase db;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private List<Category> listCategories;
    private CategoryListViewModel categoryListViewModel;
    ImageView imgv;
    private RequestOptions requestOptions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        intView(view);
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        listCategories = new ArrayList<>();
        requestOptions = new RequestOptions();
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(30));
        //khoi tao viewmodel
        ViewModelProvider.Factory factory = InjectorUtils.INSTANCE.provideCategoryListViewModelFactory(getContext());
        categoryListViewModel = ViewModelProviders.of(this, factory).get(CategoryListViewModel.class);
        List<Category> categories = categoryListViewModel.getCategoriesParent();
        listCategories = categories;

        MethodStatic.tabLayout = (TabLayout) view.findViewById(R.id.tablayouts);
        TabLayout.Tab tab = new TabLayout.Tab();
        MethodStatic.tabLayout.addTab(MethodStatic.tabLayout.newTab().setIcon(R.drawable.ic_home).setText("Home"));
        for (int i = 0; i < listCategories.size(); i++) {
            Category category = listCategories.get(i);
            int iconId;
            switch (category.getIco()) {
                case "ic_meditate":
                    iconId = R.drawable.ic_meditate;
                    break;
                case "ic_stories":
                    iconId = R.drawable.ic_stories;
                    break;
                default:
                    iconId = R.drawable.ic_sleep;
            }
            MethodStatic.tabLayout.addTab(MethodStatic.tabLayout.newTab().setIcon(iconId).setText(category.getName()));
        }

        Fragment fragmentHome = new FragmentHome();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentHome, fragmentHome).commit();
        FragmentUtil.printActivityFragmentList(getFragmentManager());

        int tabIconColor = ContextCompat.getColor(getContext(), R.color.colorTextSelect);
        int tabIconColorzero = ContextCompat.getColor(getContext(), R.color.colorTabSelect);
        for (int i = 0; i < MethodStatic.tabLayout.getTabCount(); i++) {
            if (i == 0)
                MethodStatic.tabLayout.getTabAt(i).getIcon().setColorFilter(tabIconColorzero, PorterDuff.Mode.SRC_IN);
            else
                MethodStatic.tabLayout.getTabAt(i).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        }
        MethodStatic.tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getContext(), R.color.colorTabSelect);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

                if (tab.getPosition() == 0) {
                    Fragment fragmentHome = new FragmentHome();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragmentHome, fragmentHome).commit();
                    FragmentUtil.printActivityFragmentList(getFragmentManager());

                } else {
                    Fragment fragment = new CategoryListFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putString("com.bigqsys.music.relax.ui.category.CATEGORY_ID", listCategories.get(tab.getPosition() - 1).getCategoryId());
                    fragment.setArguments(bundle);
                    transaction.replace(R.id.fragmentHome, fragment);
                    transaction.commit();
                    FragmentUtil.printActivityFragmentList(getFragmentManager());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(getContext(), R.color.colorTextSelect);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        MethodStatic.tabLayout.setVisibility(View.VISIBLE);
        EventBus.getDefault().register(this);
    }

    private void intView(View view) {
        imgv = view.findViewById(R.id.imgv);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        getResizedBitmap(icon, 60);
    }



    public static Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent noteEvent) {

        if (noteEvent.getUrl().equals("EXPANDED")) {
            MethodStatic.tabLayout
                .setVisibility(View.GONE);
        } else if (noteEvent.getUrl().equals("COLLAPSED")) {
            MethodStatic.tabLayout
                .setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}

