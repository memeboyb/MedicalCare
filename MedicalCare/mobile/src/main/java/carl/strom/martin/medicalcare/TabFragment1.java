package carl.strom.martin.medicalcare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.support.v4.app.ListFragment;

public class TabFragment1 extends ListFragment implements OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Schedule, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}


/*import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class TabFragment1 extends ListFragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        populateListView();
        return inflater.inflate(R.layout.tab_fragment_1, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        populateListView();
        //registerClickCallback();
    }

    private void populateListView() {
        // Create list of items
        String[] myItems = {"Blue", "Green", "Purple", "Red"}; // Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getActivity(), // Context for the activity.
                R.layout.text_view_item, // Layout to use
                myItems); // Items to be displayed

        // Configure the list view.
        ListView list = (ListView) view.findViewById(R.id.listView);
        list.setAdapter(adapter);
    }

    /*private void registerClickCallback() {
        ListView list = (ListView) findViewById(R.id.listView);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> paret, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                String message = "You clicked # " + position + ", which is string: " + textView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
            }
        });
}
*/




