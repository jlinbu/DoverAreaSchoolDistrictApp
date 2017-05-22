package com.example.emailey.doverareaschooldistrictapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsengia on 5/16/2017.
 * This CalendarManifest class is designed to hold a list of CalendarInfo objects. This way there is a central location for all
 * CalendarInfo objects. There are no getters and setters, only a public static List field named "manifest".
 */

public class CalendarManifest {
    public static List<CalendarInfo> manifest = new ArrayList<CalendarInfo>();
}
