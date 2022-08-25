package com.example.tabiin.adapters.quran;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabiin.R;
import com.example.tabiin.objects.sures.Sura;
import com.example.tabiin.objects.sures.Verse;

public class QuranAdapter extends RecyclerView.Adapter<QuranAdapter.ViewHolder> {
    private Sura sura;
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView num;
        public TextView arabicVerse;
        public TextView translatedVerse;
        public TextView heading;
        public TextView headingArabic;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);

            arabicVerse = itemView.findViewById(R.id.arabicViewVerse); // аят на арабском
            translatedVerse = itemView.findViewById(R.id.translateViewVerse); // аят на русском
            num = itemView.findViewById(R.id.numVerse); // номер аята

            heading = itemView.findViewById(R.id.heading);
            headingArabic = itemView.findViewById(R.id.headingArabic);

        }
    }

    @NonNull
    @Override
    public QuranAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.quran_card_item, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull QuranAdapter.ViewHolder holder, int position) {
        Verse arabicViewVerse = sura.getVerses().get(position);
        Verse translateViewVerse = sura.getTranslatedVerses().get(position);
        TextView verseView = holder.arabicVerse;
        TextView num = holder.num;
        TextView tvesre = holder.translatedVerse;
        num.setText(Integer.toString(position));
        arabicViewVerse.setText(arabicViewVerse.getText());
        tvesre.setText(translateViewVerse.getText());
        TextView heading = holder.heading;
        TextView headingArabic = holder.headingArabic;
        heading.setVisibility(View.GONE);
        headingArabic.setVisibility(View.GONE);

        if (position == 1){
            num.setVisibility(View.GONE);
            heading.setVisibility(View.VISIBLE);
            heading.setText(sura.getTranslatedName());
            headingArabic.setVisibility(View.VISIBLE);
            headingArabic.setText(sura.getName().replaceAll("[1234567890]", ""));
            verseView.setText(sura.getForeword());
            tvesre.setText(sura.getTranslatedForeword());
        }
        else {
            num.setVisibility(View.VISIBLE);
            heading.setVisibility(View.GONE);
            arabicViewVerse = sura.getVerses().get(position);
            translateViewVerse = sura.getTranslatedVerses().get(position);
            num.setText(Integer.toString(position));
            verseView.setText(arabicViewVerse.getText());
            tvesre.setText(translateViewVerse.getText());
        }
    }

    @Override
    public int getItemCount() {
        return sura.getVerses().size();
    }

    public QuranAdapter(Sura suras) {
        this.sura = suras;
    }
}

