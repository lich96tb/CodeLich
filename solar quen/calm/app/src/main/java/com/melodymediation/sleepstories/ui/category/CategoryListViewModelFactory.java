package com.melodymediation.sleepstories.ui.category;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.melodymediation.sleepstories.data.repository.CategoryRepository;
import com.melodymediation.sleepstories.data.repository.SessionRepository;
import com.melodymediation.sleepstories.data.repository.CategoryRepository;
import com.melodymediation.sleepstories.data.repository.SessionRepository;

public class CategoryListViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final CategoryRepository categoryRepository;
    private final SessionRepository sessionRepository;

    public CategoryListViewModelFactory(CategoryRepository categoryRepository, SessionRepository sessionRepository) {
        this.categoryRepository = categoryRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new CategoryListViewModel(categoryRepository, sessionRepository);
    }
}
