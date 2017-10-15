package ngame.kinoman;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapterFilm extends BaseAdapter{

    private Context mContext;
    private int mFilmId;
    private int mGenreId;

    public ImageAdapterFilm(Context c, int genreId, int filmId){
        mContext = c;
        mGenreId = genreId;
        mFilmId = filmId;
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

        float widthFilm = displayMetrics.widthPixels / 2;
        float heightFilm = displayMetrics.heightPixels / 4;

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

        Integer[] mThumbIds = new Integer[4];

        SharedPreferences mFilms = mContext.getSharedPreferences(dataFilms.getGenre(mGenreId), Context.MODE_PRIVATE);

        mThumbIds[0] = mContext.getResources().getIdentifier
                (dataFilms.getNameImage(mGenreId, mFilmId) + "0", "drawable", mContext.getPackageName());

        for(int i = 1; i < 4; i++)
            if(mFilms.getBoolean(Integer.toString(mFilmId) + Long.toString(i), false))
            mThumbIds[i] = mContext.getResources().getIdentifier
                    (dataFilms.getNameImage(mGenreId, mFilmId) + Integer.toString(i), "drawable", mContext.getPackageName());
        else mThumbIds[i] = mContext.getResources().getIdentifier
                    ("money", "drawable", mContext.getPackageName());

        return mThumbIds;
    }

}
