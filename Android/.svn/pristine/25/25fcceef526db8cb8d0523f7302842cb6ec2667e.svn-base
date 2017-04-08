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

public class PhoneInfoListAdapter extends ArrayAdapter<PhoneInfo> {
    public LayoutInflater inflater = null;
    private Context mContext;
    List<PhoneInfo> mData;
    FragmentActivity activity;

    public PhoneInfoListAdapter(FragmentActivity activity, Context context, int resource, List<PhoneInfo> items) {
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
    public PhoneInfo getItem(int position) {
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
            PhoneInfo phoI = getItem(position);

            imei.setText(phoI.getImei());
        }
        return vi;
    }
}
