package com.example.mluth.suapin.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mluth.suapin.R;
import com.example.mluth.suapin.model.Donation;

import java.util.ArrayList;

public class ItemDonation extends RecyclerView.Adapter<ItemDonation.ViewHolder> {
    private ArrayList<Donation> daftarDonation;
    private Context context;

    public ItemDonation(ArrayList<Donation> donations, Context ctx){
        /**
         * Inisiasi data dan variabel yang akan digunakan
         */
        daftarDonation = donations;
        context = ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * Inisiasi View
         * Di tutorial ini kita hanya menggunakan data String untuk tiap item
         * dan juga view nya hanyalah satu TextView
         */
        TextView tvTitle,tvPortionTitle,tvPortion;

        ViewHolder(View v) {
            super(v);
            tvTitle = (TextView) v.findViewById(R.id.tv_namaItem);
            tvPortionTitle = (TextView) v.findViewById(R.id.tv_porsiItemTitle);
            tvPortion = (TextView) v.findViewById(R.id.tv_porsiItem);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Inisiasi ViewHolder
         */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donation, parent, false);
        // mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        /**
         *  Menampilkan data pada view
         */
        final String name = daftarDonation.get(position).getFoodName();
        final int portion = daftarDonation.get(position).getPortion();
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Read detail data
                 */
            }
        });
        holder.tvTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                /**
                 *  Kodingan untuk tutorial Selanjutnya :p Delete dan update data
                 */
                return true;
            }
        });
        holder.tvTitle.setText(name);
        holder.tvPortion.setText(String.valueOf(portion));
    }

    @Override
    public int getItemCount() {
        /**
         * Mengembalikan jumlah item pada barang
         */
        return daftarDonation.size();
    }
}
