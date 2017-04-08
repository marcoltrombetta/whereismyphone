package phone.gps.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import phone.gps.R;
import phone.gps.listadapters.PhoneNameListAdapter;
import phone.gps.obj.PhoneName;
import phone.gps.runnable.PhoneNameRunnable;

public class
DevicesFragment extends ListFragment implements AbsListView.OnScrollListener {

    private List<PhoneName> listData=new ArrayList<PhoneName>();
    ListView lv;

    PhoneNameRunnable.PhoneNameInterface phoneNameInterface=new PhoneNameRunnable.PhoneNameInterface() {
        @Override
        public void onComplete(final List<PhoneName> phon) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    fillListViewData(phon);
                }
            });
        }

        @Override
        public void onError(Exception ex) {

        }
    };

    public DevicesFragment() {
    }

    private void fillListViewData(List<PhoneName>  response) {
        if (response != null){
            listData.addAll(response);
        }
        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        //SimpleAdapter adapter = new SimpleAdapter(getActivity().getBaseContext(), aList, R.layout.list_item_image, from, to);
        PhoneNameListAdapter adapter = new PhoneNameListAdapter(getActivity(),getActivity().getBaseContext(),R.layout.list_item_phonename, listData);
        setListAdapter(adapter);

        adapter.notifyDataSetChanged();
    }


    public static DevicesFragment newInstance() {
        DevicesFragment fragment = new DevicesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
       // Getting listview from xml
        lv = getListView();

        // Adding button to listview at footer
        lv.setOnScrollListener(this);

        this.startSync();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_devices, container, false);
    }

    private void startSync(){
        new Thread(new PhoneNameRunnable(phoneNameInterface, getActivity())).start();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }
}
