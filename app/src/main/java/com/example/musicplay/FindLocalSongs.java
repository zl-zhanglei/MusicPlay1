package com.example.musicplay;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 张蕾 on 2016/10/11.
 * 访问歌曲信息
 */

public class FindLocalSongs {
    public List<Mp3Info> getMp3Infos(ContentResolver contentResolver){
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();
        for(int i=0;i<cursor.getCount();i++){
            Mp3Info mp3Info = new Mp3Info();  //新建一个歌曲对象，将从cursor里读出来的数据存放进去，直到读取完毕
            cursor.moveToNext();
            long id=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));  //音乐id
            String title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));  //音乐标题
            String musician=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));//作曲家
            long size=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));  //文件大小
            String url=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));//文件路径
            long duration=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));//时长
            /*String album=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));  //唱片图片
            long album_id=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));  //唱片图片id*/
            int isMusic=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));

            if(isMusic!=0&&duration/(1000*60)>=1){
                mp3Info.setId(id);
                mp3Info.setTitle(title);
                mp3Info.setArtist(musician);
                mp3Info.setSize(size);
                mp3Info.setUrl(url);
                mp3Info.setDuration(duration);
                mp3Infos.add(mp3Info);
            }
        }
        return mp3Infos;
    }
}
