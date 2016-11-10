package com.example.doorcontrol;

import java.util.ArrayList;
import java.util.Random;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doorcontrol.adapter.DoorAdapter;
import com.example.doorcontrol.bean.Doors;
import com.example.doorcontrol.bean.GetDoorList;
import com.example.doorcontrol.bean.GetDoorResponse;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MainActivity extends BaseActivity {
	// ��������
	GetDoorResponse doorResponse;
	// ����ListView�ؼ�
	ListView lampList;
	// ����device������
	DoorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
	//	loadData();
	}
@Override
protected void onResume() {
	requestGet();
	super.onResume();
}
	
	/**
	 * ��ʼ��View
	 */
	private void findView() {
		lampList = (ListView) findViewById(R.id.door_list);
		adapter = new DoorAdapter(MainActivity.this);
		lampList.setAdapter(adapter);
		lampList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, DoorControlActivity.class);
				intent.putExtra("door_key", doorResponse.getResult().getDoors()
						.get(position));
				startActivity(intent);
			}
		});

		Button refresh = (Button) findViewById(R.id.refresh);
		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			}
		});

	}

	/**
	 * ��������
	 */
	private void loadData() {
		doorResponse = new GetDoorResponse();
		doorResponse.setMessage("��������ɹ�");
		doorResponse.setResponse(0);
		GetDoorList result = new GetDoorList();
		/*********** Doors���󼯺�start *****************/
		ArrayList<Doors> doorsList = new ArrayList<Doors>();
		for (int j = 0; j < 5; j++) {

			Doors doors = new Doors();
			doors.setId(j);
			doors.setName("������0" + (j + 1));
			doors.setDoorState(j % 2 == 0);

			doorsList.add(doors);
		}
		result.setDoors(doorsList);
		/*********** Doors���󼯺�end *****************/
		result.setTotal(doorsList.size());
		doorResponse.setResult(result);

		adapter.setResponse(doorResponse);
	}
	
	
	private void requestGet(){
long time=System.currentTimeMillis(); 
		String url="http://"+ConstantValue.IP+":8080/SmartDoorWeb/ShowDoorsServlet?time="+time;
		Log.e("", "�ٶ�=1="+url);
		
		httpUtils.send(HttpMethod.GET,url,
	new RequestCallBack<String>() {
	public void onFailure(HttpException arg0, String arg1) {
		Log.e("", "�ٶ�=1="+arg1);
		dialog.dismiss();
		Toast.makeText(MainActivity.this,
				"����ʧ��", Toast.LENGTH_SHORT).show();
	}
			public void onSuccess(ResponseInfo<String> arg0) {
				Log.e("", "�ٶ�=2="+arg0.result);
			
				doorResponse=gson.fromJson(arg0.result, GetDoorResponse.class);
				 //��������������
	
	adapter.setResponse(doorResponse);
			  	dialog.dismiss();
			  	Toast.makeText(MainActivity.this,
						"����ɹ�", Toast.LENGTH_SHORT).show();
			}
		} );
		

	}

}
