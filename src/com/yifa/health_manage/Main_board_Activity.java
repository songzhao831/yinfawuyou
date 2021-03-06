package com.yifa.health_manage;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;  
import android.content.Intent;  
import android.os.Bundle;  
import android.os.Handler;  
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
  
public class Main_board_Activity extends Activity {  
  
	/** 
     * ViewPager 
     */  
    private ViewPager viewPager;  
      
    /** 
     * 装点点的ImageView数组 
     */  
    private ImageView[] tips;  
      
    /** 
     * 装ImageView数组 
     */  
    private ImageView[] mImageViews;  
      
    /** 
     * 图片资源id 
     */  
    private int[] imgIdArray ;  
    
    private ImageView dummydata;
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main_board_layout);  
        ViewGroup group = (ViewGroup)findViewById(R.id.viewGroup);  
        viewPager = (ViewPager) findViewById(R.id.viewPager);  
        dummydata = (ImageView) findViewById(R.id.dummydata); 
        //载入图片资源ID  
        imgIdArray = new int[]{R.drawable.look_deug_home_top_img1, R.drawable.look_deug_home_top_img2, R.drawable.look_deug_home_top_img3};  
          
          
        //将点点加入到ViewGroup中  
        tips = new ImageView[imgIdArray.length];  
        for(int i=0; i<tips.length; i++){  
            ImageView imageView = new ImageView(this);  
            imageView.setLayoutParams(new LayoutParams(10,10));  
            tips[i] = imageView;  
            if(i == 0){  
                tips[i].setBackgroundResource(R.drawable.arrow_buttom);  
            }else{  
                tips[i].setBackgroundResource(R.drawable.arrow_buttom);  
            }  
              
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,    
                    LayoutParams.WRAP_CONTENT));  
            layoutParams.leftMargin = 5;  
            layoutParams.rightMargin = 5;  
            group.addView(imageView, layoutParams);  
        }  
          
          
        //将图片装载到数组中  
        mImageViews = new ImageView[imgIdArray.length];  
        for(int i=0; i<mImageViews.length; i++){  
            ImageView imageView = new ImageView(this);  
            mImageViews[i] = imageView;  
            imageView.setBackgroundResource(imgIdArray[i]);  
        }  
          
        //设置Adapter  
        viewPager.setAdapter(new MyAdapter());  
        //设置监听，主要是设置点点的背景  
       // viewPager.setOnPageChangeListener(this);  
        
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动  
        viewPager.setCurrentItem((mImageViews.length) * 100);  
        dummydata.setClickable(true);
        dummydata.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent mainIntent = new Intent(Main_board_Activity.this,  
						Blood_pressure_Activity.class);  
                startActivity(mainIntent);  
			}

        });
          
    }  
      
    /** 
     *  
     * @author xiaanming 
     * 
     */  
    public class MyAdapter extends PagerAdapter{  
  
        @Override  
        public int getCount() {  
            return Integer.MAX_VALUE;  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public void destroyItem(View container, int position, Object object) {  
       //     ((ViewPager)container).removeView(mImageViews[position % mImageViews.length]);  
              
        }  
  
        /** 
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键 
         */  
        @Override  
        public Object instantiateItem(View container, int position) {  
        	try {    
                ((ViewPager)container).addView(mImageViews[position % mImageViews.length], 0);  
            }catch(Exception e){  
                //handler something  
            }  
            return mImageViews[position % mImageViews.length];  
        }  
          
          
          
    }  
      
    /** 
     * 设置选中的tip的背景 
     * @param selectItems 
     */  
    private void setImageBackground(int selectItems){  
        for(int i=0; i<tips.length; i++){  
            if(i == selectItems){  
                tips[i].setBackgroundResource(R.drawable.arrow_buttom);  
            }else{  
                tips[i].setBackgroundResource(R.drawable.arrow_left_blue);  
            }  
        }  
    }  
  
}  