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


