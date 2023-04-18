package com.example.befree;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.rengwuxian.materialedittext.MaterialEditText;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import dmax.dialog.SpotsDialog;

public class NoteDetalis extends AppCompatActivity {
    List<ToDo> toDoList = new ArrayList<>();
    FirebaseFirestore db;

    RecyclerView listItem;
    RecyclerView.LayoutManager layoutManager;

    FloatingActionButton fab;


    public MaterialEditText title,description;
    public boolean isUpdate = false;
    public String idUpdate = "";
    Adapter adapter;
   SpotsDialog dialog;



//    private RecyclerView recycler;
//    private FloatingActionButton floatingActionButton;
//
//
//    private DatabaseReference reference;
//    private FirebaseAuth mAuth;
//    private FirebaseUser mUser;
//    private String onlineUserID;
//
//    private ProgressDialog loader;
//
//    private String key = "";
//    private String task;
//    private String description;
//
//    ImageView vern;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detalis);
        db = FirebaseFirestore.getInstance();
        dialog = new SpotsDialog(this);
        title = (MaterialEditText) findViewById(R.id.title);
        description = (MaterialEditText) findViewById(R.id.description);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Добавление
                if(!isUpdate){
                    setData(title.getText().toString(),description.getText().toString());
                }
                else {
                    updateDate(title.getText().toString(),description.getText().toString());
                    isUpdate = !isUpdate;
                }

            }
        });

        listItem = (RecyclerView) findViewById(R.id.listTodo);
        listItem.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listItem.setLayoutManager(layoutManager);

        loadData();









//        vern = findViewById(R.id.vern);
//        vern.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(NoteDetalis.this,Fragment_Home.class);
//                startActivity(intent);
//            }
//        });

//        mAuth = FirebaseAuth.getInstance();
//        recycler = findViewById(R.id.recycler);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setReverseLayout(true);
//        linearLayoutManager.setStackFromEnd(true);
//        recycler.setHasFixedSize(true);
//        recycler.setLayoutManager(linearLayoutManager);

//        loader = new ProgressDialog(this);
//
//        mUser = mAuth.getCurrentUser();
//        onlineUserID = mUser.getUid();
//        reference = FirebaseDatabase.getInstance().getReference().child("задачи").child(onlineUserID);
//
//        floatingActionButton = findViewById(R.id.fab);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addTask();
//            }
//        });
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle().equals("Удалено"))
            deleteItem(item.getOrder());
        return super.onContextItemSelected(item);
    }

    private void deleteItem(int index) {
        db.collection("Мои задачи")
                .document(toDoList.get(index).getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        loadData();
                    }
                });
    }

    private void updateDate(String title, String description) {
        db.collection("Мои задачи").document(idUpdate)
                .update("Задача",title,"Описание",description)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(NoteDetalis.this,"Обновлено!",Toast.LENGTH_SHORT).show();
                    }
                });
        db.collection("Мои задачи").document(idUpdate)
                .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        loadData();
                    }
                });
    }

    private void setData(String title, String description) {
        String id = UUID.randomUUID().toString();
        Map<String,Object> todo = new HashMap<>();
        todo.put("id",id);
        todo.put("title",title);
        todo.put("description",description);

        db.collection("Мои задачи").document(id)
                .set(todo).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        loadData();
                    }
                });
    }

    private void loadData() {
        dialog.show();
        if(toDoList.size() > 0)
            toDoList.clear();
        db.collection("Мои задачи")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot doc:task.getResult()){
                            ToDo todo = new ToDo(doc.getString("id"),
                                    doc.getString("title"),
                                    doc.getString("description"));
                            toDoList.add(todo);
                        }
                        adapter = new Adapter(NoteDetalis.this,toDoList);
                        listItem.setAdapter(adapter);
                        dialog.dismiss();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NoteDetalis.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }


//    private void addTask() {
//        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
//        LayoutInflater inflater = LayoutInflater.from(this);
//
//        View myView = inflater.inflate(R.layout.input_file,null);
//        myDialog.setView(myView);
//
//        final AlertDialog dialog = myDialog.create();
//        dialog.setCancelable(false);
//
//        final EditText task = myView.findViewById(R.id.task);
//        final EditText description = myView.findViewById(R.id.description);
//        Button save = myView.findViewById(R.id.savebtn);
//        Button cancel = myView.findViewById(R.id.cancelbtn);
//
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String mTask = task.getText().toString().trim();
//                String mDescription = description.getText().toString().trim();
//                String id = reference.push().getKey();
//                String date = DateFormat.getDateInstance().format(new Date());
//
//
//
//                if(TextUtils.isEmpty(mTask)){
//                    task.setError("Требуется задача");
//                    return;
//                }
//                if(TextUtils.isEmpty(mDescription)){
//                    description.setError("Требуется описание");
//                    return;
//                }else{
//                    loader.setMessage("Добавление даты");
//                    loader.setCanceledOnTouchOutside(false);
//                    loader.show();
//
//                    Model model = new Model(mTask,mDescription,id,date);
//                    reference.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(NoteDetalis.this,"Задача добавлена",Toast.LENGTH_SHORT).show();
//                                loader.dismiss();
//                            }else{
//                                String error = task.getException().toString();
//                                Toast.makeText(NoteDetalis.this,"Ошибка" + error,Toast.LENGTH_SHORT).show();
//                                loader.dismiss();
//                            }
//                        }
//                    });
//                }
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//
//    }
//    @Override
//    protected void onStart(){
//        super.onStart();
//
//        FirebaseRecyclerOptions<Model> options = new FirebaseRecyclerOptions.Builder<Model>()
//                .setQuery(reference,Model.class)
//                .build();
//
//
//        FirebaseRecyclerAdapter<Model,MyViewHolder> adapter = new FirebaseRecyclerAdapter<Model, MyViewHolder>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull final Model model) {
//                holder.setDate(model.getDate());
//                holder.setTask(model.getTask());
//                holder.setDesc(model.getDescription());
//
//                holder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        key = getRef(position).getKey();
//                        task = model.getTask();
//                        description = model.getDescription();
//
//
//                        updateTask();
//                    }
//                });
//            }
//
//            @NonNull
//            @Override
//            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrieved_layout,parent,false);
//                return new MyViewHolder(view);
//            }
//        };
//        recycler.setAdapter(adapter);
//        adapter.startListening();
//
//
//
//
//
//    }
//
//    public static class MyViewHolder extends RecyclerView.ViewHolder{
//        View mView;
//        public MyViewHolder(@NonNull View itemView){
//            super(itemView);
//            mView = itemView;
//        }
//        public void setTask(String task){
//            TextView taskTectView = mView.findViewById(R.id.taskTv);
//            taskTectView.setText(task);
//        }
//        public void setDesc(String desc){
//            TextView descTectView = mView.findViewById(R.id.descriptionTv);
//            descTectView.setText(desc);
//        }
//        public void setDate(String date){
//            TextView dateText = mView.findViewById(R.id.dateTv);
//        }
//    }
//
//    private void updateTask(){
//        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View view = inflater.inflate(R.layout.update_date,null);
//        myDialog.setView(view);
//
//
//        AlertDialog dialog = myDialog.create();
//
//        EditText mTask = view.findViewById(R.id.mEdittask);
//        EditText mDescription = view.findViewById(R.id.mEdittaskDesc);
//
//        mTask.setText(task);
//        mTask.setSelection(task.length());
//
//        mDescription.setText(description);
//        mDescription.setSelection(description.length());
//
//        Button deleteButton = view.findViewById(R.id.btnDelete);
//        Button updateButton = view.findViewById(R.id.btnUpdate);
//
//
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                task = mTask.getText().toString().trim();
//                description = mDescription.getText().toString().trim();
//
//                String date = DateFormat.getDateInstance().format(new Date());
//
//                Model model = new Model(task,description,key,date);
//
//                reference.child(key).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(NoteDetalis.this,"Данные успешно обновлены",Toast.LENGTH_SHORT).show();
//                        }else {
//                            String err = task.getException().toString();
//                            Toast.makeText(NoteDetalis.this,"Ошибка" + err,Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                dialog.dismiss();
//            }
//        });
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                reference.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(NoteDetalis.this,"Задача удалена успешно",Toast.LENGTH_SHORT).show();
//                        }else{
//                            String err = task.getException().toString();
//                            Toast.makeText(NoteDetalis.this,"Ошибка удаления" +err,Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
//    }
}
