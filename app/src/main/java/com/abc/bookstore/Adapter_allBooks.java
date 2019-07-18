package com.abc.bookstore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_allBooks extends RecyclerView.Adapter<Adapter_allBooks.ViewHolder> {
    Context context;
    ArrayList<model_allbook> model_allbooks = new ArrayList<>();

    public Adapter_allBooks(Context context, ArrayList<model_allbook> model_allbooks) {
        this.context = context;
        this.model_allbooks = model_allbooks;}


    public Adapter_allBooks.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Adapter_allBooks.ViewHolder(LayoutInflater.from(context).inflate(R.layout.custom_allbooks, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_allBooks.ViewHolder viewHolder, int i) {
       // model_allboks model = mmodel_allbooks.get(i);
        viewHolder.tv_book_title.setText(model_allbooks.get(i).getName());
        viewHolder.tv_author_name.setText(model_allbooks.get(i).getAuthor());
        viewHolder.tv_mob.setText(model_allbooks.get(i).getMobile());
     //  viewHolder.imge_book.s(model_allbooks.get(i).getImage());
        if(model_allbooks.get(i).getImage().isEmpty())
        {
            viewHolder.imge_book.setImageResource(R.drawable.ic_book_black_24dp);
        }
        else{
        Picasso.with(context).load(model_allbooks.get(i).getImage()).fit().centerInside().into(viewHolder.imge_book);}


        viewHolder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myMessageDialog(model_allbooks.get(viewHolder.getAdapterPosition()).getAuthor(),
                        model_allbooks.get(viewHolder.getAdapterPosition()).getName(),
                        model_allbooks.get(viewHolder.getAdapterPosition()).getEmail(),
                        model_allbooks.get(viewHolder.getAdapterPosition()).getMobile(),
                      //  model_allbooks.get(viewHolder.getAdapterPosition()).getImage(),
                        viewHolder);



            }
        });

    }
    private void myMessageDialog(String author, String name, final String email, final String mobile, ViewHolder viewHolder) {
        AlertDialog scheduleDialog;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        AlertDialog.Builder builderAlert = new AlertDialog.Builder(context);
        final View country_alert_view = inflater.inflate(R.layout.contact, null);

        TextView textmakeCall =country_alert_view.findViewById(R.id.textmakeCall);
        TextView textsendMessage =country_alert_view.findViewById(R.id.textsendMessage);
        TextView textsendEmail =country_alert_view.findViewById(R.id.textsendEmail);
        ImageView imageView = country_alert_view.findViewById(R.id.imageView);
        textmakeCall.setText(mobile);
        textsendMessage.setText(mobile);
        textsendEmail.setText(email);


        textmakeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mobile != null && !mobile.isEmpty() && mobile.length() >= 10) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + mobile));
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Missing contact number.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textsendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mobile != null && !mobile.isEmpty() && mobile.length() >= 10) {
                    Uri uri = Uri.parse("smsto:" + mobile);
                    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
                    it.putExtra("sms_body", "The SMS text");
                    context.startActivity(it);
                } else {
                    Toast.makeText(context, "Missing contact number.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        textsendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email != null && !email.isEmpty() && email.length() > 3) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "" + email, null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                    context.startActivity(emailIntent);
                } else {
                    Toast.makeText(context, "Missing Email-Id .", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builderAlert.setView(country_alert_view);


        builderAlert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderAlert.setCancelable(false);
        scheduleDialog = builderAlert.create();
        builderAlert.show();

    }

    @Override
    public int getItemCount() {
        return model_allbooks.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_book_title, tv_author_name , tv_mob;
        Button btn_more;
        ImageView imge_book;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_book_title = itemView.findViewById(R.id.tv_book_title);
            tv_author_name = itemView.findViewById(R.id.tv_author_name);
            tv_mob = itemView.findViewById(R.id.tv_mob);
            btn_more=itemView.findViewById(R.id.btn_more);
            imge_book  = itemView.findViewById(R.id.imge_book);
        }
    }


}
