package net.syarihu.android.androidtraining;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.bumptech.glide.Glide;

import net.syarihu.android.androidtraining.databinding.ListRowBinding;
import net.syarihu.android.androidtraining.gifmagazine.SearchResult;

import java.util.List;

public class GifImageListAdapter extends BaseAdapter {
    private List<SearchResult> results;
    private Context context;

    public GifImageListAdapter(Context context, List<SearchResult> results) {
        this.results = results;
        this.context = context;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int i) {
        return results.get(i);
    }

    @Override
    public long getItemId(int i) {
        return results.get(i).id;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListRowBinding binding;
        if (view == null)
            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_row, viewGroup, false);
        else
            binding = DataBindingUtil.findBinding(view);

        SearchResult result = (SearchResult) getItem(i);

        binding.title.setText(result.title);
        Glide.with(context)
                .load(result.image.defaultImage.url)
                .into(binding.gifImage);
        return binding.getRoot();
    }

    public void replaceList(List<SearchResult> searchResults) {
        this.results.clear();
        this.results.addAll(searchResults);
        notifyDataSetChanged();
    }
}
