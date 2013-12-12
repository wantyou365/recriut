package com.janan.view.mamager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ShowLineLinearLayout extends LinearLayout{
	private Paint paint = null;
//  private int color = getResources().getColor(R.color.listlinecolor);
	private int color = Color.BLACK;
	public ShowLineLinearLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public ShowLineLinearLayout(Context context, AttributeSet set){
		super(context,set);
	}
	
	
	public void setPaintColor(int color){
	    this.color = color;
	   }
	   @Override
	   protected void onDraw(Canvas canvas)
	   {
		   
		super.onDraw(canvas);
	    paint = new Paint(); 
	    //给边框设置颜色
	    paint.setColor(color);
	    //上
//	    canvas.drawLine(0, 0, this.getWidth()-1, 0, paint);
	    //左
//	    canvas.drawLine(0, 0, 0, this.getHeight()-1, paint);
	    //下
	    canvas.drawLine(5, this.getHeight(), this.getWidth()-5, this.getHeight(), paint);
	    //右
//	    canvas.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1, paint);
	   }
	 
	 
}
