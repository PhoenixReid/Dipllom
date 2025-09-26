package ru.Data;

import android.icu.text.SimpleDateFormat;

import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class NewsData {
    public static final String categorySalary = "Зарплата";
    public static final String categoryNotice = "Объявление";
    public static final String categoryParty = "Праздник";

    public  static final String titleSalary = "Зарплата Аовы";
    public  static  final String titleSecondSalary = "Зарплата уменьшилась на 5%";
    public  static final  String titleNotice = "Объявление о зарплате Аовы";
    public  static final String nullTitle = "";


    public  static final String descriptionSalary = "больше чем у вас";
    public  static final String descriptionSecondSalary = "Уменьшение зп у всех на 5%";
    public static  final  String nullDescription = "";


    public static final String today = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
    public  static final String oneDayPlThree = new SimpleDateFormat("dd", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(3)));
    public static final String theDayAfterTomorrow = new SimpleDateFormat("dd", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(2)));
    public static final String yesterday = new SimpleDateFormat("dd", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1)));
    public static final String tomorrowDay = new SimpleDateFormat("dd", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
    public static final String todayDate = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
    public static final String tomorrowData = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            .format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(1)));
}
