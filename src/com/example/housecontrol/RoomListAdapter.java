package com.example.housecontrol;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RoomListAdapter extends BaseAdapter {

private final List<Room> items;
	
	public RoomListAdapter(final Context context, final int itemResId,final List<Room> items) {
		this.items = items;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return this.items.size();
	}

	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.items.get(arg0);
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final Room row = this.items.get(arg0);
        View itemView = null;

        if (arg1 == null) {
            LayoutInflater inflater = (LayoutInflater) arg2.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.list_item, null);
        } else {
            itemView = arg1;
        }

        // Set the text of the row
        TextView txtId = (TextView) itemView.findViewById(R.id.RoomId);
        txtId.setText(Integer.toString(row.getId()));
        
        TextView firstName = (TextView) itemView.findViewById(R.id.RoomName);
        firstName.setText(row.getRoomName());
        
        return itemView;
	}

}
