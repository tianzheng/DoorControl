package com.example.doorcontrol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doorcontrol.R;
import com.example.doorcontrol.bean.GetDoorResponse;

public class DoorAdapter extends BaseAdapter {
	/**
	 * 声明一个对象
	 */
	private LayoutInflater inflater;
	/**
	 * 声明response
	 */
	private GetDoorResponse response;

	/**
	 * 
	 * 创建一个方法，传值
	 */
	public void setResponse(GetDoorResponse response) {
		this.response = response;
		notifyDataSetChanged();
		// alt+? 快捷键
	}

	public  DoorAdapter(Context context) {
		// 实例化对象
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return response == null ? 0 : response.getResult().getDoors().size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.door_item, null);
			holder.imgLamp = (ImageView) convertView
					.findViewById(R.id.img_lamp);
			holder.lampName = (TextView) convertView
					.findViewById(R.id.lamp_name);
			holder.lampState = (TextView) convertView
					.findViewById(R.id.lamp_state);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.lampName.setText("名字："
				+ response.getResult().getDoors().get(position).getName());
		boolean state = response.getResult()
				.getDoors().get(position)
				.isDoorState();
		if (state) {
			// 开灯
			holder.imgLamp.setBackgroundResource(R.drawable.door_open);
			holder.lampState.setText("状态：开");
		} else {
			// 关灯
			holder.imgLamp.setBackgroundResource(R.drawable.door_off);
			holder.lampState.setText("状态：关");
			}
		return convertView;
	}

	class ViewHolder {
		ImageView imgLamp;
		TextView lampName;
		// shift+alt+o 导包快捷键
		TextView lampState;
	}

}
