package com.niupiao.niupiao.fragments.my_tickets;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.niupiao.niupiao.R;
import com.niupiao.niupiao.activities.TicketActivity;
import com.niupiao.niupiao.adapters.MyTicketsAdapter;
import com.niupiao.niupiao.fragments.ViewPagerFragment;
import com.niupiao.niupiao.managers.TicketManager;
import com.niupiao.niupiao.models.Event;
import com.niupiao.niupiao.models.Ticket;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kmchen1 on 2/21/15.
 */
public abstract class MyTicketsViewPagerFragment extends ViewPagerFragment implements TicketManager.OnEventsLoadedListener {

    protected ListView listView;

    protected abstract void requestEventsFromManager();

    @Override
    public void onEventsLoaded(Collection<Event> events) {

        // get the adapter
        MyTicketsAdapter adapter = ((MyTicketsAdapter) listView.getAdapter());

        // pour tickets from each event into one giant collection
        Collection<Ticket> tickets = new ArrayList<>();
        for (Event event : events) {
            Collection<Ticket> eventTickets = event.getTickets();
            if (eventTickets != null) {
                tickets.addAll(eventTickets);
            }
        }

        Log.d(MyTicketsViewPagerFragment.class.getSimpleName(), "FIRST TICKET: " + events.iterator().next().getTickets().toString());

        // update the adapter
        adapter.setObjects(tickets);
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.linear_layout_with_list_view, container, false);
        listView = (ListView) root.findViewById(R.id.list_view);
        listView.setAdapter(new MyTicketsAdapter(getActivity(), R.layout.list_view_item_my_ticket));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                //MyTicketsAdapter adapter = ((MyTicketsAdapter) listView.getAdapter());
                //Ticket ticket = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), TicketActivity.class);
                startActivity(intent);
            }
        });
        requestEventsFromManager();
        return root;
    }
}
