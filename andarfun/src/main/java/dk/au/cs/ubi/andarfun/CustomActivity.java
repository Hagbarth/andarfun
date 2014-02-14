package dk.au.cs.ubi.andarfun;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;

import edu.dhbw.andar.ARObject;
import edu.dhbw.andar.ARToolkit;
import edu.dhbw.andar.AndARActivity;
import edu.dhbw.andar.exceptions.AndARException;
import lib.graphics.KingModel3D;
import lib.graphics.LightingRenderer;
import lib.graphics.Model3D;
import lib.models.Model;
import lib.parser.ObjParser;
import lib.parser.ParseException;
import lib.util.AssetsFileUtil;
import lib.util.BaseFileUtil;

/**
 * Example of an application that makes use of the AndAR toolkit.
 *
 * @author Tobi
 */
public class CustomActivity extends AndARActivity {

    CustomObject someObject, someObject2, someObject3, someObject4;
    ARToolkit artoolkit;
    private Model model;
    private Model3D model3d;
    private ARObject finishModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        CustomRenderer renderer = new CustomRenderer();//optional, may be set to null
        super.setNonARRenderer(renderer);//or might be omited
        try {
            //register a object for each marker type
            //super.setNonARRenderer(new LightingRenderer());
            artoolkit = super.getArtoolkit();

            new ModelLoader().execute("chair.obj");
            //new ModelLoader().execute("couch.obj");



            /*someObject = new CustomObject
                    ("test", "1.patt", 80.0, new double[]{0, 0}, new float[]{0.74f,0.84f,0.0f}, 1.f);
            artoolkit.registerARObject(someObject);

            someObject2 = new CustomObject
                    ("test", "2.patt", 80.0, new double[]{0, 0}, new float[]{0.22f,0.76f,0.95f}, 3.0f);
            artoolkit.registerARObject(someObject2);

            *//*someObject3 = new CustomObject
                    ("test", "3.patt", 80.0, new double[]{0, 0}, new float[]{0.93f,0.74f,0.04f}, 1.5f);
            artoolkit.registerARObject(someObject3);*//*

            someObject4 = new CustomObject
                    ("test", "3.patt", 80.0, new double[]{0, 0}, new float[]{0.88f,0.09f,0.12f}, 2.0f);
            artoolkit.registerARObject(someObject4 );*/

        } catch (Exception ex) {
            //handle the exception, that means: show the user what happened
            System.out.println("");
        }
        startPreview();
    }

    /**
     * Inform the user about exceptions that occurred in background threads.
     * This exception is rather severe and can not be recovered from.
     * TODO Inform the user and shut down the application.
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        Log.e("AndAR EXCEPTION", ex.getMessage());
        finish();
    }

    private class ModelLoader extends AsyncTask<String, Void, Void> {


        @Override
        protected Void doInBackground(String... params) {

            String modelFileName = params[0];
            String patternName = modelFileName.substring(0, modelFileName.length()-4);
            patternName = "4";
            Log.e("******** patternName", patternName);
            BaseFileUtil fileUtil;

            fileUtil = new AssetsFileUtil(getResources().getAssets());
            fileUtil.setBaseFolder("models/");


            //read the model file:
            if(modelFileName.endsWith(".obj")) {
                Log.e("******** filename", modelFileName);
                ObjParser parser = new ObjParser(fileUtil);
                try {
                    if(fileUtil != null) {
                        BufferedReader fileReader = fileUtil.getReaderFromName(modelFileName);
                        if(fileReader != null) {
                            model = parser.parse(patternName, fileReader);
                            model.scale = 600.0f;
                            finishModel = new KingModel3D(model, patternName);


                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //register model
            try {
                if(finishModel!=null)
                    artoolkit.registerARObject(finishModel);
            } catch (AndARException e) {
                e.printStackTrace();
            }
            startPreview();
        }
    }


}
