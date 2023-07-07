package adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.adminandroid.Novel;
import com.example.adminandroid.R;

import java.util.ArrayList;

public class NovelAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Novel> NovelList = new ArrayList<>();

    public void setNovelList(ArrayList<Novel> NovelList) {
        this.NovelList = NovelList;
    }

    public NovelAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        Log.d("NovelAdapter", "NovelList size: " + NovelList.size());
        return NovelList.size();
    }

    @Override
    public Object getItem(int i) {
        return NovelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;

        if (itemView == null) {
            itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_novel, viewGroup, false);
            Log.d("NovelAdapter", "Inflating item_novel layout");

        }

        ViewHolder viewHolder = new ViewHolder(itemView);

        Novel Novel = (Novel) getItem(i);
        viewHolder.bind(Novel);
        return itemView;
    }

    private class ViewHolder {
        private TextView txtTitle, txtChapters, txtTag, txtNovelCover, txtReadTimes, txtViews;

        ViewHolder(View view) {
            txtTitle = view.findViewById(R.id.edt_title);
            txtChapters = view.findViewById(R.id.edt_chapters);
            txtNovelCover = view.findViewById(R.id.edt_cover);
            txtReadTimes = view.findViewById(R.id.edt_readtimes);
            txtTag = view.findViewById(R.id.edt_tag);
            txtViews = view.findViewById(R.id.edt_views);

        }

        void bind(Novel novel) {
            txtTitle.setText(novel.getTitle());
            txtChapters.setText(novel.getChapters());
            txtNovelCover.setText(novel.getNovelCover());
            txtReadTimes.setText(novel.getReadTimes());
            txtTag.setText(novel.getTag());
            txtViews.setText(novel.getViews());
        }
    }

}