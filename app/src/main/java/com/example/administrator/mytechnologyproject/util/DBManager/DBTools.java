package com.example.administrator.mytechnologyproject.util.DBManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.administrator.mytechnologyproject.model.News;
import com.example.administrator.mytechnologyproject.model.NewsType;
import com.example.administrator.mytechnologyproject.model.SubType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/10.
 */
public class DBTools {
    private Context context;
    private DBManager dbManager;
    private SQLiteDatabase sd;
    private SQLiteOpenHelper helper;

    public DBTools(Context context) {
        this.context = context;
        dbManager = new DBManager(context);
    }

    public boolean saveLocalNewsType(SubType subType) {
        sd = dbManager.getReadableDatabase();
        String sql = "select subid from " + DBManager.NEWSTYPE_NAME + " where subid = ?";
        Cursor c = sd.rawQuery(sql, new String[]{subType.getSubid()+""});
        if (c.moveToNext()) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("subid",subType.getSubid());
        contentValues.put("subgroup",subType.getSubgroup());
        sd.insert(DBManager.NEWSTYPE_NAME,null,contentValues);
        return true;
    }

    public List<SubType> getLocalNewsType() {
        List<SubType> listSubType = new ArrayList<>();
        sd = dbManager.getReadableDatabase();
        Cursor c = sd.query(DBManager.NEWSTYPE_NAME,null,null,null,null,null,null);
        if (c.moveToFirst()) {
            do{
                int subid = c.getInt(c.getColumnIndex("subid"));
                String subgroup = c.getString(c.getColumnIndex("subgroup"));
                SubType subType = new SubType(subgroup,subid);
                listSubType.add(subType);
            }while (c.moveToNext());
            c.close();
            sd.close();
        }
        return listSubType;
    }

    public boolean saveLocalNews(News news) {
        sd = dbManager.getReadableDatabase();
        String sql = "select nid from " + DBManager.NEWS_NAME + " where nid = ?";
        Cursor c = sd.rawQuery(sql, new String[]{news.getNid() + ""});
        if (c.moveToNext()) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", news.getType());
        contentValues.put("nid", news.getNid());
        contentValues.put("summary", news.getSummary());
        contentValues.put("icon", news.getIcon());
        contentValues.put("stamp", news.getStamp());
        contentValues.put("title", news.getTitle());
        contentValues.put("link", news.getLink());
        sd.insert(DBManager.NEWS_NAME, null, contentValues);
        return true;
    }

    public List<News> getLocalNews() {
        List<News> listNews = new ArrayList<>();
        sd = dbManager.getReadableDatabase();
        Cursor c = sd.query(DBManager.NEWS_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                int type = c.getInt(c.getColumnIndex("type"));
                int nid = c.getInt(c.getColumnIndex("nid"));
                String summary = c.getString(c.getColumnIndex("summary"));
                String stamp = c.getString(c.getColumnIndex("stamp"));
                String title = c.getString(c.getColumnIndex("title"));
                String link = c.getString(c.getColumnIndex("link"));
                String icon = c.getString(c.getColumnIndex("icon"));
                News news = new News(summary, icon, stamp, title, nid, link, type);
                listNews.add(news);
            } while (c.moveToNext());
            c.close();
            sd.close();
        }
        return listNews;
    }

    public boolean saveLocalFavorite(News news) {
        sd = dbManager.getReadableDatabase();
        String sql = "select nid from " + DBManager.NEWSFAVORITE_NAME + " where nid = ?";
        Cursor c = sd.rawQuery(sql, new String[]{news.getNid() + ""});
        if (c.moveToNext()) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("type", news.getType());
        contentValues.put("nid", news.getNid());
        contentValues.put("summary", news.getSummary());
        contentValues.put("icon", news.getIcon());
        contentValues.put("stamp", news.getStamp());
        contentValues.put("title", news.getTitle());
        contentValues.put("link", news.getLink());
        sd.insert(DBManager.NEWSFAVORITE_NAME, null, contentValues);
        return true;
    }

    public List<News> getLocalFavorite() {
        List<News> listNews = new ArrayList<>();
        sd = dbManager.getReadableDatabase();
        Cursor c = sd.query(DBManager.NEWSFAVORITE_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                int type = c.getInt(c.getColumnIndex("type"));
                int nid = c.getInt(c.getColumnIndex("nid"));
                String summary = c.getString(c.getColumnIndex("summary"));
                String stamp = c.getString(c.getColumnIndex("stamp"));
                String title = c.getString(c.getColumnIndex("title"));
                String link = c.getString(c.getColumnIndex("link"));
                String icon = c.getString(c.getColumnIndex("icon"));
                News news = new News(summary, icon, stamp, title, nid, link, type);
                listNews.add(news);
            } while (c.moveToNext());
            c.close();
            sd.close();
        }
        return listNews;
    }
}
