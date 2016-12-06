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





Documents
-------------

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




----------
