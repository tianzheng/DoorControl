package com.example.doorcontrol;

import org.apache.http.entity.StringEntity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.doorcontrol.bean.Doors;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class DoorControlActivity extends BaseActivity {
	Doors doors;
	/**
	 * 开门
	 */
	Button	openClose;
	
	
	ImageView imgDoor ;
  @Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.door_control);
	findView();
	getData();
}
  
  
  
  
  private void findView() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// 返回或关闭页面
				finish();
			}
		});
		
		 imgDoor = (ImageView) findViewById(R.id.img_door);

		
		
		
		
		openClose= (Button) findViewById(R.id.open_close);
	openClose.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (doors.isDoorState()) {
				doors.setDoorState(false);	
			} else {
				doors.setDoorState(true);
			}
			requestPost();
		}
	});

	}
  
  private void getData(){
	  doors= (Doors) getIntent().getExtras().get("door_key");
	
	  setImgDoor();
  }
  
  
  private void requestPost(){

		// 提示框显示
		dialog.show();
		// 生成Json
		String requsetJson = gson.toJson(doors);

		Log.e("requsetJson==", "进度=" + requsetJson);

		// 声明，实例化对象
		RequestParams params = new RequestParams();
		try {
			params.setBodyEntity(new StringEntity(requsetJson, "UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		params.setContentType("applicatin/json");
		String url = "http://"+ConstantValue.IP+":8080/SmartDoorWeb/ChangeDoorServlet";
		httpUtils.send(HttpMethod.POST, url, params,
				new RequestCallBack<String>() {
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(DoorControlActivity.this, "请求失败",
								Toast.LENGTH_LONG).show();
						// 关闭提示框
						dialog.dismiss();
						
						if (doors.isDoorState()) {
							doors.setDoorState(false);	
						} else {
							doors.setDoorState(true);
						}
						setImgDoor();
						
					}

					public void onSuccess(ResponseInfo<String> arg0) {
						Toast.makeText(DoorControlActivity.this,
								"请求成功" + arg0.result, Toast.LENGTH_LONG).show();
						// 关闭提示框
						dialog.dismiss();
						setImgDoor();
						
						
					}
				});

	
	  
  }
  
  private void setImgDoor(){
	  if (doors.isDoorState()) {
		  openClose.setText("关门");
		  imgDoor.setBackgroundResource(R.drawable.door_off);
		} else {
		openClose.setText("开门");
		 imgDoor.setBackgroundResource(R.drawable.door_open);
		}
  }
}
