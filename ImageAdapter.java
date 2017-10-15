package ngame.kinoman;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.util.DisplayMetrics;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;
    private int mGenreId;

    public ImageAdapter(Context c, int genreId){
        mContext = c;
        mGenreId = genreId;
    }

    public int getCount(){
        return getThumbIds().length;
    }

    public Object getItem(int position){
        return getThumbIds()[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        float widthFilm = displayMetrics.widthPixels / 4;
        float heightFilm = displayMetrics.heightPixels / 6;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams((int)widthFilm, (int)heightFilm));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(getThumbIds()[position]);
        return imageView;
    }

    public Integer[] getThumbIds()
    {
        DataFilms dataFilms = (DataFilms)mContext.getApplicationContext();
        SharedPreferences mFilms = mContext.getSharedPreferences(dataFilms.getGenre(mGenreId), Context.MODE_PRIVATE);

        int count = dataFilms.countFilmsToGenre(mGenreId);
        Integer[] mThumbIds = new Integer[count];

        for(int i = 0; i < count; i++)
            mThumbIds[i] = mContext.getResources().getIdentifier
                    (dataFilms.getNameImage(mGenreId, i) + 0, "drawable", mContext.getPackageName());


            //mThumbIds[i] = mFilms.getBoolean(Integer.toString(i), false) ? mContext.getResources().getIdentifier
                   // (dataFilms.getNameImage(mGenreId, i) + 0, "drawable", mContext.getPackageName())
                   // : mContext.getResources().getIdentifier
                   // (dataFilms.getNameImage(mGenreId, i) + 0, "drawable", mContext.getPackageName());
        return mThumbIds;
    }
}
