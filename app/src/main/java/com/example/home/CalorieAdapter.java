package com.example.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalorieAdapter extends RecyclerView.Adapter<CalorieAdapter.Holder> {


    private Context context;
    private ArrayList<CalorieModel> arrayList;
    //db object
    CDatabaseHelper databaseHelper;


    public CalorieAdapter(Context context, ArrayList<CalorieModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        //initialize here
        databaseHelper = new CDatabaseHelper(context);

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.caloriesrow, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

        CalorieModel model = arrayList.get(position);
        //get data from model class
        final String id = model.getId();
        final String calorie = model.getCalorie();
        final String date = model.getDate();
        final String feelings = model.getFeelings();
        final String notes = model.getNotes();

        //set into xml
        holder.calorie.setText(calorie);
        holder.date.setText(date);
        holder.feelings.setText(feelings);
        holder.notes.setText(notes);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editDialog(
                        ""+position,
                        ""+id,
                        ""+calorie,
                        ""+date,
                        ""+feelings,
                        ""+notes
                );
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteDialog(
                        ""+id
                );

            }
        });


    }

    private void deleteDialog(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete activity");
        builder.setMessage("Are you sure you want to delete ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_delete);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                databaseHelper.deleteInfo(id);
                ((CaloriesActivity)context).onResume();
                Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();


            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        builder.create().show();
    }

    private void editDialog(String position, final String id, final String calorie, final String date, final String feelings, final String notes){

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Do you want to update.. ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_edit);

            builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent = new Intent(context, EditCalories.class);
                    intent.putExtra("ID", id);
                    intent.putExtra("CALORIE", calorie);
                    intent.putExtra("DATE", date);
                    intent.putExtra("FEELINGS", feelings);
                    intent.putExtra("NOTES", notes);
                    intent.putExtra("editCal", true);
                    context.startActivity(intent);

                }
            });

            builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.create().show();


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder{


        TextView calorie, notes, feelings, date;
        ImageButton editButton, deleteButton;



        public Holder(@NonNull View itemView) {
            super(itemView);

            calorie = itemView.findViewById(R.id.text1);
            date = itemView.findViewById(R.id.text2);
            feelings = itemView.findViewById(R.id.text3);
            notes = itemView.findViewById(R.id.text4);
            editButton = itemView.findViewById(R.id.editbtn);
            deleteButton = itemView.findViewById(R.id.deletebtn);


        }
    }

}
