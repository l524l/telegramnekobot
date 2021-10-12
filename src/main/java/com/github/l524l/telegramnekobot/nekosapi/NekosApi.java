package com.github.l524l.telegramnekobot.nekosapi;

import com.github.l524l.telegramnekobot.exceptions.CategoryNotFound;

import java.net.URL;
import java.util.List;

public abstract class NekosApi {

    protected List<NekoCategory> nekoCategoryList;

    public URL execute(String categoryName) throws Exception{
        for (NekoCategory category:
             nekoCategoryList) {

            if (category.getName().equals(categoryName)) {
                return execute(category);
            }
        }
        throw new CategoryNotFound(String.format("Категория %s не найдена", categoryName));
    }

    public abstract URL execute(NekoCategory category) throws Exception;

    public List<NekoCategory> getNekoCategoryList() {
        return nekoCategoryList;
    }

    public void setNekoCategoryList(List<NekoCategory> nekoCategoryList) {
        this.nekoCategoryList = nekoCategoryList;
    }
}
