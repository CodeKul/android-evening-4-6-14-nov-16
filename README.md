codekul.com android
===================


This repo contains codes for evening batch

----------


View
-------------

basic building block for user interface components,  rectangular area on the screen and is responsible for drawing and event handling. Used to create interactive UI components and invisible containers

----------

aapt(android asset packaging tool)
-------------

Looks your res folder and generates R.java file. Every subfolder of res folder is represented as public static final inner class , every single file of subfolder is represented as public static int field

----------

setContentView(R.layout.activity_main);
-------------

parses the given xml and creates objects in the memory.

----------

@+id/btnOkay
-------------

@ = R file, + says create new resource, btnOkay is name of the filed inside id resource

----------

findViewById(R.id.btnOkay);
-------------

get the reference of that view whos id is btnOkay and created after setContentView call.

----------

providing click listener using anonymous inner type
-------------


get the reference of that view whos id is btnOkay and created after setContentView call.

    Button btnOkay = (Button) findViewById(R.id.btnOkay);
         btnOkay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });

 Type cast is needed because you are assigning parent to child.


----------

enabling java8
-------------

if you want to enable java8 you need to add following code snipped to your build.gradle

    android {
	   defaultConfig {
	     jackOptions {
	       enabled true
	     }
	   }
	   compileOptions {
	     sourceCompatibility JavaVersion.VERSION_1_8
	     targetCompatibility JavaVersion.VERSION_1_8
	   }
	 }

----------

lambda for button click
-------------

     btnOkay.setOnClickListener(view -> {
            text.setText("lambda");
        });

----------

method references for button click
-------------

    btnOkay.setOnClickListener(this::okayClicked);


    private void okayClicked(View view){
        TextView text = (TextView) findViewById(R.id.textStatus);
        text.setText("method ref");
    }


----------




View measurement units
-------------

**px**
Pixels - corresponds to actual pixels on the screen.

**in**
Inches - based on the physical size of the screen.
1 Inch = 2.54 centimeters

**mm**
Millimeters - based on the physical size of the screen.

**dp or dip**
Density-independent Pixels - an abstract unit that is based on the physical density of the screen. These units are relative to a 160 dpi screen, so one dp is one pixel on a 160 dpi screen. The ratio of dp-to-pixel will change with the screen density, but not necessarily in direct proportion. Note: The compiler accepts both "dip" and "dp", though "dp" is more consistent with "sp".

**sp**
Scale-independent Pixels - this is like the dp unit, but it is also scaled by the user's font size preference. It is recommend you use this unit when specifying font sizes, so they will be adjusted for both the screen density and user's preference.

----------

findViewById(R.id.btnConvert).setOnClickListener(this::cliked);
-------------

Every view is clickable so dont worry about casting

----------

dp to px conversion 
-------------

px = dp * (dpi / 160)



----------

why to use sp and not dp for text sizes
-------------

dp has constant ratio transition to px: dp = px * ratio, where ratio never changes. 
sp (s for scaled) has scalable ratio: sp = px * ratio * scale , where ratio is constant and scale changes 



----------

padding and margin
-------------

**padding** - shifts the content

**margin** - shifts whole view 



----------

gravity and layout_gravity
-------------

**gravity** - shifts the content

**layout_gravity** - shifts whole view 



----------





RelativeLayout
--------------

arrangement of views as per relative pos of the parent or another views



----------



InterActivity Communication
---------------------------




     Intent intent = new Intent(this, NewActivity.class); 

**this**  is context
**NewActivity.class** is type information of target activity

Source code for above Intent constructor is 

    /**
     * Create an intent for a specific component.  All other fields (action, data,
     * type, class) are null, though they can be modified later with explicit
     * calls.  This provides a convenient way to create an intent that is
     * intended to execute a hard-coded class name, rather than relying on the
     * system to find an appropriate class for you; see {@link #setComponent}
     * for more information on the repercussions of this.
     *
     * @param packageContext A Context of the application package implementing
     * this class.
     * @param cls The component class that is to be used for the intent.
     *
     * @see #setClass
     * @see #setComponent
     * @see #Intent(String, android.net.Uri , Context, Class)
     */
    public Intent(Context packageContext, Class<?> cls) {
        mComponent = new ComponentName(packageContext, cls);
    }



----------







Starting new activity
-------------


    startActivity(intent); starting new activity for given intent.


----------



Passing data to new activity
-------------


   The activity who needs to **pass data** should use following code

    Bundle bundle = new Bundle();
    bundle.putString(KEY_MY_OS,getOs());

    intent.putExtras(bundle);

In the **new activity** you can get the passed data using 

     private String getOsFromMain() {
        Intent intent = getIntent();
        if(intent == null) throw new RuntimeException();

        Bundle bundle = intent.getExtras();
        String os = bundle.getString(MainActivity.KEY_MY_OS);

        return os;
    }

----------






send data back
-------------

If A activity needs data back from B activity.  A should start activity with 

    private void openNewActivity() {
        Intent intent = new Intent(this, NewActivity.class);

        Bundle bundle = new Bundle();
        bundle.putString(KEY_MY_OS,getOs());

        intent.putExtras(bundle);

        //startActivity(intent);

        startActivityForResult(intent, REQ_NEW_ACTIVITY);
    }
Once you comeback from B activity to A activity, **onActivityResult** from A activity gets invoked 

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_NEW_ACTIVITY){
            if(resultCode == RESULT_OK){
                setOs(getMobile(data));
            }
        }
    }

Activity B will process the data and send it back to activity A using following code

    private void throwDataToBack() {
        Intent intent = new Intent();
        intent.putExtra(KEY_MY_MOBILE,getMobile());

        setResult(RESULT_OK,intent);
        finish();
    }


  

----------




Compound Views
-------------

Combination of multiple views which will represent single view object.

 


----------


View Inflater
-------------

Creating object of layout inflater 

    LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);



if you know the context you can use above or following 

    LayoutInflater inflater = LayoutInflater.from(this);

and if you are in the activity use 

    LayoutInflater inflater = getLayoutInflater();

 

layout inflater will **convert** your **xml** to the **view** object, it is system **service**

----------


Accessing view inside compound view
-------------

This is how we will access view inside compound view 

    View compundView = inflater.inflate(R.layout.social_media, null, false);
    TextView textStatus = (TextView) compundView.findViewById(R.id.textStatus);
    textStatus.setText(status);

 


----------







Custom Views
-------------

extending to view class for working with canvas. you will need to override onDraw method which is coming from view class. You can either extend to directly view or TextView or your needed view. After overriding you will get canvas object to draw something on view.  Here is the code sample

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        canvas.drawText("{code}kul;",300,70,paint);
        paint.setColor(Color.BLACK);

        canvas.drawCircle(circlex,circley,20,paint);

        canvas.drawCircle(50,50,30,paint);
    }

when you will extend to view class you need to override 2 constructors, one for using your view via code and other for xml 

**via code**


     public MyView(Context context) {
        super(context);
        // by coding

        init();
    }



**via xml**



    public MyView(Context context, AttributeSet attr) {
        super(context,attr);

        // by xml

        init();
    }



----------





Adapter Views
-------------

**Adapter**
An Adapter object acts as a bridge between an {@link AdapterView} and the  underlying data for that view. The Adapter provides access to the data items.  The Adapter is also responsible for making a {@link android.view.View} for each item in the data set.

**ListView** 
A view that shows items in a vertically scrolling list. The items  come from the {@link ListAdapter} associated with this view.


**ArrayAdapter**
A concrete BaseAdapter that is backed by an array of arbitrary  objects.  By default this class expects that the provided resource id references  a single TextView.  If you want to use a more complex layout, use the constructors that  also takes a field id.  That field id should reference a TextView in the larger layout resource.



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataSet);
        ListView listView = (ListView) findViewById(R.id.listCountries);
        listView.setAdapter(adapter);




----------






Custom Adapter
-------------
Your adapter should extend to base adapter, which will allow you to add 4 method from BaseAdapter calss



   

     @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return countries.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        if(convertView == null) view = inflater.inflate(R.layout.list_item,null,false);
        else view = convertView;

        ((ImageView)view.findViewById(R.id.imageFlag))
                .setImageResource(countries.get(position).getCountryFlag());

        ((TextView)view.findViewById(R.id.textCountryName))
                .setText(countries.get(position).getCountryName());

        return view;
    }


 

----------


Activity Lifecycle
-------------

 There are *7* methods of activity lifecycle and it is divided into 4 states i.e.  *running*, *paused*, *stopped* or *destroyed*


    public class Activity extends ApplicationContext {
     protected void onCreate(Bundle savedInstanceState);

     protected void onStart();

     protected void onRestart();

     protected void onResume();

     protected void onPause();

     protected void onStop();

     protected void onDestroy();
     }


----------





Saving state of activity
-------------

 If your activity would be getting destroyed due to runtime configuration change or lack of memory you need to maintain the state.

     @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TEXT_INFO, product.getName());
        outState.putFloat(KEY_TEXT_PRICE, product.getPrice());
        outState.putInt(KEY_TEXT_CASHBACK, product.getCashBack());
    }

onSavedInstanceState would getting called before activity getting destroyed due to above reasons. You can retain the state by either using Bundle that comes with onCreate or you can override separate method onRestoreInstanceState 

**onCreate**

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState  != null) {
            ((TextView)findViewById(R.id.textInfo)).setText(savedInstanceState.getString(KEY_TEXT_INFO) + " - > " + savedInstanceState.getString(KEY_TEXT_ADDRESS));
        }


        findViewById(R.id.btnOkay).setOnClickListener(this::clicked);
    }


**onRestoreInstanceState**


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if(savedInstanceState  != null)
            ((TextView)findViewById(R.id.textInfo)).setText(savedInstanceState.getString(KEY_TEXT_INFO) + " - > " + savedInstanceState.getFloat(KEY_TEXT_PRICE));
    }



----------



Handling Runtime Changes
-------------

**by android** 

Almost every application should provide alternative resources to support specific device configurations e.g. **layout-en , layout-fr** here you are saying if locale of your phone is English; select layout-en other wise if it French select layout-fr . Any resource can be proceeded with qualifier. As per your phone configuration resource folder wold be getting selected. This strategy is having one problem, on the configuration change android restarts the activity every time, which is heavier job for handling.

**by own**

By considering above flaw, you can handle runtime changes on your own by mentioning it in manifest file to the activity.  

*manifest file*

    <activity android:name=".MainActivity" android:configChanges="orientation|screenSize">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

 

in an activity you have to override onConfigurationChanged method for receiving  new configuration object mentioned in activity tag. In above case it is *orientation* and *screenSize* 

*activity method*


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ((ImageView)findViewById(R.id.imageCenter)).setImageResource(R.drawable.hulk);
        }
        else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            ((ImageView)findViewById(R.id.imageCenter)).setImageResource(R.drawable.hulk1);
        }
        else {
            ((ImageView)findViewById(R.id.imageCenter)).setImageResource(R.mipmap.ic_launcher);
        }
    }




----------






Simple Value Resources
-------------

Externalization of static data is important in the perspective of maintainability and code readability . For this android has given you few resources like string, dimen, color, styles, string array, int array etc. 

**string** -  all strings that would be used some where your project 
*xml*  ` <string name="styleText">CodeKul</string>`
*from java code*  `String nameBtn = getResources().getString(R.string.btnOkay);`

**color** - useful colors would be mentioned here 
*xml* `<color name="pureWhite">#FFFFFF</color>`
*java code*  `pureWhite = ContextCompat.getColor(this,R.color.pureWhite);`

**styles** - we can combine few properties of a view in one style, to use them repeatedly.

*xml defination*  

      <style name="simpleStyle">
        <item name="android:text">@string/styleText</item>
        <item name="android:layout_width">200dp</item>
        <item name="android:layout_height">150dp</item>
    </style>

*xml use*

     <TextView
        android:id="@+id/textSimple1"
        style="@style/simpleStyle"/>
        
If style would be getting applied to activity or application it becomes theme. 

    <activity
            android:name=".MainActivity"
            android:theme="@style/simpleStyle">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>


----------


Fragments
-------------

Fragment is portion of an activity, which has its own life cycle, but it is dependent on host activity life cycle.  Fragments can be created using 2 ways , via fragment tag and fragment transaction.  

**fragment tag**

     <fragment
        android:id="@+id/fragmentLogin"
        android:layout_width="match_parent"
        android:layout_height="150dp"
        class="com.codekul.fragments.LoginFragment"
        />

 
r 
**fragment class**

Your fragment at least override onCreateView to return the face of fragment. But sometimes fragments can be faceless. You can use support fragment to run your app below honeycomb(3.x) series. 

    public class SplashFragment extends Fragment {


    public SplashFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

}


----------



Fragment Transaction
-------------

You can load fragment by running fragment transaction. Every fragment knows the host activity i.e. on which fragment is running by the following method call

    getActivity() 

If you want to load fragment to the activity you are going to use ***FragmentManager*** and ***FragmentTransaction*** in following way 

    public void runFragmentTxn(Fragment frag,String name) {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction txn = manager.beginTransaction();
        txn.addToBackStack(name);
        txn.replace(R.id.frameContainer, frag);
        txn.commit();
    }

Committing the is very important. Above method manages fragment backstack at very primitive level. You can write your logic to maintain the back stack in onBackPressed method

----------


Fragment Best practices 
-------------

Creating object of fragment , consider following 2 statements 

    LoginFragment.getInstance("android","android")
    new RegisterFragment()

Here is second statement you are creating object of RegistrationFragment and in first statement you are creating object of LoginFragment.  But first is recommended by android, because android needs empty constructor for fragment class. Why ? android creates object of fragment class using instantiate() method and this method uses newInstance() from reflection api , so it needs empty constructor lets see code below 

     Class<?> clazz = sClassMap.get(fname);
            if (clazz == null) {
               // Class not found in the cache, see if it's real, and try to add it
                clazz = context.getClassLoader().loadClass(fname);
                sClassMap.put(fname, clazz);
            }
            Fragment f = (Fragment)clazz.newInstance();
            if (args != null) {
                args.setClassLoader(f.getClass().getClassLoader());
                f.mArguments = args;
            }
            return f;

 
Here is fragments code to create object and pass bundle arguments, at the time creation of object. 

    public static LoginFragment getInstance(String userName, String password) {

        LoginFragment fragment = new LoginFragment();

        Bundle bundle = new Bundle();
        bundle.putString(KEY_USER_NAME,userName);
        bundle.putString(KEY_PASSWORD,password);

        fragment.setArguments(bundle);

        return fragment;
    }

----------



Toast Notification
-------------

**Toast**

Simple message to user, generally not intractable. By the definition in document toast is  *A toast is a view containing a quick little message for the user.  The toast class  helps you create and show those.* you can create toast using following code

    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
 
 this - context, msg - message to display ,duration - short duration. Until and unless you are not going to call show(), toast will not be displayed. 
 
**custom toast**   
   
   you can create toast with any view by specifying view to the toast object.

    private void customToast(){
        Toast toast = Toast.makeText(this,"",Toast.LENGTH_SHORT);
        ImageView image = new ImageView(this);
        image.setImageResource(R.mipmap.ic_launcher);
        toast.setView(image);
        toast.show();
    }

----------


Status bar notifications
-------------

these will appear in the status bar . 

**Pending Intent**
In this type of notification user action is pending until and unless user is not going to click on status bar notification, in such cases you are going to use pending intent. Pending can start new activity, start new service or broadcast .

    Intent intent = new Intent(this, NotificationActivity.class);

    PendingIntent pendingIntent =
                PendingIntent.getActivity(this, REQ_NOTIFICATION, intent, PendingIntent.FLAG_ONE_SHOT);


where this - context, REQ_NOTIFICATION - request code, intent - intent to fired,  PendingIntent.FLAG_ONE_SHOT - decided data of the new activity.


Sending actual notification is as simple as follows

     NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.drawable.ic_android_black_24dp,"Action",pendingIntent)
                        .build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentInfo(getResources().getString(R.string.contentInfo));
        builder.setContentTitle(getResources().getString(R.string.contentTitle))
                .setContentText(getResources().getString(R.string.contentText))
                .setDefaults(Notification.DEFAULT_ALL)
                .setOngoing(true)
                .setContentIntent(pendingIntent)
                .addAction(action)
                .setAutoCancel(true);


        Notification notification = builder.build();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_SIMPLE, notification);


actions are nothing but buttons in the notification.


----------


Dialog Notification
-------------

**FragmentDialog**

whenever you will need multiple dialogs, it good practice to extend your dialog with FragmentDialog class.

    public class MyDialog extends DialogFragment {

   
	    @NonNull
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	
	        Dialog dialog  = null;
		       
	        return dialog;
	    }
    }



where you can return the dialog object by reference of tag, that will be passed at the time of show method call


    private void showDialog(String tag) {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(),tag);
    }


**Alert**

This dialog is used to present yes, no situation. You can assign title, message body, icon and positive, negative, neutral buttons.  

    private AlertDialog showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.mipmap.ic_launcher)
                .setMessage(R.string.message)
                .setTitle(R.string.title)
                .setPositiveButton(R.string.btnYes, this::alertButtonClicked)
                .setNegativeButton(R.string.btnNo, this::alertButtonClicked);
       return  builder.create();
    }

**DatePickerDialog** 

It will show you calendar like view where you can select any date for any year. You can retrieve selected date using OnDateSetListener 

    private Dialog showDatePicker() {

        //Calendar
        DatePickerDialog datePicker = new DatePickerDialog(getActivity(), 
        (view, year, month, dayOfMonth) -> { }  , 2017,0,1);
        
        return  datePicker;
    }

**TimePickerDialog**

It will show you clock like view where you can select hours , minutes and 12 or 24 hour clock. You can retrieve selected time using OnTimeSetListener 

    private Dialog showTimePicker() {

        TimePickerDialog timePicker =
                new TimePickerDialog(getActivity(),(view, hourOfDay, minute) -> mt(""+hourOfDay +" : "+ minute),6,8,true);

        return timePicker;
    }


**Progress Dialog**

It is used to show indefinite rotating spinner or definite progress bar. You can define max progress and change progress over time.  You can assign title, message and buttons. 


    private Dialog showProgressDialog() {

        ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle(R.string.title);
        progressDialog.setMessage(getResources().getString(R.string.message));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE,getResources().getString(R.string.btnYes),(dialog, which) -> mt("Yes Clicked"));
        progressDialog.setMax(100);
        progressDialog.setProgress(45);
        return progressDialog;
    }

**Custom dialog**

It is alert dialog where you can decide custom view by setView method. Here you can use layout inflater to generate view from xml.

     private Dialog showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_dialog,null,false);
        view.findViewById(R.id.btnLogin).setOnClickListener(v -> mt("Login Clicked"));
        builder.setView(view);
        return builder.create();
    }



----------


Intents and IntentFilters
-------------

**Explicit Intent**

Intent to which you are assigning source context and target type information explicitly are refereed as explicit intents. 

    Intent intent = new Intent(this, NewActivity.class);

**Implicit Intents**

Intent to whom we are passing action, category and data are referred as implicit intents .

     Intent intent = new Intent();
     intent.setAction(Intent.ACTION_VIEW);
     intent.setDataAndType(Uri.parse("my.png"),"image/*");
     startActivity(intent);


**Intent Filters**

Everything from intent should find match with intent filter.  Generally intent filters are described in manifest file to the activity , service and receiver tag.

    <activity android:name=".NewsActivity" >
            <intent-filter>
                <action android:name="com.codekul.action.COMMAN"/>
                <action android:name="com.codekul.action.NEWS"/>
                <category android:name="android.intent.category.DEFAULT"/>
            </intent-filter>
      </activity>

Assigning data to intent filter tag, If intent filter has data your intent should specify the data with setData method

    <activity android:name=".SportsActivity" >
            <intent-filter>
                <action android:name="com.codekul.action.COMMAN"/>
                <action android:name="com.codekul.action.SPORTS"/>
                <category android:name="android.intent.category.DEFAULT"/>
                <data android:scheme="http" />
            </intent-filter>
    </activity>

here intent for above intent filter will be as follows

    private void openCommanWithData() {
        Intent intent = new Intent();
        intent.setAction("com.codekul.action.COMMAN");
        /* # */intent.setData(Uri.parse("http://codekul.com"));
        startActivity(intent);
    }


**dial** 

Use ACTION_DIAL action for opening dial screen

     private void dial(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_DIAL);
        startActivity(intent);
    }

**call**

use ACTION_CALL action for directly calling,

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel://9762548833"));
        startActivity(intent);
    }
to use this make sure that you have CALL permission in manifest file. If you are running above api level 23 i.e. on or above marshmallow you need get that permission manually from app settings

    <uses-permission android:name="android.permission.CALL_PHONE"/>


**share intent** 

If you want to your data to be shared you can use share intent 

     private void shareIntent(){
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "body";
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "sub");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

**any intent**

To ACTION_VIEW you can pass any type of data to open respective activity.

    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.parse("my.png"),"image/*");
    startActivity(intent);
 

----------




Storage
-------------

 - Shared Preferences
 - Internal Storage
 - External Storage
 - Sqlite

**Shared Preferences**
To store primitive types in key and value pairs. 

*getSharedPreferences* - provide name of preference file and can be accessed by any app.

*getPreferences* - only activity can access this in your provided mode.

*Editor* - all the changes you are going to make that would  be batched in editor. So until and unless you are not going to call *commit* changes would not be getting reflected to preference file. 

**Internal Storage** 

private data of your application, which can not be accessed by any other apps, unless you provide content provider for your private files.

*openFileInput and openFileOutPut* -  this will read or write to the private storage using standard FileInputStream or FileOutputStream.

    private void saveData() {
        try {
            FileOutputStream fos = openFileOutput("my.txt",MODE_APPEND);
            fos.write("Hello, codekul".getBytes());
            fos.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    private void display() {
        try {
            FileInputStream fis = openFileInput("my.txt");
            StringBuilder builder = new StringBuilder();
            while(true){
                int ch = fis.read();
                if(ch == -1) break;
                else builder.append((char)ch);
            }

            ((TextView)findViewById(R.id.textData))
                    .setText(builder.toString());
            fis.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

*getFilesDir* - it will return private location path.

*getCacheDir* - it will return the path where cache files will be stored in private storage.

**External Storage** 

You need to have permissions READ_EXTERNAL_STORAGE or WRITE_EXTERNAL_STORAGE.

It is sometimes private data of your app on external storage or publicly  accessible data on sdcard. It can be your fixed internal storage.

check external storage state

     private Boolean isMediaGood() {
        return Environment
                .getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }


*getExternalFilesDir* - saves private data on external storage, which can be accessed only by your app. you can pass the directory, where you want to store files 

    private void saveAppPrivate() {

        if (isMediaGood()) {
            File file = new File(getExternalFilesDir(""), "my.txt");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write("Hello , CodeKul".getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else mt(getResources().getString(R.string.bad_media));
    }

*Environment.getExternalStoragePublicDirectory()*  - saves publicly accessible data on external storage. Here also you can pass directory where you want to store files.

    private void savePublicData() {

        if (isMediaGood()) {
            File file = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    "my.txt");

            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.write("Hello , CodeKul".getBytes());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else mt(getResources().getString(R.string.bad_media));
    }

  
  
----------


SQLite
-------------

 - liter version of sql
 - self-contained, serverless, zero-configuration, transactional

**SqliteOpenHelper**

A helper class to manage database creation and version management. onCreate and onUpgrade need to 
be implemented in children.

*onCreate* - will be called only once so it is good place to create your tables.

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("create table myTable(myName text, myAge number)");
    }

*onUpgrade* - when db versions are going to be mismatched this method gets invoked.

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

**Db operations** 

*Insert -* content values would be used for mapping columns to value. Data need to be opened in write mode.

    private void insert(){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("myName",getMyName());
        values.put("myAge",getMyAge());
        db.insert("myTable",null,values);

        db.close();
    }

*Display* - here you need to manage cursor. we need to iterate over cursor. Excluding table name if all parameters are null then query will be *select * from table name.*

    private void display() {

        SQLiteDatabase db = helper.getReadableDatabase();

        String table = "myTable";
        String[] columns = {"myName"};
        String selection = "myAge = ?";
        String[] selectionArgs = {""+getMyAge()};
        String groupBy = null;
        String having =null;
        String orderBy = null;

        Cursor cursor = db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);

        if(cursor.moveToNext()){
            String myName = cursor.getString(cursor.getColumnIndex("myName"));
            //Integer myAge = cursor.getInt(cursor.getColumnIndex("myAge"));
            //Log.i("@codekul","Name - "+myName +" Age - "+myAge);
            Log.i("@codekul","Name - "+myName );
            ((EditText)findViewById(R.id.edtMyName)).setText(myName);
        }
        db.close();
    }

*update*  -  for updating any record you need to pass value that need to be updated and where statement. 

    private void update() {

        SQLiteDatabase db = helper.getWritableDatabase();

        String table="myTable";
        ContentValues values = new ContentValues();
        values.put("myName",getMyName());
        String whereClause = "myAge = ?";
        String[] whereArgs = {String.valueOf(getMyAge())};

        db.update(table,values,whereClause,whereArgs);

        db.close();
    }

 *Delete* - for delete operation you need to pass the where clause

    private void delete() {

        SQLiteDatabase db = helper.getWritableDatabase();

        String table = "myTable";
        String whereClause = "myAge = ?";
        String[] whereArgs = {String.valueOf(getMyAge())};

        db.delete(table,whereClause,whereArgs);

        db.close();
    }


**native sqls** 

You can either use androids way for performing db operations or use native sqls as follows 

*select Query -*  for selecting from database we will use rawQuery method which will return cursor.

    private void selectRawSql() {
        SQLiteDatabase sqDb = helper.getReadableDatabase();
        Cursor cursor = sqDb.rawQuery("select * from myTable",null);
        while(cursor.moveToNext()) {
            String myName = cursor.getString(cursor.getColumnIndex("myName"));
            int myAge = cursor.getInt(cursor.getColumnIndex("myAge"));
            Log.i("@codekul","Name - "+myName +" Age - "+myAge);
        }
        sqDb.close();
    }


*insert query -*  if you want to insert you would be using execSql .

     private void insertRaw() {
        SQLiteDatabase sqDb = helper.getWritableDatabase();
        sqDb.execSQL("insert into myTable values('rawAndroid',20)");
        sqDb.close();
    }

----------





Documents
-------------


----------



