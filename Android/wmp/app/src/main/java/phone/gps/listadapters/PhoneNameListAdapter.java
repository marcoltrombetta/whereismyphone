package phone.gps.listadapters;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import phone.gps.R;
import phone.gps.obj.PhoneInfo;
import phone.gps.obj.PhoneName;

public class PhoneNameListAdapter extends ArrayAdapter<PhoneName> {
    public LayoutInflater inflater = null;
    private Context mContext;
    List<PhoneName> mData;
    FragmentActivity activity;

    public PhoneNameListAdapter(FragmentActivity activity, Context context, int resource, List<PhoneName> items) {
        super(context, resource, items);
        mContext = context;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(items!=null) {
            mData = items;
        }

        this.activity=activity;
    }

    @Override
    public PhoneName getItem(int position) {
            return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return  mData.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.list_item_phonename, null);

        TextView name=(TextView)vi.findViewById(R.id.phoneinfo_name);
        TextView imei=(TextView)vi.findViewById(R.id.phoneinfo_imei);

        if(getCount()>0) {
            PhoneName phoN = getItem(position);

            name.setText(phoN.getDesc());
            imei.setText(phoN.getPhoneInfo().getImei());
        }
        return vi;
    }
}
