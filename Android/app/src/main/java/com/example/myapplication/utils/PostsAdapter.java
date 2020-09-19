package com.example.myapplication.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;
import java.util.Map;

public class PostsAdapter extends BaseAdapter {
    private Context ctx;
    private LayoutInflater lInflater;
    private List<VkClusterItem> items;
    private Boolean isHideInfo = false;

    public PostsAdapter(Context context, List<VkClusterItem> items) {
        ctx = context;

        this.items = items;

        if(ctx != null){
            lInflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public VkClusterItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Boolean isFirst = false;
        if (view == null) {
            isFirst = true;
            view = lInflater.inflate(R.layout.item_post, parent, false);
        }

       VkClusterItem post = getItem(position);

        RoundedImageView avatarIV = (RoundedImageView) view.findViewById(R.id.avatarIV);
        if(post.getName().contains("Евгений")){
            avatarIV.setImageResource(R.drawable.avatar_man);
        }
        if(post.getName().contains("Юлечка")){
            avatarIV.setImageResource(R.drawable.avatar_woman_2);
        }
        if(post.getName().contains("Ольга")){
            avatarIV.setImageResource(R.drawable.avatar_woman_3);
        }
        if(post.getName().contains("Роспотребнадзор")){
            avatarIV.setImageResource(R.drawable.avatar_nadzor);
        }
        if(post.getName().contains("Матвей")){
            avatarIV.setImageResource(R.mipmap.avatar);
        }


        ImageView photoIV = (ImageView) view.findViewById(R.id.photoIV);

        if(post.getPhotoUrlBig() != 0){
            photoIV.setVisibility(View.VISIBLE);
            photoIV.setImageResource(post.getPhotoUrlBig());
        }else{
            photoIV.setVisibility(View.GONE);
        }
        TextView timeAndEmotionTV = (TextView) view.findViewById(R.id.timeAndEmotionTV);

        String timeAndEmotion = "час назад  • ";
        if(post.getTypeEmocion().equals("best")){
            timeAndEmotion += " хорошее настроение";
        }
        if(post.getTypeEmocion().equals("middle")){
            timeAndEmotion += " умиротворение";
        }
        if(post.getTypeEmocion().equals("energy")){
            timeAndEmotion += " энергичное настроение";
        }
        if(post.getTypeEmocion().equals("scver")){
            timeAndEmotion += " скверное настроение";
        }

        timeAndEmotionTV.setText(timeAndEmotion);

        TextView postTextTV = (TextView) view.findViewById(R.id.postTextTV);
        postTextTV.setText(post.getDescription());

        TextView nameTV = (TextView) view.findViewById(R.id.nameTV);
        nameTV.setText(post.getName());

        //Picasso.with(ctx).load(note.getElementsByClass("search_item_img").attr("src")).placeholder(R.drawable.abc_ab_bottom_solid_dark_holo).resize(220, 220).centerCrop().into(avatarIV);
        return view;
    }

    public String setNeedSizeString (String text, Integer size){
        if(text.length() > size){
            text.substring(0,size);
        }
        while(text.length() < size+1){
            text += " ";
        }
        return text;
    }

}
