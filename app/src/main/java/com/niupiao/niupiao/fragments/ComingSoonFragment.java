package com.niupiao.niupiao.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.niupiao.niupiao.R;
import com.niupiao.niupiao.models.Event;

import java.util.List;

/**
 * Created by kevinchen on 2/17/15.
 */
public class ComingSoonFragment extends ViewPagerFragment {

    private ListView concertsListView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_coming_soon, container, false);
        concertsListView = (ListView) root.findViewById(R.id.list_view);
        concertsListView.setAdapter(new EventsAdapter(getActivity()));
        return root;
    }

    @Override
    public String getTitle() {
        return "Coming Soon";
    }

    public static ComingSoonFragment newInstance() {
        return new ComingSoonFragment();
    }

    class EventsAdapter extends ArrayAdapter<Event> {
        public EventsAdapter(Context context) {
            super(context, R.layout.list_view_item_concert);
        }

        public void setEvents(List<Event> events) {
            clear();
            addEvents(events);
        }

        public void addEvents(List<Event> events) {
            if (events != null) {
                for (Event event : events) {
                    add(event);
                }
            }
        }

        class ViewHolder {
            TextView name;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_view_item_concert, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.name = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Event event = getItem(position);
            viewHolder.name.setText(position + ": " + event.getName());
            return convertView;
        }
    }
}
