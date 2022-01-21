package com.example.seanactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterClass extends ArrayAdapter<ScoreBoard> {
    private Context context;
    private ArrayList<ScoreBoard> arrayList;


    public AdapterClass(@NonNull Context context, ArrayList<ScoreBoard> scoreBoard) {
        super(context,R.layout.list_item, scoreBoard);
        this.context=context;
        this.arrayList = scoreBoard;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row =convertView;
        ViewHolder holder =null;


        if (row == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_item,parent,false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }else{
            holder= (ViewHolder) row.getTag();
        }

        Glide.with(context).load(arrayList.get(position).getLayout()).into(holder.imageView);
        holder.GameName.setText(arrayList.get(position).getName());
        holder.Top5players.setText(String.valueOf(arrayList.get(position).getScore()));
        holder.Top5UserScore.setText(String.valueOf(arrayList.get(position).getScore()));



        holder.imageButton_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                firebaseAuth = FirebaseAuth.getInstance();
//                firebaseUser = firebaseAuth.getCurrentUser();
//                databaseReference = FirebaseDatabase.getInstance().getReference("AllBooks");
//                databaseReference.child(book_name.get(position).getName()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(context, "Book Deleted!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("Authors").child(firebaseUser.getUid()).child("Books");
//                databaseReference.child(book_name.get(position).getName()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        Toast.makeText(context, "Book Deleted!", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
        return row;
    }
}
class ViewHolder{
    ImageView imageView;
    TextView GameName,Top5players,Top5UserScore;
    ImageButton imageButton_Delete;

    ViewHolder(View view){
        imageView = view.findViewById(R.id.imageView_book);
        GameName = view.findViewById(R.id.textView_game_name);
        Top5players = view.findViewById(R.id.textView_Top_5_players);
        Top5UserScore = view.findViewById(R.id.textView_top_5_user_score);
        imageButton_Delete = view.findViewById(R.id.button_delete_book);


    }
}