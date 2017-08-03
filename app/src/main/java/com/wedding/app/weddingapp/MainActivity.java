package com.wedding.app.weddingapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {//


    TextView Title,Message,txtViewUrnoti;

    // Sqllite variables
    RegistrationAdapter adapter_ob;
    Cursor cursor;
// for deleting list
    int rowId, nameId;
    Cursor c;
    RegistrationAdapter regadapter;
    RegistrationOpenHelper helper_ob;
    SQLiteDatabase db_ob;
    ListView nameList;
    Button registerBtn;
    RegistrationAdapter adapter;

// countdown


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Title=(TextView)findViewById(R.id.text_title);
        Message=(TextView)findViewById(R.id.text_message);
        txtViewUrnoti=(TextView)findViewById(R.id.urnoti);

        regadapter = new RegistrationAdapter(this);
        c = regadapter.queryAll(rowId);

       startService(new Intent(this,MyService.class));

        //Sql
        nameList = (ListView) findViewById(R.id.lv_name);
        registerBtn = (Button) findViewById(R.id.btn_register);

        // java current date time
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
      //  System.out.println(dateFormat.format(date));

        adapter_ob = new RegistrationAdapter(this);
        adapter = new RegistrationAdapter(this);
        String[] from = { helper_ob.FNAME, helper_ob.LNAME,helper_ob.DATETIME}; // this code from SQLite database
        int[] to = { R.id.tv_fname, R.id.tv_lname,R.id.donoti};                 // binding the fields to listview
        cursor = adapter_ob.queryName();
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                R.layout.row, cursor, from, to);
        nameList.setAdapter(cursorAdapter);

        // Register the ListView  for Context menu
        registerForContextMenu(nameList);

       /* nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2,
                                    long arg3) {
           try {

               Bundle passdata = new Bundle();
               Cursor listCursor = (Cursor) arg0.getItemAtPosition(arg2);
                nameId = listCursor.getInt(listCursor
                       .getColumnIndex(helper_ob.KEY_ID));

               Toast.makeText(getApplicationContext(),Integer.toString(nameId), Toast.LENGTH_SHORT).show();
           }
           catch(Exception e){}
            }
        });
        */
        nameList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                @Override
                                                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                                                  Cursor listCursor=(Cursor) parent.getItemAtPosition(position);
                                                    nameId=listCursor.getInt(listCursor.getColumnIndex(helper_ob.KEY_ID));
                                                    return false;
                                                }
                                            });


       /* registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent registerIntent = new Intent(MainActivity.this,
                        RegistrationActivity.class);
                startActivity(registerIntent);
            }
        });
*/

        if(getIntent().getExtras()!=null)
        {

            for(String key: getIntent().getExtras().keySet())
            {
                if(key.equals("title")) {
                    Title.setVisibility(View.VISIBLE);
                    Title.setText(getIntent().getExtras().getString(key));

                }
                 if(key.equals("message")) {
                     Message.setVisibility(View.VISIBLE);
                     Message.setText(getIntent().getExtras().getString(key));

                 }



                Message.setPadding(0,1,20,3);
            }
            long val = adapter.insertDetails(Title.getText().toString(), Message.getText().toString(),dateFormat.format(date).toString());


        }



       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is a ALertU", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */


}
    /*
    private long lastPressedTime;
    private static final int PERIOD = 2000;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            switch (event.getAction()) {
                case KeyEvent.ACTION_DOWN:
                    if (event.getDownTime() - lastPressedTime < PERIOD) {
                        finish();
                    } else {
                        Snackbar snackbar = Snackbar
                                .make(findViewById(android.R.id.content), "Press Again to Exit", Snackbar.LENGTH_LONG);

                        snackbar.show();
                        lastPressedTime = event.getEventTime();
                    }
                    return true;
            }
        }
        return false;
    }*/

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        System.out.println("----main activity---onStart---");
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.setHeaderTitle("Delete ?");
        menu.add(0, v.getId(), 0, "Delete this notification..?");//groupId, itemId, order, title




    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Delete this notification..?"){

            regadapter.deletOneRecord(nameId);
            Toast.makeText(getApplicationContext(),"deleted",Toast.LENGTH_LONG).show();
           Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
      else{
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        cursor.requery();

    }

        public void clickContact(View view)
        {
            Intent intent2=new Intent(this,Contacts.class);
            startActivity(intent2);
           finish();
        }

    public void clickMap(View view)
        {
            // Build the intent
            Uri location = Uri.parse("geo:0,0?q=Krishna+Sea+Sight,+Puri");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

// Verify it resolves
            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
            boolean isIntentSafe = activities.size() > 0;

// Start an activity if it's safe
            if (isIntentSafe) {
                startActivity(mapIntent);
            }
            else
            {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.google.android.apps.maps"));
                startActivity(intent);
            }

        }

            public void clickGallery(View view)
            {
                Intent intent3=new Intent(this,GalleryActivity.class);
                startActivity(intent3);
            }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.menu_main, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.locationmap:
                //Intent intent1=new Intent(this,Location.class);
                // startActivity(intent1);
                // Build the intent
                Uri location = Uri.parse("geo:0,0?q=Krishna+Sea+Sight,+Puri");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

// Verify it resolves
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

// Start an activity if it's safe
                if (isIntentSafe) {
                    startActivity(mapIntent);
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.google.android.apps.maps"));
                    startActivity(intent);
                }

                return true;
            case R.id.contacts:
                Intent intent2=new Intent(this,Contacts.class);
                startActivity(intent2);
                return true;
            case R.id.gallery:
                Intent intent3=new Intent(this,GalleryActivity.class);
                startActivity(intent3);
                return true;
            case R.id.countdown:
                Intent intent4=new Intent(this,Countdown.class);
                startActivity(intent4);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    */


}