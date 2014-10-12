package com.jump.canvasdraw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CanvasDrawActivity extends Activity
{
	private static final String TAG = "CanvasDrawActivity";
	/** Called when the activity is first created. */
	private int width;
	private int height;
	private HandWrite handWrite = null;
	private Button clear = null;
    private int whichColor = 0;
	private int whichStrokeWidth = 0;
	private int whichshape=0;
	

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
 	    requestWindowFeature(Window.FEATURE_NO_TITLE);
 	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
 	    	    WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);
		
		handWrite = (HandWrite)findViewById(R.id.handwriteview);

	}
	
     
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
        menu.add(0, 1, 1, "����");
        menu.add(0, 2, 2, "��ɫ");                 
        menu.add(0, 3, 3, "����");
        menu.add(0, 4, 4, "����");
        menu.add(0,  5, 5, "��״");
        menu.add(0, 6,6, "����");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
        if(item.getItemId() == 4){

			File f = new File(Environment.getExternalStorageDirectory()
					.getAbsolutePath() + "/tuya.jpg");
			try {

				saveMyBitmap(f, handWrite.new1Bitmap);

			} catch (IOException e) {
				e.printStackTrace();
			} 
       	 
       	 
        }else if(item.getItemId() == 1){

        	handWrite.clear();            

        }else if(item.getItemId() == 2){
        	
        	Dialog mDialog = new AlertDialog.Builder(CanvasDrawActivity.this)
            .setTitle("��ɫ����")
            .setSingleChoiceItems(new String[]{"��ɫ","��ɫ","��ɫ","��ɫ","��ɫ","��ɫ","��ɫ"}, whichColor, new DialogInterface.OnClickListener() 
            {
                
                @Override
                public void onClick(DialogInterface dialog, int which) 
                {
                    // TODO Auto-generated method stub
                    switch(which)
                    {
                        case 0:
                        {
                            //���ʵ���ɫ
                            handWrite.color = Color.WHITE;
                            whichColor = 0;
                            break;
                        }
                        case 1:
                        {
                            //���ʵ���ɫ
                            handWrite.color = Color.GREEN;
                            whichColor = 1;
                            break;
                        }
                        case 2:
                        {
                            //���ʵ���ɫ
                        	handWrite.color = Color.RED;
                            whichColor = 2;
                            break;
                        }
                        case 3:
                        {
                            //���ʵ���ɫ
                            handWrite.color = Color.YELLOW;
                            whichColor = 3;
                            break;
                        }
                        case 4:
                        {
                            //���ʵ���ɫ
                            handWrite.color = Color.BLUE;
                            whichColor = 4;
                            break;
                        }
                        case 5:
                        {
                            //���ʵ���ɫ
                            handWrite.color = Color.BLACK;
                            whichColor = 5;
                            break;
                            
                        }
                        case 6:
                        {
                            //���ʵ���ɫ
                            handWrite.color = Color.GRAY;
                            whichColor = 6;
                            break;
                        }
                    }
                }
            })
            .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() 
            {
                
                @Override
                public void onClick(DialogInterface dialog, int which) 
                {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            })
            .create();
            mDialog.show();
        	
        	
        	
        }else if(item.getItemId() == 3){
        	

			Dialog mDialog = new AlertDialog.Builder(CanvasDrawActivity.this)
             .setTitle("��������")
             .setSingleChoiceItems(new String[]{"ϸ","��","��"}, whichStrokeWidth, new DialogInterface.OnClickListener() 
             {
                 
                 @Override
                 public void onClick(DialogInterface dialog, int which) 
                 {
                    
                     switch(which)
                     {
                         case 0:
                         {
                             
                             handWrite.strokeWidth = 3.0f;
                             whichStrokeWidth = 0;
                             break;
                         }
                         case 1:
                         {
                             
                             handWrite.strokeWidth = 6.0f;   
                             whichStrokeWidth = 1;
                             break;
                         }
                         case 2:
                         {
                             
                             handWrite.strokeWidth = 9.0f;
                             whichStrokeWidth = 2;
                             break;
                         }
                     }
                 }
             })
             .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() 
             {
                 
                 @Override
                 public void onClick(DialogInterface dialog, int which) 
                 {
                    
                     dialog.dismiss();
                 }
             })
             .create();
             mDialog.show();
        	
        	
        }else if(item.getItemId() ==5 ){
        	Dialog mDialog = new AlertDialog.Builder(CanvasDrawActivity.this)
            .setTitle("��״����")
            .setSingleChoiceItems(new String[]{"ֱ��","Բ��","����","Բ��","�����ı�"}, whichshape, new DialogInterface.OnClickListener() 
            {
            	public void onClick(DialogInterface dialog, int which) 
            	{
            		switch(which)
            		{
            		case 0:
            		{
            			handWrite.shape=0;
            			break;
            		}
            		case 1:
            		{
            			handWrite.shape=1;
            			break;
            		}
            		case 2:
            		{
            			handWrite.shape=2;
            			break;
            		}
            		case 3:
            		{
            			handWrite.shape=3;
            			break;
            		}
            		case 4:
            		{
            			handWrite.shape=4;
            			break;
            		}
            		
            		}
            	}
            }).setPositiveButton("ȷ��", new DialogInterface.OnClickListener() 
            {
                
                @Override
                public void onClick(DialogInterface dialog, int which) 
                {
                    // TODO Auto-generated method stub
                    dialog.dismiss();
                }
            })
            .create();
            mDialog.show();
            }else if(item.getItemId() ==6 )
            {
            	
            	AlertDialog alert=new AlertDialog.Builder(CanvasDrawActivity.this).create();
            	alert.setTitle("����");
            	alert.setMessage("����Ϳѻ�����demo��");
            	alert.show();
            	}
            
        
		return super.onOptionsItemSelected(item);
	}
	
	
	public void saveMyBitmap(File f, Bitmap mBitmap) throws IOException {
		try {
			f.createNewFile();
			FileOutputStream fOut = null;
			fOut = new FileOutputStream(f);	
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
			fOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}