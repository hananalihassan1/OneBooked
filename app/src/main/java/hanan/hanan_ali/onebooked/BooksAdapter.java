package hanan.hanan_ali.onebooked;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import hanan.hanan_ali.onebooked.BooksAdapter.BooksViewHolder;

public class BooksAdapter extends RecyclerView.Adapter<BooksViewHolder>
{
    Context context;
    private List<BookList> Bookslist;

    public BooksAdapter(Context context, List<BookList> bookslist)
    {
        this.context = context;
       this.Bookslist = bookslist;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);

        return new BooksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position)
    {
        BookList bookList=Bookslist.get(position);
        holder.name.setText(bookList.getName());
        holder.date.setText(bookList.getDate());
        holder.time.setText(bookList.getTime());
        holder.price.setText(bookList.getPrice());
        if (bookList.getShavehair().length()==0)
        {
            holder.hair.setVisibility(View.GONE);
        }
        else
            {
                holder.hair.setText(bookList.getShavehair());
            }
        if (bookList.getShavebeard().length()==0)
        {
            holder.beard.setVisibility(View.GONE);
        }else
            {
                holder.beard.setText(bookList.getShavebeard());

            }
        if (bookList.getHairpigment().length()==0)
        {
            holder.pigment.setVisibility(View.GONE);
        }else
            {
                holder.pigment.setText(bookList.getHairpigment());
            }
        if (bookList.getFacemusk().length()==0)
        {
            holder.musk.setVisibility(View.GONE);
        }
        else
            {
                holder.musk.setText(bookList.getFacemusk());
            }



    }

    @Override
    public int getItemCount()
    {
        return Bookslist.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder
    {
        TextView price,name,hair,beard,pigment,musk,time,date;

        public BooksViewHolder(View itemView)
        {
            super(itemView);
            price=itemView.findViewById(R.id.price_txt);
            name=itemView.findViewById(R.id.name_txt);
            hair=itemView.findViewById(R.id.hair_txt);
            beard=itemView.findViewById(R.id.beard_txt);
            pigment=itemView.findViewById(R.id.pigment_txt);
            musk=itemView.findViewById(R.id.musk_txt);
            time=itemView.findViewById(R.id.time_text);
            date=itemView.findViewById(R.id.date_text);
        }
    }
}
