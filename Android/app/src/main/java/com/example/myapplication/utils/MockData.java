package com.example.myapplication.utils;

import android.app.Activity;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Random;

public class MockData {
    private static ArrayList<VkClusterItem> items;
    public static String selectedTypeEmo = "";
    public static double randLong() {
        float min = 59.930000F;
        float max = 59.95000F;

        Random rand = new Random();

        return (double)rand.nextFloat() * (max - min) + min;

    }
    public static double randLat() {
        float min = 30.330000F;
        float max = 30.370000F;
        Random rand = new Random();

        return (double)rand.nextFloat() * (max - min) + min;

    }
    public static void generateData(Activity act) {
        items = new ArrayList<VkClusterItem>();
        String testPhotoUrl = "https://instarlike.com/icons_vk/";

        items.add(new VkClusterItem(testPhotoUrl+"music.png","1","Евгений Александров",act.getResources().getString(R.string.music_best1),randLong(),randLat(),"Addr1",false,"ScreenName1",0,"best"));
        items.add(new VkClusterItem(testPhotoUrl+"music.png","2","Юлечка красотулечка",act.getResources().getString(R.string.music_best2),randLong(),randLat(),"Addr1",false,"ScreenName1",0,"best"));
        items.add(new VkClusterItem(testPhotoUrl+"music.png","3","Ольга Спиридонова",act.getResources().getString(R.string.music_best3),randLong(),randLat(),"Addr1",false,"ScreenName1",0,"best"));

        items.add(new VkClusterItem(testPhotoUrl+"cinema.png","4","Евгений Александров",act.getResources().getString(R.string.cinema_best1),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_kino_1,"best"));
        items.add(new VkClusterItem(testPhotoUrl+"cinema.png","5","Юлечка красотулечка",act.getResources().getString(R.string.cinema_best2),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_kino_2,"best"));

        items.add(new VkClusterItem(testPhotoUrl+"autumn.png","6","Евгений Александров",act.getResources().getString(R.string.autumn_middle1),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_autumn_1,"middle"));
        items.add(new VkClusterItem(testPhotoUrl+"autumn.png","7","Юлечка красотулечка",act.getResources().getString(R.string.autumn_middle2),randLong(),randLat(),"Addr1",false,"ScreenName1",0,"middle"));
        items.add(new VkClusterItem(testPhotoUrl+"autumn.png","8","Ольга Спиридонова",act.getResources().getString(R.string.autumn_middle3),randLong(),randLat(),"Addr1",false,"ScreenName1",0,"middle"));

        items.add(new VkClusterItem(testPhotoUrl+"work.png","9","Евгений Александров",act.getResources().getString(R.string.work_energy1),randLong(),randLat(),"Addr1",false,"ScreenName1",0,"energy"));
        items.add(new VkClusterItem(testPhotoUrl+"work.png","10","Юлечка красотулечка",act.getResources().getString(R.string.work_energy2),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_work,"energy"));

        items.add(new VkClusterItem(testPhotoUrl+"karantin.png","11","Роспотребнадзор",act.getResources().getString(R.string.karantin_scver1),randLong(),randLat(),"Addr1",false,"ScreenName1",0,"scver"));
        items.add(new VkClusterItem(testPhotoUrl+"karantin.png","12","Юлечка красотулечка",act.getResources().getString(R.string.karantin_scver2),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_karantin,"scver"));

        items.add(new VkClusterItem(testPhotoUrl+"info_tech.png","13","Евгений Александров",act.getResources().getString(R.string.it_scver1),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_it,"scver"));
        items.add(new VkClusterItem(testPhotoUrl+"info_tech.png","14","Юлечка красотулечка",act.getResources().getString(R.string.it_scver2),randLong(),randLat(),"Addr1",false,"ScreenName1",0,"scver"));

        items.add(new VkClusterItem(testPhotoUrl+"car.png","15","Евгений Александров",act.getResources().getString(R.string.car_best1),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_auto,"best"));
        items.add(new VkClusterItem(testPhotoUrl+"car.png","16","Юлечка красотулечка",act.getResources().getString(R.string.car_best2),randLong(),randLat(),"Addr1",false,"ScreenName1",0,"best"));

        items.add(new VkClusterItem(testPhotoUrl+"game.png","17","Евгений Александров",act.getResources().getString(R.string.game_energy1),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_games,"energy"));
        items.add(new VkClusterItem(testPhotoUrl+"game.png","18","Юлечка красотулечка",act.getResources().getString(R.string.game_energy2),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_games_2,"energy"));

        items.add(new VkClusterItem(testPhotoUrl+"art.png","19","Евгений Александров",act.getResources().getString(R.string.art_energy1),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_art_1,"energy"));
        items.add(new VkClusterItem(testPhotoUrl+"art.png","20","Юлечка красотулечка",act.getResources().getString(R.string.art_energy2),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_art_2,"energy"));

        items.add(new VkClusterItem(testPhotoUrl+"humor.png","21","Евгений Александров",act.getResources().getString(R.string.humor_energy1),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_humor_1,"energy"));
        items.add(new VkClusterItem(testPhotoUrl+"humor.png","22","Юлечка красотулечка",act.getResources().getString(R.string.humor_energy2),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_humor_2,"energy"));

        items.add(new VkClusterItem(testPhotoUrl+"photo.png","23","Евгений Александров",act.getResources().getString(R.string.photo_energy1),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_photo_1,"energy"));
        items.add(new VkClusterItem(testPhotoUrl+"photo.png","24","Юлечка красотулечка",act.getResources().getString(R.string.photo_energy1),randLong(),randLat(),"Addr1",false,"ScreenName1",R.drawable.photo_photo_2,"energy"));
    }

    public static ArrayList<VkClusterItem> getItems() {
        return items;
    }

    public static void addItems(VkClusterItem item) {
        MockData.items.add(0,item);
    }
}
