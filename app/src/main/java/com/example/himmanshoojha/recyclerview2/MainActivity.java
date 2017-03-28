package com.example.himmanshoojha.recyclerview2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.util.ArrayList;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn;
    RecyclerView recyclerView_v,recyclerView_h;

    RecyclerView.LayoutManager layoutManager_v,layoutManager_h;

    MyAdapter myAdapter;

    ArrayList<ImageGallery>imageGalleries;



    ArrayList<String> photoPaths = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.bttn);

        btn.setOnClickListener(this);

        recyclerView_v = (RecyclerView)findViewById(R.id.recyclerv_v);

        recyclerView_h = (RecyclerView)findViewById(R.id.recyclerv_h);

        layoutManager_v = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true);

        recyclerView_v.setLayoutManager(layoutManager_v);

        recyclerView_v.setHasFixedSize(true);

        layoutManager_h = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);

        recyclerView_h.setLayoutManager(layoutManager_h);

        recyclerView_h.setHasFixedSize(true);



        


    }

    @Override
    public void onClick(View v) {
        photoPaths.clear();
        FilePickerBuilder.getInstance().setMaxCount(6)
                .setSelectedFiles(photoPaths)
                .setActivityTheme(R.style.AppTheme)
                .pickPhoto(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case FilePickerConst.REQUEST_CODE:
                if(resultCode==RESULT_OK && data!=null){{
                    photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_PHOTOS));
                    ImageGallery imageGallery;
                    imageGalleries = new ArrayList<>();
                    try {
                        for(String path:photoPaths){
                            imageGallery = new ImageGallery();
                            imageGallery.setUri(Uri.fromFile(new File(path)));
                            imageGalleries.add(imageGallery);
                        }
                        myAdapter = new MyAdapter(imageGalleries,MainActivity.this);
                        recyclerView_h.setAdapter(myAdapter);

                        recyclerView_v.setAdapter(myAdapter);

                    }catch (Exception e){
                        e.printStackTrace();
                    }


                }

            }
        }
    }


}
