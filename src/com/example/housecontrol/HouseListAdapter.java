package com.example.housecontrol;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/*import com.example.peopledb.Person;
import com.example.peopledb.R;*/

/*!
Common base class of common implementation 
for an Adapter that can be used in both ListView 
(by implementing the specialized ListAdapter interface} 
and Spinner (by implementing the specialized 
SpinnerAdapter interface.
Adapters are bridging classes that bind data to Views 
(such as List Views) used in the user interface.
The adapter is responsible for creating the child Views 
used to represent each item within the parent
View, and providing access to the underlying data. 
*/
public class HouseListAdapter extends BaseAdapter {

	private final List<House> items;
	
	public HouseListAdapter(final Context context, final int itemResId,final List<House> items) {
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
		final House row = this.items.get(arg0);
        View itemView = null;

        if (arg1 == null) {
            LayoutInflater inflater = (LayoutInflater) arg2.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.house_item, null);
        } else {
            itemView = arg1;
        }

        // Set the text of the row
        //TextView txtId = (TextView) itemView.findViewById(R.id.houseId);
        //txtId.setText(Integer.toString(row.getId()));
        
        TextView firstName = (TextView) itemView.findViewById(R.id.houseName);
        firstName.setText(row.getHouseName());
        
        return itemView;
	}

}
