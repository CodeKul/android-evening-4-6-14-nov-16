This repo contains codes for evening batch

----------


View
-------------

basic building block for user interface components,  rectangular area on the screen and is responsible for drawing and event handling. Used to create interactive UI components and invisible containers


aapt(android asset packaging tool)
-------------

Looks your res folder and generates R.java file. Every subfolder of res folder is represented as public static final inner class , every single file of subfolder is represented as public static int field

 setContentView(R.layout.activity_main);
 -------------

 parses the given xml and creates objects in the memory.

 @+id/btnOkay
 -------------

 @ = R file, + says create new resource, btnOkay is name of the filed inside id resource

 findViewById(R.id.btnOkay);
 -------------

 get the reference of that view whos id is btnOkay and created after setContentView call.


providing click listener to button
 -------------

 ```
 Button btnOkay = (Button) findViewById(R.id.btnOkay);
         btnOkay.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

             }
         });
 ```