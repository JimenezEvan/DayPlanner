package com.example.dayplanner;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class EventListViewModel extends AndroidViewModel {
    private static final String PREFS = "shared_prefs";
    private static final String SAVED_EVENTS = "saved_events";
    private MutableLiveData<List<Event>> events;
    private SavedStateHandle savedStateHandle;
    private Application app;

    public EventListViewModel(Application app, SavedStateHandle stateHandle) {
        super(app);
        this.app = app;
        savedStateHandle = stateHandle;
        this.events = new MutableLiveData<>();
        events.setValue(loadEvents());
        savedStateHandle.set("events", events.getValue());
    }

    private List<Event> loadEvents() {
        SharedPreferences pref = app.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String eventsJSON = pref.getString(SAVED_EVENTS, "");
        Type type = new TypeToken<ArrayList<Event>>(){}.getType();
        ArrayList<Event> events = gson.fromJson(eventsJSON, type);
        if(events == null) return new ArrayList<>();
        else return events;
    }

    /*public void setEvents(List<Event> events) {
        this.events.setValue(events);
    }*/

    public MutableLiveData<List<Event>> getEvents() {
        return savedStateHandle.getLiveData("events", new ArrayList<>());
    }

    public void addEvent(Event event) {
        List<Event> eventList = getEvents().getValue();
        eventList.add(event);
        events.setValue(eventList);
        savedStateHandle.set("events", eventList);

    }
}
