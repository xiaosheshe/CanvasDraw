package com.jump.canvasdraw;




import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class HandWrite extends View
{
    Paint paint = null;
    private Bitmap originalBitmap = null;
     Bitmap new1Bitmap = null;
    private Bitmap new2Bitmap = null;
    private float clickX = 0,clickY = 0;
    private float startX = 0,startY = 0;
    private boolean isMove = true;
    private boolean isClear = false;
    int color = Color.BLUE;
    int shape=0;
   
     float strokeWidth = 6.0f;
	public HandWrite(Context context, AttributeSet attrs)
	{
		super(context, attrs);
//		originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.t);
		originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background).copy(Bitmap.Config.ARGB_8888, true);
		//第一个参数是包含要加载的位图资源文件的对象（一般写成 getResources（）就ok了）。
		//第二个参数是需要加载的位图资源的Id。
		new1Bitmap = Bitmap.createBitmap(originalBitmap);
		
	}
	

    public void clear(){
    	isClear = true;
    	new2Bitmap = Bitmap.createBitmap(originalBitmap);
    	invalidate();
    }
    public void setstyle(float strokeWidth){
    	this.strokeWidth = strokeWidth;
    }
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.drawBitmap(HandWriting(new1Bitmap), 0, 0,null);
		
	}

	public Bitmap HandWriting(Bitmap originalBitmap)
	{
		Canvas canvas = null;
		
		if(isClear){
			canvas = new Canvas(new2Bitmap);
		}
		else{
			canvas = new Canvas(originalBitmap);
		}
		paint = new Paint(); // 创建一个采用默认设置的画笔
		paint.setStyle(Style.STROKE); //填充样式为描边
		paint.setAntiAlias(true);  //使用抗锯齿功能
		paint.setColor(color);
		paint.setStrokeWidth(strokeWidth);
		
	
		switch(shape)
		{
		case 0:
		{
			canvas.drawLine(startX, startY, clickX, clickY, paint);
			break;
		}
		case 1:
		{
			canvas.drawCircle(startX+(clickX-startX)/2, startY+(clickY-startY)/2, Math.abs(startX-clickX)/2, paint);
			break;
		}
		case 2:
		{
			canvas.drawRect(startX,startY,clickX,clickY, paint);
			break;
		}
		case 3:
		{
			canvas.drawPoint(startX, startY, paint);
			break;
			
		}
		case 4:
		{
			Paint paintText=new Paint();
			paintText.setTextSize(20);
			canvas.drawText("I LOVE ANDROID", 0, 0, paintText);
		}
	
		}
		
		
		
		startX = clickX;
		startY = clickY;
		
		if(isClear){
			return new2Bitmap;
		}
		return originalBitmap;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		clickX = event.getX();
		clickY = event.getY();
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			
			isMove = false;
			invalidate();
			return true;
		}
		else if(event.getAction() == MotionEvent.ACTION_MOVE){
			
			isMove = true;
			invalidate();
			return true;
		}
		
		return super.onTouchEvent(event);
	}
   
}
